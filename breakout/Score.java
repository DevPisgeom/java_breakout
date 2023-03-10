import java.awt.*;
//import java.awt.event.*;

public class Score extends Rectangle  {
    int SCORE_LEFT;
    int SCORE_RIGHT;
    int PANEL_HEIGHT;
    int PANEL_WIDTH;
    int FONT_SIZE=15;
    int OFFSET_NUMBERS=FONT_SIZE*4;
    int x1;
    int y1;
    Rettangolo rec1=new Rettangolo(0, 0, 0, 0, 0);
	Rettangolo rec2=new Rettangolo(0, 0, 0, 0, 0);
	Rettangolo rec3=new Rettangolo(0, 0, 0, 0, 0);
	Rettangolo rec4=new Rettangolo(0, 0, 0, 0, 0);
    Rettangolo rec5=new Rettangolo(0, 0, 0, 0, 0);

    Score(int x,int y,int HEIGHT,int WIDTH){
        x1=x;
        y1=y;
        PANEL_HEIGHT=HEIGHT;
        PANEL_WIDTH=WIDTH;

    }
    public void CheckScore(int score){
        switch(score){
            case 0:
                Zero(x1,y1);
                break;
            case 1:
                Uno(x1,y1);
                break;
            case 2:
                Due(x1,y1);
                break;
            case 3:
                Tre(x1,y1);
                break;
            case 4:
                Quattro(x1,y1);
                break;
            case 5:
                Cinque(x1,y1);
                break;
            case 6:
                Sei(x1,y1);
                break;
            case 7:
                Sette(x1,y1);
                break;
            case 8:
                Otto(x1,y1);
                break;
            case 9:
                Nove(x1,y1);
                break;
        }
    }
    
    
    public void draw(Graphics g) {
		rec1.draw(g);
		rec2.draw(g);
		rec3.draw(g);
		rec4.draw(g); 
        rec5.draw(g);

		Toolkit.getDefaultToolkit().sync();
	}
    public void Clear(){
        
        rec1.dead();
        rec2.dead();
        rec3.dead();
        rec4.dead();
        rec5.dead();

        rec1.finalize();
        rec2.finalize();
        rec3.finalize();
        rec4.finalize();
        rec5.finalize();
        
    }
    
