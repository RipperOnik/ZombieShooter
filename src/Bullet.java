
import processing.core.PImage;

public class Bullet extends Sprite{
	private float MOVESPEED = 5;
	public Bullet(Program parent, PImage img, float scale, Player player) {
		super(parent, img, scale);
		this.degrees = player.degrees; // 0-360
		this.centerX = player.centerX;
		this.centerY = player.centerY;
		calculateSpeed();
	}
	
	
	
	
	public void calculateSpeed() {
		float xSpeed, ySpeed;
		// no tangents for this angle 
		if (degrees == 90 || degrees == 270) {
			// x = c / sqrt(2)
			xSpeed = 0;
			ySpeed = MOVESPEED;
			
		}else {
			float ySpeedToxSpeed = Program.abs(Program.tan(Program.radians(degrees)));
			
			xSpeed = 1; // x
			ySpeed = ySpeedToxSpeed; // y = some_number * x
			
			float delimiter = Program.pow(ySpeed, 2) + Program.pow(xSpeed, 2); // y^2 + x^2 = delimiter^2 * x^2
			
			xSpeed = MOVESPEED / Program.sqrt(delimiter); // x = sqrt(c^2 / delimiter^2)
			ySpeed = xSpeed * ySpeedToxSpeed;
		}
		// define direction
		
		// looking down
		if (degrees > 0 && degrees < 180) {
			ySpeed = Program.abs(ySpeed);
		}
		// looking up
		else if (degrees > 180 && degrees < 360) {
			ySpeed = Program.abs(ySpeed) * -1;
		}
		
		// looking left
		if (degrees > 90 && degrees < 270) {
			xSpeed = Program.abs(xSpeed) * -1;
		}
		// looking right
		else if (degrees < 90 || degrees > 270) {
			xSpeed = Program.abs(xSpeed);
		}
		
		if (degrees == 0) {
			xSpeed = Program.abs(xSpeed);
			ySpeed = 0;
		}else if (degrees == 90) {
			xSpeed = 0;
			ySpeed = Program.abs(ySpeed);
		}else if (degrees == 180) {
			ySpeed = 0;
			xSpeed = Program.abs(xSpeed) * -1;
		}else if (degrees == 270) {
			xSpeed = 0;
			ySpeed = Program.abs(ySpeed) * -1;
		}
		
		
		this.changeX = xSpeed;
		this.changeY = ySpeed;
		
	}

}
