import java.awt.Color;

import java.awt.*;
//import java.awt.event.*;

public class Heart extends Rectangle {
    int x1=0;
    int y1=0;
    int size=0;
    int id;
    Rettangolo rec1;
	Rettangolo rec2;
	Rettangolo rec3;
	Rettangolo rec4;
    Heart(int x,int y,int Size,int id1){
        x1=x;
        y1=y;
        size=Size;
        id=id1;
        rec1=new Rettangolo(x1+size, y1, size*2, size*5, 1);
        rec2=new Rettangolo(x1, y1+size, size*7, size*3, 1);
        rec3=new Rettangolo(x1+size*4, y, size*2, size*5, 1);
        rec4=new Rettangolo(x1+size*2, y+size*4, size*3, size*2, 1);
    }
    public void draw(Graphics g) {
		rec1.draw(g);
		rec2.draw(g);
		rec3.draw(g);
		rec4.draw(g); 

		Toolkit.getDefaultToolkit().sync();
	}
    public void Dead(){
        rec1.dead();
        rec2.dead();
        rec3.dead();
        rec4.dead();
    }

}
