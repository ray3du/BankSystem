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
import javax.swing.border.Border;

import Config.Colors;
import Config.HintTextField;
import Config.Icons;

public class ForgotPassword extends JFrame implements ActionListener {
    private BorderLayout borderLayout;
    private HintTextField email;
    private JLabel error;
    private JButton login;
    private static JButton send;
    private JLabel emailJLabel;
    private JLabel emailJLabelTitle;
    private JPanel loginPanel;
    private JPanel formPanel;

    public ForgotPassword(){
        initComponents();
    }
    public static JButton getSendButton(){
        return send;
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

        emailJLabelTitle = new JLabel("Password reset");
        emailJLabelTitle.setSize(300, 30);
        emailJLabelTitle.setLocation(0, 70);
        emailJLabelTitle.setFont(labelFonts);
        emailJLabelTitle.setHorizontalAlignment(JLabel.CENTER);

        emailJLabel = new JLabel("Email");
        emailJLabel.setSize(300, 30);
        emailJLabel.setLocation(0, 100);
        emailJLabel.setFont(labelFonts);
        email = new HintTextField("Enter email");
        email.setSize(300, 30);
        email.setLocation(0, 130);
        email.setBorder(border);

        send = new JButton();
        send.setText("Send");
        send.setSize(140, 30);
        send.setLocation(0, 180);
        send.setForeground(Colors.getWhiteColor());
        send.setBackground(Colors.getPrimaryColor());
        send.setBorder(border);
        send.setCursor(new Cursor(Cursor.HAND_CURSOR));
        send.setFocusable(false);
        send.addActionListener(this);

        login = new JButton();
        login.setText("Back");
        login.setSize(140, 30);
        login.setLocation(160, 180);
        login.setForeground(Colors.getWhiteColor());
        login.setBackground(Colors.getSecondaryColor());
        login.setBorder(border);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setFocusable(false);
        login.addActionListener(this);

        error = new JLabel();
        error.setSize(300, 30);
        error.setLocation(0, 250);
        error.setFont(new Font("Tahoma", Font.ITALIC, 12));
        error.setForeground(Colors.getSecondaryColor());
        error.setHorizontalAlignment(JLabel.CENTER);

        formPanel.add(emailJLabelTitle);
        formPanel.add(emailJLabel);
        formPanel.add(email);
        formPanel.add(login);
        formPanel.add(send);
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
    private void send_action() {
        String emailString = email.getText();

        if (emailString.equals("")) {
            ForgotPassword.this.dispose();
            //new NewPassword(emailString);
            new ForgotPasswordAPI(emailString, error, ForgotPassword.this);
        } else {
            JOptionPane.showMessageDialog(ForgotPassword.this, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    private void login_action() {
        ForgotPassword.this.dispose();
        EventQueue.invokeLater(() -> {
            new Login();
        });    
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        if (source == send) {
            send_action();
        }
        if (source == login) {
            login_action();
        }
    }
}
