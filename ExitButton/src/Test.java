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

		//��ʼ�����
		frame = new JFrame();
		panel = new JPanel();
		exitButton = new JButton("�����˳�����");
		
		//������
		frame.add(panel);
		panel.add(exitButton);
		
		frame.setVisible(true);//���ô��ڿɼ�
		frame.setSize(400,400);//���ô�С
		frame.setLocationRelativeTo(null);//��Ծ�����ʾ...
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرն���
		
		//Ϊ��ť��Ӽ�����
		exitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,"���򼴽��˳�...");
				System.exit(0);
			}
		});

}
	
	public static void main(String[] args) {
		new Test();
	}
}