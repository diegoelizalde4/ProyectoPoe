

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculadoraNominas extends JFrame {
    private JTable nominasTable;
    private JButton calcularButton;
    private List<Nominas> nominas;

    public CalculadoraNominas(List<Nominas> nominas) {
        this.nominas = nominas;

        setTitle("Calculadora de Nóminas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Apellido", "Horas Trabajadas", "Salario Base", "Impuestos", "Prestaciones", "Salario Bruto", "Salario Neto"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        nominasTable = new JTable(tableModel);

        actualizarTabla(tableModel);

        JScrollPane scrollPane = new JScrollPane(nominasTable);
        add(scrollPane, BorderLayout.CENTER);

        calcularButton = new JButton("Calcular");
        add(calcularButton, BorderLayout.SOUTH);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularNominas(tableModel);
            }
        });

        setVisible(true);
    }

    private void actualizarTabla(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (Nominas nomina : nominas) {
            Object[] rowData = {
                nomina.getEmpleado().getId(),
                nomina.getEmpleado().getNombre(),
                nomina.getEmpleado().getApellido(),
                nomina.getHorasTrabajadas(),
                nomina.getSalarioBase(),
                nomina.getImpuestos(),
                nomina.getPrestaciones(),
                nomina.getSalarioBruto(),
                nomina.getSalarioNeto()
            };
            tableModel.addRow(rowData);
        }
    }

    private void calcularNominas(DefaultTableModel tableModel) {
        for (Nominas nomina : nominas) {
            double salarioBruto = nomina.getHorasTrabajadas() * nomina.getSalarioBase();
            double impuestos = salarioBruto * 0.10;
            double prestaciones = salarioBruto * 0.10;
            double salarioNeto = salarioBruto - impuestos - prestaciones;

            nomina.setImpuestos(impuestos);
            nomina.setPrestaciones(prestaciones);
            nomina.setSalarioBruto(salarioBruto);
            nomina.setSalarioNeto(salarioNeto);
        }
        actualizarTabla(tableModel);
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
