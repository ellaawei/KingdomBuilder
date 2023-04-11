import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Card {
	private ArrayList<BufferedImage> objectiveCards;
	private ArrayList<BufferedImage> terrainCards;
	private ArrayList<BufferedImage> discard;

	private boolean hasCardsLeft;
	private BufferedImage workers, citizens, discoverers, knights, lords, farmers, merchants, fisherman, miners,
			hermits;
	private BufferedImage grass, canyon, desert, flower, forest;

	public Card() throws IOException {
		objectiveCards = new ArrayList<>();
		terrainCards = new ArrayList<>();
		discard = new ArrayList<>();
		workers = ImageIO.read(this.getClass().getResource("/Image/ObjectiveWorkers.png"));
		citizens = ImageIO.read(this.getClass().getResource("/Image/ObjectiveCitizens.png"));
		discoverers = ImageIO.read(this.getClass().getResource("/Image/ObjectiveDiscoverers.png"));
		knights = ImageIO.read(this.getClass().getResource("/Image/ObjectiveKnights.png"));
		lords = ImageIO.read(this.getClass().getResource("/Image/ObjectiveLords.png"));
		farmers = ImageIO.read(this.getClass().getResource("/Image/ObjectiveFarmers.png"));
		merchants = ImageIO.read(this.getClass().getResource("/Image/ObjectiveMerchants.png"));
		fisherman = ImageIO.read(this.getClass().getResource("/Image/ObjectiveFisherman.png"));
		miners = ImageIO.read(this.getClass().getResource("/Image/ObjectiveMiners.png"));
		hermits = ImageIO.read(this.getClass().getResource("/Image/ObjectiveHermits.png"));

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

		grass = ImageIO.read(this.getClass().getResource("/Image/TerrainGrass.png"));
		canyon = ImageIO.read(this.getClass().getResource("/Image/TerrainCanyon.png"));
		desert = ImageIO.read(this.getClass().getResource("/Image/TerrainDesert.png"));
		flower = ImageIO.read(this.getClass().getResource("/Image/TerrainFlower.png"));
		forest = ImageIO.read(this.getClass().getResource("/Image/TerrainForest.png"));
		for (int i = 0; i < 5; i++) {
			terrainCards.add(grass);
			terrainCards.add(canyon);
			terrainCards.add(desert);
			terrainCards.add(flower);
			terrainCards.add(forest);
		}
		Collections.shuffle(objectiveCards);
		Collections.shuffle(terrainCards);
	}

	public void drawTerrainCards(Graphics g) 
	{
		g.drawImage(terrainCards.get(0), 1068, 316, 149, 215, null);
		if(GamePanel.clickedFinishTurn)
		{
			discard.add(0, terrainCards.get(0));
		}
	}

	public void drawDiscard(Graphics g) 
	{
		if(GamePanel.clickedFinishTurn)
			g.drawImage(discard.get(0), 1129, 27, 163, 220, null);
	}

	public void removeTerrainCard() {
		terrainCards.remove(0);
	}

	public ArrayList<BufferedImage> getTerrainCards() {
		return terrainCards;
	}

	public void drawObjectiveCards(Graphics g) {
		g.drawImage(objectiveCards.get(0), 95, 55, 200, 250, null);
		g.drawImage(objectiveCards.get(1), 295, 55, 200, 250, null);
		g.drawImage(objectiveCards.get(2), 495, 55, 200, 250, null);
	}

	public boolean getHasTerrainCardsLeft() {
		hasCardsLeft = !terrainCards.isEmpty();
		return hasCardsLeft;
	}

	public void setHasCardLeft(boolean bool) {
		hasCardsLeft = bool;
	}
	
	public void refill() {
		for (int i = 0; i < 5; i++) {
			terrainCards.add(grass);
			terrainCards.add(canyon);
			terrainCards.add(desert);
			terrainCards.add(flower);
			terrainCards.add(forest);
		}
		Collections.shuffle(terrainCards);
	}
}
