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

	private BufferedImage images;
	private BufferedImage objCardsPage, tokensPage, terrainDeck;
	private boolean clickedObjectiveCards, clickedViewTokens, clickedTerrain;
	private ArrayList<BufferedImage> objectives;
	private ArrayList<BufferedImage> currentObjectiveCards;
	private Card c;
	private Board b;

	public GamePanel() throws IOException {
		objectives = new ArrayList<BufferedImage>();
		currentObjectiveCards = new ArrayList<BufferedImage>();
		c = new Card();
		b = new Board();
		images = ImageIO.read(this.getClass().getResource("/Image/board.png"));
		terrainDeck = ImageIO.read(this.getClass().getResource("/Image/TerrainDeck.png"));
		objCardsPage = ImageIO.read(this.getClass().getResource("/Image/ObjectiveCardsPage.png"));
		tokensPage = ImageIO.read(this.getClass().getResource("/Image/TokensPage.png"));
		addMouseListener(this);
		repaint();
	}

	public void paint(Graphics g) {
		g.drawImage(images, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(terrainDeck, 954, 27, 163, 220, null);
		b.drawBoard(g);
		
		c.drawObjectiveCards(g);
		if (c.getHasTerrainCardsLeft() && clickedTerrain) {
			c.drawTerrainCards(g);
			c.drawDiscard(g);
		}
		if (c.getHasTerrainCardsLeft() == false) {
			c.refill();
		}

		if (clickedObjectiveCards) {
			g.drawImage(objCardsPage, 0, 0, getWidth(), getHeight(), null);
			return;
		}
		if (clickedViewTokens) {
			g.drawImage(tokensPage, 0, 0, getWidth(), getHeight(), null);
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
		if (x >= 954 && x <= 1117 && y >= 27 && y <= 247) {
			clickedTerrain = true;
			if (c.getHasTerrainCardsLeft()) {
				c.removeTerrainCard();
			}
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
