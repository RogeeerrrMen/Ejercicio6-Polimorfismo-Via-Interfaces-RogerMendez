public class PasarelaTransferencia extends IntegracionAPI implements Transaccional, Auditable {

    private String banco;
    private String numeroCuenta;

    public PasarelaTransferencia(String id, String proveedor, String endpoint, boolean conectado, double latenciaPromedio, String banco, String numeroCuenta) {

        super(id, proveedor, endpoint, conectado, latenciaPromedio);

        if (banco == null || banco.equals("")) {

            throw new IllegalArgumentException("Banco no valido");
        }

        if (numeroCuenta == null || numeroCuenta.equals("")) {

            throw new IllegalArgumentException("Numero de cuenta no valido");
        }

        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {

        return banco;
    }

    public String getNumeroCuenta() {

        return numeroCuenta;
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

        String auditoria = "Auditoria de transferencia bancaria";
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
        configuracion += "\nBanco: " + banco;
        configuracion += "\nNumero de cuenta: ****";

        return configuracion;
    }

}