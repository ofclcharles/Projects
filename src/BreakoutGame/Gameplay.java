package BreakoutGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer; //import java.util.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener { // KeyListener for detecting error keys; ActionListener for detecting the ball
	Random random = new Random();
	int n = random.nextInt(2+1-2) - 2;
	private boolean play = false; 
	private int score = 0, totalBricks = 21 ;
	private int delay = 5, playerX = 310, ballPosX = 120, ballPosY = 350, ballXDir = n, ballYDir = -2; 
	private brickMap brick;
	private Timer timer;
	
	public Gameplay() {
		brick = new brickMap(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) { // to draw the slider, ball and other visuals
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		// brick map
		brick.draw((Graphics2D)g);
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(681, 0, 3, 592);
		// score
		g.setColor(Color.red);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);
		// paddle
		g.setColor(Color.green);
		g.fillRect(playerX,  550, 100, 8);
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		if(totalBricks <= 0) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.MAGENTA);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("YOU WON!", 260, 300);
			g.setColor(Color.blue);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press ENTER to restart", 190, 350);
		}
		
		if(ballPosY > 570) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("GAME OVER!, Score: " + score, 190, 300);
			g.setColor(Color.blue);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press ENTER to restart", 190, 350);
		}
		
		g.dispose();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) { 
	}	
	
	@Override	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100,8))) {
				ballYDir = -ballYDir;
			}
			A: for(int i = 0; i < brick.map.length; i++) {
				for(int j = 0; j < brick.map[0].length;j++) {
					if(brick.map[i][j] > 0) {
						int brickX = j * brick.brickWidth + 80;
						int brickY = i * brick.brickHeight + 50;
						int brickWidth = brick.brickWidth;
						int brickHeight = brick.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)){
							brick.setBrickValue(0, i, j);
							totalBricks--;
							score +=5;
							
							if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
								ballXDir = -ballXDir;
							} else {
								ballYDir = -ballYDir;
							}
							break A; // breaks out of the loop to point 'A'
						}
					}
				}
			}
			
			ballPosX += ballXDir;
			ballPosY -= ballYDir;
			if(ballPosX < 0) {
				ballXDir = -ballXDir;
			}
			if(ballPosY < 0) {
				ballYDir = -ballYDir;
			}
			if(ballPosX > 670) {
				ballXDir = -ballXDir;
			}
		}
		
		repaint(); // re-draws the JFrame 
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(playerX >= 600) {
				playerX = 600;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!play) {
				play = true;
				ballPosX = 120;
				ballPosY =350;
				ballXDir = -1;
				ballYDir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				brick = new brickMap(3, 7);
				
				repaint();
			}
		}
	
	}
	
	public void moveRight() {
		play = true;
		playerX += 20; // moves 20 pixels to the right
	}
	
	public void moveLeft() {
		play = true;
		playerX -= 20; // moves 20 pixels to the left
	}
	
	
	
	
	
	
}
