package net.lakkie.test;

import java.util.Random;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.app.ExitCode;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.defaults.input.AttachmentController;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.renders.RenderEngineBasic;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.input.Buttons;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.level.Level;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.math.Vector4;
import net.lakkie.pixely.utils.MovementInputLayout;
import net.lakkie.pixely.window.JFrameWindow;
import net.lakkie.test.entities.EntityBody;
import net.lakkie.test.entities.EntityPlayer;

public class GameTest
{

	public static final int width = 1280, height = 720;
	public static final Random rand = new Random();

	public static void main(String[] args)
	{
		// Create the render engine
		new RenderEngineBasic(new Vector4(0, 0, width, height));

		// Create a level
		Level level = new Level("test");

		// Load sprites
		Sprite spriteRed = new Sprite("/img/red.png", "red");
		// Sprite spriteBlank = new Sprite(0xff00ff00, width, height, "blank");
		Sprite spriteGreen = new Sprite("/img/test.png", "green");

		// Load entities
		EntityPlayer player = new EntityPlayer(level, spriteRed, new Vector2i(50, 50), "player");
		player.add(new AttachmentController(3, MovementInputLayout.WASD));
		EntityBody body = new EntityBody(level, new Vector2i(150, 25), "body");

		// Load tiles
		for (int i = 0; i < 1000; i++)
		{
			new Tile(level, spriteGreen, rand.nextInt(2000) - 1000, rand.nextInt(2000) - 1000, "test-" + i);
		}

		// Create the context to store game variables in
		PixelyContext context = new PixelyContext(new GameContextProvider());
		// Create and setup the window
		JFrameWindow jframe = new JFrameWindow(context, "Test Game", width, height);

		// Show the window
		jframe.show();

		player.start(context);

		// Get the current render engine
		RenderEngine engine = (RenderEngine) context.get(PixelyContext.renderEngine);

		Application.loadModLibs();
		Application.loadMod("/mods/test.js");

		Application.setUpdate((ctx) -> {
			/*
			 * Update clause
			 */

			jframe.updateCanvasWithFrame();
			Vector4 windowSize = jframe.getSize();
			if (engine.hasViewportChanged(windowSize))
			{
				engine.resizeViewport(windowSize);
			}

			// Debug switch
			if (InputManager.isKeyFirstDown(Buttons.VK_F1))
			{
				ctx.set(PixelyContext.debug, !ctx.isDebugActive());
			}

			player.update(ctx);
			body.update(ctx);

		});

		Application.setPostUpdate((ctx) -> {

			body.postUpdate(ctx);

		});

		Application.setRender((ctx) -> {

			/*
			 * Render clause
			 */

			// Clear background
			engine.clear(0x00000000);

			// Render all tiles
			for (Tile tile : Tile.tiles)
			{
				engine.renderTile(tile);
			}

			engine.renderEntity(player);
			engine.renderEntity(body);

		});

		Application.setExitDetails("Successfully closed");
		Application.setExitCode(ExitCode.SUCCESS);
		Application.start(context, jframe);

		Application.exit();
	}

}
