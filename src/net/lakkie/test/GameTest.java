package net.lakkie.test;

import java.awt.image.BufferedImage;
import java.util.Random;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.app.ExitCode;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.defaults.input.AttachmentController;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.renders.RenderEngineGraphics;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.graphics.tex.loadDirect.DirectTextureLoader;
import net.lakkie.pixely.input.Buttons;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.level.Level;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.math.Vector4;
import net.lakkie.pixely.utils.AnchorGraphics;
import net.lakkie.pixely.utils.AnchorGraphicsMode;
import net.lakkie.pixely.utils.MovementInputLayout;
import net.lakkie.pixely.window.JFrameWindow;
import net.lakkie.test.entities.EntityPlayer;

public class GameTest
{

	public static final int width = 1280, height = 720;
	public static final int maxSprites = 10005;
	public static final Random rand = new Random();
	public static RenderEngine renderEngine;

	public static void main(String[] args)
	{
		// Create the render engine
		renderEngine = new RenderEngineGraphics(new Vector4(0, 0, width, height));

		// Create a level
		Level level = new Level("test");

		// Load sprites
		Sprite spriteRed = new Sprite("/img/red.png", "red");
		Sprite spriteGreen = new Sprite("/img/test.png", "green");

		// Load UI sprites
		BufferedImage buttonImage = DirectTextureLoader.readImage("/img/ui/button.png");
		
		// Load entities
		EntityPlayer player = new EntityPlayer(level, spriteRed, new Vector2i(50, 50), "player");
		player.add(new AttachmentController(3, MovementInputLayout.WASD));

		// Load tiles
		for (int i = 0; i < 30; i++)
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

			level.update(ctx);

		});

		Application.setPostUpdate((ctx) -> {

			level.postUpdate(ctx);

		});

		Application.setRender((ctx) -> {

			/*
			 * Render clause
			 */

			// Render the level
			level.render(ctx);
			
			// Render some GUI
			AnchorGraphics.setAnchorPoints(AnchorGraphicsMode.BOTTOM, AnchorGraphicsMode.RIGHT);
			AnchorGraphics.drawImage(Application.graphics, buttonImage, 0, 0, buttonImage.getWidth(), buttonImage.getHeight());
			
		});

		Application.setExitDetails("Successfully closed");
		Application.setExitCode(ExitCode.SUCCESS);
		Application.start(context, jframe);

		Application.exit();
	}

}
