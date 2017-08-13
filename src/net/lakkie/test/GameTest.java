package net.lakkie.test;

import java.util.Random;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.app.ExitCode;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.RenderEngineResizeMode;
import net.lakkie.pixely.graphics.renders.RenderEngineShaded;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.input.Buttons;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.level.Level;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.utils.Vector2;
import net.lakkie.pixely.utils.Vector4;
import net.lakkie.pixely.window.JFrameWindow;
import net.lakkie.test.entities.EntityPlayer;

public class GameTest {

	public static final int width = 1280, height = 720;
	public static final Random rand = new Random();

	public static void main(String[] args) {
		// Create the render engine
		new RenderEngineShaded(new Vector4(0, 0, width, height));

		// Create a level
		Level level = new Level("test");

		// Load sprites
		Sprite spriteRed = new Sprite("/img/red.png", "red");
		// Sprite spriteBlank = new Sprite(0xff00ff00, width, height, "blank");
		Sprite spriteGreen = new Sprite("/img/test.png", "green");

		// Load entities
		Entity entity = new EntityPlayer(level, spriteRed, new Vector2(50, 50), "player");

		// Load tiles
		for (int i = 0; i < 1000; i++) {
			new Tile(level, spriteGreen, rand.nextInt(2000) - 1000, rand.nextInt(2000) - 1000, "test-" + i);
		}

		// Create the context to store game variables in
		PixelyContext context = new PixelyContext(new GameContextProvider());
		// Create and setup the window
		JFrameWindow jframe = new JFrameWindow(context, "Test Game", width, height);

		// Show the window
		jframe.show();

		entity.start(context);

		// Get the current render engine
		RenderEngine engine = (RenderEngine) context.get(PixelyContext.renderEngine);
		engine.resizeMode = RenderEngineResizeMode.PADDING_BOTTOM_RIGHT;

		Application.setUpdate((ctx) -> {
			/*
			 * Update clause
			 */

			jframe.updateCanvasWithFrame();
			Vector4 windowSize = jframe.getSize();
			if (engine.hasViewportChanged(windowSize)) {
				engine.resizeViewport(windowSize);
			}

			// Debug switch
			if (InputManager.isKeyFirstDown(Buttons.VK_F1)) {
				ctx.set(PixelyContext.debug, !ctx.isDebugActive());
			}
		});

		Application.setRender((ctx) -> {

			/*
			 * Render clause
			 */

			// Clear background
			engine.clear(0x00000000);

			// Render all tiles
			for (Tile tile : Tile.tiles) {
				engine.renderTile(tile);
			}

			engine.renderEntity(entity);

		});

		Application.setExitDetails("Successfully closed");
		Application.setExitCode(ExitCode.SUCCESS);
		Application.start(context, jframe);

		Application.exit();
	}

}
