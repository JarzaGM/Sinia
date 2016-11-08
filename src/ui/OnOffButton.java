package ui;

public abstract class OnOffButton extends Button{
	
	public boolean state; // false = off; true = on
	
	public OnOffButton(float x, float y, float sx, float sy, String text) {
		super(x, y, sx, sy, text);
	}
	
}
