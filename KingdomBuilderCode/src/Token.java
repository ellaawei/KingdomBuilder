import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Token 
{
	public Hexagon.HexType type;
	private BufferedImage image;
	private int x, y;
	public Polygon polygon;
	public boolean bSelectedToken = false;
	public Player player;
	public boolean bFromThisTurn = false;
	public int count = 0;
	public static Map<Hexagon.HexType, ArrayList<Hexagon>> boardTokens = new HashMap<Hexagon.HexType, ArrayList<Hexagon>>();

	public Token(Hexagon.HexType type) 
	{
		this.type = type;
		player = null;
		
		try
		{
			switch (type)
			{ 
			case Oracle:
				image = ImageIO.read(this.getClass().getResource("/pictures/locationhexoracle.png"));
				break;
			case Tower:
				image = ImageIO.read(this.getClass().getResource("/pictures/locationhextower.png"));
				break;
			case Harbor:
				image = ImageIO.read(this.getClass().getResource("/pictures/locationhexharbor.png"));
				break;
			case Barn:
				image = ImageIO.read(this.getClass().getResource("/pictures/loactionhexbarn.png"));
				break;
			case Tavern:
				image = ImageIO.read(this.getClass().getResource("/pictures/locationhextavern.png"));
				break;
			case Oasis:
				image = ImageIO.read(this.getClass().getResource("/pictures/locationhexoasis.png"));
				break;
			case Paddock:
				image = ImageIO.read(this.getClass().getResource("/pictures/locationhexpaddock.png"));
				break;
			case Farm:
				image = ImageIO.read(this.getClass().getResource("/pictures/locationhexfarm.png"));
				break;
			}	
		}
		catch (Exception e) {}
	}
	
	public static Token getToken(Hexagon.HexType type)
	{
		Token t = null;
		int count = Board.listTokens.get(type);
		if (count < 2)
		{
			t = new Token(type);
			count++;
			Board.listTokens.put(type, count);
		}
		
		return t;
	}
	public void setCoordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setPlayer(Player p)
	{
		player = p;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, x, y, 40, 50, null);
		
		if (bSelectedToken)
		{
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.setStroke(new BasicStroke(3));
	        g2d.setColor(Color.YELLOW);
	        g2d.drawPolygon(polygon.xpoints, polygon.ypoints, 6);
		}
		
		for (Map.Entry<Hexagon.HexType, ArrayList<Hexagon>> entry : boardTokens.entrySet()) 
		{
			Hexagon.HexType type = entry.getKey();
			ArrayList<Hexagon> list = entry.getValue();
			
			for (Hexagon h : list)
			{
				int num = Board.listTokens.get(type);
				h.DrawTokenNumber(num, g);
			}
		}
	}
}
