import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Test {
	
	private JButton exitButton;
	private JFrame frame;
	private JPanel panel;
	
	public Test() {

		//初始化组件
		frame = new JFrame();
		panel = new JPanel();
		exitButton = new JButton("测试退出程序");
		
		//添加组件
		frame.add(panel);
		panel.add(exitButton);
		
		frame.setVisible(true);//设置窗口可见
		frame.setSize(400,400);//设置大小
		frame.setLocationRelativeTo(null);//相对居中显示...
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭动作
		
		//为按钮添加监听器
		exitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,"程序即将退出...");
				System.exit(0);
			}
		});

}
	
	public static void main(String[] args) {
		new Test();
	}
}