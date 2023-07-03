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
	
	float viewX = 0;
	float viewY = 0;
		
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
		imageMode(CENTER);
		PImage playerImage = loadImage("player/player.png");
		redBrickImage = loadImage("red_brick.png");
		enemyImage = loadImage("enemy/zombie_walk.png");
		
		
		objects = new ArrayList<Sprite>();
		enemies = new ArrayList<Sprite>();
		
		player = new Player(this, playerImage, 100f/128f, objects);
		player.centerX = FULL_WIDTH/2;
		player.centerY = FULL_HEIGHT/2;
		
		createObjects();
				
	
		
	}
	@Override
	public void draw() {
		background(0, 255, 0);
		scroll();
		
//		println(player.isDead());
	
		
		player.move();
		player.display();
		
		for(Sprite obj: objects) {
			obj.display();
		}
		
		for(Sprite enemy: enemies) {
			((Enemy)enemy).rotate();
			((Enemy)enemy).move();
			((Enemy)enemy).display();
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
		if (key == 'd') {
			player.changeX = player.MOVESPEED;
		}
		if (key == 'a') {
			player.changeX = -player.MOVESPEED;
		}
		if (key == 's') {
			player.changeY = player.MOVESPEED;
		}
		if (key == 'w') {
			player.changeY = -player.MOVESPEED;
		}   
	}
	@Override
	public void keyReleased() {
		if (key == 'd') {
			player.changeX = 0;
		}
		if (key == 'a') {
			player.changeX = 0;
		}
		if (key == 's') {
			player.changeY = 0;
		}
		if (key == 'w') {
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
		    	  ArrayList<Sprite> allies = (ArrayList)enemies.clone();
				  Enemy enemy = new Enemy(this, enemyImage, 100f/128f, player, allies);
				  enemy.centerX = SPRITE_SIZE/2 + col * SPRITE_SIZE;
				  enemy.centerY = SPRITE_SIZE/2 + row * SPRITE_SIZE;
				  enemies.add(enemy);
		      }
		    }
		  }  
		
	}

}
