package net.lakkie.pixely.app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.i.Renderable;
import net.lakkie.pixely.i.Updatable;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.utils.Colors;
import net.lakkie.pixely.window.Window;

public class Application {

	private static boolean nanoLoadTime = false;
	private static boolean recordLoadTime = false;
	private static long startTime;
	private static Canvas c;
	public static PixelyContext ctx;
	public static int targetWidth, targetHeight;
	public static Window<?> currentWindow;
	private static String normTitle;
	private static Updatable update;
	private static Renderable render;

	public static void exit(ExitCode code) {
		System.exit(code.getCode());
	}

	public static void start(PixelyContext context, Window<?> window, Updatable update, Renderable render) {
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
		Application.update = update;
		Application.render = render;
		if (c.getBufferStrategy() == null) {
			c.createBufferStrategy(3);
		}
		ctx.set("graphics", c.getBufferStrategy().getDrawGraphics());
		startLoop();
	}

	private static void update() {
		if (update != null) {
			update.update(ctx);
		}

		c.setSize(targetWidth, targetHeight);
		Time.update++;
		postUpdate();
	}
	
	private static void postUpdate() {
		InputManager.clearFirstClicks();
	}

	private static void render() {
		BufferStrategy bs = c.getBufferStrategy();
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		clear(g, Colors.light_blue);

		g.setColor(Color.red);

		render.render(Application.ctx);
		g.drawImage(((RenderEngine) Application.ctx.get("render_engine")).cameraImage, 0, 0, targetWidth, targetHeight,
				null);

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
		int updates = 0;
		int frames = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (ctx.isDebugActive()) {
					currentWindow.rename(normTitle + " | FPS: " + frames + " UPS: " + updates);
				}
				frames = 0;
				updates = 0;
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

	public static void pause(long time, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
