import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Players 
{
	private Player[] listPlayers;
    public final static int SX = 940, SY = 27;
	private int startx = SX, starty = SY+488;
	private int activePlayer;
	
	private int cw=147, ch=215, sx=SX+95, sy=SY+289;
	private int[] cx= {sx, sx+cw*2, sx, sx+cw*2}, cy= {sy, sy, sy+ch+40, sy+ch+40};
	private Card.TerrainCard activeCard;
	private Card.TerrainCard discardCard;
	private boolean bIsFinish = true;
	private Map<Hexagon.HexType, Token> tokens = new HashMap<Hexagon.HexType, Token>();
	public static int clickCount = 0;
	public int first = (int)(Math.floor(Math.random() *4));
	
	public Players()
	{
		listPlayers = new Player[4];
		activePlayer = first;
		discardCard = null;
		bIsFinish = true;
		
		listPlayers[0] = new Player(startx, starty);
		listPlayers[0].settlementNum = 40;
		listPlayers[0].setSettlementColor(new Color(205, 102, 0)); // orange 
		listPlayers[1] = new Player(startx + 295, starty);
		listPlayers[1].settlementNum = 40;
		listPlayers[1].setSettlementColor(new Color(65, 65, 65)); // dark gray
		listPlayers[2] = new Player(startx, starty + 260);
		listPlayers[2].settlementNum = 40;
		listPlayers[2].setSettlementColor(new Color(255, 255, 204)); // ight yellow
		listPlayers[3] = new Player(startx + 295, starty + 260);
		listPlayers[3].settlementNum = 40;
		listPlayers[3].setSettlementColor(new Color(51, 153, 255));  // light blue
	}
	
	public void drawPlayers(Graphics g) 
	{
		if (activePlayer >= 0 && bIsFinish == false)
		{
			g.drawImage(activeCard.image, cx[activePlayer], cy[activePlayer], cw, ch, null);
			listPlayers[activePlayer].setTerrainColor(activeCard.color);
		}
		
		if (discardCard != null)
		{
			g.drawImage(discardCard.image, SX+170, SY+5, cw, ch, null);
		}
		
//		
//		if(first == 0)
//		{
//			listPlayers[0].setActive(true);
//			listPlayers[1].setActive(false);
//			listPlayers[2].setActive(false);
//			listPlayers[3].setActive(false);
//		}
//		else if(first == 1)
//		{
//			listPlayers[1].setActive(true);
//			listPlayers[0].setActive(false);
//			listPlayers[2].setActive(false);
//			listPlayers[3].setActive(false);
//		}
//		else if(first == 2)
//		{
//			listPlayers[2].setActive(true);
//			listPlayers[0].setActive(false);
//			listPlayers[1].setActive(false);
//			listPlayers[3].setActive(false);
//		}
//		else if(first == 3)
//		{
//			listPlayers[3].setActive(true);
//			listPlayers[0].setActive(false);
//			listPlayers[1].setActive(false);
//			listPlayers[2].setActive(false);
//		}
		listPlayers[0].setActive(false);
		listPlayers[1].setActive(false);
		listPlayers[2].setActive(false);
		listPlayers[3].setActive(false);
		listPlayers[activePlayer].setActive(true);
		listPlayers[0].paintPlayer(g);
		listPlayers[1].paintPlayer(g);
		listPlayers[2].paintPlayer(g);
		listPlayers[3].paintPlayer(g);
	}
	
	public void setActivePayer(int n)
	{
		activePlayer = n;
		listPlayers[n].setActive(true);
		listPlayers[(n+1)%4].setActive(false);
		listPlayers[(n+2)%4].setActive(false);
		listPlayers[(n+3)%4].setActive(false);
	}
	
	public Player getActivePlayer()
	{
		return listPlayers[activePlayer];
	}
	
	public void nextPlayer()
	{
		activePlayer++;
		activePlayer = activePlayer % 4;
		setActivePayer(activePlayer);
	}
	
	public void drawTerrainCards(Card.TerrainCard card) 
	{
		if (bIsFinish == true)
		{
			activeCard = card;
			bIsFinish = false;
		}
	}
	
	public void clickedFinish()
	{
		nextPlayer();
		discardCard = activeCard;
		bIsFinish = true;
		clickCount = 0;
	}
	
	public boolean getIsFinsh()
	{
		return bIsFinish;
	}
}
