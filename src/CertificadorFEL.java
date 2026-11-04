public class CertificadorFEL extends IntegracionAPI implements Documentable, Auditable {

    private String codigoCertificador;
    private String versionXML;

    public CertificadorFEL(String id, String proveedor, String endpoint, boolean conectado, double latenciaPromedio, String codigoCertificador, String versionXML) {

        super(id, proveedor, endpoint, conectado, latenciaPromedio);

        if (codigoCertificador == null || codigoCertificador.equals("")) {

            throw new IllegalArgumentException("Codigo de certificador no valido");
        }

        if (versionXML == null || versionXML.equals("")) {

            throw new IllegalArgumentException("Version XML no valida");
        }

        this.codigoCertificador = codigoCertificador;
        this.versionXML = versionXML;
    }

    public String getCodigoCertificador() {

        return codigoCertificador;
    }

    public String getVersionXML() {

        return versionXML;
    }

    @Override
    public String generarDocumento(String referencia) {

        if (!estaConectado() || referencia == null || referencia.equals("")) {

            return "";
        }

        String documento = "<factura>";
        documento += "\n  <referencia>" + referencia + "</referencia>";
        documento += "\n  <certificador>" + codigoCertificador + "</certificador>";
        documento += "\n  <version>" + versionXML + "</version>";
        documento += "\n  <estado>Certificada</estado>";
        documento += "\n</factura>";

        return documento;
    }

    @Override
    public String generarAuditoria() {

        String auditoria = "Auditoria de certificador FEL";
        auditoria += "\nID: " + getId();
        auditoria += "\nProveedor: " + getProveedor();
        auditoria += "\nConectado: " + estaConectado();

        return auditoria;
    }

    @Override
    public String obtenerTipoServicio() {

        return "Certificador FEL";
    }

    @Override
    public String obtenerConfiguracion() {

        String configuracion = super.obtenerConfiguracion();
        configuracion += "\nCodigo de certificador: " + codigoCertificador;
        configuracion += "\nVersion XML: " + versionXML;

        return configuracion;
    }
}