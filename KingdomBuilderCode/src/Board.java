import java.awt.Color;
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
	Point anchor = new Point(67, 87); // Hexgon starting point
	private Hexagon[][] Hexadjacent;
	private ArrayList<Hexagon> houses = new ArrayList<Hexagon>();
	public static int activePlayer = -1;
	public static Player[] players = new Player[4];

	class SubBoard {
		public BufferedImage image;
		public String file;

		SubBoard(BufferedImage image, String file) {
			this.image = image;
			this.file = file;
		}
	}

	public Board() throws IOException {
		players[0] = new Player();
		players[0].setSettlementColor(new Color(124, 124, 124));
		players[1] = new Player();
		players[1].setSettlementColor(new Color(255, 158, 0));
		players[2] = new Player();
		players[2].setSettlementColor(new Color(0, 108, 255));
		players[3] = new Player();
		players[3].setSettlementColor(Color.WHITE);
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
			for (int col = 1; col < 10; col++) {
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
		g.drawImage(boards.get(0).image, -111, 44, 775, 445, null);
		g.drawImage(boards.get(1).image, 283, 44, 775, 445, null);
		g.drawImage(boards.get(2).image, -111, 393, 775, 445, null);
		g.drawImage(boards.get(3).image, 283, 393, 775, 445, null);

		for (Hexagon h : houses) {
			Polygon p = new Polygon(h.xpoints(), h.ypoints(), 6);
			g.setColor(h.getColor());
			g.fillPolygon(p);
			g.drawPolygon(p);
		}
	}

	public void mouseClick(Point p) {
		for (int row = 0; row < 20; row++) {
			for (int col = 0; col < 20; col++) {
				Hexagon h = Hexadjacent[row][col];
				Polygon pol = new Polygon(h.xpoints(), h.ypoints(), 6);
				if (!pol.contains(p))
					continue;

				if (activePlayer >= 0) {
					Hexagon hex = h.getHouse(players[activePlayer].getTerrainColor(), players[activePlayer].getSettlementColor());

					if (hex != null)
					{
						players[activePlayer].addTile(h);
						houses.add(hex);
					}
				}
			}
		}
	}

}
