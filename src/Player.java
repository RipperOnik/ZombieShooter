import processing.core.PImage;

public class Player extends Sprite{
	private float ROTATION_SPEED = 20;
	public float MOVESPEED = 5;
	public Player(Program parent, PImage img, float scale) {
		super(parent, img, scale);
	}
	public void rotate(boolean clockwise) {
		if (clockwise) {
			degrees+=ROTATION_SPEED;
		}else {
			degrees-=ROTATION_SPEED;
		}
		if (degrees >= 360) {
			degrees -= 360;
		}else if (degrees <= -360) {
			degrees += 360;
		}
	}

}
