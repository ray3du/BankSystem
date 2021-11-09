package Bank;

import javax.swing.JPanel;

import Config.Colors;

public class Withdraw extends JPanel{
    public Withdraw(){
        initComponents();
    }
    private void initComponents() {
        this.setBackground(Colors.getPrimaryColor());
        this.setSize(600, 500);
        this.setName("Withdraw");
        this.setLocation(200, 0);
    }
}
