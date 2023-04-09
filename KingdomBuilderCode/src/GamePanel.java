import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

import javax.swing.JPanel;
public class GamePanel extends JPanel implements MouseMotionListener, MouseListener
{
	private int x;
	private int y;
	
	public static BufferedImage images, board1, board2, board3, board4, board5, board6, board7, board8;
	public GamePanel() throws IOException {
		images = ImageIO.read(this.getClass().getResource("/Image/board.png"));
		board1 = ImageIO.read(this.getClass().getResource("/Image/Board1.png"));
		board2 = ImageIO.read(this.getClass().getResource("/Image/Board2.png"));
		board3 = ImageIO.read(this.getClass().getResource("/Image/Board3.png"));
		board4 = ImageIO.read(this.getClass().getResource("/Image/Board4.png"));
		addMouseListener(this);
		repaint();
	}
	public void paint(Graphics g) {
		g.drawImage(images, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(board1, -111, 45, 750, 445, null);
		g.drawImage(board2, 270, 45, 750, 445, null);
		g.drawImage(board3, -111, 393, 750, 445, null);
		g.drawImage(board4, 270, 393, 750, 445, null);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
