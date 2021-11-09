package Bank;

import javax.swing.JPanel;

import Config.Colors;

/**
 * Deposit
 */
public class Deposit extends JPanel{
    public Deposit(){
        initComponents();
    }
    private void initComponents(){
        this.setBackground(Colors.getSecondaryColor());
        this.setName("Deposit");
        this.setSize(600, 500);
        this.setLocation(200, 0);
    }
}