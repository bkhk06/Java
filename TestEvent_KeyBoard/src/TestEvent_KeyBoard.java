import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

public class TestEvent_KeyBoard {
	public static void main(String[] args) {

		JFrame frame = new JFrame(" TestKeyPressed ");
		// JLabel jl = new JLabel("你好");
		
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true); // 去掉窗口的装饰
		frame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG); // 设置为简单搜索对话框风格
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 设置关闭按钮失效

		frame.setSize(350, 100);
		frame.setVisible(true);
		JLabel label = new JLabel("Press 'Ctrl+Q' to quit FME_msg_test for ");
		frame.add(label);
		
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.isControlDown() == true) && (e.getKeyCode() == KeyEvent.VK_Q)) {
					System.out.println("ctrl + Q has pressed,quit!");
					System.exit(1);
				} /*else {
					System.out.println("other key  pressed");
				}*/
			}
		});
		frame.setSize(500, 500);
		frame.setVisible(true);
		
	}
}