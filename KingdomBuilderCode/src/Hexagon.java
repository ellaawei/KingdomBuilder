import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

public class Hexagon 
{
	private int[] x, y;
	public Color color;
	private int cx, cy; // center coordinate (x, y)
	public boolean bOccupied;
	public HexType neighborTokenType = HexType.None;
	public int tokenRow, tokenCol;
	HexType type;
	Hexagon settlement;
	public int row;
	public int col;
	
	public void setNeighborToken(HexType t, int row, int col)
	{
		settlement = null;
		neighborTokenType = t;
		tokenRow = row;
		tokenCol = col;
	}
	public enum HexType {None, Castle, Oracle, Tower, Harbor, Barn, Tavern, Oasis, Paddock, Farm };
	public Hexagon(Polygon p)
	{
		type = HexType.None;
		x = p.xpoints;
		y = p.ypoints;
		bOccupied = false;
		
		cx = (x[0] + x[2]) / 2;
		cy = (y[0] + y[5]) / 2;
	}
	
	public int[] xpoints()
	{
		return x;
	}
	
	public int[] ypoints()
	{
		return y;
	}
	
	public void setColor(String sc)
	{
		switch (sc)
		{
		case "Green":
			color = Color.GREEN; break;
		case "Yellow":
			color = Color.YELLOW; break;
		case "Pink":
			color = Color.PINK; break;
		case "Blue":
			color = Color.BLUE; break;
		case "Gray":
			color = Color.GRAY; break;
		case "Brown":
			color = new Color(102, 51, 0); break;
		case "Dark Green":
			color = new Color(0, 102, 0); break;
		default:
			if (sc.startsWith("Special"))
			{
				String s = sc.substring("Special ".length());
				type = HexType.valueOf(s);
				if (type != Hexagon.HexType.Castle)
				{
					ArrayList<Hexagon> list = Token.boardTokens.get(type);
					if (list == null)
						list = new ArrayList<Hexagon>();
					list.add(this);
					Token.boardTokens.put(type, list);
				}
			}
		}
	}
	public Color getColor()
	{
		return color;
	}
	
	public HexType getType()
	{
		return type;
	}
	
	public Hexagon getHouse(Color terrainColor, Color settlementColor)
	{
		if(!terrainColor.equals(color)) {
			return null;
		}
		bOccupied = true;
		int[] hx = { (x[0]+cx)/2, (x[1]+cx)/2, (x[2]+cx)/2, (x[3]+cx)/2, (x[4]+cx)/2, (x[5]+cx)/2 };
		int[] hy = { (y[0]+cy)/2, (y[1]+cy)/2, (y[2]+cy)/2, (y[3]+cy)/2, (y[4]+cy)/2, (y[5]+cy)/2 };
		
		Polygon p = new Polygon(hx, hy, 6);
		settlement = new Hexagon(p);
		settlement.color = settlementColor;
		return settlement;
	}
	
	public HexType getHexType()
	{
		if (type != HexType.Castle && type != HexType.None)
		{
			return type;
		}
		
		return HexType.None;
	}
	
	public void DrawAsActive(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.RED);
        g2d.drawPolygon(x, y, 6);
	}
	
	public void DrawTokenNumber(int num, Graphics g)
	{
		if (num > 0)
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.drawString(""+num, cx-5, cy+15);
		}
	}
}
