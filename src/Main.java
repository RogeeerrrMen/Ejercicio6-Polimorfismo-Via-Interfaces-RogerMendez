public class Main {

    public static void main(String[] args) {

        ControladorIntegraciones controlador = new ControladorIntegraciones();

        VistaConsola vista = controlador.getVista();

        controlador.iniciar();

        while (controlador.estaEjecutando()) {

            mostrarMensaje(vista.obtenerMenu());

            int opcion = vista.leerEntero();

            String resultado = solicitarDatosOperacion(opcion, vista, controlador);

            mostrarMensaje(resultado);
        }

        mostrarMensaje("Programa finalizado");
    }

    private static void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }

    private static String solicitarDatosOperacion(int opcion, VistaConsola vista, ControladorIntegraciones controlador) {

        String resultado = "";

        switch (opcion) {

            case 1:
                resultado = controlador.consultarIntegraciones();
                break;

            case 2:
                String idConsulta = solicitarIdentificador(vista);
                resultado = controlador.consultarConfiguracion(idConsulta);
                break;

            case 3:
                resultado = controlador.ordenarIntegraciones();
                break;

            case 4:
                String idCobro = solicitarIdentificador(vista);
                double montoCobro = solicitarMonto(vista);
                resultado = controlador.realizarCobro(idCobro, montoCobro);
                break;

            case 5:
                String idReintegro = solicitarIdentificador(vista);
                double montoReintegro = solicitarMonto(vista);
                resultado = controlador.realizarReintegro(idReintegro, montoReintegro);
                break;

            case 6:
                String idDocumento = solicitarIdentificador(vista);

                mostrarMensaje("Ingrese la referencia:");
                String referencia = vista.leerTexto();

                resultado = controlador.solicitarDocumento(idDocumento, referencia);
                break;

            case 7:
                String idAuditoria = solicitarIdentificador(vista);
                resultado = controlador.consultarAuditoria(idAuditoria);
                break;

            case 8:
                controlador.finalizar();
                resultado = "Saliendo del sistema...";
                break;
            
            default:
                resultado = "Opcion invalida";
                break;
                
        }

        return resultado;
    }

    private static String solicitarIdentificador(VistaConsola vista) {

        mostrarMensaje("Ingrese el ID de la integracion:");

        return vista.leerTexto();
    }

    private static double solicitarMonto(VistaConsola vista) {

        double monto = -1;

        do {

            mostrarMensaje("Ingrese un monto mayor que cero:");

            monto = vista.leerDouble();

            if (monto <= 0) {

                mostrarMensaje("Monto invalido. Intente nuevamente.");
            }

        } while (monto <= 0);

        return monto;

    }
    
}