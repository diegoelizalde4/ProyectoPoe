
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AreaJefeDeArea extends JFrame {
    private JTable jefeTable;
    private List<Empleado> empleados;

    public AreaJefeDeArea(List<Empleado> empleados) {
        this.empleados = empleados;

        setTitle("Empleados del Área Jefe de Área");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Apellido", "Dirección", "Correo", "Cargo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        jefeTable = new JTable(tableModel);

        actualizarTabla(tableModel);

        JScrollPane scrollPane = new JScrollPane(jefeTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void actualizarTabla(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (Empleado empleado : empleados) {
            if (empleado.getCargo().equals("Jefe de Area")) {
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
