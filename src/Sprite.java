

import processing.core.PImage;

public class Sprite {
	PImage image;
	float centerX, centerY;
	float changeX, changeY;
	float degrees; // from -360 to 360 
//	private float ROTATION_SPEED = 10;
	float w, h;
	Program parent;
	
	public Sprite(Program parent, PImage img, float scale) {
		this.parent = parent;
		image = img;
		w = image.width * scale;
		h = image.height * scale;
		centerX = 0;
		centerY = 0;
		changeX = 0;
		changeY = 0;
		this.degrees = 0;
	}
	public void display() {
		parent.pushMatrix();
		parent.translate(centerX, centerY);
		parent.rotate(Program.radians(degrees));
		parent.image(image, 0, 0, w, h);
		parent.popMatrix();
	}
	public void move() {
		centerX += changeX;
		centerY += changeY;
	}
//	public void rotate(boolean clockwise) {
//		if (clockwise) {
//			degrees+=ROTATION_SPEED;
//		}else {
//			degrees-=ROTATION_SPEED;
//		}
//		if (degrees >= 360) {
//			degrees -= 360;
//		}else if (degrees <= -360) {
//			degrees += 360;
//		}
//	}
//	public void rotate(float angle) {
//		degrees = angle;
//		if (degrees >= 360) {
//			degrees -= 360;
//		}else if (degrees <= -360) {
//			degrees += 360;
//		}
//	}
	
	
	public float getLeft() {
		return centerX - w/2;
	}
	public void setLeft(float newLeft) {
		centerX = newLeft + w/2;
	}
	public float getRight() {
		return centerX + w/2;
	}
	public void setRight(float newRight) {
		centerX = newRight - w/2;
	}
	public float getTop() {
		return centerY - h/2;
	}
	public void setTop(float newTop) {
		centerY = newTop + h/2;
	}
	public float getBottom() {
		return centerY + h/2;
	}
	public void setBottom(float newBottom) {
		centerY = newBottom - h/2; 
	}
	
	
	

}
