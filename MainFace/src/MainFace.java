import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JFrame;
 
@SuppressWarnings("serial")
public class MainFace extends JFrame {
 
    public MainFace() {
 
        super("Ideal GUI");
        //���Ĭ�ϵĵ���رհ�ť�Ķ���ʵ��
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
         
        //����Ĵ�����Ϊ�������Ӽ����¼�����
        addKeyListener(new KeyListener() {
 
            public void keyPressed(KeyEvent e) {
                //����ĳ����ʱ���ô˷���
 
            }
 
            public void keyReleased(KeyEvent e) {
                //�ͷ�ĳ����ʱ���ô˷���
            }
 
            public void keyTyped(KeyEvent e) {
                //����ĳ����ʱ���ô˷���
                if (e.getKeyChar() == 'Q') {
                    System.exit(1);
                }
            }
 
        });
    }
     
    public static void main(String args[]) {
    	MainFace mGUI = new MainFace();
        mGUI.setSize(400, 300);
        mGUI.setVisible(true);
        mGUI.requestFocus();
    }
}