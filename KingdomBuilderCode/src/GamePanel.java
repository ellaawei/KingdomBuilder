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
	
	public static BufferedImage images;
	public GamePanel() throws IOException {
		images = ImageIO.read(this.getClass().getResource("/pictures/objective cards.png"));
		addMouseListener(this);
		repaint();
	}
	public void paint(Graphics g) {
		
		g.drawImage(images, 0, 0, getWidth(), getHeight(), null);
//		g.setColor(Color.BLACK);
//		g.fillRect(100, 100, 100, 100);
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
