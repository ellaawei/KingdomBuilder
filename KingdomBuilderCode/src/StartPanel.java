import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class StartPanel 
{
	private boolean startGame;
	private String startMessage;
	private BufferedImage startScreen;
	private BufferedImage RuleBoard;
	
	public StartPanel() {
		try {
			startScreen = ImageIO.read(StartPanel.class.getResource("/pictures/StartScreen.jpg"));
		}
		catch (Exception e) {
			System.out.println("Error");
		}
		addMouseListener(this);
		repaint();
	}
	
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
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	public void rotateBoards()
	{
		
	}
	public void mouseClicked(MouseEvent e) {}
	
	public void MousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x>=532 && x<=1052 && y>=445 && y<=577) {
			try {
				GameFrame a = new GameFrame("Kingdom Builder");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error");
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
