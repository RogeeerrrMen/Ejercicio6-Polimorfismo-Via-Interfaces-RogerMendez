public class PasarelaTarjeta extends IntegracionAPI implements Transaccional, Auditable {

    private String tipoTarjeta;
    private double comision;

    public PasarelaTarjeta(String id, String proveedor, String endpoint, boolean conectado, double latenciaPromedio, String tipoTarjeta, double comision) {

        super(id, proveedor, endpoint, conectado, latenciaPromedio);

        if (tipoTarjeta == null || tipoTarjeta.equals("")) {

            throw new IllegalArgumentException("Tipo de tarjeta no valido");
        }

        if (comision < 0 || comision > 100) {

            throw new IllegalArgumentException("Comision invalida");
        }

        this.tipoTarjeta = tipoTarjeta;
        this.comision = comision;
    }

    public String getTipoTarjeta() {

        return tipoTarjeta;
    }

    public double getComision() {

        return comision;
    }

    @Override
    public boolean procesarCobro(double monto) {

        if (!estaConectado() || monto <= 0 || Double.isNaN(monto) || Double.isInfinite(monto)) {

            return false;
        }

        return true;
    }

    @Override
    public boolean procesarReintegro(double monto) {

        if (!estaConectado() || monto <= 0 || Double.isNaN(monto) || Double.isInfinite(monto)) {

            return false;
        }

        return true;
    }

    @Override
    public String generarAuditoria() {

        String auditoria = "Auditoria de pasarela de tarjeta";
        auditoria += "\nID: " + getId();
        auditoria += "\nProveedor: " + getProveedor();
        auditoria += "\nConectado: " + estaConectado();

        return auditoria;
    }

    @Override
    public String obtenerTipoServicio() {

        return "Pasarela de pago";
    }

    @Override
    public String obtenerConfiguracion() {

        String configuracion = super.obtenerConfiguracion();
        configuracion += "\nTipo de tarjeta: " + tipoTarjeta;
        configuracion += "\nComision: " + comision + "%";

        return configuracion;
    }

}