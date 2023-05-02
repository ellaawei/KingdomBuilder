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
    public int settlementNum;
    public int x, y;
    private boolean isActive;

    public Player(int x, int y)
    {
    	this.x = x;
    	this.y = y;
    	//settlements = new Settlement[40];
        locationTiles = new ArrayList<>();
        settlementNum = 40;
        isActive = false;
    }
    
    public void setActive(boolean bActive)
    {
    	isActive = bActive;
    }
    public boolean isNear(Tiles locationTile) 
    {
    	return false;
    }
    
    public int getSettlementNum()
    {
    	return settlementNum;
    }
    
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
    
    public void settlementsLeft(int x)
    {
    	if(settlementNum > 0) //change if statement later to a "canAdd" variables to implement tiles as well as the 3 default
    		settlementNum = settlementNum - x;
    }
    public void paintPlayer(Graphics g)
    {    	
    	g.setColor(Color.YELLOW);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
    	if(settlementNum > 0) 
    		g.drawString(getSettlementNum() + "", x, y);
    	else 
    		g.drawString(0 + "", x, y);
    	
    }
    public void sellsFirst(boolean first) {}
    public void setLocationTiles(ArrayList<Tiles>locations) {}
    public int getScore() {
        return score;
    }
    public void calculateScore() {}
}
