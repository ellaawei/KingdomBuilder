import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

public class Board {
	private BufferedImage board1, board2, board3, board4, board5, board6, board7, board8;
	private ArrayList<BufferedImage> boards;
	Point pos;
	
	public Board() throws IOException {
		boards = new ArrayList<BufferedImage>();
		board1 = ImageIO.read(this.getClass().getResource("/Image/Board1.png"));
		board2 = ImageIO.read(this.getClass().getResource("/Image/Board2.png"));
		board3 = ImageIO.read(this.getClass().getResource("/Image/Board3.png"));
		board4 = ImageIO.read(this.getClass().getResource("/Image/Board4.png"));
		board5 = ImageIO.read(this.getClass().getResource("/Image/Board5.png"));
		board6 = ImageIO.read(this.getClass().getResource("/Image/Board6.png"));
		board7 = ImageIO.read(this.getClass().getResource("/Image/Board7.png"));
		board8 = ImageIO.read(this.getClass().getResource("/Image/Board8.png"));
		boards.add(board1);
		boards.add(board2);
		boards.add(board3);
		boards.add(board4);
		boards.add(board5);
		boards.add(board6);
		boards.add(board7);
		boards.add(board8);
		Collections.shuffle(boards);
		
	}
	public void selectHex(int x, int y) {
		
	}
	public void setTilePositions() {
		
	}
	public void drawBoard(Graphics g) {
		g.drawImage(boards.get(0), -111, 44, 750, 445, null);
		g.drawImage(boards.get(1), 270, 44, 750, 445, null);
		g.drawImage(boards.get(2), -111, 393, 750, 445, null);
		g.drawImage(boards.get(3), 270, 393, 750, 445, null);
	}
	
}