    public void Zero(int x,int y){
        
        Clear();
            rec1= new Rettangolo(x,y,FONT_SIZE*3,FONT_SIZE,1); 
            rec2= new Rettangolo(x,y,FONT_SIZE,FONT_SIZE*5,2);
            rec3= new Rettangolo(x,y+FONT_SIZE*4,FONT_SIZE*3,FONT_SIZE,3);
            rec4= new Rettangolo(x+FONT_SIZE*2,y,FONT_SIZE,FONT_SIZE*5,4);
    }
    public void Uno(int x, int y){
        Clear();
            rec4=new Rettangolo(x,y,FONT_SIZE,FONT_SIZE*5,1);
    }
    public void Due(int x, int y){
        Clear();
            rec1=new Rettangolo(x,y,FONT_SIZE*3,FONT_SIZE,1);
            rec2=new Rettangolo(x+FONT_SIZE*2,y,FONT_SIZE,FONT_SIZE*3,2);
            rec3=new Rettangolo(x,y+FONT_SIZE*2,FONT_SIZE*3,FONT_SIZE,3);
            rec4=new Rettangolo(x,y+FONT_SIZE*2,FONT_SIZE, FONT_SIZE*3, 4);
            rec5=new Rettangolo(x,y+FONT_SIZE*4,FONT_SIZE*3,FONT_SIZE,5);
    }
    public void Tre(int x,int y){
        Clear();
        rec1= new Rettangolo(x,y,FONT_SIZE*3,FONT_SIZE,1); 
        rec3= new Rettangolo(x,y+FONT_SIZE*2,FONT_SIZE*3,FONT_SIZE,2);
        
        rec4= new Rettangolo(x+FONT_SIZE*2,y,FONT_SIZE,FONT_SIZE*5,4);
        rec5=new Rettangolo(x,y+FONT_SIZE*4,FONT_SIZE*3,FONT_SIZE,5);

    }
    public void Quattro(int x, int y){
        Clear();
        rec4= new Rettangolo(x+FONT_SIZE*2,y,FONT_SIZE,FONT_SIZE*5,4);
        rec3= new Rettangolo(x,y+FONT_SIZE*2,FONT_SIZE*3,FONT_SIZE,2);
        rec5=new Rettangolo(x,y,FONT_SIZE,FONT_SIZE*3,5);

    }
    public void Cinque(int x,int y){
        Clear();
            rec1=new Rettangolo(x,y,FONT_SIZE*3,FONT_SIZE,1);
            rec2=new Rettangolo(x,y,FONT_SIZE,FONT_SIZE*3,2);
            rec3=new Rettangolo(x,y+FONT_SIZE*2,FONT_SIZE*3,FONT_SIZE,3);
            rec4=new Rettangolo(x+FONT_SIZE*2,y+FONT_SIZE*2,FONT_SIZE, FONT_SIZE*3, 4);
            rec5=new Rettangolo(x,y+FONT_SIZE*4,FONT_SIZE*3,FONT_SIZE,5);
    }
    public void Sei(int x, int y){
        Clear();
            //rec1= new Rettangolo(x,y,FONT_SIZE*3,FONT_SIZE,1); 
            rec2= new Rettangolo(x,y,FONT_SIZE,FONT_SIZE*5,2);
            rec3= new Rettangolo(x,y+FONT_SIZE*4,FONT_SIZE*3,FONT_SIZE,3);
            rec4= new Rettangolo(x+FONT_SIZE*2,y+FONT_SIZE*2,FONT_SIZE,FONT_SIZE*3,4);
            rec5=new Rettangolo(x,y+FONT_SIZE*2,FONT_SIZE*3,FONT_SIZE,5);
    }
    public void Sette(int x, int y){
        Clear();
        rec2=new Rettangolo(x,y,FONT_SIZE*3,FONT_SIZE,1);
        rec5= new Rettangolo(x+FONT_SIZE*2,y,FONT_SIZE,FONT_SIZE*5,4);
    }
    public void Otto(int x,int y){
        Clear();
            rec1= new Rettangolo(x,y,FONT_SIZE*3,FONT_SIZE,1); 
            rec2= new Rettangolo(x,y,FONT_SIZE,FONT_SIZE*5,2);
            rec3= new Rettangolo(x,y+FONT_SIZE*4,FONT_SIZE*3,FONT_SIZE,3);
            rec4= new Rettangolo(x+FONT_SIZE*2,y,FONT_SIZE,FONT_SIZE*5,4);
            rec5= new Rettangolo(x,y+FONT_SIZE*2,FONT_SIZE*3,FONT_SIZE,3);
    }
    public void Nove(int x,int y){
        Clear();
            rec1= new Rettangolo(x,y,FONT_SIZE*3,FONT_SIZE,1); 
            rec2= new Rettangolo(x,y,FONT_SIZE,FONT_SIZE*3,2);
            //rec3= new Rettangolo(x,y+FONT_SIZE*4,FONT_SIZE*3,FONT_SIZE,3);
            rec4= new Rettangolo(x+FONT_SIZE*2,y,FONT_SIZE,FONT_SIZE*5,4);
            rec5= new Rettangolo(x,y+FONT_SIZE*2,FONT_SIZE*3,FONT_SIZE,3);
    }
    public void W(int offset){
        Clear();
        
        rec1= new Rettangolo(x1+offset,y1,FONT_SIZE,FONT_SIZE*5,2);
        rec4= new Rettangolo(x1+offset,y1+FONT_SIZE*4,FONT_SIZE*5,FONT_SIZE,3);
        rec2= new Rettangolo(x1+FONT_SIZE*4+offset,y1,FONT_SIZE,FONT_SIZE*5,4);
        rec5= new Rettangolo(x1+FONT_SIZE*2+offset, y1+FONT_SIZE, FONT_SIZE, FONT_SIZE*3, 5);

    }
}
