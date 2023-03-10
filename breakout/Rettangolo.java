import java.awt.Color;

import java.awt.*;
//import java.awt.event.*;

public class Rettangolo extends Rectangle {
    int id;
    Rettangolo(int x, int y, int RETTANGOLO_WIDTH, int RETTANGOLO_HEIGHT, int RETTANGOLO_ID){
		super(x,y,RETTANGOLO_WIDTH,RETTANGOLO_HEIGHT); //costruttore di Rectangle
		id=RETTANGOLO_ID;		
	}
    public void draw(Graphics g) {
        switch(id){
            case 0:
                g.setColor(Color.RED);
                break;
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.ORANGE);
                break;
            case 3:
                g.setColor(Color.ORANGE);
                break;
            case 4:
                g.setColor(Color.GREEN);
                break;
            case 5:
                g.setColor(Color.GREEN);
                break;
            case 6:
                g.setColor(Color.YELLOW);
                break;
            case 7:
                g.setColor(Color.YELLOW);
                break;
            default:
                g.setColor(Color.RED);
        }
        g.fillRect(x, y, width, height);
    }
    public int dead(){
        x=1000;
        y=1000;
        switch(id){
            case 0:
                return 7;
                
            case 1:
                return 7;
                
            case 2:
                return 5;
                
            case 3:
                return 5;
                
            case 4:
                return 3;
                
            case 5:
                return 3;
                
            case 6:
                return 1;
                
            case 7:
                return 1;
                
            default:
                return 0;
        }

    }
    
    public void finalize(){}
}
