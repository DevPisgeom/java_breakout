import java.awt.*;
import java.util.*;
import java.awt.image.*;  	//per utilizzare BufferedImage
import javax.swing.*;
import javax.swing.text.LayeredHighlighter;

import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;
import java.time.OffsetDateTime;

public class GamePanel extends JPanel implements Runnable {
    static final int LARGHEZZA = 461;
	static final int ALTEZZA = 550;
	static final Dimension SCREEN_SIZE = new Dimension(LARGHEZZA,ALTEZZA );
	static final int BALL_DIAMETER = 10;  //palla rotonda
    static int PADDLE_WIDTH = 80;
	static final int PADDLE_HEIGHT = 15;
	static final int PADDLE_DISTANZA_MURI = 20;
	static final int BORDER_OFFSET = 40 ; 
	int BALL_SPEED=5;
	int TOCCHI=0;
	int vite=3;
	int score=0;
	int score_score0=0;
	int score_score1=0;
	int score_score2=0;
	int WINNER=-1;
	int FONT_SIZE=20;
    int OFFSET_NUMBERS=FONT_SIZE*4;
	boolean end=false;
	int BALL_DISTANCE=60;
	Score score0=new Score(0,ALTEZZA/2+20,ALTEZZA,LARGHEZZA);
	Score score1=new Score(OFFSET_NUMBERS,ALTEZZA/2+20,ALTEZZA,LARGHEZZA);
	Score score2=new Score(OFFSET_NUMBERS*2,ALTEZZA/2+20,ALTEZZA,LARGHEZZA);
	Heart cuore1=new Heart(LARGHEZZA/2,ALTEZZA/2+20,5,1);
	Heart cuore2=new Heart(LARGHEZZA/2+OFFSET_NUMBERS/2,ALTEZZA/2+20,5,1);
	Heart cuore3=new Heart(LARGHEZZA/2+OFFSET_NUMBERS,ALTEZZA/2+20,5,1);

	static final int B_ROWS = 8; // righe di mattoncini
	static final int B_COLS = 14; // mattoncini per ogni riga
	static final int BRICK_WIDTH = 32;
	static final int BRICK_HEIGHT = 20;

	boolean sound=true;
	boolean split=true;

    Thread gameThread; //Thread eseguibile
	BufferedImage buffer; //awg.image 
	Graphics graphics;

    Random random; 
	Ball palla;
	
	Rettangolo top_line;
	Rettangolo bottom_line;
	Rettangolo l_line;
	Rettangolo r_line;
	Paddle paddle;

	int soundTrackNumber=0;
	int oldsoundTrackNumber=0;

	Clip suono1;
	

