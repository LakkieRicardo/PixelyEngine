package net.lakkie.test;

import java.awt.Canvas;

import net.lakkie.pixely.app.CanvasCreator;
import net.lakkie.pixely.context.IContextProvider;
import net.lakkie.pixely.context.PixelyContext;

public class GameContextProvider implements IContextProvider
{

	public void setup(PixelyContext context)
	{
		Canvas canvas = CanvasCreator.createCanvas(GameTest.width, GameTest.height);
		context.put(canvas, 60.0D, GameTest.width, GameTest.height, false, GameTest.renderEngine, GameTest.maxSprites);
	}

}
