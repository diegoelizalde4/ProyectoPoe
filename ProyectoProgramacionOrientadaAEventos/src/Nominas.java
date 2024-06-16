
public class Nominas {
    private Empleado empleado;
    private int horasTrabajadas;
    private double salarioBase;
    private double impuestos;
    private double prestaciones;
    private double salarioBruto;
    private double salarioNeto;

    public Nominas(Empleado empleado, int horasTrabajadas) {
        this.empleado = empleado;
        this.horasTrabajadas = horasTrabajadas;
        switch (empleado.getCargo()) {
            case "General":
                this.salarioBase = 100;
                break;
            case "Jefe de Area":
                this.salarioBase = 250;
                break;
            case "Empleado":
                this.salarioBase = 150;
                break;
        }
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(double prestaciones) {
        this.prestaciones = prestaciones;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(double salarioNeto) {
        this.salarioNeto = salarioNeto;
    }
}
