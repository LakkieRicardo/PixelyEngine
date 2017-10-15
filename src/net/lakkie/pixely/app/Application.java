package net.lakkie.pixely.app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.i.IRenderable;
import net.lakkie.pixely.i.IUpdatable;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.logging.LogUtils;
import net.lakkie.pixely.mods.js.JSLoader;
import net.lakkie.pixely.utils.Colors;
import net.lakkie.pixely.utils.DataRepresenter;
import net.lakkie.pixely.window.Window;

public final class Application
{

	// Public
	public static Window<?> currentWindow;
	public static PixelyContext ctx;
	public static int targetWidth;
	public static int targetHeight;
	public static RenderEngine renderEngine;
	/**
	 * A {@link Graphics2D} object that represents the latest render's graphics
	 */
	public static Graphics graphics;

	// Private
	private static Canvas c;
	private static String normTitle;
	private static IUpdatable update;
	private static IUpdatable postUpdate;
	private static IRenderable render;
	private static IRenderable ui;
	private static Set<IUpdatable> updates = new HashSet<IUpdatable>();
	private static Set<IUpdatable> postUpdates = new HashSet<IUpdatable>();
	private static boolean close;
	private static int fps, ups;
	private static int spritesOnScreen;
	private static int loop_Frames, loop_Updates;
	private static Runnable firstStart;
	private static ExitDetails exitDetails;
	private static ExitCode code;

	public static void exit()
	{
		stop(code);
		if (code == null)
		{
			System.exit(ExitCode.SUCCESS.getCode());
		} else
		{
			System.exit(code.getCode());
		}
	}

	public static void setExitCode(ExitCode code)
	{
		Application.code = code;
	}

	public static void start(PixelyContext context, Window<?> window)
	{
		c = context.getCanvas();
		ctx = context;
		targetWidth = context.getWidth();
		targetHeight = context.getHeight();
		currentWindow = window;
		normTitle = window.getTitle();
		renderEngine = (RenderEngine) ctx.get(PixelyContext.renderEngine);
		if (c.getBufferStrategy() == null)
		{
			c.createBufferStrategy(3);
		}
		if (firstStart != null)
		{
			firstStart.run();
		}
		renderEngine.firstStart();
		logLoadTime(System.currentTimeMillis() - ManagementFactory.getRuntimeMXBean().getStartTime());
		for (Entity entity : Entity.entities)
		{
			entity.start(ctx);
		}
		startLoop();
	}

	private static void update()
	{
		if (update != null)
		{
			update.update(ctx);
		}

		if (updates.size() > 0)
		{
			for (IUpdatable update : updates)
			{
				update.update(ctx);
			}
		}

		c.setSize(targetWidth, targetHeight);
		Time.update++;
		postUpdate();
	}

	private static void postUpdate()
	{
		InputManager.clearFirstClicks();
		if (postUpdate != null)
		{
			postUpdate.update(ctx);
		}
		if (postUpdates.size() > 0)
		{
			for (IUpdatable update : postUpdates)
			{
				update.update(ctx);
			}
		}
		spritesOnScreen = 0;
	}

	private static void render()
	{
		BufferStrategy bs = c.getBufferStrategy();
		Graphics g = (Graphics2D) bs.getDrawGraphics();
		Application.graphics = g;
		ctx.set(PixelyContext.graphics, g);

		clear(g, Colors.light_blue);

		g.setColor(Color.red);

		if (render != null)
		{
			render.render(Application.ctx);
		}
		g.drawImage(((RenderEngine) Application.ctx.get(PixelyContext.renderEngine)).cameraImage, 0, 0, targetWidth, targetHeight, null);

		// See RenderEngine#finishRender for details
		renderEngine.finishRender();
		
		if (ui != null)
		{
			ui.render(ctx);
		}

		if (ctx.isDebugActive())
		{
			g.setColor(Color.red);
			g.drawString("FPS: " + fps, 10, 20);
			g.drawString("UPS: " + ups, 10, 40);
			g.drawString("Viewport: " + ((RenderEngine) ctx.get(PixelyContext.renderEngine)).viewport, 10, 60);
			g.drawString("Update Count: " + Time.update, 10, 80);
			g.drawString("Should close: " + DataRepresenter.booleanToYesNo(close), 10, 100);
			g.drawString("Update Function Count: " + updates.size(), 10, 120);
			g.drawString("Sprites On Screen: " + spritesOnScreen, 10, 140);
			g.drawString("Live Frames: " + loop_Frames, 10, 160);
			g.drawString("Live Updates: " + loop_Updates, 10, 180);
		}

		g.dispose();
		bs.show();
	}

	public static void clear(Graphics g, Color color)
	{
		g.setColor(color);
		g.fillRect(0, 0, targetWidth, targetHeight);
	}

	private static void startLoop()
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = ctx.getUPS();
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (!close)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1)
			{
				update();
				loop_Updates++;
				delta--;
			}
			render();
			loop_Frames++;

			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				if (ctx.isDebugActive())
				{
					currentWindow.rename(normTitle + " | FPS: " + loop_Frames + " UPS: " + loop_Updates);
				} else
				{
					currentWindow.rename(normTitle);
				}
				fps = loop_Frames;
				ups = loop_Updates;
				loop_Frames = 0;
				loop_Updates = 0;
			}
		}
	}

	private static void logLoadTime(long delta)
	{
		double seconds = delta;
		seconds /= 1000.0D;
		LogUtils.get().info("Loading took " + delta + " milliseconds, or " + seconds + " seconds.");
	}

	public static void requestClose()
	{
		Application.close = true;
	}

	public static void pause(long time, TimeUnit unit)
	{
		try
		{
			Thread.sleep(unit.toMillis(time));
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void setUIRenderable(IRenderable ui)
	{
		Application.ui = ui;
	}

	public static void setRender(IRenderable render)
	{
		Application.render = render;
	}

	public static void setUpdate(IUpdatable update)
	{
		Application.update = update;
	}

	public static void setPostUpdate(IUpdatable postUpdate)
	{
		Application.postUpdate = postUpdate;
	}

	/**
	 * This reference to the set of updatables are used to add or remove or call
	 * updates.
	 * 
	 * @return A direct reference to the updatables
	 */
	public static Set<IUpdatable> getUpdatables()
	{
		return Application.updates;
	}

	public static Set<IUpdatable> getPostUpdatables()
	{
		return postUpdates;
	}

	public static void registerSpriteRender()
	{
		spritesOnScreen++;
	}

	public static void setFirstStart(Runnable r)
	{
		firstStart = r;
	}

	/**
	 * Sets the exit details to be called during the exit of the application.
	 * 
	 * @param exitDetails
	 */
	public static void setExitDetails(String details)
	{
		setExitDetails(details, System.out);
	}

	public static void setExitDetails(String details, OutputStream out)
	{
		Application.exitDetails = new ExitDetails(details, out);
	}

	private static void stop(ExitCode code)
	{
		if (code != null)
		{
			LogUtils.info("Exit code: %s", code);
		}
		if (exitDetails != null)
		{
			LogUtils.info(exitDetails.getDetails());
		}
	}

	public static void loadModLibs()
	{
		JSLoader.evalScript("");
	}

	public static void loadMod(String path)
	{
		StringBuilder test = new StringBuilder();
		Scanner scanner = new Scanner(Application.class.getResourceAsStream(path));
		while (scanner.hasNextLine())
		{
			test.append(scanner.nextLine());
			test.append('\n');
		}
		scanner.close();
		JSLoader.evalScript(new String(test));
	}

}
