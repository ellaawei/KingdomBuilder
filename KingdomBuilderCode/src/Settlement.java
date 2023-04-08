import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class Settlement
{
  private int numLeft;
  private Point position;
  private boolean bordersWater;
  private boolean bordersMountain;
  private boolean bordersCastle;
  public static BufferedImage[]settlementImages;
  private Tiles tile;
  public Settlement()
  {
  }
  
  public int getSettlements() 
  {
    return 0;
  }
  public int resetSettlements() 
  {
    return 0;
  }
  public void buildSettlements(Point pos) {}
  public void mouseAction(MouseEvent e) {}
  public void moveSettlements (Point pos, Point pos2) {}
}
