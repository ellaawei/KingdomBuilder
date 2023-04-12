
public class Player()
{
    private String myColor;
    private boolean isFirst;
    private ArrayList<Tiles> locationTiles;
    private Settlement settlemets[40];
    private int score;
    private BufferedImage currentSettlement;

    pubilc Player()
    {

    }
    public boolean isNear(Tile locationTile) {}
    public int tokensLeft() {}


    public String getColor() {
        return myColor;
    }
    public boolean isFirst() {
        return isFirst;
    }
    public ArrayList<Tiles> getLocationTiles() {}
    public void setColor(String color) {
        myColor = color;
    }
    public void sellsFirst(boolean first) {}
    public void setLocationTiles(ArrayList<Tiles>locations) {}
    public int getScore() {
        return score;;
    }
    public void calculateScore() {}
}
