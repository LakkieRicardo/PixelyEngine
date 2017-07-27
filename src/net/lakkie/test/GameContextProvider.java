package net.lakkie.test;

import java.awt.Canvas;

import net.lakkie.pixely.app.CanvasCreator;
import net.lakkie.pixely.context.ContextProvider;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.RenderEngine;

public class GameContextProvider implements ContextProvider {

	public void setup(PixelyContext context) {
		Canvas canvas = CanvasCreator.createCanvas(GameTest.width, GameTest.height);
		context.put(canvas, 60.0D, GameTest.width, GameTest.height, true, RenderEngine.engines.get("test"));
	}

}
