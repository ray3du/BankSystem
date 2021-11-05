package Bank;

import javax.swing.JFrame;

public class Dashboard extends JFrame {
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
        this.setTitle("Bank Management System");
        this.setSize(800, 500);
        this.setVisible(true);
    }
}
