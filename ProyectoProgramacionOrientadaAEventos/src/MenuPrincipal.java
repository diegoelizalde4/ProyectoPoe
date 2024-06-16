

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MenuPrincipal extends JFrame {
    private JComboBox<String> menuComboBox;
    private JButton goButton;
    private List<Empleado> empleados;
    private List<Nominas> nominas;
    private HistorialNominas historialNominas;
    private boolean isAuthenticated = false;
    private Map<String, String> userCredentials;

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        empleados = new LinkedList<>();
        nominas = new LinkedList<>();
        historialNominas = new HistorialNominas();
        userCredentials = new HashMap<>();
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");

        String[] menuOptions = {"Seleccione una opción", "Login", "Registro de Empleados", "Tabla de Empleados", "Registro de Horas", "Calculadora de Nóminas", "Área General", "Área Jefe de Área", "Área Empleado", "Historial de Nóminas"};
        menuComboBox = new JComboBox<>(menuOptions);
        goButton = new JButton("Ir");

        add(new JLabel("Seleccione una opción:"));
        add(menuComboBox);
        add(new JLabel(""));
        add(goButton);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigate();
            }
        });

        setVisible(true);
    }

    private void navigate() {
        String selectedOption = (String) menuComboBox.getSelectedItem();
        switch (selectedOption) {
            case "Login":
                new LoginDialog(this, userCredentials);
                break;
            case "Registro de Empleados":
                if (isAuthenticated) {
                    new FormularioEmpleados(empleados);
                } else {
                    showMessage("Por favor, inicie sesión primero.");
                }
                break;
            case "Tabla de Empleados":
                if (isAuthenticated) {
                    new EmpleadosTabla(empleados);
                } else {
                    showMessage("Por favor, inicie sesión primero.");
                }
                break;
            case "Registro de Horas":
                if (isAuthenticated) {
                    new RegistroHoras(empleados, nominas);
                } else {
                    showMessage("Por favor, inicie sesión primero.");
                }
                break;
            case "Calculadora de Nóminas":
                if (isAuthenticated) {
                    new CalculadoraNominas(nominas, historialNominas);
                } else {
                    showMessage("Por favor, inicie sesión primero.");
                }
                break;
            case "Área General":
                if (isAuthenticated) {
                    new AreaGeneral(empleados);
                } else {
                    showMessage("Por favor, inicie sesión primero.");
                }
                break;
            case "Área Jefe de Área":
                if (isAuthenticated) {
                    new AreaJefeDeArea(empleados);
                } else {
                    showMessage("Por favor, inicie sesión primero.");
                }
                break;
            case "Área Empleado":
                if (isAuthenticated) {
                    new AreaEmpleado(empleados);
                } else {
                    showMessage("Por favor, inicie sesión primero.");
                }
                break;
            case "Historial de Nóminas":
                if (isAuthenticated) {
                    new HistorialNominasFrame(historialNominas);
                } else {
                    showMessage("Por favor, inicie sesión primero.");
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Seleccione una opción válida.");
                break;
        }
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
