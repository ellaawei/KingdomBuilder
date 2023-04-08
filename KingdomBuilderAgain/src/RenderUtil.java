import java.awt.Polygon;

public class RenderUtil 
{
	public static Polygon createHexagon(double cx, double cy, double hexRad)
	{
		int[] xp = new int[6];
		int[] yp = new int[6];
		
		int x = 0;
		for(double r = Math.PI / 6;  r < Math.PI * 2; r += Math.PI / 3)
		{
			xp[x] = (int) Math.round(Math.cos(r) * hexRad + cx);
			yp[x] = (int) Math.round(Math.sin(r) * hexRad + cy);
			x++;
		}
		
		return new Polygon(xp, yp, 6);
	}
	
	public static double getAltRadius(double hexRad)
	{
		return hexRad * Math.sin(Math.PI / 3);
	}
	
	public static double getHorizontalShift(double hexRad)
	{
		return getAltRadius(hexRad) * 2;
	}
	
	public static double getVerticalShift(double hexRad)
	{
		return 2 * getAltRadius(hexRad) * Math.cos(Math.PI / 6);
	}
}
