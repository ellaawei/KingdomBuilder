import java.awt.Point;
public class Tiles 
{
	private int terrainType;
	private Point pos;
	private int borderLocation;
	private Player occupier;
	
	public Tiles()
	{
		
	}
	
	public boolean canGet() {}
	public int getTerrainType() {
		return terrainType;
	}
	public int getBorderLocation() {
		return borderLocation;
	}
	public void setBorderLocation(int loc)
	{
	 borderLocation = loc;
	}
	public boolean canPlaceWater() {}
	public void buildOracle() {}
	public void buildFarm() {}
	public void buildOasis() {}
	public void buildBarn() {}
	public void buildPaddock() {}
	public void buildHarbor() {}
	public void buildTower() {}
	public void buildTavern() {}
	public Player getPlayer() {
		return occupier;
	}
	public void setPlayer(Player occupier) {
		this.occupier = occupier;
	}
	public boolean isClicked(int x, int y) {}

}
