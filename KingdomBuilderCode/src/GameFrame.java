
import java.io.IOException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public static int WIDTH = 1600, HEIGHT = 960;
	
	public GameFrame(String title) throws IOException {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new GamePanel());
		setVisible(true);
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
