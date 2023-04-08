import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
public class Game 
{
	static Hexagon h;
	public static void main(String[] args) throws IOException
	{
		final Board b = new Board();
		b.repaint();
		
		b.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				b.checkHex(arg0.getPoint());
				b.CheckDeck(arg0.getPoint());
				b.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				h = b.checkHex2(arg0.getPoint());
				if(h!=null)
					h.setDarker(true);
				b.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(h!=null)
					h.setDarker(false);
				b.repaint();
			}
		});
	}
}
