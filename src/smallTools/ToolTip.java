package smallTools;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

public class ToolTip {


    private int num = 0;

    private JLabel label = new JLabel("test", JLabel.CENTER);

    private MouseEvent curMove;

    private boolean entered;

 

    public ToolTip() {

        JFrame frame = new JFrame();

        label.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                // 记录上一次的鼠标移动位置

                curMove = e;

            }

        });

        label.addMouseListener(new MouseAdapter() {

            @Override

            public void mouseEntered(MouseEvent e) {

                // 标记鼠标在 JLabel 控件内

                entered = true;

            }

             

            @Override

            public void mouseExited(MouseEvent e) {

                // 标记鼠标移出了JLabel 控件

                entered = false;

            }

        });

         

        // 创建刷新 ToolTip 的线程

        initTipThread();

        

        

        JPanel testPane = new JPanel(new BorderLayout());

 

        testPane.add(new JButton("NORTH"), BorderLayout.NORTH);

        testPane.add(new JButton("SOUTH"), BorderLayout.SOUTH);

        testPane.add(new JButton("WEST"), BorderLayout.WEST);

        testPane.add(new JButton("EAST"), BorderLayout.EAST);

        testPane.add(label, BorderLayout.CENTER);

 

        frame.setContentPane(testPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300, 200);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }

     

    private void initTipThread() {

    	// 设置ToolTip的最小间隔刷新

    	ToolTipManager.sharedInstance().setInitialDelay(1);

        new Thread(new Runnable() {

            public void run() {

                while (true) {

                    try {

                        // 延时（此时间不可小于ToolTip的最小间隔刷新，否则无法正常刷新）

                        Thread.sleep(50);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                    num++;

                    SwingUtilities.invokeLater(new Runnable() {


                        public void run() {

                            label.setToolTipText("" + num);

                            if (entered) {

                                // 当前鼠标在控件内时，定时模拟一次 mouseMoved 事件

                                ToolTipManager.sharedInstance().mouseMoved(curMove);

                            }

                        }

                    });

                }

            }

        }).start();

    }

     

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                new ToolTip();               

            }

        });

     

    }

 

}
