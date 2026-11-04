public class BilleteraDigital extends IntegracionAPI implements Transaccional, Auditable {

    private String plataforma;
    private String moneda;

    public BilleteraDigital(String id, String proveedor, String endpoint, boolean conectado, double latenciaPromedio, String plataforma, String moneda) {

        super(id, proveedor, endpoint, conectado, latenciaPromedio);

        if (plataforma == null || plataforma.equals("")) {

            throw new IllegalArgumentException("Plataforma no valida");
        }

        if (moneda == null || moneda.equals("")) {

            throw new IllegalArgumentException("Moneda no valida");
        }

        this.plataforma = plataforma;
        this.moneda = moneda;
    }

    public String getPlataforma() {

        return plataforma;
    }

    public String getMoneda() {

        return moneda;
    }

    @Override
    public boolean procesarCobro(double monto) {

        if (!estaConectado() || monto <= 0) {

            return false;
        }

        return true;
    }

    @Override
    public boolean procesarReintegro(double monto) {

        if (!estaConectado() || monto <= 0) {

            return false;
        }

        return true;
    }

    @Override
    public String generarAuditoria() {

        String auditoria = "Auditoria de billetera digital";
        auditoria += "\nID: " + getId();
        auditoria += "\nProveedor: " + getProveedor();
        auditoria += "\nConectado: " + estaConectado();

        return auditoria;
    }

    @Override
    public String obtenerTipoServicio() {

        return "Billetera digital";
    }

    @Override
    public String obtenerConfiguracion() {

        String configuracion = super.obtenerConfiguracion();
        configuracion += "\nPlataforma: " + plataforma;
        configuracion += "\nMoneda: " + moneda;

        return configuracion;    
    }
    
}