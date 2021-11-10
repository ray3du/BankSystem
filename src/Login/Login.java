package Login;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Bank.Dashboard;
import Config.Colors;
import Config.HintTextField;
import Config.Icons;

/**
 * Login
 */
public class Login extends JFrame{
    private BorderLayout borderLayout;
    private JButton login;
    private JButton register;
    private JTextField email;
    private JLabel loginTitle;
    private JPanel loginPanel;
    private JPanel formPanel;
    private JLabel emailJLabel;
    private JLabel passwordJLabel;
    private JLabel forgotPassword;
    private JLabel forgotPasswordClick;
    private JLabel emailError;
    private JLabel passwordError;
    private JLabel error;
    private JPasswordField password;

    public Login(){
        initLoginPage();
    }
    private void initLoginPage(){
        borderLayout = new BorderLayout(0, 10);

        loginTitle = new JLabel("BANK MANAGEMENT SYSTEM");
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

        emailJLabel = new JLabel("Email");
        emailJLabel.setSize(300, 30);
        emailJLabel.setLocation(0, 20);
        emailJLabel.setFont(labelFonts);
        email = new HintTextField("Enter admin email");
        email.setSize(300, 30);
        email.setLocation(0, 50);
        email.setBorder(border);
        emailError = new JLabel();
        emailError.setSize(300, 20);
        emailError.setLocation(0, 78);
        emailError.setFont(new Font("Tahoma", Font.ITALIC, 10));
        emailError.setForeground(Colors.getSecondaryColor());

        passwordJLabel = new JLabel("Password");
        passwordJLabel.setSize(300, 30);
        passwordJLabel.setLocation(0, 90);
        passwordJLabel.setFont(labelFonts);
        password = new JPasswordField();
        password.setSize(300, 30);
        password.setLocation(0, 120);
        password.setBorder(border);
        passwordError = new JLabel();
        passwordError.setSize(300, 20);
        passwordError.setLocation(0, 148);
        passwordError.setFont(new Font("Tahoma", Font.ITALIC, 10));
        passwordError.setForeground(Colors.getSecondaryColor());

        error = new JLabel();
        error.setSize(300, 20);
        error.setLocation(0, 225);
        // error.setHorizontalAlignment(JLabel.CENTER);
        error.setFont(new Font("Tahoma", Font.ITALIC, 11));
        error.setForeground(Colors.getSecondaryColor());

        forgotPassword = new JLabel("Forgot password?");
        forgotPassword.setSize(110, 30);
        forgotPassword.setLocation(0, 160);
        forgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));

        forgotPasswordClick = new JLabel("Here");
        forgotPasswordClick.setSize(50, 30);
        forgotPasswordClick.setLocation(110, 160);
        forgotPasswordClick.setFont(new Font("Tahoma", Font.PLAIN, 11));
        forgotPasswordClick.setForeground(Colors.getPrimaryColor());
        forgotPasswordClick.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordClick.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Login.this.dispose();
                EventQueue.invokeLater(() -> {
                    new ForgotPassword();
                });
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {}
            @Override
            public void mouseReleased(MouseEvent arg0) {}
        });

        login = new JButton();
        login.setText("Login");
        login.setSize(140, 30);
        login.setLocation(0, 190);
        login.setForeground(Colors.getWhiteColor());
        login.setBackground(Colors.getPrimaryColor());
        login.setBorder(border);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // checkErrors();
                new Dashboard();
            }
        });

        register = new JButton();
        register.setText("Register");
        register.setSize(140, 30);
        register.setLocation(160, 190);
        register.setForeground(Colors.getWhiteColor());
        register.setBackground(Colors.getSecondaryColor());
        register.setBorder(border);
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));

        formPanel.add(emailJLabel);
        formPanel.add(email);
        formPanel.add(emailError);
        formPanel.add(password);
        formPanel.add(passwordJLabel);
        formPanel.add(passwordError);
        formPanel.add(forgotPassword);
        formPanel.add(forgotPasswordClick);
        formPanel.add(login);
        formPanel.add(register);
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
    private void checkErrors(){
        login.setEnabled(false);
        String username = email.getText();
        char[] passTemp = password.getPassword();
        StringBuilder builder = new StringBuilder();
        builder.append(passTemp);
        String pass = builder.toString();

        if (username.equals("Enter admin email")) {
            emailError.setText("Email cannot be empty");
        }else{
            emailError.setText("");
        }
        if (pass.equals("")) {
            passwordError.setText("Password cannot be empty");
        }else{
            passwordError.setText("");
            new LoginAPI(username, pass, error, this);
            login.setEnabled(true);
        }
        login.setEnabled(true);
    }
}