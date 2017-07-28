package net.lakkie.test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.renders.RenderEngineTest;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.graphics.tex.loadDirect.DirectTextureLoader;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.utils.AnchorGraphics;
import net.lakkie.pixely.utils.AnchorGraphicsMode;
import net.lakkie.pixely.utils.MovementInputLayout;
import net.lakkie.pixely.utils.TestSnippet;
import net.lakkie.pixely.utils.Vector4;
import net.lakkie.pixely.window.JFrameWindow;

public class GameTest {

	public static final int width = 1280, height = 720;
	public static final Random rand = new Random();
	public static BufferedImage button = null;

	public static void main(String[] args) {
		Application.recordLoadStart(true);

		// Create the render engine
		new RenderEngineTest(new Vector4(0, 0, 1280, 720));

		// Load sprites
		Sprite spriteTest = new Sprite("/img/test.png", "test");
		
		// Load UI textures
		button = DirectTextureLoader.readImage("/img/ui/button.png");
		
		// Load tiles
		for (int i = 0; i < 1000; i++) {
			new Tile(spriteTest, rand.nextInt(2000) - 1000, rand.nextInt(2000) - 1000, "tile_test");
		}

		// Create the context to store game variables in
		PixelyContext context = new PixelyContext(new GameContextProvider());
		// Create and setup the window
		JFrameWindow jframe = new JFrameWindow(context, "Test Game", width, height);
		
		// Show the window
		jframe.show();
		
		// Get the current render engine
		RenderEngine engine = (RenderEngine) context.get("render_engine");

		Application.setUIRenderable((ctx) -> {
			
			/*
			 * UI clause
			 */
			
			Graphics g = (Graphics) ctx.get(PixelyContext.graphics);
			AnchorGraphics.setAnchorPoints(AnchorGraphicsMode.BOTTOM, AnchorGraphicsMode.LEFT);
			AnchorGraphics.drawImage(g, button, 50, -150, 100, 100);
			
		});
		
		Application.start(context, jframe, (ctx) -> {

			/*
			 * Update clause
			 */
			
			jframe.updateSizeWithApplication();
			Vector4 windowSize = new Vector4(0, 0, jframe.getFrame().getWidth(), jframe.getFrame().getHeight());
			if (engine.hasViewportChanged(windowSize)) {
				engine.resizeViewport(windowSize);
			}

			// Check for input and move
			TestSnippet.moveUsingButtons(MovementInputLayout.WASD, 3, engine);

		}, (ctx) -> {

			/*
			 * Render clause
			 */
			
			// Clear background
			engine.clear(0x00000000);
			
			// Render all tiles
			for (Tile tile : Tile.tiles) {
				engine.renderTile(tile);
			}

		});
	}

}
