import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class Player
{
    public Color settlementColor;
    public Color terrainColor;
    
    private boolean isFirst;
    private ArrayList<Hexagon> locationTiles;
    //private Settlement settlements[];
    private int score;
    private BufferedImage currentSettlement;
    public static boolean hexClicked = false;
  //  public int settlementNum;
    //public int x = 960, y = 515;

    public Player()
    {
    	//settlements = new Settlement[40];
        locationTiles = new ArrayList<>();
      //  settlementNum = 40;
    }
    public boolean isNear(Tiles locationTile) 
    {
    	return false;
    }
    
//    public int getSettlementNum()
//    {
//    	return settlementNum;
//    }
    
    public int tokensLeft() {
    	return 0;
    }
    public Color getSettlementColor() {
        return settlementColor;
    }
    public Color getTerrainColor() {
        return terrainColor;
    }
    public boolean isFirst() {
        return isFirst;
    }
    public void addTile(Hexagon h)
    {
    	locationTiles.add(h);
    }
    public ArrayList<Hexagon> getLocationTiles() {
    	return locationTiles;
    }
    public void setSettlementColor(Color color) {
        settlementColor =color;
    }
    public void setTerrainColor(Color color) {
    	terrainColor =color;
    }
    
//    public void settlementsLeft(int x)
//    {
//    	settlementNum = settlementNum - x;
//    }
////    public void paintPlayer(Graphics g)
//    {
//    	if(x == 1263 && y == 515)
//    	{
//    		g.setColor(Color.white);
//    	}
//    	else
//    	{
//    		g.setColor(Color.black);
//    	}
//		g.setFont(new Font("Times New Roman", Font.BOLD, 30));
//		g.drawString(getSettlementNum() + "", x, y);
////		g.setColor(Color.white);
////		g.drawString(players[1].getSettlementNum() + "", 1263, 517);
////		g.setColor(Color.black);
////		g.drawString(players[2].getSettlementNum() + "", 960, 775);
////		g.drawString(players[0].getSettlementNum() + "", 1265, 777);
//    }
    public void sellsFirst(boolean first) {}
    public void setLocationTiles(ArrayList<Tiles>locations) {}
    public int getScore() {
        return score;
    }
    public void calculateScore() {}
}
