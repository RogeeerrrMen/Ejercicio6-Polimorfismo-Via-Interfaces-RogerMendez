import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaGrafica extends JFrame {

    private ControladorIntegraciones controlador;
    private JTextArea areaTexto;
    private JPanel panelBotones;

    public VistaGrafica(ControladorIntegraciones controlador) {

        this.controlador = controlador;

        setTitle("Sistema de Integraciones API");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

         setLayout(new BorderLayout());

        JLabel titulo = new JLabel("SISTEMA DE INTEGRACIONES API",SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        add(titulo, BorderLayout.NORTH);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(areaTexto);
        add(scroll, BorderLayout.CENTER);

        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 2, 10, 10));

        add(panelBotones, BorderLayout.SOUTH);

        crearBotones();

        areaTexto.setText(controlador.consultarIntegraciones());

        setVisible(true);

    }

    private void crearBotones() {

        String[] opciones = {

            "Listar integraciones",
            "Consultar API",
            "Ordenar por latencia",
            "Procesar cobro",
            "Procesar reintegro",
            "Generar documento",
            "Consultar auditoria",
            "Salir"
        };

        for (int i = 0; i < opciones.length; i++) {

            JButton boton = new JButton(opciones[i]);

            final int opcion = i + 1;

            boton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    procesarOpcion(opcion);
                }
            });

            panelBotones.add(boton);
        }
    }

    private void procesarOpcion(int opcion) {

        switch (opcion) {

            case 1:
                areaTexto.setText(
                        controlador.consultarIntegraciones());
                break;

            case 2:
                consultarAPI();
                break;

            case 3:
                areaTexto.setText(
                        controlador.ordenarIntegraciones());
                break;

            case 4:
                realizarCobro();
                break;

            case 5:
                realizarReintegro();
                break;

            case 6:
                generarDocumento();
                break;

            case 7:
                consultarAuditoria();
                break;

            case 8:
                dispose();
                break;
        }

    }

    private void consultarAPI() {

        String id = JOptionPane.showInputDialog( this, "Ingrese el ID de la integracion:");

        if (id != null) {

            areaTexto.setText(controlador.consultarConfiguracion(id));
        }
    }

    private void realizarCobro() {

        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la integracion:");

        if (id == null) {

            return;
        }

        String entrada = JOptionPane.showInputDialog(this, "Ingrese el monto del cobro:");

        if (entrada == null) {

            return;
        }

        try {

            double monto = Double.parseDouble(entrada);

            if (monto <= 0 || !(monto < Double.POSITIVE_INFINITY)) {

                areaTexto.setText("Monto invalido");

            } else {

                areaTexto.setText( controlador.realizarCobro(id, monto));
            }
            
        } catch (NumberFormatException e) {

            areaTexto.setText("Monto invalido");
        }

    }

    private void realizarReintegro() {

        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la integracion:");

        if (id == null) {

            return;
        }

        String entrada = JOptionPane.showInputDialog( this, "Ingrese el monto del reintegro:");

        if (entrada == null) {

            return;
        }

        try {

            double monto = Double.parseDouble(entrada);

            if (monto <= 0 || !(monto < Double.POSITIVE_INFINITY)) {

                areaTexto.setText("Monto invalido");

            } else {

                areaTexto.setText( controlador.realizarReintegro(id, monto));
            }

        } catch (NumberFormatException e) {

            areaTexto.setText("Monto invalido");
        }
    }

    private void generarDocumento() {

        String id = JOptionPane.showInputDialog( this, "Ingrese el ID de la integracion:");

        if (id == null) {

            return;
        }

        String referencia = JOptionPane.showInputDialog( this, "Ingrese la referencia del documento:");

        if (referencia == null) {

            return;
        }

        areaTexto.setText( controlador.solicitarDocumento(id, referencia));
    }

    private void consultarAuditoria() {

        String id = JOptionPane.showInputDialog( this, "Ingrese el ID de la integracion:");

        if (id != null) {

            areaTexto.setText(controlador.consultarAuditoria(id));
        }
    }
}