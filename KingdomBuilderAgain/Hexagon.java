import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Hexagon
{
	private int[] x, y;
	private Color sc,c;
	private int tiles;
	public String Special;
	private boolean settled, Extra;
	private Polygon hex;
	boolean darker;
	
	public Hexagon(Polygon p, Color c, Polygon hex)
	{
		x = p.xpoints;
		y = p.ypoints;
		this.c = c;
		settled= false;
		this.setHex(hex);
		sc= Color.ORANGE;
		Special = null;
		Extra = false;
		tiles = 2;
	}
	
	public void paint(Graphics g)
	{
	
	}
	
	public Color getColor()
	{
		if(darker)
			return c.darker();
		else
			return c;
	}
	
	public void setDarker(boolean d)
	{
		darker = d;
	}
	
	public boolean getDarker()
	{
		return darker;
	}
	
	public int[] xpoints()
	{
		return x;
	}
	
	public int[] ypoints()
	{
		return y;
	}
	
	public void setSettlement(boolean b)
	{
		settled= b;
	}

	public boolean getSettlement() {
		// TODO Auto-generated method stub
		return settled;
	}

	public void setColor(Color blue) {
		// TODO Auto-generated method stub
		c= blue;
		
	}

	public Polygon getHex() {
		return hex;
	}

	public void setHex(Polygon hex) {
		this.hex = hex;
	}
	
	public void setString(String x)
	{
		Special = x;
		Extra = true;
	}
	
	public String getString()
	{
		return Special;
	}
	
	public boolean getExtra()
	{
		return Extra;
	}
	
	public void setSettledColor(Color colo){sc= colo;}
	
	public Color getSettledColor(){return sc;}
	
	public boolean equals(Hexagon h)
	{
		int[] xpoints = h.xpoints();
		int[] ypoints = h.ypoints();
		boolean b = true;
		int x1 = 0;
		
		for(Integer i : x)
		{
			if(i != xpoints[x1])
			{
				b = false;
				return b;
			}
			x1++;
		}
		
		x1 =0;
		for(Integer i : y)
		{
			if(i != ypoints[x1])
			{
				b = false;
				return b;
			}
			x1++;
		}
		
		return b;
	}

	public int getTiles() {
		return tiles;
	}
}
