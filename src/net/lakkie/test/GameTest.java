package net.lakkie.test;

import javax.swing.JFrame;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.app.ExitCode;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.window.JFrameWindow;
import net.lakkie.pixely.window.Window;

public class GameTest {

	public static final int width = 1280, height = 720;
	
	public static void main(String[] args) {
		Application.recordLoadStart(true);
		PixelyContext context = new PixelyContext(new GameContextProvider());
		Window<JFrame> jframe = new JFrameWindow(context, "Test Game", width, height);
		jframe.appendContext();
		jframe.show();
		
		Application.start(context, jframe, (ctx) -> {
			Application.targetWidth = ((JFrameWindow) jframe).getFrame().getWidth();
			Application.targetHeight = ((JFrameWindow) jframe).getFrame().getHeight();
		});
		
		Application.exit(ExitCode.SUCCESS);
	}
	
}
