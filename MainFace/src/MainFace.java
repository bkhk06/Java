import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JFrame;
 
@SuppressWarnings("serial")
public class MainFace extends JFrame {
 
    public MainFace() {
 
        super("Ideal GUI");
        //添加默认的点击关闭按钮的动作实现
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
         
        //下面的代码是为主面板添加键盘事件监听
        addKeyListener(new KeyListener() {
 
            public void keyPressed(KeyEvent e) {
                //按下某个键时调用此方法
 
            }
 
            public void keyReleased(KeyEvent e) {
                //释放某个键时调用此方法
            }
 
            public void keyTyped(KeyEvent e) {
                //键入某个键时调用此方法
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