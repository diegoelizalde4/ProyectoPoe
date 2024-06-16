//by Diego Elizalde
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class MenuPrincipal extends JFrame {
    private JComboBox<String> menuComboBox;
    private JButton goButton;
    private List<Empleado> empleados;
    private List<Nominas> nominas;

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        empleados = new LinkedList<>();
        nominas = new LinkedList<>();

        String[] menuOptions = {"Seleccione una opción", "Registro de Empleados", "Tabla de Empleados", "Registro de Horas", "Calculadora de Nóminas", "Área General", "Área Jefe de Área", "Área Empleado"};
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
            case "Registro de Empleados":
                new FormularioEmpleados(empleados);
                break;
            case "Tabla de Empleados":
                new EmpleadosTabla(empleados);
                break;
            case "Registro de Horas":
                new RegistroHoras(empleados, nominas);
                break;
            case "Calculadora de Nóminas":
                new CalculadoraNominas(nominas);
                break;
            case "Área General":
                new AreaGeneral(empleados);
                break;
            case "Área Jefe de Área":
                new AreaJefeDeArea(empleados);
                break;
            case "Área Empleado":
                new AreaEmpleado(empleados);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Seleccione una opción válida.");
                break;
        }
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
