import java.awt.*;
import java.util.*;
//import java.math.*;
public class Ball extends Rectangle {
    Random random;
		
	float dx;
	float dy;
	double vectorX=-1;
	double vectorY;
	int ballSpeed=3;
	int oldballSpeed;
	//int coin=0;

    public Ball(int x, int y, int width, int height) {
	
		super(x,y,width,height); //costruttore di Rectangle
		kickoff();		
	}
	//public void Cost(int x, int y, int width, int height,int winner){
	//	Ball(x, y, width, height, winner);
	//}

	public void kickoff(){
		Random rand = new Random();
		

		
		vectorY=rand.nextFloat()*-2;
		if(rand.nextInt(2)==0){
			vectorX=Math.sqrt(2-vectorY*vectorY);
			while((int)vectorY==0){
			vectorY=rand.nextFloat()*-2;
			vectorX=Math.sqrt(2-vectorY*vectorY);
			}
		}else{
			vectorX=-1*(Math.sqrt(2-vectorY*vectorY));
			while((int)vectorY==0){
				vectorY=rand.nextFloat()*-2;
				vectorX=-1*Math.sqrt(2-vectorY*vectorY);
				}
		}
		
		
		setDX(vectorX);
		setDY(vectorY);
	}
	
    public void setDX(double vectorX) {
		dx = (float)(vectorX*ballSpeed);
	}
    public void setDY(double vectorY) {
		dy = (float)(vectorY*ballSpeed);
	}
	public void setY(int y1){
		y=y1;
	}
	public void setX(int x1){
		x=x1;
	}
    public void move() {	
		
        x = x + (int)dx;
        y = y + (int)dy;
   }
   public void draw(Graphics g) {
		g.setColor(Color.white);
	
		g.fillOval(x, y, height, width); 
	}
	public int CheckSpeed(int TOCCHI){
		if(TOCCHI>=4 && TOCCHI<12){
			ballSpeed=(int) (ballSpeed*1.25);
			//setDX(vectorX);
			//setDY(vectorY);
			return(1);
			
		}else{
			if(TOCCHI>=12){
				ballSpeed=5;
				//setDX(vectorX);
				//setDY(vectorY);
				return(2);
				
			}else{
				return(0);
			}
		}
		//System.out.println(TOCCHI);
		//System.out.println(ballSpeed);
		
	}
	public void finalize(){}
}
