import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class StartPanel 
{
	private boolean startGame;
	private String startMessage;
	private BufferedImage TileScreen;
	private BufferedImage RuleBoard;
	
	public String getStartMessage()
	{
		return startMessage;
	}
	
	public void setStartMessage(String message)
	{
		startMessage = message;
	}
	
	public void paint(Graphics g)
	{
		
	}
	
	public void rotateBoards()
	{
		
	}
	
	public void mouseClicked(MouseEvent e) {}

}