	Rettangolo[][] brick = new Rettangolo[B_COLS][B_ROWS];
	GamePanel(){ 
		
		//-------------------------- qui istanzio gli oggetti --------------------------
		random = new Random();

		paddle=new Paddle(LARGHEZZA/2-PADDLE_WIDTH/2,ALTEZZA-20-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT,1);
		palla=new Ball(LARGHEZZA/2-BALL_DIAMETER/2,ALTEZZA-BALL_DISTANCE-PADDLE_HEIGHT-BALL_DIAMETER,BALL_DIAMETER,BALL_DIAMETER);
		
		top_line= new Rettangolo(0, 0, LARGHEZZA, 2, 1);
		bottom_line= new Rettangolo(0,ALTEZZA-2,LARGHEZZA,2,1);
		l_line= new Rettangolo(0, 0, 2, ALTEZZA, 1);
		r_line= new Rettangolo(LARGHEZZA-2,0,2,ALTEZZA,1);

		newBricks();

		
		this.addKeyListener(new AL());
		
		//--------------------------- non toccare -------------------------------
		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);
		//this.addKeyListener(new AL());
		gameThread = new Thread(this);
		gameThread.start();
		//-----------------------------------------------------------------------
		try {
			suono1 = AudioSystem.getClip();
			suono1.open(AudioSystem.getAudioInputStream(getClass().getResource("SoundTrackSpeed2.wav")));
			suono1.setLoopPoints(0,1000000);
			suono1.loop(1000);
		}catch (Exception exc){
			exc.printStackTrace(System.out);
		}
	}
	public void newBricks() {
		int off=1;
		for (int j=0; j<B_COLS; j++)
		for (int k=0; k< B_ROWS; k++)
		brick[j][k] = new Rettangolo(j*BRICK_WIDTH+off*j, k*BRICK_WIDTH+(off/3), BRICK_WIDTH, BRICK_HEIGHT,k);
	}
	
	public void playSound(String name,boolean loop,boolean check){
		try {
		Clip suono = AudioSystem.getClip();
		suono.open(AudioSystem.getAudioInputStream(getClass().getResource(name)));
		
		if(loop && check ){
			suono.setLoopPoints(0,1000000);
			suono.loop(1000);
		}else{
			if(check)suono.start();
		}
		
		}
		catch (Exception exc)
		{
		exc.printStackTrace(System.out);
		}
		
	}
	
    public void paintComponent(Graphics g) { // ---------non toccare ---------
		
		super.paintComponent(g);
		
		buffer = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		graphics = buffer.getGraphics();
		
		draw(graphics);
		
		g.drawImage(buffer,0,0,this);
		
	}
    public void draw(Graphics g) {
		
		// ------ qui invoco il metodo .draw(g) dei singoli oggetti ------
		score0.draw(g);
		score1.draw(g);
		score2.draw(g);
		
		cuore1.draw(g);
		cuore2.draw(g);
		cuore3.draw(g);

		paddle.draw(g);
		palla.draw(g);
		top_line.draw(g);
		bottom_line.draw(g);
		l_line.draw(g);
		r_line.draw(g);
		for (int j=0; j<B_COLS; j++)
		for (int k=0; k< B_ROWS; k++)
		brick[j][k].draw(g);

		
	
        // the following line helps with animation ---------------------------
        Toolkit.getDefaultToolkit().sync(); 
        // This method ensures that the display is up-to-date. 
        // It is useful for animation: timing the painting operation 
        // should be performed by calling Toolkit.sync() 
        // after each paint to ensure the drawing commands 
        // are flushed to the graphics card. ---------------------------------  
	}
    public void move() {
		paddle.move();
		palla.move();
		//qui invoco l'aggiornamento della posizione degli oggetti grafici
		
	}
	public void reset(){
		
		palla.setDX(0);
		palla.setDY(0);
		palla.finalize();
		palla=new Ball(LARGHEZZA/2-BALL_DIAMETER/2,ALTEZZA-BALL_DISTANCE-PADDLE_HEIGHT-BALL_DIAMETER,BALL_DIAMETER,BALL_DIAMETER);
		paddle.finalize();
		paddle=new Paddle(LARGHEZZA/2-PADDLE_WIDTH/2,ALTEZZA-30-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT,1);
		vite--;
		if(vite==2){
			cuore1.Dead();
		}else{
			if(vite==1){
				cuore2.Dead();
			}else{
				if(vite==0){
					cuore3.Dead();
				}else{
					sound=false;
					paddle.finalize();
					PADDLE_WIDTH=LARGHEZZA+30;
					paddle=new Paddle(0,ALTEZZA-20-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT,1);

				}
			}
		}

		TOCCHI=0;
		//palla.CheckSpeed(TOCCHI);

	}
	
    public void checkCollision() {
		
		
		if(paddle.x<0){
			paddle.x=0;
		}
		if(paddle.x+PADDLE_WIDTH>LARGHEZZA){
			paddle.x=LARGHEZZA-PADDLE_WIDTH;
		}

		if(palla.x>=LARGHEZZA - BALL_DIAMETER){ 
			palla.dx = -palla.dx;
			palla.setX(LARGHEZZA-BALL_DIAMETER-1);
		}
		if(palla.x<0){
			palla.dx = -palla.dx;
			palla.setX(0);
		}
		if(palla.y<0){ 
			palla.dy = -palla.dy;
			if(split){
			playSound("PaddleSplitSound.wav", false, sound);
			int xpaddle=paddle.x;
			PADDLE_WIDTH=PADDLE_WIDTH/2;
			paddle.finalize();
			paddle=new Paddle(xpaddle,ALTEZZA-20-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT,1);
			split=false;
			}
		}
		if(palla.y>ALTEZZA - BALL_DIAMETER) {
			reset();
			playSound("LoseHp.wav",false,sound);
			//palla.dy = -palla.dy;
			
		}
		//----- la palla rimbalza quando tocca i margini superiore ed inferiore della finestra ------
		for (int j=0; j<B_COLS; j++){
			for (int k=0; k< B_ROWS; k++){
				if((palla.y+BALL_DIAMETER/2>=brick[j][k].y) && palla.x+BALL_DIAMETER/2>=brick[j][k].x && palla.x+BALL_DIAMETER/2 <= brick[j][k].x+BRICK_WIDTH && (palla.y+BALL_DIAMETER/2 <= brick[j][k].y+BRICK_HEIGHT)){
					palla.dy = -palla.dy;
					if(sound)score=score+brick[j][k].dead();
					if(score/10!=0){
						if(score/100!=0){
							score0.CheckScore(score/100);
							score1.CheckScore((score/10)%10);
							score2.CheckScore(score%10);
						}else{
							score2.CheckScore(score%10);
							score1.CheckScore(score/10);
							score0.CheckScore(0);
						}
					}else{
						score0.CheckScore(0);
						score1.CheckScore(0);
						score2.CheckScore(score);
					}
					playSound("BrickHitSound.wav", false, sound);
					if(score==448){
						sound=false;
						paddle.finalize();
						PADDLE_WIDTH=LARGHEZZA+30;
						paddle=new Paddle(0,ALTEZZA-20-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT,1);
						
					}
					
				}
			}
		}
		if(palla.y+BALL_DIAMETER/2>=paddle.y && palla.x+BALL_DIAMETER+1>=paddle.x && palla.x+1 <= paddle.x+PADDLE_WIDTH){
			//BOUNCE paddle sinistra (paddle1)
			//System.out.println("dica");
			float dir;
			float offset_bounce=(float)0.6;
			if(palla.x+BALL_DIAMETER/2 <= paddle.x+PADDLE_WIDTH/8){
				
				dir=-2;
			}else{
				if(palla.x+BALL_DIAMETER/2 <= paddle.x+(PADDLE_WIDTH/8)*2){
					
					dir=(float)-1.4;
				}else{
					if(palla.x+BALL_DIAMETER/2 <= paddle.x+(PADDLE_WIDTH/8)*3){
						
						dir=(float)-0.7;
					}else{
						if(palla.x+BALL_DIAMETER/2 <= paddle.x+(PADDLE_WIDTH/8)*5){
							
							dir=0;
						}else{
							if(palla.x+BALL_DIAMETER/2 <= paddle.x+(PADDLE_WIDTH/8)*6){
								
								dir=(float)0.7;
							}else{
								if(palla.x+BALL_DIAMETER/2 <= paddle.x+(PADDLE_WIDTH/8)*7){
									
									dir=(float)1.4;
								}else{
									dir=(float)2;
								}
							}
						}
					}
				}
			}
			dir=dir*offset_bounce;
			palla.setY(paddle.y-BALL_DIAMETER-1);
			
			palla.setDY(-1*(Math.sqrt(2-dir*dir)));
			palla.setDX(dir);
			
			TOCCHI++;
			
			playSound("hitHurt.wav",false,sound);
			System.out.println(TOCCHI);
			soundTrackNumber=palla.CheckSpeed(TOCCHI);
			switch (soundTrackNumber){
				case 0 :
					if(oldsoundTrackNumber!=soundTrackNumber){
						oldsoundTrackNumber=soundTrackNumber;
						suono1.stop();
						try {
							suono1.close();
							suono1.open(AudioSystem.getAudioInputStream(getClass().getResource("SoundTrackSpeed2.wav")));
							suono1.setLoopPoints(0,1000000);
							suono1.loop(1000);
						}catch (Exception exc){
							exc.printStackTrace(System.out);
						}
						
						
					}
					
				case 1:
					if(oldsoundTrackNumber!=soundTrackNumber){
						oldsoundTrackNumber=soundTrackNumber;
						suono1.stop();
						try {
							suono1.close();
							suono1.open(AudioSystem.getAudioInputStream(getClass().getResource("SoundTrackSpeed3.wav")));
							suono1.setLoopPoints(0,1000000);
							suono1.loop(1000);
						}catch (Exception exc){
							exc.printStackTrace(System.out);
						}
					
					
					}
					
				case 2:
					if(oldsoundTrackNumber!=soundTrackNumber){
						oldsoundTrackNumber=soundTrackNumber;
						suono1.stop();
						try {
							suono1.close();
							suono1.open(AudioSystem.getAudioInputStream(getClass().getResource("SoundTrackSpeed4.wav")));
							suono1.setLoopPoints(0,1000000);
							suono1.loop(1000);
						}catch (Exception exc){
							exc.printStackTrace(System.out);
						}
				
				
					}
				
			}
			//palla.setDY(-1);
		}
		
	}
    public void run() { //game loop

		long lastTime = System.nanoTime();
		double FPS = 60.0; // picture frames in 1 secondo
		double duration = 1000000000/FPS; //intervallo (in ns) tra 2 frame
		double delta = 0;

		while(true) { // per sempre
			long now = System.nanoTime();
			delta += (now -lastTime)/duration; // il tempo trascorso è > intervallo? se sì, devo disegnare nuovo frame
			lastTime = now;

			if(delta >=1) { // artificio per gestire 
			
				//-------- le operazioni della nostra applicazione -----------
				
				
				
				move();  //chiama il metodo .move() di tutti gli oggetti che si muovono
				checkCollision(); //controlla collisioni di tutti gli oggetti che si muovono
				
				
						
				//-------- --------------------------------------- -----------
			
				repaint(); //is used to tell a component (gamepanel) to repaint itself.
				delta--;
			
			} //end if
			
		} //end while
		
	} //end run()
	public class AL extends KeyAdapter{  
		//l’Adapter è un Listner che implementa tutte le funzioni {}
		//
		//KeyAdapter implementa i 3 metodi:
		//KeyPressed, KeyTyped, KeyReleased 
		//di KeyLisner senza che l'utente debba ridefinirli tutti
		//l’utente implementa solo quelli che usa

		//questo metodo SPOSTA il paddle quando il tasto è premuto
		public void keyPressed(KeyEvent e) {
			
			//paddle1.keyPressed(e);
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				paddle.setDeltaX(1);
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				paddle.setDeltaX(-1);
			}
			
		}


		//questo metodo FERMA il paddle rilasciando il tasto, azzerando il DeltaY
		public void keyReleased(KeyEvent e) {
			
			//paddle1.keyReleased(e);
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				paddle.setDeltaX(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				paddle.setDeltaX(0);
			}
			
		}
	
	} //end public class AL
} //end GamePanel

