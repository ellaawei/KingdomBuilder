import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Token 
{
	public Hexagon.HexType type;
	private BufferedImage image;
	private int x, y;
	
	public Token(Hexagon.HexType type) 
	{
		this.type = type;
		
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
	
	public void setCoordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, x, y, 40, 50, null);
	}
}
