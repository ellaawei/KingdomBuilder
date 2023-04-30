
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public static int WIDTH = 1500, HEIGHT = 850;
	
	public GameFrame(String title) throws IOException {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new GamePanel());
		setVisible(true);
		setResizable(false);
	}
	
	public void nextScreen()
	{
		
	}
	
	public void previousScreen()
	{
		
	}
	
	public void TileMaximise()
	{
		
	}
	
	public void ObjectiveCards()
	{
		
	}
	

}
