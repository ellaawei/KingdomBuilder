import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Card {
	private ArrayList<BufferedImage> objectiveCards;
	private ArrayList<TerrainCard> terrainCards;
	private ArrayList<BufferedImage> discard;
	private BufferedImage workers, citizens, discoverers, knights, lords, farmers, merchants, fisherman, miners,
			hermits;
	//private BufferedImage grass, canyon, desert, flower, forest;
	private int cw=149, ch=215, sx=1068, sy=316;
	private int[] cx= {sx, sx+cw*2, sx, sx+cw*2}, cy= {sy, sy, sy+ch+40, sy+ch+40};
	public static boolean terrainClicked = false;
	
	class TerrainCard
	{
		public BufferedImage image;
		public Color color;
		
		public TerrainCard(BufferedImage image, Color color)
		{
			this.image = image;
			this.color = color;
		}
	}
	public Card() throws IOException {
		objectiveCards = new ArrayList<>();
		terrainCards = new ArrayList<>();
		discard = new ArrayList<>();
		workers = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveWorkers.png"));
		citizens = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveCitizens.png"));
		discoverers = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveDiscoverers.png"));
		knights = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveKnights.png"));
		lords = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveLords.png"));
		farmers = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveFarmers.png"));
		merchants = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveMerchants.png"));
		fisherman = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveFisherman.png"));
		miners = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveMiners.png"));
		hermits = ImageIO.read(this.getClass().getResource("/pictures/ObjectiveHermits.png"));

		objectiveCards.add(workers);
		objectiveCards.add(citizens);
		objectiveCards.add(discoverers);
		objectiveCards.add(knights);
		objectiveCards.add(lords);
		objectiveCards.add(farmers);
		objectiveCards.add(merchants);
		objectiveCards.add(fisherman);
		objectiveCards.add(miners);
		objectiveCards.add(hermits);

		for (int i = 0; i < 5; i++) {
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainGrass.png")), Color.GREEN));
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainCanyon.png")), new Color(102, 51, 0)));
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainDesert.png")), Color.YELLOW));
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainFlower.png")), Color.PINK));
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainForest.png")), new Color(0, 102, 0)));
		}
		Collections.shuffle(objectiveCards);
		Collections.shuffle(terrainCards);
	}
	
	public void drawTerrainCards() 
	{
		GamePanel.players.drawTerrainCards(terrainCards.get(0));
		//if(GamePanel.click==GamePanel.clickType.terrain) {
		//	GamePanel.players.drawPlayers(g);
		//}
	}

	public void drawDiscard(Graphics g) 
	{
		discard.add(0, terrainCards.get(0).image);
		if(GamePanel.clickedFinishTurn)
		{
			g.drawImage(discard.get(0), 1129, 27, 163, 220, null);
		}
	}

	public void removeTerrainCard() 
	{
		if (terrainCards.size() > 1)
			terrainCards.remove(0);
		else
		{
			try
			{
				for (int i = 0; i < 5; i++) {
					terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainGrass.png")), Color.GREEN));
					terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainCanyon.png")), new Color(102, 51, 0)));
					terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainDesert.png")), Color.YELLOW));
					terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainFlower.png")), Color.PINK));
					terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainForest.png")), new Color(0, 102, 0)));
				}
			}catch(Exception e) {
				System.out.println("card class");
			}
			Collections.shuffle(terrainCards);
			terrainCards.remove(0);
		}
	}

	//public ArrayList<BufferedImage> getTerrainCards() {
	//	return terrainCards;
	//}

	public void drawObjectiveCards(Graphics g) {
		g.drawImage(objectiveCards.get(0), 95, 55, 200, 250, null);
		g.drawImage(objectiveCards.get(1), 295, 55, 200, 250, null);
		g.drawImage(objectiveCards.get(2), 495, 55, 200, 250, null);
	}

	public boolean getHasTerrainCardsLeft() {
		return !terrainCards.isEmpty();
	}
	
	public void refill() throws IOException {
		for (int i = 0; i < 5; i++) {
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainGrass.png")), Color.GREEN));
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainCanyon.png")), new Color(102, 51, 0)));  // brown
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainDesert.png")), Color.YELLOW));
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainFlower.png")), Color.PINK));
			terrainCards.add(new TerrainCard(ImageIO.read(this.getClass().getResource("/pictures/TerrainForest.png")), new Color(0, 102, 0)));
		}
		Collections.shuffle(terrainCards);
	}
}
