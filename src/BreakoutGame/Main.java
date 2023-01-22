package BreakoutGame;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
 
		JFrame jFrame = new JFrame();
		Gameplay gPlay = new Gameplay();
		jFrame.setBounds(10, 10, 700, 600);
		jFrame.setTitle("Breakout Ball");
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(gPlay);
	}

}
