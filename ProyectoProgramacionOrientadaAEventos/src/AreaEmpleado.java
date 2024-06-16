
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AreaEmpleado extends JFrame {
    private JTable empleadoTable;
    private List<Empleado> empleados;

    public AreaEmpleado(List<Empleado> empleados) {
        this.empleados = empleados;

        setTitle("Empleados del Área Empleado");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Apellido", "Dirección", "Correo", "Cargo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        empleadoTable = new JTable(tableModel);

        actualizarTabla(tableModel);

        JScrollPane scrollPane = new JScrollPane(empleadoTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void actualizarTabla(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (Empleado empleado : empleados) {
            if (empleado.getCargo().equals("Empleado")) {
                Object[] rowData = {
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getDireccion(),
                    empleado.getCorreo(),
                    empleado.getCargo()
                };
                tableModel.addRow(rowData);
            }
        }
    }
}
