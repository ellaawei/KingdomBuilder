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
	public static boolean clickedObjectiveCards, clickedViewTokens, clickedTerrain, clickedFinishTurn;
	private ArrayList<BufferedImage> objectives;
	private ArrayList<BufferedImage> currentObjectiveCards;
	private Card c;
	private Board b;
	public enum clickType{none, terrain, finishedTurn, viewTokens, objectiveCards};
	public static clickType click;
	public GamePanel() throws IOException {
		objectives = new ArrayList<BufferedImage>();
		currentObjectiveCards = new ArrayList<BufferedImage>();
		c = new Card();
		b = new Board();
		board = ImageIO.read(this.getClass().getResource("/pictures/board.png"));
		terrainDeck = ImageIO.read(this.getClass().getResource("/pictures/TerrainDeck.png"));
		objCardsPage = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveCardsPage.png"));
		tokensPage = ImageIO.read(this.getClass().getResource("/pictures/TokensPage.png"));
		clickedTerrain = false;
		addMouseListener(this);
		repaint();
	}

	public void paint(Graphics g) 
	{
		g.drawImage(board, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(terrainDeck, 954, 27, 163, 220, null);
		c.drawObjectiveCards(g);
		b.drawBoard(g);
		if (c.getHasTerrainCardsLeft() && click==clickType.terrain) 
		{
			c.drawTerrainCards(g);
		}
		if (c.getHasTerrainCardsLeft() == false) 
		{
			try {
				c.refill();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (click==clickType.objectiveCards) 
		{
			g.drawImage(objCardsPage, 0, 0, getWidth(), getHeight(), null);
			return;
		}
		else if (click==clickType.viewTokens)
		{
			g.drawImage(tokensPage, 0, 0, getWidth(), getHeight(), null);
			return;
		}
		
		else if(click==clickType.finishedTurn)
		{
			c.drawDiscard(g);
			//return;
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		click = clickType.none;
		if (x >= 703 && x <= 876 && y >= 35 && y <= 85) {
			click=clickType.objectiveCards;
		}
		else if (x >= 1320 && x <= 1493 && y >= 162 && y <= 224) {
			click=clickType.viewTokens;
		}
		else if (x >= 954 && x <= 1117 && y >= 27 && y <= 247) 
		{
			if (c.getHasTerrainCardsLeft()) 
			{
				c.removeTerrainCard();
			}
			click=clickType.terrain;
		}
		else if(x >= 1305 && x <= 1510 && y >= 65 && y <= 140)
		{
			click=clickType.finishedTurn;
		}
		b.mouseClick(e.getPoint());
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
