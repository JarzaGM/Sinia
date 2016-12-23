package ui;

public abstract class MultiStateButton extends Button{
	
	public int state, maxState; // custom: doesn't have to be just on and off (maxState is just the cap)
	
	public MultiStateButton(float x, float y, float sx, float sy, String text) {
		super(x, y, sx, sy, text);
	}

}
