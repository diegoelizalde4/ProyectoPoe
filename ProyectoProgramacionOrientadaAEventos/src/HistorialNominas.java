
import java.util.ArrayList;
import java.util.List;

public class HistorialNominas {
    private List<RegistroNomina> registros;

    public HistorialNominas() {
        registros = new ArrayList<>();
    }

    public void agregarRegistro(RegistroNomina registro) {
        registros.add(registro);
    }

    public List<RegistroNomina> getRegistros() {
        return registros;
    }
}
