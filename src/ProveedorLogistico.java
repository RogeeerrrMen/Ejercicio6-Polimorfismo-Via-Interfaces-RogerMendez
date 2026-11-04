public class ProveedorLogistico extends IntegracionAPI implements Documentable, Auditable {

    private String zonaCobertura;
    private double costoBaseEnvio;

    public ProveedorLogistico(String id, String proveedor, String endpoint, boolean conectado, double latenciaPromedio, String zonaCobertura, double costoBaseEnvio) {

        super(id, proveedor, endpoint, conectado, latenciaPromedio);
    

        if (zonaCobertura == null || zonaCobertura.equals("")) {

            throw new IllegalArgumentException("Zona de cobertura no valida");
        }

        if (costoBaseEnvio < 0) {

            throw new IllegalArgumentException("Costo de envio invalido");
        }

        this.zonaCobertura = zonaCobertura;
        this.costoBaseEnvio = costoBaseEnvio;

    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public double getCostoBaseEnvio() {
        return costoBaseEnvio;
    }

    @Override 
    public String generarDocumento(String referencia) {

        if (!estaConectado() || referencia == null || referencia.equals("")) {

            return "";
        }

        String documento = "GUIA DE ENVIO";
        documento += "\nReferencia: " + referencia;
        documento += "\nProveedor: " + getProveedor();
        documento += "\nZona de cobertura: " + zonaCobertura;
        documento += "\nCosto base: Q" + costoBaseEnvio;

        return documento;
    }

    @Override
    public String generarAuditoria() {

        String auditoria = "Auditoria de proveedor logistico";
        auditoria += "\nID: " + getId();
        auditoria += "\nProveedor: " + getProveedor();
        auditoria += "\nConectado: " + estaConectado();

        return auditoria;
    }

    @Override
    public String obtenerTipoServicio() {

        return "Proveedor logistico";
    }

    @Override
    public String obtenerConfiguracion() {

        String configuracion = super.obtenerConfiguracion();
        configuracion += "\nZona de cobertura: " + zonaCobertura;
        configuracion += "\nCosto base de envio: Q" + costoBaseEnvio;

        return configuracion;
    }

}