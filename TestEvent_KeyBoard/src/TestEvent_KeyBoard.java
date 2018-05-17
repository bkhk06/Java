import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestEvent_KeyBoard {
	public static void main(String[] args) {

		JFrame frame = new JFrame(" TestKeyPressed ");
		//JLabel jl = new JLabel("ÄãºÃ");
		
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.isControlDown() == true) && (e.getKeyCode() == KeyEvent.VK_UP)) {
					System.out.println("ctrl + UP has pressed");
					System.exit(1);
				} else {
					System.out.println("other key  pressed");
				}
			}
		});
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}