package Login;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

import Config.Colors;
import Config.Icons;

public class NewPassword extends JFrame{
    private BorderLayout borderLayout;
    private JPasswordField password;
    private JButton login;
    private JButton confirm;
    private JLabel emailJLabel;
    private JLabel emailJLabelTitle;
    private JLabel error;
    private JPanel loginPanel;
    private JPanel formPanel;
    private String email;

    public NewPassword(String email){
        this.email = email;
        initComponents();
    }
    private void initComponents(){
        Font labelFonts = new Font("Arial", Font.BOLD, 13);
        Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        
        borderLayout = new BorderLayout(0, 10);
        loginPanel = new JPanel();
        loginPanel.setSize(400, 400);
        loginPanel.setLocation(200, 25); 
        // loginPanel.setBackground(new Color(0, 0, 0));

        // Form layout
        formPanel = new JPanel();
        formPanel.setSize(300, 300);
        formPanel.setLocation(50, 50);
        // formPanel.setBackground(new Color(255, 255, 255));

        emailJLabelTitle = new JLabel("Enter the password sent to your email");
        emailJLabelTitle.setSize(300, 30);
        emailJLabelTitle.setLocation(0, 70);
        emailJLabelTitle.setFont(labelFonts);
        emailJLabelTitle.setHorizontalAlignment(JLabel.CENTER);

        emailJLabel = new JLabel("Password");
        emailJLabel.setSize(300, 30);
        emailJLabel.setLocation(0, 100);
        emailJLabel.setFont(labelFonts);
        password = new JPasswordField();
        password.setSize(300, 30);
        password.setLocation(0, 130);
        password.setBorder(border);

        confirm = new JButton();
        confirm.setText("Confirm");
        confirm.setSize(140, 30);
        confirm.setLocation(0, 180);
        confirm.setForeground(Colors.getWhiteColor());
        confirm.setBackground(Colors.getPrimaryColor());
        confirm.setBorder(border);
        confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                StringBuilder builder = new StringBuilder();
                char[] pass = password.getPassword();
                String tempPass = builder.append(pass).toString();
                String passwd = tempPass; 
                if (!passwd.equals("")) {
                   new NewPasswordAPI(passwd, email, error, NewPassword.this);
                } else {
                    JOptionPane.showMessageDialog(NewPassword.this, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        login = new JButton();
        login.setText("Login");
        login.setSize(140, 30);
        login.setLocation(160, 180);
        login.setForeground(Colors.getWhiteColor());
        login.setBackground(Colors.getSecondaryColor());
        login.setBorder(border);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                NewPassword.this.setVisible(false); 
                EventQueue.invokeLater(() -> {
                    new Login();
                });    
            }
        });

        error = new JLabel();
        error.setSize(300, 30);
        error.setLocation(0, 250);
        error.setFont(new Font("Tahoma", Font.ITALIC, 12));
        error.setForeground(Colors.getSecondaryColor());
        error.setHorizontalAlignment(JLabel.CENTER);

        formPanel.add(emailJLabelTitle);
        formPanel.add(emailJLabel);
        formPanel.add(password);
        formPanel.add(login);
        formPanel.add(confirm);
        formPanel.add(error);
        formPanel.setLayout(null);

        loginPanel.add(formPanel, BorderLayout.CENTER);
        loginPanel.setLayout(borderLayout);

        this.add(loginPanel);
        this.setSize(800, 500);
        this.setTitle("Bank Management System");
        this.setIconImage(Icons.getIcon().getImage());
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
