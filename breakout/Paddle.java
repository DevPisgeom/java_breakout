import java.awt.*;
//import java.awt.event.*;

public class Paddle extends Rectangle {
    int id;
	boolean end=false;
	int dx;
	int paddleSpeed = 6;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int PADDLE_ID){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT); //costruttore di Rectangle
		id=PADDLE_ID;		
	}
	public void draw(Graphics g) {
		g.setColor(Color.GREEN); //colore paddle1
		g.fillRect(x, y, width, height);
	}
	public void setDeltaX(int xDirection) { 
		dx = xDirection*paddleSpeed;
	}
	public void move() {
		if(end==false){
			x = x + dx;
		}
	}
	public void end(int height){
		end=true;
		y=y+height;
	}
	public void finalize(){}
}
