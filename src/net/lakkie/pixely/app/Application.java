package net.lakkie.pixely.app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.i.Renderable;
import net.lakkie.pixely.i.Updatable;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.utils.Colors;
import net.lakkie.pixely.utils.DataRepresenter;
import net.lakkie.pixely.window.Window;

public class Application {

	/*
	 * TODO: Organize these variables, and document them!
	 */
	
	private static boolean nanoLoadTime = false;
	private static boolean recordLoadTime = false;
	private static long startTime;
	private static Canvas c;
	public static PixelyContext ctx;
	public static int targetWidth, targetHeight;
	public static Window<?> currentWindow;
	private static String normTitle;
	private static Updatable update;
	private static Updatable postUpdate;
	private static Renderable render;
	private static Renderable ui;
	private static Set<Updatable> updates = new HashSet<Updatable>();
	private static Set<Updatable> postUpdates = new HashSet<Updatable>();
	private static boolean close;
	private static int fps, ups;
	private static int spritesOnScreen;
	private static int loop_Frames, loop_Updates;
	private static Runnable firstStart;
	public static RenderEngine renderEngine;

	public static void exit(ExitCode code) {
		System.exit(code.getCode());
	}

	public static void start(PixelyContext context, Window<?> window) {
		if (recordLoadTime) {
			if (nanoLoadTime) {
				logLoadTime(System.nanoTime() - startTime);
			} else {
				logLoadTime(System.currentTimeMillis() - startTime);
			}
		}
		c = context.getCanvas();
		ctx = context;
		targetWidth = context.getWidth();
		targetHeight = context.getHeight();
		currentWindow = window;
		normTitle = window.getTitle();
		renderEngine = (RenderEngine) ctx.get(PixelyContext.renderEngine);
		if (c.getBufferStrategy() == null) {
			c.createBufferStrategy(3);
		}
		if (firstStart != null) {
			firstStart.run();
		}
		renderEngine.firstStart();
		startLoop();
	}

	private static void update() {
		if (update != null) {
			update.update(ctx);
		}

		for (Updatable update : updates) {
			update.update(ctx);
		}

		c.setSize(targetWidth, targetHeight);
		Time.update++;
		postUpdate();
	}

	private static void postUpdate() {
		InputManager.clearFirstClicks();
		if (postUpdate != null) {
			postUpdate.update(ctx);
		}
		spritesOnScreen = 0;
	}

	private static void render() {
		BufferStrategy bs = c.getBufferStrategy();
		Graphics g = (Graphics2D) bs.getDrawGraphics();
		ctx.set(PixelyContext.graphics, g);

		clear(g, Colors.light_blue);

		g.setColor(Color.red);

		if (render != null) {
			render.render(Application.ctx);
		}
		g.drawImage(((RenderEngine) Application.ctx.get(PixelyContext.renderEngine)).cameraImage, 0, 0, targetWidth,
				targetHeight, null);

		if (ui != null) {
			ui.render(ctx);
		}

		if (ctx.isDebugActive()) {
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

	public static void clear(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(0, 0, targetWidth, targetHeight);
	}

	private static void startLoop() {
		long lastTime = System.nanoTime();
		double amountOfTicks = ctx.getUPS();
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (!close) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				loop_Updates++;
				delta--;
			}
			render();
			loop_Frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (ctx.isDebugActive()) {
					currentWindow.rename(normTitle + " | FPS: " + loop_Frames + " UPS: " + loop_Updates);
				}
				fps = loop_Frames;
				ups = loop_Updates;
				loop_Frames = 0;
				loop_Updates = 0;
			}
		}
	}

	private static void logLoadTime(long delta) {
		if (nanoLoadTime) {
			double seconds = delta;
			seconds /= 1000000000.0D;
			System.out.println("Loading took " + delta + " nanoseconds, or " + seconds + " seconds.");
		} else {
			double seconds = delta;
			seconds /= 1000.0D;
			System.out.println("Loading took " + delta + " milliseconds, or " + seconds + " seconds.");
		}
	}

	public static void recordLoadStart(boolean nanoAccurate) {
		nanoLoadTime = nanoAccurate;
		recordLoadTime = true;
		if (nanoLoadTime) {
			startTime = System.nanoTime();
		} else {
			startTime = System.currentTimeMillis();
		}
	}

	public static boolean isNanoAccurate() {
		return nanoLoadTime;
	}

	public static void requestClose() {
		Application.close = true;
	}

	public static void pause(long time, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void setUIRenderable(Renderable ui) {
		Application.ui = ui;
	}

	public static void setRender(Renderable render) {
		Application.render = render;
	}

	public static void setUpdate(Updatable update) {
		Application.update = update;
	}

	public static void setPostUpdate(Updatable postUpdate) {
		Application.postUpdate = postUpdate;
	}

	/**
	 * This reference to the set of updatables are used to add or remove or call
	 * updates.
	 * 
	 * @return A direct reference to the updatables
	 */
	public static Set<Updatable> getUpdatables() {
		return Application.updates;
	}
	
	public static Set<Updatable> getPostUpdatables() {
		return postUpdates;
	}
	
	public static void registerSpriteRender() {
		spritesOnScreen++;
	}
	
	public static void setFirstStart(Runnable r) {
		firstStart = r;
	}

}
