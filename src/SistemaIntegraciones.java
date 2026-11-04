import java.util.ArrayList;
import java.util.Collections;

public class SistemaIntegraciones {

    private ArrayList<IntegracionAPI> integraciones;

    public SistemaIntegraciones() {

        integraciones = new ArrayList<IntegracionAPI>();
    }

    public boolean registrarIntegracion(IntegracionAPI integracion) {

        if (integracion == null) {

            return false;
        }

        if (buscarIntegracion(integracion.getId()) != null) {

            return false;
        }

        integraciones.add(integracion);
        return true;
    }

    public IntegracionAPI buscarIntegracion(String id) {

        if (id == null) {

            return null;
        }

        for (IntegracionAPI integracion : integraciones) {

            if (integracion.getId().equals(id)) {

                return integracion;
            }
        }

        return null;
    }

    public IntegracionAPI buscarIntegracion(IntegracionAPI integracion) {

        if (integracion == null) {

            return null;
        }

        return buscarIntegracion(integracion.getId());
    }

    public ArrayList<IntegracionAPI> getIntegraciones() {

        return new ArrayList<IntegracionAPI>(integraciones);
    }

    public void ordenarPorLatencia() {

        Collections.sort(integraciones);
    }

    public String obtenerConfiguracion(String id) {

        IntegracionAPI integracion = buscarIntegracion(id);

        if (integracion == null) {

            return "";
        }

        return integracion.obtenerConfiguracion();
    }

    public boolean procesarCobro(String id, double monto) {

        IntegracionAPI integracion = buscarIntegracion(id);

        if (integracion instanceof Transaccional) {

            Transaccional servicio = (Transaccional) integracion;
            return procesarCobro(servicio, monto);
        }

        return false;
    }

    private boolean procesarCobro(Transaccional servicio, double monto) {

        return servicio.procesarCobro(monto);
    }

    public boolean procesarReintegro(String id, double monto) {

        IntegracionAPI integracion = buscarIntegracion(id);

        if (integracion instanceof Transaccional) {

            Transaccional servicio = (Transaccional) integracion;
            return procesarReintegro(servicio, monto);
        }

        return false;
    }

    private boolean procesarReintegro(Transaccional servicio, double monto) {

        return servicio.procesarReintegro(monto);
    }

    public String generarDocumento(String id, String referencia) {

        IntegracionAPI integracion = buscarIntegracion(id);

        if (integracion instanceof Documentable) {

            Documentable servicio = (Documentable) integracion;
            return generarDocumento(servicio, referencia);
        }

        return "";
    }

    private String generarDocumento(Documentable servicio, String referencia) {

        return servicio.generarDocumento(referencia);
    }

    public String generarAuditoria(String id) {

        IntegracionAPI integracion = buscarIntegracion(id);

        if (integracion instanceof Auditable) {

            Auditable servicio = (Auditable) integracion;
            return generarAuditoria(servicio);
        }

        return "";
    }

    private String generarAuditoria(Auditable servicio) {

        return servicio.generarAuditoria();
    }
}