import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Board {
	private SubBoard board1, board2, board3, board4, board5, board6, board7, board8;
	private ArrayList<SubBoard> boards;
	Point pos;
	Point anchor = new Point(37, 87); // Hexgon starting point
	private Hexagon[][] Hexadjacent;
	private ArrayList<Hexagon> houses = new ArrayList<Hexagon>();

	class SubBoard {
		public BufferedImage image;
		public String file;

		SubBoard(BufferedImage image, String file) {
			this.image = image;
			this.file = file;
		}
	}

	public Board() throws IOException {
		boards = new ArrayList<SubBoard>();
		board1 = new SubBoard(ImageIO.read(this.getClass().getResource("/pictures/Board1.png")), "One.txt");
		board2 = new SubBoard(ImageIO.read(this.getClass().getResource("/pictures/Board2.png")), "Two.txt");
		board3 = new SubBoard(ImageIO.read(this.getClass().getResource("/pictures/Board3.png")), "Three.txt");
		board4 = new SubBoard(ImageIO.read(this.getClass().getResource("/pictures/Board4.png")), "Four.txt");
		board5 = new SubBoard(ImageIO.read(this.getClass().getResource("/pictures/Board5.png")), "Five.txt");
		board6 = new SubBoard(ImageIO.read(this.getClass().getResource("/pictures/Board6.png")), "Six.txt");
		board7 = new SubBoard(ImageIO.read(this.getClass().getResource("/pictures/Board7.png")), "Seven.txt");
		board8 = new SubBoard(ImageIO.read(this.getClass().getResource("/pictures/Board8.png")), "Eight.txt");
		boards.add(board1);
		boards.add(board2);
		boards.add(board3);
		boards.add(board4);
		boards.add(board5);
		boards.add(board6);
		boards.add(board7);
		boards.add(board8);
		Collections.shuffle(boards);

		Hexadjacent = new Hexagon[20][20];
		RenderUtil r = new RenderUtil();
		double HexLen = 22.7;
		double altRad = r.getAltRadius(HexLen);

		for (int row = 0; row < 20; row++) {
			for (int col = 0; col < 20; col++) {
				double cx, cy;

				if (row % 2 == 0) {
					cx = anchor.x + HexLen + r.getHorizontalShift(HexLen) * col;
					cy = anchor.y + HexLen + row * (r.getVerticalShift(HexLen) + 0.7);
				} else {
					cx = anchor.x + HexLen + r.getHorizontalShift(HexLen) * col + altRad;
					cy = anchor.y + HexLen + row * (r.getVerticalShift(HexLen) + 0.7);
				}

				Polygon p = r.createHexagon(cx, cy, HexLen);
				Hexadjacent[row][col] = new Hexagon(p);
			}
		}

		Scanner input = new Scanner(new File(boards.get(0).file));
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				Hexadjacent[row][col].setColor(input.nextLine());
			}
		}
		input = new Scanner(new File(boards.get(1).file));
		for (int row = 0; row < 10; row++) {
			for (int col = 10; col < 20; col++) {
				Hexadjacent[row][col].setColor(input.nextLine());
			}
		}
		input = new Scanner(new File(boards.get(2).file));
		for (int row = 10; row < 20; row++) {
			for (int col = 0; col < 10; col++) {
				Hexadjacent[row][col].setColor(input.nextLine());
			}
		}
		input = new Scanner(new File(boards.get(3).file));
		for (int row = 10; row < 20; row++) {
			for (int col = 10; col < 20; col++) {
				Hexadjacent[row][col].setColor(input.nextLine());
			}
		}

		int i = 0;
	}

	public void selectHex(int x, int y) {

	}

	public void setTilePositions() {

	}

	public void drawBoard(Graphics g) {
		g.drawImage(boards.get(0).image, -141, 44, 775, 445, null);
		g.drawImage(boards.get(1).image, 253, 44, 775, 445, null);
		g.drawImage(boards.get(2).image, -141, 393, 775, 445, null);
		g.drawImage(boards.get(3).image, 253, 393, 775, 445, null);
//		if(activePlayer == 0)
//		{
//			g.drawString(players[0].getSettlementNum() + "", 960, 515);
//		}
//		else if(activePlayer == 1)
//		{
//			//g.drawString(players[1].getSettlementNum() + "", 960, 515);
//		}
		int count = 40;

		for (Hexagon h : houses) 
		{	
			Polygon p = new Polygon(h.xpoints(), h.ypoints(), 6);
			g.setColor(h.getColor());
			g.fillPolygon(p);
			g.drawPolygon(p);
			count++;
			if (count == 40)
				break;
		}
		GamePanel.players.drawPlayers(g);
	}

	public void mouseClick(Point p) {
		if (GamePanel.players.getIsFinsh() == true)
			return;
		
		for (int row = 0; row < 20; row++) {
			for (int col = 0; col < 20; col++) {
			
				Hexagon h = Hexadjacent[row][col];
				Polygon pol = new Polygon(h.xpoints(), h.ypoints(), 6);

				if (h.bOccupied) // already selected
					continue;
				if (!pol.contains(p))
					continue;

				Player activePlayer = GamePanel.players.getActivePlayer();
				if (activePlayer != null) {
					Hexagon hex = h.getHouse(activePlayer.getTerrainColor(), activePlayer.getSettlementColor());

					if (hex != null)
					{
						activePlayer.addTile(h);
						houses.add(hex);
						activePlayer.settlementsLeft(1);
					}
				}
			}
		}
	}

}
