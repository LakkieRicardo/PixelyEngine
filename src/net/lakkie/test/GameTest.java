package net.lakkie.test;

import javax.swing.JFrame;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.app.ExitCode;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.renders.RenderEngineTest;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.utils.Registries;
import net.lakkie.pixely.utils.Vector4;
import net.lakkie.pixely.window.JFrameWindow;
import net.lakkie.pixely.window.Window;

public class GameTest {

	public static final int width = 1280, height = 720;
	public static int spriteX = 0, spriteY = 0;
	
	public static void main(String[] args) {
		Application.recordLoadStart(true);
		
		// Create the render engine
		Registries.registerRenderEngine(new RenderEngineTest(new Vector4(0, 0, 1280, 720)));
		// Load sprites
		Registries.registerSprite(new Sprite("/img/test.png", "test"));
		
		PixelyContext context = new PixelyContext(new GameContextProvider());
		Window<JFrame> jframe = new JFrameWindow(context, "Test Game", width, height);
		jframe.appendContext();
		jframe.show();
		
		Application.start(context, jframe, (ctx) -> {
			
			// Update
			Application.targetWidth = ((JFrameWindow) jframe).getFrame().getWidth();
			Application.targetHeight = ((JFrameWindow) jframe).getFrame().getHeight();
			
			spriteX++;
			spriteY++;
		}, (ctx) -> {
			
			// Render
			RenderEngine engine = (RenderEngine) ctx.get("render_engine");
			// Clear background
			engine.clear(0x00000000);
			engine.renderSprite(spriteX, spriteY, Sprite.sprites.get("test"));
			
		});
		
		Application.exit(ExitCode.SUCCESS);
	}
	
}
