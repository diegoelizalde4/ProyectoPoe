

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmpleadosTabla extends JFrame {
    private JTable empleadosTable;
    private DefaultTableModel tableModel;

    public EmpleadosTabla(List<Empleado> empleados) {
        setTitle("Tabla de Empleados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Apellido", "Direccion", "Correo", "Cargo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        empleadosTable = new JTable(tableModel);

        for (Empleado empleado : empleados) {
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

        add(new JScrollPane(empleadosTable), BorderLayout.CENTER);
        setVisible(true);
    }
}
