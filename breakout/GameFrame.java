import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel panel;
	//GameMenu panel2;
	GameFrame() { //costruttore	
		panel = new GamePanel();
        this.getContentPane().add(panel);
		this.setTitle(" BreakoutV1 ");
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
    }
}
