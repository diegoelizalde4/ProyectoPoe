

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalculadoraNominas extends JFrame {
    private JTable nominasTable;
    private DefaultTableModel tableModel;
    private JButton calcularButton;
    private JButton guardarButton;
    private List<Nominas> nominas;
    private HistorialNominas historialNominas;

    public CalculadoraNominas(List<Nominas> nominas, HistorialNominas historialNominas) {
        this.nominas = nominas;
        this.historialNominas = historialNominas;

        setTitle("Calculadora de Nóminas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Horas Trabajadas", "Salario Base", "Impuestos", "Prestaciones", "Salario Bruto", "Salario Neto"};
        tableModel = new DefaultTableModel(columnNames, 0);
        nominasTable = new JTable(tableModel);

        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(nominasTable);
        add(scrollPane, BorderLayout.CENTER);

        calcularButton = new JButton("Calcular");
        guardarButton = new JButton("Guardar Nóminas");
        guardarButton.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calcularButton);
        buttonPanel.add(guardarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularNominas();
                guardarButton.setEnabled(true);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarNominas();
            }
        });

        setVisible(true);
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Nominas nomina : nominas) {
            Empleado empleado = nomina.getEmpleado();
            Object[] rowData = {
                empleado.getId(),
                empleado.getNombre(),
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

    private void calcularNominas() {
        for (Nominas nomina : nominas) {
            double salarioBase = nomina.getHorasTrabajadas() * nomina.getSalarioBase();
            double impuestos = salarioBase * 0.10;
            double prestaciones = salarioBase * 0.10;
            double salarioBruto = salarioBase;
            double salarioNeto = salarioBase - impuestos - prestaciones;

            nomina.setSalarioBruto(salarioBruto);
            nomina.setImpuestos(impuestos);
            nomina.setPrestaciones(prestaciones);
            nomina.setSalarioNeto(salarioNeto);
        }
        actualizarTabla();
    }

    private void guardarNominas() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String fechaHora = formatter.format(date);

        for (Nominas nomina : nominas) {
            historialNominas.agregarRegistro(new RegistroNomina(nomina, fechaHora));
        }
        new HistorialNominasFrame(historialNominas);
    }
}
