
public class RegistroNomina {
    private Nominas nomina;
    private String fechaHora;

    public RegistroNomina(Nominas nomina, String fechaHora) {
        this.nomina = nomina;
        this.fechaHora = fechaHora;
    }

    public Nominas getNomina() {
        return nomina;
    }

    public String getFechaHora() {
        return fechaHora;
    }
}
