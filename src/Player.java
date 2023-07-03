import java.util.ArrayList;

import processing.core.PImage;

public class Player extends Sprite{
	private float ROTATION_SPEED = 10;
	public float MOVESPEED = 5;
	ArrayList<Sprite> objects;
	ArrayList<Sprite> bullets;
	ArrayList<Sprite> enemies;
	PImage bulletImage;
	
	private boolean hasDied = false;
	public Player(Program parent, PImage img, float scale, ArrayList<Sprite> objects, PImage bulletImage, ArrayList<Sprite> enemies) {
		super(parent, img, scale);
		this.objects = objects;
		this.bullets = new ArrayList<Sprite>();
		this.bulletImage = bulletImage;
		this.enemies = enemies;
		
	}
	public void rotate(boolean clockwise) {
		// goes from 0 to 360
		if (clockwise) {
			degrees+=ROTATION_SPEED;
		}else {
			degrees-=ROTATION_SPEED;
		}
		
		if (degrees < 0) {
			degrees += 360;		
		}
		else if (degrees >= 360) {
			degrees -= 360;
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
	public void shoot() {
		Bullet bullet = new Bullet(parent, bulletImage, 30f/57f, this, enemies, bullets);
		bullets.add(bullet);
	}
	@Override
	public void display() {
		super.display();
	}
	public void displayBullets() {
		for(Sprite bullet: bullets) {
			((Bullet)bullet).move();
			((Bullet)bullet).display();
		}
	}
//	public void checkBulletCollision() {
//		for(Sprite bullet: bullets) {
//			((Bullet)bullet).checkEnemyCollision();
//		}
//	}

}
