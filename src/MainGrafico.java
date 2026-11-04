public class MainGrafico {

    public static void main(String[] args) {

        ControladorIntegraciones controlador = new ControladorIntegraciones();

        controlador.iniciar();

        VistaGrafica vista = new VistaGrafica(controlador);
    }
}
