package Bank;

import java.awt.EventQueue;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Dashboard extends JFrame {
    private JLabel settings;
    private JPanel menuDash;
    private JPanel menuPanel;
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
        Border border = BorderFactory.createRaisedBevelBorder();
        menuPanel = new JPanel();
        menuPanel.setSize(200, 500);
        menuPanel.setLocation(0, 0);
        menuPanel.setBorder(border);
        
        Path currentPath = Paths.get("");
        String pathTemp = currentPath.toAbsolutePath().toString();
        String path = pathTemp + "/public/img/icon.png";
        ImageIcon icon = new ImageIcon(path);
        settings = new JLabel("Settings");
        settings.setSize(150, 100);
        settings.setLocation(25, 400);
        settings.setIcon(icon);
        settings.setHorizontalTextPosition(JLabel.CENTER);
        settings.setVerticalTextPosition(JLabel.TOP);
        settings.setBorder(border);

        EventQueue.invokeLater(() -> {
            menuPanel.add(settings);
        });

        menuDash = new JPanel();
        menuDash.setSize(600, 500);
        menuDash.setLocation(200, 0);

        this.add(menuPanel);
        this.add(menuDash);
        this.setTitle("Bank Management System");
        this.setSize(800, 500);
        this.setVisible(true);
    }
}
