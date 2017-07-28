package net.lakkie.test;

import java.util.Random;

import javax.swing.JFrame;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.app.ExitCode;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.renders.RenderEngineTest;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.input.Buttons;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.utils.Vector4;
import net.lakkie.pixely.window.JFrameWindow;
import net.lakkie.pixely.window.Window;

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

		PixelyContext context = new PixelyContext(new GameContextProvider());
		Window<JFrame> jframe = new JFrameWindow(context, "Test Game", width, height);
		jframe.appendContext();
		jframe.show();
		RenderEngine engine = (RenderEngine) context.get("render_engine");

		Application.start(context, jframe, (ctx) -> {

			// Update
			Application.targetWidth = ((JFrameWindow) jframe).getFrame().getWidth();
			Application.targetHeight = ((JFrameWindow) jframe).getFrame().getHeight();

			int speed = 3; 
			if (InputManager.isKeyPressed(Buttons.VK_W)) {
				engine.translateViewport(0, -speed);
			}

			if (InputManager.isKeyPressed(Buttons.VK_A)) {
				engine.translateViewport(-speed, 0);
			}
			
			if (InputManager.isKeyPressed(Buttons.VK_S)) {
				engine.translateViewport(0, speed);
			}

			if (InputManager.isKeyPressed(Buttons.VK_D)) {
				engine.translateViewport(speed, 0);
			}

		}, (ctx) -> {

			// Render
			// Clear background
			engine.clear(0x00000000);
			for (Tile tile : Tile.tiles) {
				engine.renderTile(tile);
			}

		});

		Application.exit(ExitCode.SUCCESS);
	}

}
