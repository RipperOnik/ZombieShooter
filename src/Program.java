import java.util.ArrayList;

import processing.core.*;

public class Program extends PApplet{
	final static float SPRITE_SCALE = 50f/128f;
	final static float SPRITE_SIZE = 50f;
	final static float RIGHT_MARGIN = 200;
	final static float LEFT_MARGIN = 200;
	final static float VERTICAL_MARGIN = 200;
	final static float FULL_WIDTH = 1700f;
	final static float FULL_HEIGHT= 1300f;
	
	float viewX, viewY;
	int score, maxScore;
	boolean won;
		
	Player player;
	PImage enemyImage, redBrickImage;
	ArrayList<Sprite> objects;
	ArrayList<Sprite> enemies;
	
	public static void main(String[] args) {
		PApplet.main("Program");
	}
	@Override
	public void settings() {
		size(800, 600);
	}
	@Override
	public void setup() {
		viewX = 0;
		viewY = 0;
		score = 0;
		won = false;
		
		
		imageMode(CENTER);
		PImage playerImage = loadImage("player/player.png");
		PImage bulletImage = loadImage("player/bullet.png");
		redBrickImage = loadImage("red_brick.png");
		enemyImage = loadImage("enemy/zombie_walk.png");
		
		
		objects = new ArrayList<Sprite>();
		enemies = new ArrayList<Sprite>();
		
		player = new Player(this, playerImage, 100f/128f, objects, bulletImage, enemies);
		player.centerX = FULL_WIDTH/2;
		player.centerY = FULL_HEIGHT/2;
		
		createObjects();
		
		maxScore = enemies.size();
		
		
		
	}
	@Override
	public void draw() {
		if (won || player.isDead()) {
			displayGameOver();
			
		}else {
			background(0, 255, 0);
			scroll();
			if (frameCount % 5 == 0) {			
				checkBulletCollision();
			}
			
			player.move();
			player.display();
			player.displayBullets();
			
			for(Sprite obj: objects) {
				obj.display();
			}
			
			for(Sprite enemy: enemies) {
				((Enemy)enemy).rotate();
				((Enemy)enemy).move();
				((Enemy)enemy).display();
			}	
			displayScore();
		}
		
	}
	void displayScore() {
		textSize(32);
		fill(255,0,0);
		text("Zombies: " + score + "/"+ maxScore, viewX + 50, viewY + 50);
	}
	void displayGameOver() {
		fill(0,0,255);
		scroll();
		float textX = viewX + width/2 - 100;
		float textY = viewY + height/2;
		text("GAME OVER", textX, textY);
		if (player.isDead()) {
			text("YOU LOSE", textX, textY + 50);
		}else if(won) {
			text("YOU WIN", textX, textY + 50);
		}
		text("PRESS SPACE TO RESTART", textX, textY + 100);
		if (keyCode == 32) {
			setup();
		}
		
	}
	void checkBulletCollision() {
		for(int i = 0; i < player.bullets.size(); i++) {
			for(int j = 0; j < enemies.size(); j++) {
				
				if (player.bullets.get(i).checkCollision(enemies.get(j))) {
					player.bullets.remove(i);
					enemies.remove(j);
					score++;
					if (enemies.size() == 0) {
						won = true;
					}
					return;
				}
			}
		}
	}
	void scroll() {
		float rightBoundary = viewX + width - RIGHT_MARGIN;
		if (player.getRight() > rightBoundary) {
			viewX += player.getRight() - rightBoundary;
		}
		float leftBoundary = viewX + LEFT_MARGIN;
		if (player.getLeft() < leftBoundary) {
			viewX -= leftBoundary - player.getLeft();
		}
		float bottomBoundary = viewY + height - VERTICAL_MARGIN;
		if (player.getBottom() > bottomBoundary) {
			viewY += player.getBottom() - bottomBoundary;
		}
		float topBoundary = viewY + VERTICAL_MARGIN;
		if (player.getTop() < topBoundary) {
			viewY -= topBoundary - player.getTop();
		}
		translate(-viewX, -viewY);
	}
	@Override
	public void keyPressed() {
		if (keyCode == RIGHT) {
			player.rotate(true);
		}else if (keyCode == LEFT) {
			player.rotate(false);
		}
		else if (keyCode == 32) {
			player.shoot();
		}   
		else if (key == 'd') {
			player.changeX = player.MOVESPEED;
		}
		else if (key == 'a') {
			player.changeX = -player.MOVESPEED;
		}
		else if (key == 's') {
			player.changeY = player.MOVESPEED;
		}
		else if (key == 'w') {
			player.changeY = -player.MOVESPEED;
		}  

		
	}
	@Override
	public void keyReleased() {
		if (key == 'd') {
			player.changeX = 0;
		}
		else if (key == 'a') {
			player.changeX = 0;
		}
		else if (key == 's') {
			player.changeY = 0;
		}
		else if (key == 'w') {
			player.changeY = 0;
		}  
	}
	void createObjects() {
		 String[] lines = loadStrings("map.csv");
		  for(int row = 0; row < lines.length; row++){
		    String[] values = split(lines[row], ";");
		    for(int col = 0; col < values.length; col++){
		      if(values[col].equals("1")){
		        Sprite s = new Sprite(this, redBrickImage, SPRITE_SCALE);
		        s.centerX = SPRITE_SIZE/2 + col * SPRITE_SIZE;
		        s.centerY = SPRITE_SIZE/2 + row * SPRITE_SIZE;
		        objects.add(s);
		      }
		      else if(values[col].equals("2")){
				  Enemy enemy = new Enemy(this, enemyImage, 100f/128f, player, enemies);
				  enemy.centerX = SPRITE_SIZE/2 + col * SPRITE_SIZE;
				  enemy.centerY = SPRITE_SIZE/2 + row * SPRITE_SIZE;
				  enemies.add(enemy);
		      }
		    }
		  }  
		
	}

}
