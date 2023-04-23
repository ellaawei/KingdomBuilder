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
    private Settlement settlements[];
    private int score;
    private BufferedImage currentSettlement;

    public Player()
    {
    	settlements = new Settlement[40];
        locationTiles = new ArrayList<>();
    }
    public boolean isNear(Tiles locationTile) 
    {
    	return false;
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
    public void sellsFirst(boolean first) {}
    public void setLocationTiles(ArrayList<Tiles>locations) {}
    public int getScore() {
        return score;
    }
    public void calculateScore() {}
}
