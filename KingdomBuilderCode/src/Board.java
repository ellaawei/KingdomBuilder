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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Board {
	private SubBoard board1, board2, board3, board4, board5, board6, board7, board8;
	private ArrayList<SubBoard> boards;
	Point pos;
	Point anchor = new Point(37, 87); // Hexgon starting point
	private Hexagon[][] Hexadjacent;
	private ArrayList<Hexagon> houses = new ArrayList<Hexagon>();
	private Hexagon activeHex;
	public static Map<Hexagon.HexType, Integer> listTokens = new HashMap<Hexagon.HexType, Integer>();
	
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
		//boards.add(board3);
		boards.add(board4);
		boards.add(board6);
		//boards.add(board5);
		boards.add(board7);
		boards.add(board8);
		Collections.shuffle(boards);

		listTokens.put(Hexagon.HexType.Barn, 0);
		listTokens.put(Hexagon.HexType.Farm, 0);
		listTokens.put(Hexagon.HexType.Harbor, 0);
		listTokens.put(Hexagon.HexType.Oasis, 0);
		listTokens.put(Hexagon.HexType.Oracle, 0);
		listTokens.put(Hexagon.HexType.Paddock, 0);
		listTokens.put(Hexagon.HexType.Tower, 0);
		listTokens.put(Hexagon.HexType.Tavern, 0);
		
		activeHex = null;
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
				Hexadjacent[row][col].row = row;
				Hexadjacent[row][col].col = col;
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
		if (activeHex != null)
			activeHex.DrawAsActive(g);
	}

	public void mouseClick(Point p)  {
		if (GamePanel.players.getIsFinsh() == true)
			return;
			
		// click on token
		boolean bClickedToken = false;
		if (Players.clickCount == 0 || Players.clickCount == 3)
		{
			for (int i = 0; i < Players.listHaxTokens.size(); i++)
			{
				Polygon pol = Players.listHaxTokens.get(i).polygon;
				if (!pol.contains(p))
					continue;
				
				if (Players.listHaxTokens.get(i).bFromThisTurn == false)
				{
					Players.setActiveToken(Players.listHaxTokens.get(i));
					bClickedToken = true;
					return;
				}
			}
		}
		
		// No token clicked and click count is 3
		if (Players.clickCount == 3 && Players.getActiveToken() == null)
			return; 
		
		for (int row = 0; row < 20; row++) {
			for (int col = 0; col < 20; col++) {
				Hexagon h = Hexadjacent[row][col];
				Polygon pol = new Polygon(h.xpoints(), h.ypoints(), 6);

				if (!pol.contains(p))
					continue;
				
				Token tok = Players.getActiveToken();
						
				if (tok != null)
				{
					if (HandleToken(tok, h) == true)
						return;
				}
				else
				{
					if (h.bOccupied) // already selected
						continue;
					
					Player activePlayer = GamePanel.players.getActivePlayer();
					if (activePlayer != null) {
						
						if (activePlayer.getLocationTiles().size() == 0 || isAdjacent(h, activePlayer.getLocationTiles()) == true)
						{
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
		}
	}

	public boolean HandleToken(Token token, Hexagon h)
	{
		if (activeHex == null)
		{
			if (token.type == Hexagon.HexType.Harbor)
			{
				if (token.player.getLocationTiles().contains(h))
				{
					activeHex = h;
					return true;
				}
			}
			else if (token.type == Hexagon.HexType.Barn)
			{
				if (token.player.getLocationTiles().contains(h))
				{
					activeHex = h;
					return true;
				}
			}
			
			if (h.bOccupied)
				return false;
			
			if (token.type == Hexagon.HexType.Farm)
			{
				if (h.color == Color.GREEN)
				{	
					ArrayList<Hexagon> list = token.player.getLocationTiles();
					
					if (isAdjacent(h, list))
					{
						Hexagon hex = h.getHouse(Color.GREEN, token.player.settlementColor);
						houses.add(hex);
						
						token.player.addTile(h);
						Players.setActiveToken(null);
					}
				}
			}
			else if (token.type == Hexagon.HexType.Oasis)
			{
				if (h.color == Color.YELLOW)
				{	
					ArrayList<Hexagon> list = token.player.getLocationTiles();
					
					if (isAdjacent(h, list))
					{
						Hexagon hex = h.getHouse(Color.YELLOW, token.player.settlementColor);
						houses.add(hex);
						
						token.player.addTile(h);
						Players.setActiveToken(null);
					}
				}
			}
			else if (token.type == Hexagon.HexType.Oracle)
			{
				if (h.color == token.player.terrainColor)
				{	
					ArrayList<Hexagon> list = token.player.getLocationTiles();
					
					if (isAdjacent(h, list))
					{
						Hexagon hex = h.getHouse(token.player.terrainColor, token.player.settlementColor);
						houses.add(hex);
						
						token.player.addTile(h);
						Players.setActiveToken(null);
					}
				}
			}

		}
		else
		{
			if (h.bOccupied)
				return false;
			
			if (token.type == Hexagon.HexType.Harbor)
			{
				if (h.color == Color.BLUE)
				{	
					ArrayList<Hexagon> list = token.player.getLocationTiles();
					
					if (isAdjacent(h, list))
					{
						Hexagon hex = h.getHouse(Color.BLUE, token.player.settlementColor);
						houses.add(hex);
						
						houses.remove(activeHex.settlement);
						activeHex.bOccupied = false;
						activeHex = null;
						token.player.addTile(h);
						token.player.removeTile(activeHex);
						Players.setActiveToken(null);
					}

				}
			}
			else if (token.type == Hexagon.HexType.Barn)
			{
				if (h.color == token.player.terrainColor)
				{	
					ArrayList<Hexagon> list = token.player.getLocationTiles();
					
					if (isAdjacent(h, list))
					{
						Hexagon hex = h.getHouse(token.player.terrainColor, token.player.terrainColor);
						houses.add(hex);
						
						houses.remove(activeHex.settlement);
						activeHex.bOccupied = false;
						activeHex = null;
						token.player.addTile(h);
						token.player.removeTile(activeHex);
						Players.setActiveToken(null);
					}

				}
			}
		}
		
		return false;
	}
	
	public boolean isAdjacent(Hexagon target, ArrayList<Hexagon> list)
	{
		boolean bRet = false;
		
		if (list.size() == 0)
			return true;
		
		for (int i = 0; i < list.size(); i++)
		{
			Hexagon h = list.get(i);
			
			if (h.row % 2 == 0)
			{
				if ((target.row == h.row - 1) && (target.col == h.col -1 || target.col == h.col))
				{
					return true;
				}
				else if ((target.row == h.row) && (target.col == h.col -1 || target.col == h.col + 1))
				{
					return true;
				}
				else if ((target.row == h.row + 1) && (target.col == h.col -1 || target.col == h.col))
				{
					return true;
				}
			}
			else
			{
				if ((target.row == h.row - 1) && (target.col == h.col || target.col == h.col + 1))
				{
					return true;
				}
				else if ((target.row == h.row) && (target.col == h.col -1 || target.col == h.col + 1))
				{
					return true;
				}
				else if ((target.row == h.row + 1) && (target.col == h.col || target.col == h.col + 1))
				{
					return true;
				}
			}
		}
		
		// if none of the adjacent hex has the same color, it is free to pick
		if (NeighorHasColortoUse(target.color, list) == false)
			return true;
		
		return bRet;
	}
	
	public boolean NeighorHasColortoUse(Color c, ArrayList<Hexagon> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			Hexagon h = list.get(i);
			
			Hexagon topleft = null;
			Hexagon topright = null;
			Hexagon left = null;
			Hexagon right = null;
			Hexagon bottomleft = null;
			Hexagon bottomright = null;
			
			if (h.row % 2 == 0)
			{
				if (h.row-1 >= 0 && h.col-1 >= 0)
					topleft = Hexadjacent[h.row-1][h.col-1];
				if (h.row-1 >= 0)
					topright = Hexadjacent[h.row-1][h.col];
				if ( h.col-1 >= 0)
					left = Hexadjacent[h.row][h.col-1];
				if (h.col+1 < 20)
					right = Hexadjacent[h.row][h.col+1];
				if (h.row+1 < 20 && h.col-1 >= 0)
					bottomleft = Hexadjacent[h.row+1][h.col-1];
				if (h.row+1 < 20)
					bottomright = Hexadjacent[h.row+1][h.col];
				
			}
			else
			{
				if (h.row-1 >= 0)
					topleft = Hexadjacent[h.row-1][h.col];
				if (h.row-1 >= 0 && h.col+2 < 20)
					topright = Hexadjacent[h.row-1][h.col+1];
				if (h.col-1 >= 0)
					left = Hexadjacent[h.row][h.col-1];
				if (h.col+1 < 20)
					right = Hexadjacent[h.row][h.col+1];
				if (h.row+1 < 20)
					bottomleft = Hexadjacent[h.row+1][h.col];
				if (h.row+1 < 20 && h.col+1 < 20)
					bottomright = Hexadjacent[h.row+1][h.col+1];
			}
			if (topleft != null && topleft.bOccupied == false && topleft.color != null && topleft.color.getRGB() == c.getRGB())
				return true;
			if (topright != null && topright.bOccupied == false && topright.color != null && topright.color.getRGB() == c.getRGB())
				return true;
			if (left != null && left.bOccupied == false && left.color != null && left.color.getRGB() == c.getRGB())
				return true;
			if (right != null && right.bOccupied == false && right.color != null && right.color.getRGB() == c.getRGB())
				return true;
			if (bottomleft != null && bottomleft.bOccupied == false && bottomleft.color != null && bottomleft.color.getRGB() == c.getRGB())
				return true;
			if (bottomright != null && bottomright.bOccupied == false && bottomright.color != null && bottomright.color.getRGB() == c.getRGB())
				return true;
		}
		
		return false;
	}
	
	public boolean has3inaline(Hexagon h, ArrayList<Hexagon> list)
	{
		Hexagon[] h1 = new Hexagon[6];
		Hexagon[] h2 = new Hexagon[6];
		Hexagon[] h3 = new Hexagon[6];
		
		int index = 0;
		if (h.row % 2 == 0)
		{
			if (h.row >= 3 && h.col >= 2)
			{
				h1[index] = Hexadjacent[h.row-1][h.col-1];
				h2[index] = Hexadjacent[h.row-2][h.col-1];
				h2[index] = Hexadjacent[h.row-3][h.col-2];
				index++;
			}
			
			if (h.row >= 3 && h.col + 1 < 20)
			{
				h1[index] = Hexadjacent[h.row-1][h.col];
				h2[index] = Hexadjacent[h.row-2][h.col+1];
				h2[index] = Hexadjacent[h.row-3][h.col+1];
				index++;
			}
			
			if ( h.col >= 3)
			{
				h1[index] = Hexadjacent[h.row][h.col-1];
				h2[index] = Hexadjacent[h.row][h.col-2];
				h2[index] = Hexadjacent[h.row][h.col-3];
				index++;
			}
			
			if ( h.col + 3 < 20)
			{
				h1[index] = Hexadjacent[h.row][h.col+1];
				h2[index] = Hexadjacent[h.row][h.col+2];
				h2[index] = Hexadjacent[h.row][h.col+3];
				index++;
			}
			
			if ( h.row+3 < 20 && h.col >= 2)
			{
				h1[index] = Hexadjacent[h.row+1][h.col-1];
				h2[index] = Hexadjacent[h.row+2][h.col-1];
				h2[index] = Hexadjacent[h.row+3][h.col-2];
				index++;
			}
			
			if ( h.row+3 < 20 && h.col >= 2)
			{
				h1[index] = Hexadjacent[h.row+1][h.col];
				h2[index] = Hexadjacent[h.row+2][h.col+1];
				h2[index] = Hexadjacent[h.row+3][h.col+1];
				index++;
			}
		}
		else
		{
			if (h.row >= 3 && h.col >= 1)
			{
				h1[index] = Hexadjacent[h.row-1][h.col];
				h2[index] = Hexadjacent[h.row-2][h.col-1];
				h2[index] = Hexadjacent[h.row-3][h.col-1];
				index++;
			}
			
			if (h.row >= 3 && h.col + 2 < 20)
			{
				h1[index] = Hexadjacent[h.row-1][h.col+1];
				h2[index] = Hexadjacent[h.row-2][h.col+1];
				h2[index] = Hexadjacent[h.row-3][h.col+2];
				index++;
			}
			
			if ( h.col >= 3)
			{
				h1[index] = Hexadjacent[h.row][h.col-1];
				h2[index] = Hexadjacent[h.row][h.col-2];
				h2[index] = Hexadjacent[h.row][h.col-3];
				index++;
			}
			
			if ( h.col + 3 < 20)
			{
				h1[index] = Hexadjacent[h.row][h.col+1];
				h2[index] = Hexadjacent[h.row][h.col+2];
				h2[index] = Hexadjacent[h.row][h.col+3];
				index++;
			}
			
			if ( h.row+3 < 20 && h.col >= 1)
			{
				h1[index] = Hexadjacent[h.row+1][h.col];
				h2[index] = Hexadjacent[h.row+2][h.col-1];
				h2[index] = Hexadjacent[h.row+3][h.col-1];
				index++;
			}
			
			if ( h.row+3 < 20 && h.col + 2 < 20)
			{
				h1[index] = Hexadjacent[h.row+1][h.col+1];
				h2[index] = Hexadjacent[h.row+2][h.col+1];
				h2[index] = Hexadjacent[h.row+3][h.col+2];
				index++;
			}
		}
		
		

		
		return false;
	}
}
