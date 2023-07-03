import processing.core.PImage;

public class Enemy extends Sprite{
	Player player;
	public Enemy(Program parent, PImage img, float scale, Player player) {
		super(parent, img, scale);
		this.player = player;
	}
	
	// rotate enemy towards player
	public void rotate() {
		float angle = Program.atan2(player.centerY - centerY, player.centerX - centerX) * 180 / Program.PI;
		degrees = angle;
		if (degrees >= 360) {
			degrees -= 360;
		}else if (degrees <= -360) {
			degrees += 360;
		}
	}

}
