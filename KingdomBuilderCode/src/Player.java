import java.util.*;
import java.awt.image.BufferedImage;

public class Player
{
    private String myColor;
    private boolean isFirst;
    private ArrayList<Tiles> locationTiles;
    private Settlement[] settlements;
    private int score;
    private BufferedImage currentSettlement;

    public Player()
    {
        locationTiles = new ArrayList<>();
        settlements = new Settlement[40];
    }
    public boolean isNear(Tiles locationTile) {}
    public int tokensLeft() {}


    public String getColor() {
        return myColor;
    }
    public boolean isFirst() {
        return isFirst;
    }
    public ArrayList<Tiles> getLocationTiles() {
        return locationTiles;
    }

    public void setColor(String color) {
        myColor = color;
    }
    public void sellsFirst(boolean first) {
        isFirst = first;
    }

    public void setLocationTiles(ArrayList<Tiles>locationTiles) {
        this.locationTiles = locationTiles;
    }
    public int getScore() {
        return score;
    }
    public void calculateScore() {}
}
