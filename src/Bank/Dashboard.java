package Bank;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Config.StringPath;


public class Dashboard extends JFrame {
    private JPanel menuDash;
    private JPanel menuPanel;
    private JLabel depositLabel;
    private JLabel withdrawLabel;
    private JLabel accountLabel;
    private JLabel settingsJLabel;
    private JLabel usernameLabel;
    private String token; 

    public Dashboard(){
        initComponents();
    }

    public void setToken(String token){
        this.token = token;
    }
    
    public String getToken(){
        return this.token;
    }

    private void initComponents(){
        CardLayout layout = new CardLayout();
        Border border = BorderFactory.createRaisedBevelBorder();

        menuPanel = new JPanel();
        menuPanel.setSize(200, 470);
        menuPanel.setLocation(0, 0);
        menuPanel.setBorder(border);

        usernameLabel = new JLabel("admin@gmail.com");
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        String depositPath = StringPath.getAbsolutePath() + "/public/deposit-icon-21.jpg";
        ImageIcon depositIcon = new ImageIcon(depositPath);
        depositLabel = new JLabel("Deposit");
        depositLabel.setIcon(depositIcon);
        depositLabel.setHorizontalTextPosition(JLabel.CENTER);
        depositLabel.setVerticalTextPosition(JLabel.TOP);
        depositLabel.setHorizontalAlignment(JLabel.CENTER);
        depositLabel.setVerticalAlignment(JLabel.BOTTOM);
        depositLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        depositLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        depositLabel.setBorder(border);
        depositLabel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                menuDash.add("Deposit", new Deposit());
                menuDash.invalidate();
                menuDash.validate();
                menuDash.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {}

            @Override
            public void mouseExited(MouseEvent arg0) {}

            @Override
            public void mousePressed(MouseEvent arg0) {}

            @Override
            public void mouseReleased(MouseEvent arg0) {}
            
        });
        
        withdrawLabel = new JLabel("Withdraw");
        withdrawLabel.setHorizontalTextPosition(JLabel.CENTER);
        withdrawLabel.setVerticalTextPosition(JLabel.BOTTOM);
        withdrawLabel.setHorizontalAlignment(JLabel.CENTER);
        withdrawLabel.setVerticalAlignment(JLabel.BOTTOM);
        withdrawLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        withdrawLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        withdrawLabel.setBorder(border);
        withdrawLabel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                menuDash.add("Withdraw", new Withdraw());
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {}

            @Override
            public void mouseExited(MouseEvent arg0) {}

            @Override
            public void mousePressed(MouseEvent arg0) {}

            @Override
            public void mouseReleased(MouseEvent arg0) {}
            
        });
        
        
        accountLabel = new JLabel("Account");
        accountLabel.setHorizontalTextPosition(JLabel.CENTER);
        accountLabel.setVerticalTextPosition(JLabel.BOTTOM);
        accountLabel.setHorizontalAlignment(JLabel.CENTER);
        accountLabel.setVerticalAlignment(JLabel.BOTTOM);
        accountLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        accountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accountLabel.setBorder(border);
        
        String path = StringPath.getAbsolutePath() + "/public/img/icon.png";
        ImageIcon icon = new ImageIcon(path);
        settingsJLabel = new JLabel("Settings");
        settingsJLabel.setIcon(icon);
        settingsJLabel.setHorizontalTextPosition(JLabel.CENTER);
        settingsJLabel.setVerticalTextPosition(JLabel.BOTTOM);
        settingsJLabel.setHorizontalAlignment(JLabel.CENTER);
        settingsJLabel.setVerticalAlignment(JLabel.BOTTOM);
        settingsJLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        settingsJLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        settingsJLabel.setBorder(border);

        menuPanel.setLayout(new GridLayout(5, 1, 50, 5));
        EventQueue.invokeLater(() -> {
            menuPanel.add(usernameLabel);
            menuPanel.add(depositLabel);
            menuPanel.add(withdrawLabel);
            menuPanel.add(accountLabel);
            menuPanel.add(settingsJLabel);
        });

        menuDash = new JPanel();
        menuDash.setSize(600, 500);
        menuDash.setLocation(200, 0);
        menuDash.setLayout(layout);

        this.add(menuPanel);
        this.add(menuDash);
        this.setTitle("Bank Management System");
        this.setSize(800, 500);
        this.setVisible(true);
    }
}
