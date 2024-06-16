

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegistroHoras extends JFrame {
    private JTextField idField;
    private JTextField horasField;
    private JButton validarButton;
    private List<Empleado> empleados;
    private List<Nominas> nominas;

    public RegistroHoras(List<Empleado> empleados, List<Nominas> nominas) {
        this.empleados = empleados;
        this.nominas = nominas;

        setTitle("Registro de Horas");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        idField = new JTextField();
        horasField = new JTextField();
        validarButton = new JButton("Validar");

        add(new JLabel("ID del Empleado:"));
        add(idField);
        add(new JLabel("Horas Trabajadas:"));
        add(horasField);
        add(new JLabel(""));
        add(validarButton);

        validarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarHoras();
            }
        });

        setVisible(true);
    }

    private void validarHoras() {
        int id = Integer.parseInt(idField.getText());
        int horasTrabajadas = Integer.parseInt(horasField.getText());
        Empleado empleado = buscarEmpleadoPorId(id);

        if (empleado != null) {
            Nominas nomina = new Nominas(empleado, horasTrabajadas);
            nominas.add(nomina);
            JOptionPane.showMessageDialog(this, "Horas registradas para: " + empleado.getNombre() + " " + empleado.getApellido());
        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
        }
    }

    private Empleado buscarEmpleadoPorId(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
