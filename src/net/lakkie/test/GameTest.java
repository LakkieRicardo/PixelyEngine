package net.lakkie.test;

import javax.swing.JFrame;

import net.lakkie.pixely.window.GenericWindow;

public class GameTest {

	public static void main(String[] args) {
		GenericWindow<JFrame> window = new GenericWindow<JFrame>("Test Frame", 1280, 720, JFrame.class);
		System.out.println("Success!");
		
	}
	
}
