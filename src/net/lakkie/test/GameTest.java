package net.lakkie.test;

import java.util.Random;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.renders.RenderEngineTest;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.utils.MovementInputLayout;
import net.lakkie.pixely.utils.TestSnippet;
import net.lakkie.pixely.utils.Vector4;
import net.lakkie.pixely.window.JFrameWindow;

public class GameTest {

	public static final int width = 1280, height = 720;
	public static final Random rand = new Random();

	public static void main(String[] args) {
		Application.recordLoadStart(true);

		// Create the render engine
		new RenderEngineTest(new Vector4(0, 0, 1280, 720));

		// Load sprites
		Sprite spriteTest = new Sprite("/img/test.png", "test");
		
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

		Application.start(context, jframe, (ctx) -> {

			// Update
			Application.targetWidth = ((JFrameWindow) jframe).getFrame().getWidth();
			Application.targetHeight = ((JFrameWindow) jframe).getFrame().getHeight();

			// Check for input and move
			TestSnippet.moveUsingButtons(MovementInputLayout.WASD, 3, engine);

		}, (ctx) -> {

			// Render
			// Clear background
			engine.clear(0x00000000);
			for (Tile tile : Tile.tiles) {
				engine.renderTile(tile);
			}

		});
	}

}
