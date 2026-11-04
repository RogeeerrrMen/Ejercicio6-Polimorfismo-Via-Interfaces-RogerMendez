public abstract class IntegracionAPI implements Comparable<IntegracionAPI> {

    private final String id;
    private String proveedor;
    private String endpoint;
    private boolean conectado;
    private double latenciaPromedio;

    protected IntegracionAPI(String id, String proveedor, String endpoint, boolean conectado, double latenciaPromedio) {

        if (id == null || id.equals("")) {

            throw new IllegalArgumentException("ID no valido");
        }

        if (proveedor == null || proveedor.equals("")) {

            throw new IllegalArgumentException("Proveedor no valido");
        }

        if (endpoint == null || endpoint.equals("")) {

            throw new IllegalArgumentException("Endpoint no valido");
        }

        if (latenciaPromedio < 0) {

            throw new IllegalArgumentException("Latencia no valida");
        }

        this.id = id;
        this.proveedor = proveedor;
        this.endpoint = endpoint;
        this.conectado = conectado;
        this.latenciaPromedio = latenciaPromedio;

    }

    public String getId() {

        return id;
    }

    public String getProveedor() {

        return proveedor;
    }

    public String getEndpoint() {

        return endpoint;
    }

    public boolean estaConectado() {

        return conectado;
    }

    public double getLatenciaPromedio() {

        return latenciaPromedio;
    }

    public void setConectado(boolean conectado) {

        this.conectado = conectado;
    }

    public void setLatenciaPromedio(double latenciaPromedio) {

        if (latenciaPromedio < 0) {

            throw new IllegalArgumentException("Latencia no valida");
        }

        this.latenciaPromedio = latenciaPromedio;
    }

    public String obtenerConfiguracion() {

        String configuracion = "ID: " + id;
        configuracion += "\nProveedor: " + proveedor;
        configuracion += "\nEndpoint: " + endpoint;
        configuracion += "\nConectado: " + conectado;
        configuracion += "\nLatencia promedio: " + latenciaPromedio + " ms";

        return configuracion;
    }

    public abstract String obtenerTipoServicio(); 

    @Override
    public int compareTo(IntegracionAPI otra) {

        if (this.latenciaPromedio < otra.latenciaPromedio) {

            return -1;
        }

        if (this.latenciaPromedio > otra.latenciaPromedio) {

            return 1;
        }

        return this.id.compareTo(otra.id);
    }

    @Override
    public String toString() {

        String informacion = "ID: " + id;
        informacion += "\nProveedor: " + proveedor;
        informacion += "\nTipo: " + obtenerTipoServicio();
        informacion += "\nConectado: " + conectado;
        informacion += "\nLatencia: " + latenciaPromedio + " ms";

        return informacion;
    }

    @Override
    public boolean equals(Object objeto) {

        if (this == objeto) {

            return true;
        }

        if (objeto == null || !(objeto instanceof IntegracionAPI)) {

            return false;
        }

        IntegracionAPI otra = (IntegracionAPI) objeto;

        return this.id.equals(otra.id);
    }

    @Override
    public int hashCode() {

        return id.hashCode();
    }   

}