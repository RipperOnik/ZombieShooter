import java.util.ArrayList;

import processing.core.PImage;

public class Player extends Sprite{
	private float ROTATION_SPEED = 20;
	public float MOVESPEED = 5;
	ArrayList<Sprite> objects;
	
	private boolean hasDied = false;
	public Player(Program parent, PImage img, float scale, ArrayList<Sprite> objects) {
		super(parent, img, scale);
		this.objects = objects;
		
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
	@Override
	public void move() {
		// move vertically
		centerY += changeY;
		ArrayList<Sprite> collisionList = checkCollisionList(objects);
		if (collisionList.size() > 0) {
			Sprite collidedSprite = collisionList.get(0);
			// if going down
			if (changeY > 0) {
				setBottom(collidedSprite.getTop());
			}
			// if going up
			else if(changeY < 0) {
				setTop(collidedSprite.getBottom());
			}
		}
		
		// move horizontally
		centerX += changeX;
		collisionList = checkCollisionList(objects);
		if (collisionList.size() > 0) {
			Sprite collidedSprite = collisionList.get(0);
			// if going right
			if (changeX > 0) {
				setRight(collidedSprite.getLeft());
			}
			// if going left
			else if(changeX < 0) {
				setLeft(collidedSprite.getRight());
			}
		}
	}
	public void die() {
		hasDied = true;
	}
	public boolean isDead() {
		return hasDied;
	}

}
