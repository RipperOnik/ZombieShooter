import processing.core.*;

public class Program extends PApplet{
	
	
	Player player;
	Enemy enemy;
	PImage enemyImage;
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
		enemyImage = loadImage("enemy/zombie_walk.png");
		
		player = new Player(this, playerImage, 100f/128f);
		player.centerX = width/2;
		player.centerY = height/2;
		
		enemy = new Enemy(this, enemyImage, 100f/128f, player);
		enemy.centerX = 100;
		enemy.centerY = 100;
		
		
		
		
	}
	@Override
	public void draw() {
		background(0, 255, 0);
	
		
		player.move();
		player.display();
		
		enemy.move();
		enemy.rotate();
		enemy.display();
		
		
	}
	@Override
	public void keyPressed() {
		if (keyCode == RIGHT) {
			player.rotate(true);
		}else if (keyCode == LEFT) {
			player.rotate(false);
		}
		if (key == 'd') {
			player.changeX = 5;
		}else if (key == 'a') {
			player.changeX = -5;
		}else if (key == 's') {
			player.changeY = 5;
		}else if (key == 'w') {
			player.changeY = -5;
		}   
	}
	@Override
	public void keyReleased() {
		if (key == 'd') {
			player.changeX = 0;
		}else if (key == 'a') {
			player.changeX = 0;
		}else if (key == 's') {
			player.changeY = 0;
		}else if (key == 'w') {
			player.changeY = 0;
		}  
	}

}
