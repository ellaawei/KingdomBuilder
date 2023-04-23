import java.awt.Color;
import java.awt.Polygon;

public class Hexagon 
{
	private int[] x, y;
	public Color color;
	private int cx, cy; // center coordinate (x, y)
	
	public Hexagon(Polygon p)
	{
		x = p.xpoints;
		y = p.ypoints;
		
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
		}
	}
	public Color getColor()
	{
		return color;
	}
	
	public Hexagon getHouse(Color terrainColor, Color settlementColor)
	{
		if(!terrainColor.equals(color)) {
			return null;
		}
		int[] hx = { (x[0]+cx)/2, (x[1]+cx)/2, (x[2]+cx)/2, (x[3]+cx)/2, (x[4]+cx)/2, (x[5]+cx)/2 };
		int[] hy = { (y[0]+cy)/2, (y[1]+cy)/2, (y[2]+cy)/2, (y[3]+cy)/2, (y[4]+cy)/2, (y[5]+cy)/2 };
		
		Polygon p = new Polygon(hx, hy, 6);
		Hexagon h = new Hexagon(p);
		h.color = settlementColor;
		return h;
	}
}
