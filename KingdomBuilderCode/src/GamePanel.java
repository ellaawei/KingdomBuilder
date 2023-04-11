import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener {
	private int x;
	private int y;

	private BufferedImage board;
	private BufferedImage objCardsPage, tokensPage, terrainDeck;
	public static boolean clickedObjectiveCards, clickedViewTokens, clickedTerrain, clickedFinishTurn = false;
	private ArrayList<BufferedImage> objectives;
	private ArrayList<BufferedImage> currentObjectiveCards;
	private Card c;
	private Board b;

	public GamePanel() throws IOException {
		objectives = new ArrayList<BufferedImage>();
		currentObjectiveCards = new ArrayList<BufferedImage>();
		c = new Card();
		b = new Board();
		board = ImageIO.read(this.getClass().getResource("/Image/board.png"));
		terrainDeck = ImageIO.read(this.getClass().getResource("/Image/TerrainDeck.png"));
		objCardsPage = ImageIO.read(this.getClass().getResource("/Image/ObjectiveCardsPage.png"));
		tokensPage = ImageIO.read(this.getClass().getResource("/Image/TokensPage.png"));
		addMouseListener(this);
		repaint();
	}

	public void paint(Graphics g) 
	{
		g.drawImage(board, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(terrainDeck, 954, 27, 163, 220, null);
		c.drawObjectiveCards(g);
		b.drawBoard(g);
		if (c.getHasTerrainCardsLeft() && clickedTerrain) 
		{
			c.drawTerrainCards(g);
			c.drawDiscard(g);
		}
		if (c.getHasTerrainCardsLeft() == false) 
		{
			c.refill();
		}

		if (clickedObjectiveCards) 
		{
			g.drawImage(objCardsPage, 0, 0, getWidth(), getHeight(), null);
			return;
		}
		if (clickedViewTokens) 
		{
			g.drawImage(tokensPage, 0, 0, getWidth(), getHeight(), null);
		}
		
		if(clickedFinishTurn)
		{
			c.drawDiscard(g);
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x >= 703 && x <= 876 && y >= 35 && y <= 85) {
			clickedObjectiveCards = true;
		} else {
			clickedObjectiveCards = false;
		}
		if (x >= 1320 && x <= 1493 && y >= 162 && y <= 224) {
			clickedViewTokens = true;
		} else {
			clickedViewTokens = false;
		}
		if (x >= 954 && x <= 1117 && y >= 27 && y <= 247) 
		{
			clickedTerrain = true;
			if (c.getHasTerrainCardsLeft()) 
			{
				c.removeTerrainCard();
			}
		}
		
		if(x >= 1345 && x <= 1450 && y >= 100 && y <= 320)
		{
			clickedFinishTurn = true;
			
		}
		repaint();
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
