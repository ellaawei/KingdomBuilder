import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Board extends JPanel
{
	private ArrayList<Hexagon> b1,b2,b3,b4;
	private JFrame parentFrame;
	private Scanner input;
	private Color color, col, discardCardColor, MerchColor;
	private ArrayList<Integer> colors;
	ArrayList<String> KBCards, roles; 
	private Polygon card, discardCard, deck, Player1, Player2, Player3, Player4, role1;
	private Rectangle extra1, extra2, extra3, extra4, extra5, extra6, extra7, extra8;
	private int numHouses, P1Houses, P2Houses, P3Houses, P4Houses, pturn, P1Points, P2Points, P3Points, P4Points;
	private boolean p1,p2,p3,p4, turn2, r1, r2, r3, endGame, MerchAdjacent, MerchTurn;
	private Hexagon[][] Hexadjacent;
	
	public Board() throws IOException
	{
		col = Color.orange;
		
		endGame = false;
		
		MerchAdjacent = false;
		MerchTurn = false;
		MerchColor = Color.black;
		
		parentFrame = new JFrame("Kingdom Builder");
		parentFrame.setSize(800, 600);
		parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		parentFrame.setVisible(true);
		
		parentFrame.add(this);
		
		extra1 = new Rectangle(1450, 220, 50, 175);
		extra2 = new Rectangle(1450, 280, 50, 175);
		extra3 = new Rectangle(1450, 340, 50, 175);
		extra4 = new Rectangle(1450, 400, 50, 175);
		extra5 = new Rectangle(1450, 460, 50, 175);
		extra6 = new Rectangle(1450, 520, 50, 175);
		extra7 = new Rectangle(1450, 580, 50, 175);
		extra8 = new Rectangle(1450, 640, 50, 175);
		
		p1 = p2= p3 =p4 = turn2 = false;
		
		b1 = new ArrayList<Hexagon>();
		b2 = new ArrayList<Hexagon>();
		b3 = new ArrayList<Hexagon>();
		b4 = new ArrayList<Hexagon>();
		
		int[] xpoints = {1250,1250,1360,1360};
		int[] ypoints = {100,250,250,100};
		int[] xpoints3 = {1460, 1460, 1570,1570};
		int[] ypoints10 = {100,200,200,100};
		
		role1 = new Polygon(xpoints3, ypoints10, 4);
		
		deck = new Polygon(xpoints,ypoints , 4);
		
		int[] ypoints2 = {300,450,450,300};
		card = new Polygon(xpoints, ypoints2, 4);
		
		color = Color.BLACK;
		
		int[] ypoints3 = {500,650,650,500};
		discardCard = new Polygon(xpoints,ypoints3,4);
		discardCardColor = Color.BLACK;
		
		
		int[] ypoints5 = {730,770,770,730};
		Player1= new Polygon(xpoints, ypoints5, 4);
		
		int[] ypoints7 = {850,890,890,850};
		Player3= new Polygon(xpoints, ypoints7, 4);
		
		int[] xpoints2 = {1460, 1460, 1570,1570};
		Player2 = new Polygon(xpoints2, ypoints5, 4);
		
		Player4 = new Polygon(xpoints2, ypoints7, 4);
		
		P1Houses = 40;
		P2Houses = 40;
		P3Houses = 40;
		P4Houses = 40;
		
		pturn = 1;
		
		colors = new ArrayList<Integer>();
		for(int i = 0; i <5; i++)
			for(int j = 0; j<5; j++)
				colors.add(i);
		
		numHouses = 0;
		
		P1Points = 0;
		P2Points = 0;
		P3Points = 0;
		P4Points = 0;
		
		KBCards = new ArrayList<String>();
		KBCards.add("Fishermen"); KBCards.add("Miners"); KBCards.add("Discoverers"); KBCards.add("Workers"); KBCards.add("Knights");KBCards.add("Farmers");
		KBCards.add("Lords"); KBCards.add("Hermits"); KBCards.add("Citizens"); KBCards.add("Merchants");
		
		Collections.shuffle(KBCards);
		
		roles = new ArrayList<String>();
		roles.add(KBCards.get(0));
		roles.add(KBCards.get(1));
		roles.add(KBCards.get(2));
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		int i = (int)(Math.random()*8+1);
		a.add(i);
	
		makeBoard1(i);
		
		i = (int)(Math.random()*8+1);
		 
		if(a.contains(i))
		{
			boolean b = false;
			while(!b)
			{
				i = (int)(Math.random()*8+1);
				if(!a.contains(i))
					b = true;
			}
			a.add(i);
		}
		else
			a.add(i);
		
		makeBoard2(i);
		
		i = (int)(Math.random()*8+1);
		if(a.contains(i))
		{
			boolean b = false;
			while(!b)
			{
				i = (int)(Math.random()*8+1);
				if(!a.contains(i))
					b = true;
			}
			a.add(i);
		}
		else
			a.add(i);
		
		makeBoard3(i);
		
		
		i = (int)(Math.random()*8+1);
		if(a.contains(i))
		{
			boolean b = false;
			while(!b)
			{
				i = (int)(Math.random()*8+1);
				if(!a.contains(i))
					b = true;
			}
			a.add(i);
		}
		else
			a.add(i);
		
		makeBoard4(i);
		
		Hexadjacent = new Hexagon[20][20];
		
		int b1num = 0;
		int b2num = 0;
		int b3num = 0;
		int b4num = 0;
		
		for(int x = 0; x<20; x++)
		{
			for(int y = 0; y<20; y++)
			{
				if(x<=9 && y<=9)
				{
					Hexagon hex = b1.get(b1num);
					b1num++;
					Hexadjacent[x][y] = hex;
				}
				else if(x<=9 && y<=19)
				{
					Hexagon hex = b2.get(b2num);
					b2num++;
					Hexadjacent[x][y] = hex;
				}
				else if(x<=19 && y<=9)
				{
					Hexagon hex = b3.get(b3num);
					b3num++;
					Hexadjacent[x][y] = hex;
				}
				else if(x<=19 && y<=19)
				{
					Hexagon hex = b4.get(b4num);
					b4num++;
					Hexadjacent[x][y] = hex;
				}
			}
		}
	}
	
	@SuppressWarnings({"static-access" })
	public void makeBoard1(int i) throws IOException
	{
		input = new Scanner(new File("One.txt"));
		
		switch(i)
		{
			case 1: input = new Scanner(new File("One.txt"));break;
			case 2: input = new Scanner(new File("Two.txt"));break;
			case 3: input = new Scanner(new File("Three.txt"));break;
			case 4: input = new Scanner(new File("Four.txt"));break;
			case 5: input = new Scanner(new File("Five.txt"));break;
			case 6: input = new Scanner(new File("Six.txt"));break;
			case 7: input = new Scanner(new File("Seven.txt"));break;
			case 8: input = new Scanner(new File("Eight.txt"));break;
		}
		
		RenderUtil r = new RenderUtil();
		
		double altRad = r.getAltRadius(32);
		
		for(int row = 0; row<10; row++)
		{
			for(int x = 0; x<10; x++)
			{
				double cx, cy;
				
				if(row % 2 == 0)
				{
					cx = 32 + r.getHorizontalShift(32)*x;
					cy = 32 + row* r.getVerticalShift(32);
				}
				else
				{
					cx = 32 + r.getHorizontalShift(32)*x + altRad;
					cy = 32 + row* r.getVerticalShift(32);
				}
				
				Polygon p = r.createHexagon(cx ,cy, 32);
				Polygon p2 = r.createHexagon(cx ,cy, 16);
					
				Color c = Color.red;
					
				String g = input.nextLine();
					
				c = getColor(g);
					
				Hexagon h = new Hexagon(p,c, p2);
				
				if(c == Color.WHITE)
				{
					if(g.contains("Oracle"))
						h.setString("Oracle");
					else if(g.contains("Farm"))
						h.setString("Farm");
					else if(g.contains("Oasis"))
						h.setString("Oasis");
					else if(g.contains("Tower"))
						h.setString("Tower");
					else if(g.contains("Tavern"))
						h.setString("Tavern");
					else if(g.contains("Barn"))
						h.setString("Barn");
					else if(g.contains("Harbor"))
						h.setString("Harbor");
					else if(g.contains("Paddock"))
						h.setString("Paddock");
					else
						h.setString("Castle");
				}
				
				b1.add(h);
			}	
		}
	}
	
	@SuppressWarnings({"static-access" })
	public void makeBoard2(int i) throws IOException
	{
		switch(i)
		{
			case 1: input = new Scanner(new File("One.txt"));break;
			case 2: input = new Scanner(new File("Two.txt"));break;
			case 3: input = new Scanner(new File("Three.txt"));break;
			case 4: input = new Scanner(new File("Four.txt"));break;
			case 5: input = new Scanner(new File("Five.txt"));break;
			case 6: input = new Scanner(new File("Six.txt"));break;
			case 7: input = new Scanner(new File("Seven.txt"));break;
			case 8: input = new Scanner(new File("Eight.txt"));break;
		}
		
		RenderUtil r = new RenderUtil();
		
		double altRad = r.getAltRadius(32);
		
		for(int row = 0; row<10; row++)
		{
			for(int x = 0; x<10; x++)
			{
				double cx, cy;
				int x1 = 10;
				
				if(row % 2 == 0)
				{
					cx = 32 + r.getHorizontalShift(32)*(x+x1);
					cy = 32 + row*r.getVerticalShift(32);
				}
				else
				{
					cx = 32 + r.getHorizontalShift(32)*(x+x1) + altRad;
					cy = 32 + row* r.getVerticalShift(32);
				}
				
				Polygon p = r.createHexagon(cx ,cy, 32);
				Polygon p2 = r.createHexagon(cx ,cy, 16);
					
				Color c = Color.red;
					
				String g = input.nextLine();
					
				c = getColor(g);
					
				Hexagon h = new Hexagon(p,c, p2);
				
				if(c == Color.WHITE)
				{
					if(g.contains("Oracle"))
						h.setString("Oracle");
					else if(g.contains("Farm"))
						h.setString("Farm");
					else if(g.contains("Oasis"))
						h.setString("Oasis");
					else if(g.contains("Tower"))
						h.setString("Tower");
					else if(g.contains("Tavern"))
						h.setString("Tavern");
					else if(g.contains("Barn"))
						h.setString("Barn");
					else if(g.contains("Harbor"))
						h.setString("Harbor");
					else if(g.contains("Paddock"))
						h.setString("Paddock");
					else
						h.setString("Castle");
				}
				
				b2.add(h);
			}	
		}
	}
	
	@SuppressWarnings({"static-access" })
	public void makeBoard3(int i) throws IOException
	{
		switch(i)
		{
			case 1: input = new Scanner(new File("One.txt"));break;
			case 2: input = new Scanner(new File("Two.txt"));break;
			case 3: input = new Scanner(new File("Three.txt"));break;
			case 4: input = new Scanner(new File("Four.txt"));break;
			case 5: input = new Scanner(new File("Five.txt"));break;
			case 6: input = new Scanner(new File("Six.txt"));break;
			case 7: input = new Scanner(new File("Seven.txt"));break;
			case 8: input = new Scanner(new File("Eight.txt"));break;
		}
		
		RenderUtil r = new RenderUtil();
		
		double altRad = r.getAltRadius(32);
		
		for(int row = 0; row<10; row++)
		{
			for(int x = 0; x<10; x++)
			{
				double cx, cy;
				int x1 = 10;
				
				if(row % 2 == 0)
				{
					cx = 32 + r.getHorizontalShift(32)*x;
					cy = 32 + (row+x1)*r.getVerticalShift(32);
				}
				else
				{
					cx = 32 + r.getHorizontalShift(32)*x + altRad;
					cy = 32 + (row+x1)* r.getVerticalShift(32);
				}
				
				Polygon p = r.createHexagon(cx ,cy, 32);
				Polygon p2 = r.createHexagon(cx ,cy, 16);
					
				Color c = Color.red;
					
				String g = input.nextLine();
					
				c = getColor(g);
					
				Hexagon h = new Hexagon(p,c, p2);
				
				if(c == Color.WHITE)
				{
					if(g.contains("Oracle"))
						h.setString("Oracle");
					else if(g.contains("Farm"))
						h.setString("Farm");
					else if(g.contains("Oasis"))
						h.setString("Oasis");
					else if(g.contains("Tower"))
						h.setString("Tower");
					else if(g.contains("Tavern"))
						h.setString("Tavern");
					else if(g.contains("Barn"))
						h.setString("Barn");
					else if(g.contains("Harbor"))
						h.setString("Harbor");
					else if(g.contains("Paddock"))
						h.setString("Paddock");
					else
						h.setString("Castle");
				}
				
				b3.add(h);
										
			}	
		}
	}
	
	@SuppressWarnings({"static-access" })
	public void makeBoard4(int i) throws IOException
	{
		switch(i)
		{
			case 1: input = new Scanner(new File("One.txt"));break;
			case 2: input = new Scanner(new File("Two.txt"));break;
			case 3: input = new Scanner(new File("Three.txt"));break;
			case 4: input = new Scanner(new File("Four.txt"));break;
			case 5: input = new Scanner(new File("Five.txt"));break;
			case 6: input = new Scanner(new File("Six.txt"));break;
			case 7: input = new Scanner(new File("Seven.txt"));break;
			case 8: input = new Scanner(new File("Eight.txt"));break;
		}
		
		RenderUtil r = new RenderUtil();
		
		double altRad = r.getAltRadius(32);
		
		for(int row = 0; row<10; row++)
		{
			for(int x = 0; x<10; x++)
			{
				double cx, cy;
				int x1 = 10;
				
				if(row % 2 == 0)
				{
					cx = 32 + r.getHorizontalShift(32)*(x+x1);
					cy = 32 + (row+x1)*r.getVerticalShift(32);
				}
				else
				{
					cx = 32 + r.getHorizontalShift(32)*(x+x1) + altRad;
					cy = 32 + (row+x1)* r.getVerticalShift(32);
				}
				
				Polygon p = r.createHexagon(cx ,cy, 32);
				Polygon p2 = r.createHexagon(cx ,cy, 16);
					
				Color c = Color.red;
					
				String g = input.nextLine();
					
				c = getColor(g);
					
				Hexagon h = new Hexagon(p,c, p2);
				
				if(c == Color.WHITE)
				{
					if(g.contains("Oracle"))
						h.setString("Oracle");
					else if(g.contains("Farm"))
						h.setString("Farm");
					else if(g.contains("Oasis"))
						h.setString("Oasis");
					else if(g.contains("Tower"))
						h.setString("Tower");
					else if(g.contains("Tavern"))
						h.setString("Tavern");
					else if(g.contains("Barn"))
						h.setString("Barn");
					else if(g.contains("Harbor"))
						h.setString("Harbor");
					else if(g.contains("Paddock"))
						h.setString("Paddock");
					else
						h.setString("Castle");
				}
				
				b4.add(h);
			}
		}	
	}

	public void checkHex(Point p)
	{
		for(int i=0; i < b1.size(); i++)
		{
			boolean valid = false;
			Hexagon a=b1.get(i);
			Polygon pol = new Polygon(a.xpoints(), a.ypoints(), 6);
			if(!pol.contains(p)) continue;
			if(!a.getColor().equals(color)) continue;
			if(a.getSettlement()) continue;
			
			ArrayList<Hexagon> adj = adjacent(a);
			for(Hexagon adj1 : adj)
			{
				if(adj1.getSettlement() && adj1.getSettledColor().equals(col))
					valid = true;
			}
			
			if(!valid)
			{
				int count = 0;
				for(int r = 0; r < 20; r++)
				{
					for(int c = 0; c < 20; c++)
					{
						if(Hexadjacent[r][c].getColor().equals(color) && !Hexadjacent[r][c].getSettlement())
						{
							ArrayList<Hexagon> adj2 = adjacent(Hexadjacent[r][c]);
							for(Hexagon adj1 : adj2)
								if(adj1.getSettlement() && adj1.getSettledColor().equals(col))
									count++;
						}
					}
				}
			
				if(count == 0)
				{
					for(int r = 0; r < 20; r++)
					{
						for(int c = 0; c < 20; c++)
						{
							if(Hexadjacent[r][c].getColor().equals(color) && !Hexadjacent[r][c].getSettlement())
								count++;
						}
					}
					
					if(count>0)
						valid = true;
				}
			}
			
			if(valid && p1)
			{
				b1.get(i).setSettlement(true);
				b1.get(i).setSettledColor(col);
				
				numHouses++;
				
				if(col == Color.orange)
					P1Houses--;
				else if(col == Color.CYAN)
					P2Houses--;
				else if(col == Color.WHITE)
					P3Houses--;
				else if(col == Color.MAGENTA)
					P4Houses--;
			}
		}
		
		for(int i=0; i< b2.size(); i++)
		{
			boolean valid = false;
			Hexagon a=b2.get(i);
			Polygon pol = new Polygon(a.xpoints(), a.ypoints(), 6);
			if(!pol.contains(p)) continue;
			if(!a.getColor().equals(color)) continue;
			if(a.getSettlement()) continue;
			
			ArrayList<Hexagon> adj = adjacent(a);
			for(Hexagon adj1 : adj)
			{
				if(adj1.getSettlement() && adj1.getSettledColor().equals(col))
					valid = true;
			}
			
			if(!valid)
			{
				int count = 0;
				for(int r = 0; r < 20; r++)
				{
					for(int c = 0; c < 20; c++)
					{
						if(Hexadjacent[r][c].getColor().equals(color) && !Hexadjacent[r][c].getSettlement())
						{
							ArrayList<Hexagon> adj2 = adjacent(Hexadjacent[r][c]);
							for(Hexagon adj1 : adj2)
								if(adj1.getSettlement() && adj1.getSettledColor().equals(col)) count++;
						}
					}
				}
				
				if(count == 0)
				{
					for(int r = 0; r < 20; r++)
					{
						for(int c = 0; c < 20; c++)
						{
							if(Hexadjacent[r][c].getColor().equals(color) && !Hexadjacent[r][c].getSettlement())
								count++;
						}
					}
					
					if(count>0)
						valid = true;
				}
			}
			
			if(valid && p1)
			{
				b2.get(i).setSettlement(true);
				b2.get(i).setSettledColor(col);
				numHouses++;
				
				if(col == Color.orange)
					P1Houses--;
				else if(col == Color.CYAN)
					P2Houses--;
				else if(col == Color.WHITE)
					P3Houses--;
				else if(col == Color.MAGENTA)
					P4Houses--;
			}
		}
		
		for(int i=0; i< b3.size(); i++)
		{
			boolean valid = false;
			
			Hexagon a=b3.get(i);
			Polygon pol = new Polygon(a.xpoints(), a.ypoints(), 6);
			if(!pol.contains(p)) continue;
			if(!a.getColor().equals(color)) continue;
			if(a.getSettlement()) continue;
			
			ArrayList<Hexagon> adj = adjacent(a);
			for(Hexagon adj1 : adj)
			{
				if(adj1.getSettlement() && adj1.getSettledColor().equals(col))
					valid = true;
			}
			
			if(!valid)
			{
				int count = 0;
				for(int r = 0; r < 20; r++)
				{
					for(int c = 0; c < 20; c++)
					{
						if(Hexadjacent[r][c].getColor().equals(color) && !Hexadjacent[r][c].getSettlement())
						{
							ArrayList<Hexagon> adj2 = adjacent(Hexadjacent[r][c]);
							for(Hexagon adj1 : adj2)
								if(adj1.getSettlement() && adj1.getSettledColor().equals(col)) count++;
						}
					}
				}
				
				if(count == 0)
				{
					for(int r = 0; r < 20; r++)
					{
						for(int c = 0; c < 20; c++)
						{
							if(Hexadjacent[r][c].getColor().equals(color) && !Hexadjacent[r][c].getSettlement())
								count++;
						}
					}
					
					if(count>0)
						valid = true;
				}
			}
			
			if(valid && p1)
			{
				b3.get(i).setSettlement(true);
				b3.get(i).setSettledColor(col);
				numHouses++;
				
				if(col == Color.orange)
					P1Houses--;
				else if(col == Color.CYAN)
					P2Houses--;
				else if(col == Color.WHITE)
					P3Houses--;
				else if(col == Color.MAGENTA)
					P4Houses--;
			}

		}
		
		for(int i=0; i< b4.size(); i++)
		{
			boolean valid = false;
			
			Hexagon a=b4.get(i);
			Polygon pol = new Polygon(a.xpoints(), a.ypoints(), 6);
			if(!pol.contains(p)) continue;
			if(!a.getColor().equals(color)) continue;
			if(a.getSettlement()) continue;
			
			ArrayList<Hexagon> adj = adjacent(a);
			for(Hexagon adj1 : adj)
			{
				if(adj1.getSettlement() && adj1.getSettledColor().equals(col))
					valid = true;
			}
			
			if(!valid)
			{
				int count = 0;
				for(int r = 0; r < 20; r++)
				{
					for(int c = 0; c < 20; c++)
					{
						if(Hexadjacent[r][c].getColor().equals(color) && !Hexadjacent[r][c].getSettlement())
						{
							ArrayList<Hexagon> adj2 = adjacent(Hexadjacent[r][c]);
							for(Hexagon adj1 : adj2)
								if(adj1.getSettlement() && adj1.getSettledColor().equals(col)) count++;
						}
					}
				}
				
				if(count == 0)
				{
					for(int r = 0; r < 20; r++)
					{
						for(int c = 0; c < 20; c++)
						{
							if(Hexadjacent[r][c].getColor().equals(color) && !Hexadjacent[r][c].getSettlement())
								count++;
						}
					}
					
					if(count>0)
						valid = true;
				}
			}
			
			if(valid && p1)
			{
				b4.get(i).setSettlement(true);
				b4.get(i).setSettledColor(col);
				numHouses++;
				
				if(col == Color.orange)
					P1Houses--;
				else if(col == Color.CYAN)
					P2Houses--;
				else if(col == Color.WHITE)
					P3Houses--;
				else if(col == Color.MAGENTA)
					P4Houses--;
			}

		}
		
		if(!PTurnHouses())
		{
			discardCard();
			numHouses = 3;
		}
		
		if(numHouses == 3)
		{
			if(color  != Color.black)
				discardCard();
			numHouses = 0;
			
			if(pturn == 4)
			{
				if(P1Houses > 0 )
				{
					pturn = 1;
					alterColor(1);
				}
				else if(P2Houses > 0 && p2)
				{
					pturn = 2;
					alterColor(2);
				}
				else if(P3Houses > 0 && p3)
				{
					pturn = 3;
					alterColor(3);
				}
				else
					pturn = 4;
				
				turn2 = true;
			}
			
			else if(pturn == 3 && p4)
			{
				if(P4Houses > 0 && p4)
				{
					pturn = 4;
					alterColor(4);
				}
				else if(P1Houses > 0 && p1)
				{
					pturn = 1;
					alterColor(1);
				}
				else if(P2Houses > 0 && p2)
				{
					pturn = 2;
					alterColor(2);
				}
				else
					pturn = 3;
			}
			
			else if(pturn == 2 && p3)
			{
				if(P3Houses > 0 && p3)
				{
					pturn = 3;
					alterColor(3);
				}
				else if(P4Houses > 0 && p4)
				{
					pturn = 4;
					alterColor(4);
				}
				else if(P1Houses > 0 && p1)
				{
					pturn = 1;
					alterColor(1);
				}
				else
					pturn = 2;
			}
			
			else if(pturn == 1 && p2)
			{
				if(P2Houses > 0 && p2)
				{
					pturn = 2;
					alterColor(2);
				}
				else if(P3Houses > 0 && p3)
				{
					pturn = 3;
					alterColor(3);
				}
				else if(P4Houses > 0 && p4)
				{
					pturn = 4;
					alterColor(4);
				}
				else
					pturn = 1;
			}
			
			else
			{
				if(P1Houses > 0)
				{
					pturn = 1;
					alterColor(1);
				}
				else if(!endGame)
				{
					endGame();
				}
				turn2 = true;
			}
		}

	}
	
	public Hexagon checkHex2(Point p)
	{
		for(int i=0; i< b1.size(); i++)
		{
			Hexagon a=b1.get(i);
			Polygon pol = new Polygon(a.xpoints(), a.ypoints(), 6);
			if(pol.contains(p))
				return a;
		}
		
		for(int i=0; i< b2.size(); i++)
		{
			Hexagon a=b2.get(i);
			Polygon pol = new Polygon(a.xpoints(), a.ypoints(), 6);
			if(pol.contains(p))
				return a;
		}
		
		for(int i=0; i< b3.size(); i++)
		{
			Hexagon a=b3.get(i);
			Polygon pol = new Polygon(a.xpoints(), a.ypoints(), 6);
			if(pol.contains(p))
				return a;
		}
		
		for(int i=0; i< b4.size(); i++)
		{
			Hexagon a=b4.get(i);
			Polygon pol = new Polygon(a.xpoints(), a.ypoints(), 6);
			if(pol.contains(p))
				return a;
		}
		return null;
	}
	
	public void CheckDeck(Point p)
	{
		if(deck.contains(p) && color == Color.BLACK && !endGame)
		{
			Collections.shuffle(colors);
			int x= colors.remove(0);
			
			switch(x)
			{
				case 0: color = Color.GREEN;break;
				case 1: color = Color.GREEN.darker().darker();break;
				case 2: color = Color.yellow;break;
				case 3: color = Color.PINK;break;
				case 4: color = new Color(139, 69, 13);break;
			}
			
			if(colors.size() == 0)
				resetDeck();
		}
		
		if(Player1.contains(p))
			p1=true;
		
		if(Player2.contains(p) && p1 && !turn2)
			p2 =true;
		
		if(Player3.contains(p) && p1 && p2 && !turn2)
			p3 = true;
		
		if(Player4.contains(p) && p1 && p2 && p3 && !turn2)
			p4= true;
		
		if(role1.contains(p)&&r2)
			r3 = true;
		
		if(role1.contains(p)&&r1)
			r2 = true;
		
		if(role1.contains(p))
			r1 = true;
	}
	
	public void discardCard()
	{
		discardCardColor = color;
		color = Color.black;
	}
	
	@SuppressWarnings("static-access")
	public void paint(Graphics g)
	{
		RenderUtil r = new RenderUtil();
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0,2000,1500);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		if(!endGame)
			g.drawString(("Player "+pturn+"'s Turn"), 1250, 50);
		else
			g.drawString("Game Over", 1250,50);
		g.drawString("Objective Cards", 1480, 75);
		
		for(Hexagon h: b1)
		{
			g.setColor(h.getColor());
			Polygon p = new Polygon(h.xpoints(), h.ypoints(), 6);
			g.fillPolygon(p);
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
			if(h.getSettlement())
			{
				p = h.getHex();
				g.setColor(h.getSettledColor());
				g.fillPolygon(p);
				g.setColor(Color.BLACK);
				g.drawPolygon(p);
			}
			
			if(h.getExtra())
			{
				g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				g.drawString(h.getString(), h.xpoints()[2]+3 ,h.ypoints()[2]-10);
			}
		}
		
		for(Hexagon h : b2)
		{
			g.setColor(h.getColor());
			Polygon p = new Polygon(h.xpoints(), h.ypoints(), 6);
			g.fillPolygon(p);
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
			if(h.getSettlement())
			{
				p = h.getHex();
				g.setColor(h.getSettledColor());
				g.fillPolygon(p);
				g.setColor(Color.BLACK);
				g.drawPolygon(p);
			}
			
			if(h.getExtra())
			{
				g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				g.drawString(h.getString(), h.xpoints()[2]+3 ,h.ypoints()[2]-10);
			}
		}
		
		for(Hexagon h : b3)
		{
			g.setColor(h.getColor());
			Polygon p = new Polygon(h.xpoints(), h.ypoints(), 6);
			g.fillPolygon(p);
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
			if(h.getSettlement())
			{
				p = h.getHex();
				g.setColor(h.getSettledColor());
				g.fillPolygon(p);
				g.setColor(Color.BLACK);
				g.drawPolygon(p);
			}
			
			if(h.getExtra())
			{
				g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				g.drawString(h.getString(), h.xpoints()[2]+3 ,h.ypoints()[2]-10);
			}
		}
		
		for(Hexagon h : b4)
		{
			g.setColor(h.getColor());
			Polygon p = new Polygon(h.xpoints(), h.ypoints(), 6);
			g.fillPolygon(p);
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
			if(h.getSettlement())
			{
				p = h.getHex();
				g.setColor(h.getSettledColor());
				g.fillPolygon(p);
				g.setColor(Color.BLACK);
				g.drawPolygon(p);
			}
			
			if(h.getExtra())
			{
				g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				g.drawString(h.getString(), h.xpoints()[2]+3 ,h.ypoints()[2]-10);
			}
		}
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		
		g.setColor(Color.GRAY);
		g.fillPolygon(deck);
		g.setColor(Color.BLACK);
		g.drawString("Draw", 1270, 170);
		g.drawString("Card", 1270, 200);
		
		g.setColor(color);
		g.fillPolygon(card);
		
		g.setColor(discardCardColor);
		g.fillPolygon(discardCard);
		
		g.setColor(Color.ORANGE);
		g.fillPolygon(Player1);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString(("x "+P1Houses+" "), 1270, 800);
		Polygon p21 = r.createHexagon(1345, 795, 16);
		g.fillPolygon(p21);
	    g.setColor(Color.BLACK);
	    if(p1)
	    	g.drawString("Player 1", 1270, 755);
	    else
	    	g.drawString("P1 Join", 1270, 755);
		
		g.setColor(Color.CYAN);
		g.fillPolygon(Player2);
		g.drawString(("x "+P2Houses+" "), 1480, 800);
		p21 = r.createHexagon(1550, 795, 16);
		g.fillPolygon(p21);
		g.setColor(Color.BLACK);
		if(p2)
			g.drawString("Player 2", 1480, 755);
		else
			g.drawString("P2 Join", 1480, 755);
		
		g.setColor(Color.WHITE);
		g.fillPolygon(Player3);
		g.drawString(("x "+P3Houses+" "), 1270, 920);
		p21 = r.createHexagon(1345, 915, 16);
		g.fillPolygon(p21);
		g.setColor(Color.BLACK);
		if(p3)
			g.drawString("Player 3", 1270, 875);
		else
			g.drawString("P3 Join", 1270, 875);
		
		g.setColor(Color.magenta);
		g.fillPolygon(Player4);
		g.drawString(("x "+P4Houses+" "), 1480, 920);
		p21 = r.createHexagon(1550, 915, 16);
		g.fillPolygon(p21);
		g.setColor(Color.BLACK);
		if(p4)
			g.drawString("Player 4", 1480, 875);
		else
			g.drawString("P4 Join", 1480, 875);
		
		g.setColor(Color.black);
		g.drawPolygon(role1);
		
		
		g.setColor(Color.white);
		if(r1)
			g.drawString(roles.get(0), 1487, 120);
		else
			g.drawString("Role 1", 1487, 120);
		
		if(r2)
			g.drawString(roles.get(1), 1487, 160);
		else
			g.drawString("Role 2", 1487, 160);
		
		if(r3)
			g.drawString(roles.get(2), 1487, 200);
		else
			g.drawString("Role 3", 1487, 200);
		
		g.drawRect(extra1.x, extra1.y, extra1.height, extra1.width);
		g.drawRect(extra2.x, extra2.y, extra2.height, extra2.width);
		g.drawRect(extra3.x, extra3.y, extra3.height, extra3.width);
		g.drawRect(extra4.x, extra4.y, extra4.height, extra4.width);
		g.drawRect(extra5.x, extra5.y, extra5.height, extra5.width);
		g.drawRect(extra6.x, extra6.y, extra6.height, extra6.width);
		g.drawRect(extra7.x, extra7.y, extra7.height, extra7.width);
		g.drawRect(extra8.x, extra8.y, extra8.height, extra8.width);
		
		g.drawString("Oracle : 0", extra1.x+25, extra1.y+30);
		g.drawString("Farm : 0", extra2.x+25, extra2.y+30);
		g.drawString("Oasis : 0", extra3.x+25, extra3.y+30);
		g.drawString("Tower : 0", extra4.x+25, extra4.y+30);
		g.drawString("Tavern : 0", extra5.x+25, extra5.y+30);
		g.drawString("Barn : 0", extra6.x+25, extra6.y+30);
		g.drawString("Harbor : 0", extra7.x+25, extra7.y+30);
		g.drawString("Paddock : 0", extra8.x+25, extra8.y+30);
	}
	
	public Color getColor(String s)
	{
		Color c = Color.GRAY;
		
		if(s.contains("Dark"))
			c = Color.GREEN.darker().darker();
		else if(s.contains("Green"))
			c = Color.GREEN;
		else if(s.contains("Blue"))
			c = Color.BLUE;
		else if(s.contains("Pink"))
			c = Color.PINK;
		else if(s.contains("Gray"))
			c = Color.GRAY;
		else if(s.contains("Yellow"))
			c = Color.YELLOW;
		else if(s.contains("Brown"))
			c = new Color(139, 69, 13);
		else
			c = Color.WHITE;
		
		return c;
	}
	
	public void alterColor(int player)
	{
		switch(player)
		{
			case 1: col= Color.ORANGE;break;
			case 2: col= Color.CYAN;break;
			case 3: col= Color.WHITE;break;
			case 4: col= Color.MAGENTA; break;
		}
	}
	
	public void resetDeck()
	{
		for(int i = 0; i <5; i++)
			for(int j = 0; j<5; j++)
				colors.add(i);
	}

	public void endGame()
	{
		endGame = true;
		
		Castles();
		
		if(roles.contains("Fishermen"))
			Fisherman();
		
		if(roles.contains("Miners"))
			Miners();
		
		if(roles.contains("Discoverers"))
			Discoverers();
		
		if(roles.contains("Workers"))
			Workers();
		
		if(roles.contains("Knights"))
			Knights();
		
		if(roles.contains("Farmers"))
			Farmers();
		
		if(roles.contains("Lords"))
			Lords();
		
		if(roles.contains("Hermits"))
		{
			ArrayList<Hexagon> visited = new ArrayList<Hexagon>();
			for(int r = 0; r<20; r++)
			{
				for(int c = 0; c<20;c++)
				{
					Hexagon h = Hexadjacent[r][c];
					
					if(h.getSettlement() && !visited.contains(h))
					{
						visited.add(h);
						if(h.getSettledColor() == Color.ORANGE)
							P1Points++;
						else if(h.getSettledColor() == Color.CYAN)
							P2Points++;
						else if(h.getSettledColor() == Color.WHITE)
							P3Points++;
						else if(h.getSettledColor() == Color.MAGENTA)
							P4Points++;
						visited = Hermits(h, visited);
					}
				}
			}
		}
			
		if(roles.contains("Citizens"))
		{
			ArrayList<Hexagon> visited = new ArrayList<Hexagon>();
			int p1max, p2max, p3max, p4max;
			p1max = p2max = p3max = p4max =0;
			
			for(int r = 0; r<20; r++)
			{
				for(int c = 0; c<20;c++)
				{
					Hexagon h = Hexadjacent[r][c];
					
					if(h.getSettlement() && !visited.contains(h))
					{
						int current = visited.size();
						visited.add(h);
						visited = Hermits(h, visited);
						int after = visited.size();
						
						int num = after-current;
						
						if(h.getSettledColor() == Color.ORANGE)
							if(num > p1max)
								p1max = num;
						else if(h.getSettledColor() == Color.CYAN)
							if(num > p2max)
								p2max = num;
						else if(h.getSettledColor() == Color.WHITE)
							if(num > p3max)
								p3max = num;
						else if(h.getSettledColor() == Color.MAGENTA)
							if(num > p4max)
								p4max = num;
					}
				}
			}
			
			P1Points+=(p1max/2);
			P2Points+=(p2max/2);
			P3Points+=(p3max/2);
			P4Points+=(p4max/2);
		}
		
		if(roles.contains("Merchants"))
		{
			ArrayList<Hexagon> visited = new ArrayList<Hexagon>();
			for(int r = 0; r<20; r++)
			{
				for(int c = 0; c<20;c++)
				{
					Hexagon h = Hexadjacent[r][c];
					
					ArrayList<Hexagon> WhiteVisited = new ArrayList<Hexagon>();
					if(h.getColor() == Color.WHITE)
					{
						MerchTurn = false;
						MerchAdjacent = false;
						visited = Merchants(h, visited, WhiteVisited);
					}
				}
			}
		}
		
		System.out.println("P1 Points: "+P1Points);
		System.out.println("P2 Points: "+P2Points);
		System.out.println("P3 Points: "+P3Points);
		System.out.println("P4 Points: "+P4Points);
	}
	
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
							if(h.getSettledColor() == Color.ORANGE)
								P1Points++;
							else if(h.getSettledColor() == Color.CYAN)
								P2Points++;
							else if(h.getSettledColor() == Color.WHITE)
								P3Points++;
							else if(h.getSettledColor() == Color.MAGENTA)
								P4Points++;
							
							valid = true;
						}
					}
				}
			}
		}
	}
	
	public void Miners()
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
						if(hex.getColor() == Color.GRAY && !valid)
						{
							if(h.getSettledColor() == Color.ORANGE)
								P1Points++;
							else if(h.getSettledColor() == Color.CYAN)
								P2Points++;
							else if(h.getSettledColor() == Color.WHITE)
								P3Points++;
							else if(h.getSettledColor() == Color.MAGENTA)
								P4Points++;
							
							valid = true;
						}
					}
				}
			}
		}
	}
	
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
					if(h.getSettledColor() == Color.ORANGE && !P1)
					{
						P1Points++;
						P1 = true;		
					}
					else if(h.getSettledColor() == Color.CYAN && !P2)
					{
						P2Points++;
						P2 = true;		
					}
					else if(h.getSettledColor() == Color.WHITE && !P3)
					{
						P3Points++;
						P3 = true;		
					}
					else if(h.getSettledColor() == Color.MAGENTA && !P4)
					{
						P4Points++;
						P4 = true;		
					}
				}
			}
		}
	}
	
	public void Workers()
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
						if(hex.getColor() == Color.WHITE && !valid)
						{
							if(h.getSettledColor() == Color.ORANGE)
								P1Points++;
							else if(h.getSettledColor() == Color.CYAN)
								P2Points++;
							else if(h.getSettledColor() == Color.WHITE)
								P3Points++;
							else if(h.getSettledColor() == Color.MAGENTA)
								P4Points++;
							
							valid = true;
						}
					}
				}
			}
		}
	}
	
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
					if(h.getSettledColor() == Color.ORANGE)
					{
						P1++;
					}
					else if(h.getSettledColor() == Color.CYAN)
					{
						P2++;
					}
					else if(h.getSettledColor() == Color.WHITE)
					{
						P3++;
					}
					else if(h.getSettledColor() == Color.MAGENTA)
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
					if(h.getSettledColor() == Color.ORANGE)
						P1Points+=2;
			}
		}
		
		for(int r = P2Row; r<=P2Row; r++)
		{
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				if(Hexadjacent[r][c].getSettlement())
					if(h.getSettledColor() == Color.CYAN)
						P2Points+=2;
			}
		}
		
		for(int r = P3Row; r<=P3Row; r++)
		{
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				if(Hexadjacent[r][c].getSettlement())
					if(h.getSettledColor() == Color.WHITE)
						P3Points+=2;
			}
		}
		
		for(int r = P4Row; r<=P4Row; r++)
		{
			for(int c =0; c<20; c++)
			{
				Hexagon h = Hexadjacent[r][c];
				if(Hexadjacent[r][c].getSettlement())
					if(h.getSettledColor() == Color.MAGENTA)
						P4Points+=2;
			}
		}
	}
	
	public void Lords()
	{
		int p1houses, p2houses, p3houses, p4houses, max, max2;
		p1houses = p2houses = p3houses = p4houses = max = max2 = 0;
		
		for(Hexagon h : b1)
		{
			if(h.getSettlement())
			{
				if(h.getSettledColor() == Color.ORANGE)
				{
					p1houses++;
				}
				else if(h.getSettledColor() == Color.CYAN)
				{
					p2houses++;
				}
				else if(h.getSettledColor() == Color.WHITE)
				{
					p3houses++;
				}
				else if(h.getSettledColor() == Color.MAGENTA)
				{
					p4houses++;	
				}
			}
		}
		if(p1houses > max)
			max = p1houses;
		if(p2houses > max)
			max = p2houses;
		if(p3houses > max)
			max = p3houses;
		if(p4houses > max)
			max = p4houses;
		
		if(p1houses > max2 && p1houses != max)
			max2 = p1houses;
		if(p2houses > max2 && p2houses != max)
			max2 = p2houses;
		if(p3houses > max2 && p3houses != max)
			max2 = p3houses;
		if(p4houses > max2 && p4houses != max)
			max2 = p4houses;
		
		if(p1houses == max && max!=0)
			P1Points+=12;
		if(p2houses == max && max!=0)
			P2Points+=12;
		if(p3houses == max && max!=0)
			P3Points+=12;
		if(p4houses == max && max!=0)
			P4Points+=12;
		
		if(p1houses == max2 && max2 !=0)
			P1Points+=6;
		if(p2houses == max2 && max2 !=0)
			P2Points+=6;
		if(p3houses == max2 && max2 !=0)
			P3Points+=6;
		if(p4houses == max2 && max2 !=0)
			P4Points+=6;

		p1houses = p2houses = p3houses = p4houses = max = max2 = 0;
		for(Hexagon h : b2)
		{
			if(h.getSettlement())
			{
				if(h.getSettledColor() == Color.ORANGE)
				{
					p1houses++;
				}
				else if(h.getSettledColor() == Color.CYAN)
				{
					p2houses++;
				}
				else if(h.getSettledColor() == Color.WHITE)
				{
					p3houses++;
				}
				else if(h.getSettledColor() == Color.MAGENTA)
				{
					p4houses++;	
				}
			}
		}
		if(p1houses > max)
			max = p1houses;
		if(p2houses > max)
			max = p2houses;
		if(p3houses > max)
			max = p3houses;
		if(p4houses > max)
			max = p4houses;
		
		if(p1houses > max2 && p1houses != max)
			max2 = p1houses;
		if(p2houses > max2 && p2houses != max)
			max2 = p2houses;
		if(p3houses > max2 && p3houses != max)
			max2 = p3houses;
		if(p4houses > max2 && p4houses != max)
			max2 = p4houses;
		
		if(p1houses == max && max !=0)
			P1Points+=12;
		if(p2houses == max && max !=0)
			P2Points+=12;
		if(p3houses == max && max !=0)
			P3Points+=12;
		if(p4houses == max && max !=0)
			P4Points+=12;
		
		if(p1houses == max2 && max2 !=0)
			P1Points+=6;
		if(p2houses == max2 && max2 !=0)
			P2Points+=6;
		if(p3houses == max2 && max2 !=0)
			P3Points+=6;
		if(p4houses == max2 && max2 !=0)
			P4Points+=6;
		
		p1houses = p2houses = p3houses = p4houses = max = max2 = 0;
		for(Hexagon h : b3)
		{
			if(h.getSettlement())
			{
				if(h.getSettledColor() == Color.ORANGE)
				{
					p1houses++;
				}
				else if(h.getSettledColor() == Color.CYAN)
				{
					p2houses++;
				}
				else if(h.getSettledColor() == Color.WHITE)
				{
					p3houses++;
				}
				else if(h.getSettledColor() == Color.MAGENTA)
				{
					p4houses++;	
				}
			}
		}
		if(p1houses > max)
			max = p1houses;
		if(p2houses > max)
			max = p2houses;
		if(p3houses > max)
			max = p3houses;
		if(p4houses > max)
			max = p4houses;
		
		if(p1houses > max2 && p1houses != max)
			max2 = p1houses;
		if(p2houses > max2 && p2houses != max)
			max2 = p2houses;
		if(p3houses > max2 && p3houses != max)
			max2 = p3houses;
		if(p4houses > max2 && p4houses != max)
			max2 = p4houses;
		
		if(p1houses == max && max !=0)
			P1Points+=12;
		if(p2houses == max && max !=0)
			P2Points+=12;
		if(p3houses == max && max !=0)
			P3Points+=12;
		if(p4houses == max && max !=0)
			P4Points+=12;
		
		if(p1houses == max2 && max2 !=0)
			P1Points+=6;
		if(p2houses == max2 && max2 !=0)
			P2Points+=6;
		if(p3houses == max2 && max2 !=0)
			P3Points+=6;
		if(p4houses == max2 && max2 !=0)
			P4Points+=6;
		
		p1houses = p2houses = p3houses = p4houses = max = max2 = 0;
		for(Hexagon h : b4)
		{
			if(h.getSettlement())
			{
				if(h.getSettledColor() == Color.ORANGE)
				{
					p1houses++;
				}
				else if(h.getSettledColor() == Color.CYAN)
				{
					p2houses++;
				}
				else if(h.getSettledColor() == Color.WHITE)
				{
					p3houses++;
				}
				else if(h.getSettledColor() == Color.MAGENTA)
				{
					p4houses++;	
				}
			}
		}
		if(p1houses > max)
			max = p1houses;
		if(p2houses > max)
			max = p2houses;
		if(p3houses > max)
			max = p3houses;
		if(p4houses > max)
			max = p4houses;
		
		if(p1houses > max2 && p1houses != max)
			max2 = p1houses;
		if(p2houses > max2 && p2houses != max)
			max2 = p2houses;
		if(p3houses > max2 && p3houses != max)
			max2 = p3houses;
		if(p4houses > max2 && p4houses != max)
			max2 = p4houses;
		
		if(p1houses == max && max !=0)
			P1Points+=12;
		if(p2houses == max && max !=0)
			P2Points+=12;
		if(p3houses == max && max !=0)
			P3Points+=12;
		if(p4houses == max && max !=0)
			P4Points+=12;
		
		if(p1houses == max2 && max2 !=0)
			P1Points+=6;
		if(p2houses == max2 && max2 !=0)
			P2Points+=6;
		if(p3houses == max2 && max2 !=0)
			P3Points+=6;
		if(p4houses == max2 && max2 !=0)
			P4Points+=6;
	}
	
	public void Farmers()
	{
		int p1houses, p2houses, p3houses, p4houses;
		p1houses = p2houses = p3houses = p4houses = 0;
		
		for(Hexagon h : b1)
		{
			if(h.getSettlement())
			{
				if(h.getSettledColor() == Color.ORANGE)
				{
					p1houses++;
				}
				else if(h.getSettledColor() == Color.CYAN)
				{
					p2houses++;
				}
				else if(h.getSettledColor() == Color.WHITE)
				{
					p3houses++;
				}
				else if(h.getSettledColor() == Color.MAGENTA)
				{
					p4houses++;	
				}
			}
		}
		
		int p1houses2, p2houses2, p3houses2, p4houses2;
		p1houses2 = p2houses2 = p3houses2 = p4houses2 = 0;
		for(Hexagon h : b2)
		{
			if(h.getSettlement())
			{
				if(h.getSettledColor() == Color.ORANGE)
				{
					p1houses2++;
				}
				else if(h.getSettledColor() == Color.CYAN)
				{
					p2houses2++;
				}
				else if(h.getSettledColor() == Color.WHITE)
				{
					p3houses2++;
				}
				else if(h.getSettledColor() == Color.MAGENTA)
				{
					p4houses2++;	
				}
			}
		}
		
		int p1houses3, p2houses3, p3houses3, p4houses3;
		p1houses3 = p2houses3 = p3houses3 = p4houses3 = 0;
		for(Hexagon h : b3)
		{
			if(h.getSettlement())
			{
				if(h.getSettledColor() == Color.ORANGE)
				{
					p1houses3++;
				}
				else if(h.getSettledColor() == Color.CYAN)
				{
					p2houses3++;
				}
				else if(h.getSettledColor() == Color.WHITE)
				{
					p3houses3++;
				}
				else if(h.getSettledColor() == Color.MAGENTA)
				{
					p4houses3++;	
				}
			}
		}
		
		int p1houses4, p2houses4, p3houses4, p4houses4;
		p1houses4 = p2houses4 = p3houses4 = p4houses4 = 0;
		for(Hexagon h : b4)
		{
			if(h.getSettlement())
			{
				if(h.getSettledColor() == Color.ORANGE)
				{
					p1houses4++;
				}
				else if(h.getSettledColor() == Color.CYAN)
				{
					p2houses4++;
				}
				else if(h.getSettledColor() == Color.WHITE)
				{
					p3houses4++;
				}
				else if(h.getSettledColor() == Color.MAGENTA)
				{
					p4houses4++;	
				}
			}
		}
		
		int min1, min2, min3, min4;
		min1 = min2 = min3 = min4 = Integer.MAX_VALUE;
		
		boolean p1, p2, p3, p4;
		p1 = p2= p3= p4 = false;
		
		if(p1houses < min1 && p1houses != 0)
			min1 = p1houses;
		if(p1houses2 < min1 && p1houses2 != 0)
			min1 = p1houses2;
		if(p1houses3 < min1 && p1houses3 != 0)
			min1 = p1houses3;
		if(p1houses4 < min1 && p1houses4 != 0)
			min1 = p1houses4;
		
		if(p1houses == min1 && !p1)
		{
			P1Points+= (p1houses*3);
			p1 = true;
		}
		if(p1houses2 == min1 && !p1)
		{
			P1Points+= (p1houses2*3);
			p1 = true;
		}
		
		if(p1houses3 == min1 && !p1)
		{
			P1Points+= (p1houses3*3);
			p1 = true;
		}
		if(p1houses4 == min1 && !p1)
		{
			P1Points+= (p1houses4*3);
			p1 = true;
		}
		
		//Player 2
		if(p2houses < min2 && p2houses != 0)
			min2 = p2houses;
		if(p2houses2 < min2 && p2houses2 != 0)
			min2 = p2houses2;
		if(p2houses3 < min2 && p2houses3 != 0)
			min2 = p2houses3;
		if(p2houses4 < min2 && p2houses4 != 0)
			min2 = p2houses4;
		
		if(p2houses == min2 && !p2)
		{
			P2Points+= (p2houses*3);
			p2 = true;
		}
		if(p2houses2 == min2 && !p2)
		{
			P2Points+= (p2houses2*3);
			p2 = true;
		}
		
		if(p2houses3 == min2 && !p2)
		{
			P2Points+= (p2houses3*3);
			p2 = true;
		}
		if(p2houses4 == min2 && !p2)
		{
			P2Points+= (p2houses4*3);
			p2 = true;
		}
		
		//Player 3
		if(p3houses < min3 && p3houses != 0)
			min3 = p3houses;
		if(p3houses2 < min3 && p3houses2 != 0)
			min3 = p3houses2;
		if(p3houses3 < min3 && p3houses3 != 0)
			min3 = p3houses3;
		if(p3houses4 < min3 && p3houses4 != 0)
			min3 = p3houses4;
		
		if(p3houses == min3 && !p3)
		{
			P3Points+= (p3houses*3);
			p3 = true;
		}
		if(p3houses2 == min3 && !p3)
		{
			P3Points+= (p3houses2*3);
			p3 = true;
		}
		
		if(p3houses3 == min3 && !p3)
		{
			P3Points+= (p3houses3*3);
			p3 = true;
		}
		if(p3houses4 == min3 && !p3)
		{
			P3Points+= (p3houses4*3);
			p3 = true;
		}
		
		//Player 4
		if(p4houses < min4 && p4houses != 0)
			min4 = p4houses;
		if(p4houses2 < min4 && p4houses2 != 0)
			min4 = p4houses2;
		if(p4houses3 < min4 && p4houses3 != 0)
			min4 = p4houses3;
		if(p4houses4 < min4 && p4houses4 != 0)
			min4 = p4houses4;
		
		if(p4houses == min4 && !p4)
		{
			P4Points+= (p4houses*3);
			p4 = true;
		}
		if(p4houses2 == min4 && !p4)
		{
			P4Points+= (p4houses2*3);
			p4 = true;
		}
		
		if(p4houses3 == min4 && !p4)
		{
			P4Points+= (p4houses3*3);
			p4 = true;
		}
		if(p4houses4 == min4 && !p4)
		{
			P4Points+= (p4houses4*3);
			p4 = true;
		}
	}
	
	public void Castles()
	{
		for(int row = 0; row<20; row++)
		{
			for(int col = 0; col<20; col++)
			{
				Hexagon h = Hexadjacent[row][col];
				
				if(h.getColor() == Color.WHITE && h.getString().equals("Castle"))
				{
					boolean p1, p2 ,p3, p4;
					p1 = p2 =p3 = p4 = false;
					ArrayList<Hexagon> adj = adjacent(h);
					for(Hexagon hex: adj)
					{
						if(hex.getSettlement())
						{
							if(hex.getSettledColor() == Color.ORANGE && !p1)
							{
								P1Points+=3;
								p1 = true;
							}
							else if(hex.getSettledColor() == Color.CYAN && !p2)
							{
								P2Points+=3;
								p2 = true;
							}
							else if(hex.getSettledColor() == Color.WHITE && !p3)
							{
								P3Points+=3;
								p3 = true;
							}
							else if(hex.getSettledColor() == Color.MAGENTA && !p4)
							{
								P4Points+=3;
								p4 = true;
							}
						}
					}
				}
			}
		}
	}
	
	public ArrayList<Hexagon> Merchants(Hexagon h, ArrayList<Hexagon> visited, ArrayList<Hexagon> WhiteVisited)
	{
		ArrayList<Hexagon> adj= adjacent(h);
		
		for(Hexagon hex: adj)
		{
			if(hex.getSettlement())
			{
				if(hex.getSettledColor() == MerchColor && !visited.contains(hex) && MerchTurn)
				{
					visited.add(hex);
					Merchants(hex, visited, WhiteVisited);
				}
				
				else if(h.getColor() == Color.WHITE && !MerchTurn)
				{
					MerchTurn = true;
					MerchColor = hex.getSettledColor();
					WhiteVisited.add(h);
					Merchants(hex, visited, WhiteVisited);
				}
				
				else if(hex.getSettledColor() != MerchColor && h.getColor() == Color.WHITE && MerchTurn)
				{
					MerchColor = hex.getSettledColor();
					MerchAdjacent = false;
					WhiteVisited = new ArrayList<Hexagon>();
					WhiteVisited.add(h);
				}
			}
			
			else if(hex.getColor() == Color.WHITE)
			{
				if(!MerchAdjacent && !WhiteVisited.contains(hex))
				{
					if(h.getSettledColor() == Color.ORANGE)
						P1Points+=8;
					else if(h.getSettledColor() == Color.CYAN)
						P2Points+=8;
					else if(h.getSettledColor() == Color.WHITE)
						P3Points+=8;
					else if(h.getSettledColor() == Color.MAGENTA)
						P4Points+=8;
					
					MerchAdjacent = true;
				}
				
				else if(MerchAdjacent && !WhiteVisited.contains(hex))
				{
					if(h.getSettledColor() == Color.ORANGE)
						P1Points+=4;
					else if(h.getSettledColor() == Color.CYAN)
						P2Points+=4;
					else if(h.getSettledColor() == Color.WHITE)
						P3Points+=4;
					else if(h.getSettledColor() == Color.MAGENTA)
						P4Points+=4;
						
				}
				
				WhiteVisited.add(hex);
				Merchants(hex, visited, WhiteVisited);
			}
		}
		
		return visited;
	}
	
	public ArrayList<Hexagon> Hermits(Hexagon h, ArrayList<Hexagon> visited)
	{
		ArrayList<Hexagon> adj= adjacent(h);
		
		for(Hexagon hex: adj)
		{
			if(hex.getSettlement())
			{
				if(hex.getSettledColor() == h.getSettledColor() && !visited.contains(hex))
				{
					visited.add(hex);
					Hermits(hex, visited);
				}
			}
		}
		
		return visited;
	}
		
	public ArrayList<Hexagon> Citizens(Hexagon h, ArrayList<Hexagon> visited)
	{
		ArrayList<Hexagon> adj= adjacent(h);
		
		for(Hexagon hex: adj)
		{
			if(hex.getSettlement())
			{
				if(hex.getSettledColor() == h.getSettledColor() && !visited.contains(hex))
				{
					visited.add(hex);
					Citizens(hex, visited);
				}
			}
		}
		
		return visited;
	}
	
	public boolean PTurnHouses()
	{
		if(pturn == 1)
		{
			if(P1Houses > 0)
				return true;
			else
				return false;
		}
		if(pturn == 2)
		{
			if(P2Houses > 0)
				return true;
			else
				return false;
		}
		if(pturn == 3)
		{
			if(P3Houses > 0)
				return true;
			else
				return false;
		}
		if(pturn == 4)
		{
			if(P4Houses > 0)
				return true;
			else
				return false;
		}
		return false;
		
	}
	
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
}
