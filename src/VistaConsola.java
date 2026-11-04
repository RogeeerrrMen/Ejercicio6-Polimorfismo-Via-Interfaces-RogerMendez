import java.util.Scanner;
import java.util.ArrayList;

public class VistaConsola {

    private Scanner scanner;

    public VistaConsola() {

        scanner = new Scanner(System.in);
    }

    public int leerEntero() {

        int numero = -1;

        try {

            numero = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {

            numero = -1;
        }

        return numero;
    }

    public double leerDouble() {

        double numero = -1;

        try {

            numero = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {

            numero = -1;
        }

        return numero;
    }

    public String leerTexto() {

        return scanner.nextLine();
    }

    public String obtenerMenu() {

        String menu = "\n===== SISTEMA DE INTEGRACIONES =====";
        menu += "\n1. Listar integraciones";
        menu += "\n2. Consultar configuracion";
        menu += "\n3. Ordenar por latencia";
        menu += "\n4. Procesar cobro";
        menu += "\n5. Procesar reintegro";
        menu += "\n6. Generar documento";
        menu += "\n7. Consultar auditoria";
        menu += "\n8. Salir";
        menu += "\nSeleccione una opcion: ";

        return menu;
    }

    public String formatearIntegracion(IntegracionAPI integracion) {

        if (integracion == null) {

            return "Integracion no encontrada";
        }

        return integracion.toString();
    }

    public String formatearIntegraciones( ArrayList<IntegracionAPI> integraciones) {

        if (integraciones == null || integraciones.size() == 0) {

            return "No hay integraciones registradas";
        }

        String resultado = "\n===== INTEGRACIONES REGISTRADAS =====";

        for (IntegracionAPI integracion : integraciones) {

            resultado += "\n\n" + formatearIntegracion(integracion);
        }

        return resultado;
    }

    public String formatearConfiguracion(IntegracionAPI integracion) {

        if (integracion == null) {

            return "Integracion no encontrada";
        }

        return integracion.obtenerConfiguracion();
    }

    public String formatearResultado(String operacion, boolean exitoso) {

        String resultado = "";

        if (exitoso) {

            resultado = operacion + " realizada correctamente";
        } else {

            resultado = "No se pudo realizar: " + operacion;
        }

        return resultado;
    }

    
}