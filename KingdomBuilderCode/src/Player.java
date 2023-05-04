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
    private ArrayList<Token> tokens;
    //private Map<Hexagon.HexType, Token> tokens;
    
    //private Settlement settlements[];
    private int score;
    private BufferedImage currentSettlement;
    public int settlementNum;
    public int x, y;
    private boolean isActive;
    private int[] tx, ty;
    private int ax, ay;
    public boolean isFirstPlay = false;

    public Player(int x, int y)
    {
    	this.x = x;
    	this.y = y;
    	// token coordinates
    	tx = new int[] {x-3, x+42, x-3, x+42};
    	ty = new int[] {y-150, y-150, y-95, y-95};
    	// active token coordinate
    	ax = x + 20;
    	ay = y - 240;
    	//settlements = new Settlement[40];
        locationTiles = new ArrayList<>();
        tokens = new ArrayList<Token>() ;
        settlementNum = 40;
        isActive = false;
    }
    
    public void AddToken(Hexagon.HexType type) 
    {
    	// check if token added
    	boolean bAdded = false;
    	for (int i = 0; i < tokens.size(); i++)
    	{
    		if (type == tokens.get(i).type)
    		{
    			bAdded = true;
    			break;
    		}
    	}
    	
    	if (bAdded == false)
    	{
    		int index = tokens.size();			
    		Polygon p = RenderUtil.createHexagon(tx[index]+20, ty[index]+25, 22.7);
    		
    		Token t = Token.getToken(type);
    		if (t != null)
    		{
	    		t.polygon = p;
	    		t.bFromThisTurn = true; // add token of this turn
	    		tokens.add(t);
	    		Hexagon h = new Hexagon(p);
	    		t.setPlayer(this);
	    		Players.listHaxTokens.add(t);
    		}
    	}
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
    public void removeTile(Hexagon h)
    {
    	locationTiles.remove(h);
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
		
		if (settlementNum > 0)
			g.drawString(getSettlementNum() + "", x, y);
		else
			g.drawString(0 + "", x, y);
		
		// paint active token
		if (isFirstPlay)
		{
			g.drawImage(GamePanel.activeToken, ax, ay, 50, 50, null);
		}
		// paint tokens
		for (int i = 0; i < tokens.size(); i++) 
		{
	        Token t = tokens.get(i);
	        t.setCoordinate(tx[i], ty[i]);
	        t.paint(g);
	    }
    }
    
    public void finishTurn()
    {
    	for (int i = 0; i < tokens.size(); i++) 
		{
	        Token t = tokens.get(i);
	        t.bFromThisTurn = false;
	    }
    }
    public void sellsFirst(boolean first) {}
    public void setLocationTiles(ArrayList<Tiles>locations) {}
    public int getScore() {
        return score;
    }
    public void calculateScore() {}
}
