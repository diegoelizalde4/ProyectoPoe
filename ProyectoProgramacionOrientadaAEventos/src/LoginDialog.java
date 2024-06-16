
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class LoginDialog extends JDialog {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Map<String, String> userCredentials;
    private MenuPrincipal menuPrincipal;

    public LoginDialog(MenuPrincipal menuPrincipal, Map<String, String> userCredentials) {
        this.menuPrincipal = menuPrincipal;
        this.userCredentials = userCredentials;

        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        userField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        add(new JLabel("Usuario:"));
        add(userField);
        add(new JLabel("Contraseña:"));
        add(passwordField);
        add(new JLabel(""));
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });

        setLocationRelativeTo(menuPrincipal);
        setVisible(true);
    }

    private void authenticate() {
        String user = userField.getText();
        String password = new String(passwordField.getPassword());

        if (userCredentials.containsKey(user) && userCredentials.get(user).equals(password)) {
            menuPrincipal.setAuthenticated(true);
            JOptionPane.showMessageDialog(this, "Login exitoso.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
}
