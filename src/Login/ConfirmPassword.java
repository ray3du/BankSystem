package Login;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

import Config.Colors;
import Config.Icons;

/**
 * Login
 */
public class ConfirmPassword extends JFrame implements ActionListener{
    private BorderLayout borderLayout;
    private JButton ConfirmPassword;
    private JLabel loginTitle;
    private JPanel loginPanel;
    private JPanel formPanel;
    private JLabel emailJLabel;
    private JLabel passwordJLabel;
    private JLabel passwordError;
    private JLabel error;
    private JPasswordField password;
    private JPasswordField confirmPassword;

    public ConfirmPassword(){
        initLoginPage();
    }
    private void initLoginPage(){
        borderLayout = new BorderLayout(0, 10);

        loginTitle = new JLabel("Change Password");
        loginTitle.setSize(400, 20);
        loginTitle.setLocation(90, 25);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 14));

        loginPanel = new JPanel();
        loginPanel.setSize(400, 400);
        loginPanel.setLocation(200, 25);
    
        // Form layout
        formPanel = new JPanel();
        formPanel.setSize(300, 300);
        formPanel.setLocation(50, 50);

        Font labelFonts = new Font("Arial", Font.BOLD, 13);
        Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);

        emailJLabel = new JLabel("New Password");
        emailJLabel.setSize(300, 30);
        emailJLabel.setLocation(0, 20);
        emailJLabel.setFont(labelFonts);
        password = new JPasswordField("");
        password.setSize(300, 30);
        password.setLocation(0, 50);
        password.setBorder(border);

        passwordJLabel = new JLabel("Confirm Password");
        passwordJLabel.setSize(300, 30);
        passwordJLabel.setLocation(0, 90);
        passwordJLabel.setFont(labelFonts);
        confirmPassword = new JPasswordField();
        confirmPassword.setSize(300, 30);
        confirmPassword.setLocation(0, 120);
        confirmPassword.setBorder(border);
        passwordError = new JLabel();
        passwordError.setSize(300, 20);
        passwordError.setLocation(0, 148);
        passwordError.setFont(new Font("Tahoma", Font.ITALIC, 10));
        passwordError.setForeground(Colors.getSecondaryColor());

        error = new JLabel();
        error.setSize(300, 20);
        error.setLocation(0, 225);
        error.setFont(new Font("Tahoma", Font.ITALIC, 11));
        error.setForeground(Colors.getSecondaryColor());

        ConfirmPassword = new JButton();
        ConfirmPassword.setText("Set");
        ConfirmPassword.setSize(140, 30);
        ConfirmPassword.setLocation(0, 190);
        ConfirmPassword.setForeground(Colors.getWhiteColor());
        ConfirmPassword.setBackground(Colors.getPrimaryColor());
        ConfirmPassword.setBorder(border);
        ConfirmPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirmPassword.setFocusable(false);
        ConfirmPassword.addActionListener(this);

        formPanel.add(emailJLabel);
        formPanel.add(password);
        formPanel.add(password);
        formPanel.add(passwordJLabel);
        formPanel.add(passwordError);
        formPanel.add(confirmPassword);
        formPanel.add(ConfirmPassword);
        formPanel.add(error);
        formPanel.setLayout(null);

        loginPanel.add(loginTitle, BorderLayout.NORTH);
        loginPanel.add(formPanel, BorderLayout.PAGE_END);
        loginPanel.setLayout(borderLayout);

        this.add(loginPanel);
        this.setSize(800, 500);
        this.setIconImage(Icons.getIcon().getImage());
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void ConfirmPassword_action() {
        StringBuilder builder = new StringBuilder();
        char [] pass = password.getPassword();
        String tempPass = builder.append(pass).toString();
        String passwd = tempPass;

        StringBuilder builder1 = new StringBuilder();
        char [] pass1 = confirmPassword.getPassword();
        String tempPass1 = builder1.append(pass1).toString();
        String passwd1 = tempPass1;

        if (passwd.equals("") || passwd1.equals("")) {
            JOptionPane.showMessageDialog(ConfirmPassword.this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!passwd.equals(passwd1)) {
                JOptionPane.showMessageDialog(ConfirmPassword.this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new ConfirmPasswordAPI(passwd, passwordError, ConfirmPassword.this);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {        
        Object source = arg0.getSource();
        if (source == ConfirmPassword) {
            ConfirmPassword_action();
        }
    }
}