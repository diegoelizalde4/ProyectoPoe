

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormularioEmpleados extends JFrame {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField direccionField;
    private JTextField correoField;
    private JRadioButton generalButton;
    private JRadioButton jefeButton;
    private JRadioButton empleadoButton;
    private JButton registerButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private List<Empleado> empleados;

    public FormularioEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;

        setTitle("Formulario de Empleados");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2));

        idField = new JTextField();
        nombreField = new JTextField();
        apellidoField = new JTextField();
        direccionField = new JTextField();
        correoField = new JTextField();

        generalButton = new JRadioButton("General");
        jefeButton = new JRadioButton("Jefe de Area");
        empleadoButton = new JRadioButton("Empleado");
        ButtonGroup cargoGroup = new ButtonGroup();
        cargoGroup.add(generalButton);
        cargoGroup.add(jefeButton);
        cargoGroup.add(empleadoButton);

        registerButton = new JButton("Registrar");
        deleteButton = new JButton("Eliminar");
        updateButton = new JButton("Modificar");
        searchButton = new JButton("Buscar");

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Apellido:"));
        add(apellidoField);
        add(new JLabel("Direccion:"));
        add(direccionField);
        add(new JLabel("Correo:"));
        add(correoField);
        add(new JLabel("Cargo:"));
        add(generalButton);
        add(jefeButton);
        add(empleadoButton);
        add(registerButton);
        add(deleteButton);
        add(updateButton);
        add(searchButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerEmpleado();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmpleado();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmpleado();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmpleado();
            }
        });

        setVisible(true);
    }

    private void registerEmpleado() {
        int id = Integer.parseInt(idField.getText());
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String direccion = direccionField.getText();
        String correo = correoField.getText();
        String cargo = "";

        if (generalButton.isSelected()) {
            cargo = "General";
        } else if (jefeButton.isSelected()) {
            cargo = "Jefe de Area";
        } else if (empleadoButton.isSelected()) {
            cargo = "Empleado";
        }

        Empleado empleado = new Empleado(id, nombre, apellido, direccion, correo, cargo);
        empleados.add(empleado);
        JOptionPane.showMessageDialog(this, "Empleado registrado: " + empleado);
    }

    private void deleteEmpleado() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del empleado a eliminar:"));
        Empleado empleadoAEliminar = null;

        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                empleadoAEliminar = empleado;
                break;
            }
        }

        if (empleadoAEliminar != null) {
            empleados.remove(empleadoAEliminar);
            JOptionPane.showMessageDialog(this, "Empleado eliminado: " + empleadoAEliminar);
        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
        }
    }

    private void updateEmpleado() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del empleado a modificar:"));
        Empleado empleadoAActualizar = null;

        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                empleadoAActualizar = empleado;
                break;
            }
        }

        if (empleadoAActualizar != null) {
            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", empleadoAActualizar.getNombre());
            String nuevoApellido = JOptionPane.showInputDialog("Nuevo apellido:", empleadoAActualizar.getApellido());
            String nuevaDireccion = JOptionPane.showInputDialog("Nueva dirección:", empleadoAActualizar.getDireccion());
            String nuevoCorreo = JOptionPane.showInputDialog("Nuevo correo:", empleadoAActualizar.getCorreo());
            String[] opciones = {"General", "Jefe de Area", "Empleado"};
            String nuevoCargo = (String) JOptionPane.showInputDialog(null, "Nuevo cargo:", "Modificar Empleado", JOptionPane.QUESTION_MESSAGE, null, opciones, empleadoAActualizar.getCargo());

            empleadoAActualizar = new Empleado(id, nuevoNombre, nuevoApellido, nuevaDireccion, nuevoCorreo, nuevoCargo);

            for (int i = 0; i < empleados.size(); i++) {
                if (empleados.get(i).getId() == id) {
                    empleados.set(i, empleadoAActualizar);
                    break;
                }
            }

            JOptionPane.showMessageDialog(this, "Empleado modificado: " + empleadoAActualizar);
        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
        }
    }

    private void searchEmpleado() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del empleado a buscar:"));
        Empleado empleadoBuscado = null;

        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                empleadoBuscado = empleado;
                break;
            }
        }

        if (empleadoBuscado != null) {
            JOptionPane.showMessageDialog(this, "Empleado encontrado: " + empleadoBuscado);
        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
        }
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
