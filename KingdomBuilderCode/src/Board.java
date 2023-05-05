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
		boards.add(board6);
		boards.add(board5);
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

		// preset each hexgon with its neighboring token
		for (int row = 0; row < 20; row++) 
		{
			for (int col = 0; col < 20; col++) 
			{
				Hexagon h = Hexadjacent[row][col];
				Hexagon.HexType type = h.getType();
				if (type != Hexagon.HexType.None && type != Hexagon.HexType.Castle)
				{
					int a = 0;
					if (type == Hexagon.HexType.Barn)
						a = 5;
					if (row % 2 == 0)
					{
						Hexadjacent[row-1][col-1].setNeighborToken(type, row, col);	
						Hexadjacent[row-1][col].setNeighborToken(type, row, col);	
						Hexadjacent[row][col-1].setNeighborToken(type, row, col);
						Hexadjacent[row][col+1].setNeighborToken(type, row, col);
						Hexadjacent[row+1][col-1].setNeighborToken(type, row, col);
						Hexadjacent[row+1][col].setNeighborToken(type, row, col);
					}
					else
					{
						Hexadjacent[row-1][col].setNeighborToken(type, row, col);	
						Hexadjacent[row-1][col+1].setNeighborToken(type, row, col);	
						Hexadjacent[row][col-1].setNeighborToken(type, row, col);
						Hexadjacent[row][col+1].setNeighborToken(type, row, col);
						Hexadjacent[row+1][col].setNeighborToken(type, row, col);
						Hexadjacent[row+1][col+1].setNeighborToken(type, row, col);
					}
				}
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
		
		for (Hexagon h : houses) 
		{
			Polygon p = new Polygon(h.xpoints(), h.ypoints(), 6);
			g.setColor(h.getColor());
			g.fillPolygon(p);
			g.drawPolygon(p);
		}
		GamePanel.players.drawPlayers(g);
	}

	public void mouseClick(Point p)  {
		if (GamePanel.players.getIsFinsh() == true)
			return;
		
		if (Players.clickCount == 3)
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
						Players.clickCount++;
						activePlayer.addTile(h);
						houses.add(hex);
						activePlayer.settlementsLeft(1);
						
						Hexagon.HexType t = h.neighborTokenType;
						if (t != Hexagon.HexType.None)
							activePlayer.AddToken(t);
					}
				}
			}
		}
	}
	
	//objective cards scoring
	public ArrayList<Hexagon> adjacent(Hexagon h)
	{
		ArrayList<Hexagon> adjacencies = new ArrayList<Hexagon>();
		for(int row = 0; row<20; row++)
		{
			for(int col = 0; col<20; col++)
			{
				Hexagon hex = Hexadjacent[row][col];

				if(hex.equals(h))
				{
					if(row%2 == 0)
					{
						if(row > 0 && row <19)
						{
							if(col < 19 && col > 0)
							{
								adjacencies.add(Hexadjacent[row+1][col]);
								adjacencies.add(Hexadjacent[row][col-1]);
								adjacencies.add(Hexadjacent[row][col+1]);
								adjacencies.add(Hexadjacent[row+1][col-1]);
								adjacencies.add(Hexadjacent[row-1][col]);
								adjacencies.add(Hexadjacent[row-1][col-1]);
							}
							else
							{
								if(col == 0)
								{
									adjacencies.add(Hexadjacent[row+1][col]);
									adjacencies.add(Hexadjacent[row-1][col]);
									adjacencies.add(Hexadjacent[row][col+1]);
								}
								else if(col == 19)
								{
									adjacencies.add(Hexadjacent[row+1][col]);
									adjacencies.add(Hexadjacent[row-1][col]);
									adjacencies.add(Hexadjacent[row][col-1]);
									adjacencies.add(Hexadjacent[row+1][col-1]);
									adjacencies.add(Hexadjacent[row-1][col-1]);
								}
							}
						}
						else
						{
							if(row == 0)
							{
								if(col < 19 && col > 0)
								{
									adjacencies.add(Hexadjacent[row+1][col]);
									adjacencies.add(Hexadjacent[row][col-1]);
									adjacencies.add(Hexadjacent[row][col+1]);
									adjacencies.add(Hexadjacent[row+1][col-1]);
								}
								else
								{
									if(col == 0)
									{
										adjacencies.add(Hexadjacent[row+1][col]);
										adjacencies.add(Hexadjacent[row][col+1]);
									}
									else if(col == 19)
									{
										adjacencies.add(Hexadjacent[row+1][col]);
										adjacencies.add(Hexadjacent[row][col-1]);
										adjacencies.add(Hexadjacent[row+1][col-1]);
									}
								}
							}
						}
					}
					
					else
					{
						if(row > 0 && row < 19)
						{
							if(col < 19 && col > 0)
							{
								adjacencies.add(Hexadjacent[row+1][col]);
								adjacencies.add(Hexadjacent[row+1][col+1]);
								adjacencies.add(Hexadjacent[row][col+1]);
								adjacencies.add(Hexadjacent[row][col-1]);
								adjacencies.add(Hexadjacent[row-1][col]);
								adjacencies.add(Hexadjacent[row-1][col+1]);
							}
							else
							{
								if(col == 0)
								{
									adjacencies.add(Hexadjacent[row+1][col]);
									adjacencies.add(Hexadjacent[row-1][col]);
									adjacencies.add(Hexadjacent[row][col+1]);
									adjacencies.add(Hexadjacent[row+1][col+1]);
									adjacencies.add(Hexadjacent[row-1][col+1]);
								}
								else if(col == 19)
								{
									adjacencies.add(Hexadjacent[row+1][col]);
									adjacencies.add(Hexadjacent[row-1][col]);
									adjacencies.add(Hexadjacent[row][col-1]);
								}
							}
						}
						else
						{
							if(row == 19)
							{
								if(col<19 && col > 0)
								{
									adjacencies.add(Hexadjacent[row-1][col]);
									adjacencies.add(Hexadjacent[row-1][col+1]);
									adjacencies.add(Hexadjacent[row][col+1]);
									adjacencies.add(Hexadjacent[row][col-1]);
								}
								else
								{
									if(col == 0)
									{
										adjacencies.add(Hexadjacent[row][col+1]);
										adjacencies.add(Hexadjacent[row-1][col]);
										adjacencies.add(Hexadjacent[row-1][col+1]);
									}
									else if(col == 19)
									{
										adjacencies.add(Hexadjacent[row][col-1]);
										adjacencies.add(Hexadjacent[row-1][col]);
									}
								}
							}
						}
					}
				}
			}
		}
		return adjacencies;
	}
	
	//castle
	public void Castles()
	{
		for(int row = 0; row<20; row++)
		{
			for(int col = 0; col<20; col++)
			{
				Hexagon h = Hexadjacent[row][col];
				
				if(h.getType().equals(Hexagon.HexType.Castle))
				{
					boolean p1, p2 ,p3, p4;
					p1 = p2 =p3 = p4 = false;
					ArrayList<Hexagon> adj = adjacent(h);
					for(Hexagon hex: adj)
					{
						if(hex.getSettlement())
						{
							if(hex.bOccupied && hex.getColor() == new Color(205, 102, 0) && !p1)
							{
								Players.listPlayers[0].score +=3;
								p1 = true;
							}
							else if(hex.bOccupied && hex.getColor() == new Color(65, 65, 65) && !p2)
							{
								Players.listPlayers[1].score+=3;
								p2 = true;
							}
							else if(hex.bOccupied && hex.getColor() == new Color(255, 255, 204) && !p3)
							{
								Players.listPlayers[2].score+=3;
								p3 = true;
							}
							else if(hex.bOccupied && hex.getColor() == new Color(51, 153, 255) && !p4)
							{
								Players.listPlayers[3].score+=3;
								p4 = true;
							}
						}
					}
				}
			}
		}
	}
	
	//fishermen
	public void Fisherman()
	{
		for(int r = 0; r<20; r++)
		{
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				
				if(Hexadjacent[r][c].getSettlement())
				{
					ArrayList<Hexagon> adj = adjacent(h);
					boolean valid = false;
					for(Hexagon hex: adj)
					{
						if(hex.getColor() == Color.BLUE && !valid && h.getColor() != Color.BLUE)
						{
							if(h.bOccupied && h.getColor() == new Color(205, 102, 0))
								Players.listPlayers[0].score++;
							else if(h.bOccupied && h.getColor() == new Color(65, 65, 65))
								Players.listPlayers[1].score++;
							else if(h.bOccupied && h.getColor() == new Color(255, 255, 204))
								Players.listPlayers[2].score++;
							else if(h.bOccupied && h.getColor() == new Color(51, 153, 255))
								Players.listPlayers[3].score++;
							
							valid = true;
						}
					}
				}
			}
		}
	}
	
	//knights
	public void Knights()
	{
		int P1Max, P2Max, P3Max, P4Max;
		P1Max = P2Max = P3Max = P4Max = 0;
		int P1Row, P2Row, P3Row, P4Row;
		P1Row =P2Row = P3Row = P4Row = 0;
		for(int r = 0; r<20; r++)
		{
			int P1, P2,P3,P4;
			P1 = P2 = P3 =P4 = 0;
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				
				if(Hexadjacent[r][c].getSettlement())
				{
					if(h.bOccupied && h.getColor() == new Color(205, 102, 0))
					{
						P1++;
					}
					else if(h.bOccupied && h.getColor() == new Color(65, 65, 65))
					{
						P2++;
					}
					else if(h.bOccupied && h.getColor() == new Color(255, 255, 204))
					{
						P3++;
					}
					else if(h.bOccupied && h.getColor() == new Color(51, 153, 255))
					{
						P4++;	
					}
				}
			}
			
			if(P1>P1Max)
			{
				P1Max = P1;
				P1Row = r;
			}
			if(P2>P2Max)
			{
				P2Max = P2;
				P2Row = r;
			}
			if(P3>P3Max)
			{
				P3Max = P3;
				P3Row = r;
			}
			if(P4>P4Max)
			{
				P4Max = P4;
				P4Row = r;
			}
		}
		
		for(int r = P1Row; r<=P1Row; r++)
		{
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				if(Hexadjacent[r][c].getSettlement())
					if(h.bOccupied && h.getColor() == new Color(205, 102, 0))
						Players.listPlayers[0].score+=2;
			}
		}
		
		for(int r = P2Row; r<=P2Row; r++)
		{
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				if(Hexadjacent[r][c].getSettlement())
					if(h.bOccupied && h.getColor() == new Color(65, 65, 65))
						Players.listPlayers[1].score+=2;
			}
		}
		
		for(int r = P3Row; r<=P3Row; r++)
		{
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				if(Hexadjacent[r][c].getSettlement())
					if(h.bOccupied && h.getColor() == new Color(255, 255, 204))
						Players.listPlayers[2].score+=2;
			}
		}
		
		for(int r = P4Row; r<=P4Row; r++)
		{
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				if(Hexadjacent[r][c].getSettlement())
					if(h.bOccupied && h.getColor() == new Color(51, 153, 255))
						Players.listPlayers[3].score+=2;
			}
		}
	}
	
	//discoverers
	public void Discoverers()
	{
		for(int r = 0; r<20; r++)
		{
			boolean P1, P2, P3, P4;
			P1 = P2 = P3 =P4 = false;
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				
				if(Hexadjacent[r][c].getSettlement())
				{
					if(h.bOccupied && h.getColor() == new Color(205, 102, 0) && !P1)
					{
						Players.listPlayers[0].score++;
						P1 = true;		
					}
					else if(h.bOccupied && h.getColor() == new Color(65, 65, 65) && !P2)
					{
						Players.listPlayers[1].score++;
						P2 = true;		
					}
					else if(h.bOccupied && h.getColor() == new Color(255, 255, 204) && !P3)
					{
						Players.listPlayers[2].score++;
						P3 = true;		
					}
					else if(h.bOccupied && h.getColor() == new Color(51, 153, 255) && !P4)
					{
						Players.listPlayers[3].score++;
						P4 = true;		
					}
				}
			}
		}
	}

}
