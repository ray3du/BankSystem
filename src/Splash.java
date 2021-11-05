import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import Config.Icons;
import Login.Login;

/**
 * Splash
 */
public class Splash extends JFrame {
    private JProgressBar progressBar; 

    public Splash(){
        Thread backThread = new Thread(){
            public void run() {
                initComponents();
                try {
                    sleep(4000);
                    Splash.this.setVisible(false);
                    EventQueue.invokeLater(() -> {
                        new Login();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        };
        backThread.start();
    }

    private void initComponents(){
        ImageIcon imageIcon = new ImageIcon("/home/ray3du/Desktop/projects/BankingSystem/public/img/accounting-policies.jpg");
        JLabel label = new JLabel();
        label.setSize(800, 500);
        label.setLocation(0, 0);
        label.setIcon(imageIcon);

        progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setSize(700, 30);
        progressBar.setLocation(50, 460);
        progressBar.setStringPainted(true);

        this.add(progressBar);
        this.add(label);
        this.setSize(800, 500);
        this.setIconImage(Icons.getIcon().getImage());
        EventQueue.invokeLater(() -> {
            this.setUndecorated(true);
            this.setVisible(true);
        });
        fill();
    }
    public void fill(){
        int counter = 0;
        while( counter <= 100 ){
            progressBar.setValue(counter);
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter += 10;
        }
    
    }
}