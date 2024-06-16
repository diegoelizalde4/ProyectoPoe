
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HistorialNominasFrame extends JFrame {
    private JTable historialTable;
    private DefaultTableModel tableModel;
    private JButton imprimirButton;

    public HistorialNominasFrame(HistorialNominas historial) {
        setTitle("Historial de Nóminas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Horas Trabajadas", "Salario Base", "Impuestos", "Prestaciones", "Salario Bruto", "Salario Neto", "Fecha y Hora"};
        tableModel = new DefaultTableModel(columnNames, 0);
        historialTable = new JTable(tableModel);

        for (RegistroNomina registro : historial.getRegistros()) {
            Nominas nomina = registro.getNomina();
            Empleado empleado = nomina.getEmpleado();
            Object[] rowData = {
                empleado.getId(),
                empleado.getNombre(),
                nomina.getHorasTrabajadas(),
                nomina.getSalarioBase(),
                nomina.getImpuestos(),
                nomina.getPrestaciones(),
                nomina.getSalarioBruto(),
                nomina.getSalarioNeto(),
                registro.getFechaHora()
            };
            tableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(historialTable);
        add(scrollPane, BorderLayout.CENTER);

        imprimirButton = new JButton("Imprimir TXT");
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    imprimirTXT();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(imprimirButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void imprimirTXT() throws IOException {
        String dest = "historial_nominas.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(dest));

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            writer.write(tableModel.getColumnName(i) + "\t");
        }
        writer.write("\n");

        for (int rows = 0; rows < tableModel.getRowCount(); rows++) {
            for (int cols = 0; cols < tableModel.getColumnCount(); cols++) {
                writer.write(tableModel.getValueAt(rows, cols).toString() + "\t");
            }
            writer.write("\n");
        }

        writer.close();

        JOptionPane.showMessageDialog(this, "Archivo TXT generado exitosamente.");
    }
}
