public class ControladorIntegraciones {

    private SistemaIntegraciones sistema;
    private VistaConsola vista;
    private boolean ejecutando;

    public ControladorIntegraciones() {

        sistema = new SistemaIntegraciones();
        vista = new VistaConsola();
        ejecutando = true;
    }

    public void iniciar() {

        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {

        sistema.registrarIntegracion(new PasarelaTarjeta("API-001", "TarjetaPlus", "api.tarjetaplus.com",
        true, 150, "Credito", 3.5));

        sistema.registrarIntegracion(new PasarelaTarjeta("API-002", "PagoSeguro", "api.pagoseguro.com",
        true, 95, "Debito", 2.5));

        sistema.registrarIntegracion(new PasarelaTransferencia("API-003", "BancoExpress", "api.bancoexpress.com",
        true, 120, "Banco Express", "123456789"));

        sistema.registrarIntegracion(new PasarelaTransferencia("API-004", "TransferGT", "api.transfergt.com",
        false, 180, "Banco GT", "987654321"));

        sistema.registrarIntegracion(new BilleteraDigital("API-005", "WalletGo", "api.walletgo.com",
        true, 75, "WalletGo", "GTQ"));

        sistema.registrarIntegracion(new BilleteraDigital("API-006", "PagoMovil", "api.pagomovil.com",
        true, 110, "PagoMovil", "USD"));

        sistema.registrarIntegracion(new ProveedorLogistico("API-007", "EnvioExpress", "api.envioexpress.com",
        true, 85, "Guatemala", 35));

        sistema.registrarIntegracion(new ProveedorLogistico("API-008", "EntregaRapida", "api.entregarapida.com",
        false, 200, "Centroamerica", 50));

        sistema.registrarIntegracion(new CertificadorFEL("API-009", "FacturaGT", "api.facturagt.com",
        true, 130, "CERT-GT", "1.0"));

        sistema.registrarIntegracion(new CertificadorFEL("API-010", "CertificaPlus", "api.certificaplus.com",
        true, 160, "CERT-PLUS", "2.0"));

    }

    public String consultarIntegraciones() {

        return vista.formatearIntegraciones(sistema.getIntegraciones());
    }

    public String ordenarIntegraciones() {

        sistema.ordenarPorLatencia();

        return vista.formatearIntegraciones(sistema.getIntegraciones());
    }

    public String consultarConfiguracion(String id) {

        IntegracionAPI integracion = sistema.buscarIntegracion(id);

        if (integracion == null) {

            return "Integracion no encontrada";
        }

        return vista.formatearConfiguracion(integracion);
    }

    public String realizarCobro(String id, double monto) {

        boolean exitoso = sistema.procesarCobro(id, monto);

        return vista.formatearResultado("Operacion de cobro", exitoso);
    }

    public String realizarReintegro(String id, double monto) {

        boolean exitoso = sistema.procesarReintegro(id, monto);

        return vista.formatearResultado("Solicitud de reintegro", exitoso);
    }

    public String solicitarDocumento(String id, String referencia) {

        String documento = sistema.generarDocumento(id, referencia);

        if (documento.equals("")) {

            return "No se pudo generar el documento";
        }

        return documento;
    }

    public String consultarAuditoria(String id) {

        String auditoria = sistema.generarAuditoria(id);

        if (auditoria.equals("")) {

            return "No se pudo obtener la auditoria";
        }

        return auditoria;
    }

    public boolean estaEjecutando() {

        return ejecutando;
    }

    public VistaConsola getVista() {

        return vista;
    }

    public void finalizar() {

        ejecutando = false;
    }
}