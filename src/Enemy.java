import processing.core.PImage;

public class Enemy extends Sprite{
	private float MOVESPEED = 1;
	Player player;
	public Enemy(Program parent, PImage img, float scale, Player player) {
		super(parent, img, scale);
		this.player = player;
	}
	
	// rotate enemy towards player
	public void rotate() {
		float angle = Program.atan2(player.centerY - centerY, player.centerX - centerX) * 180 / Program.PI;
		this.degrees = angle;
		if (degrees >= 360) {
			degrees -= 360;
		}else if (degrees <= -360) {
			degrees += 360;
		}
	}
	
	public void calculateSpeed() {
		float xSpeed, ySpeed;
		// no tangents for this angle 
		if (degrees == 90 || degrees == 270 || degrees == -90 || degrees == -270) {
			// x = c / sqrt(2)
			xSpeed = 0;
			ySpeed = MOVESPEED;
			
		}else {
			float ySpeedToxSpeed = Program.abs(Program.tan(degrees));
			
			xSpeed = 1; // x
			ySpeed = ySpeedToxSpeed; // y = some_number * x
			
			float delimiter = Program.pow(ySpeed, 2) + Program.pow(xSpeed, 2); // y^2 + x^2 = delimiter^2 * x^2
			
			xSpeed = MOVESPEED / Program.sqrt(delimiter); // x = sqrt(c^2 / delimiter^2)
			ySpeed = xSpeed * ySpeedToxSpeed;
		}
		
		if (centerX < player.centerX) {
			xSpeed = Program.abs(xSpeed);
		}else if (centerX > player.centerX) {
			xSpeed *= Program.abs(xSpeed) * -1;
		}
		
		if (centerY < player.centerY) {
			ySpeed = Program.abs(ySpeed);
		}else if (centerY > player.centerY) {
			ySpeed *= Program.abs(ySpeed) * -1;
		}
		this.changeX = xSpeed;
		this.changeY = ySpeed;
		
	}
	@Override
	public void move() {
		calculateSpeed();
		super.move();
	}

}
