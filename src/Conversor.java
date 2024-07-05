import javax.swing.*;

public class Conversor {
    public static void main(String[] args) {
        String[] opciones = {"Conversor de Moneda", "Conversor de Temperatura", "Salir"};

        while (true) {
            String opcionSeleccionada = mostrarMenu(opciones);

            if (opcionSeleccionada != null) {
                switch (opcionSeleccionada) {
                    case "Conversor de Moneda":
                        realizarConversion(new Moneda[] {
                            new Moneda("Pesos Mexicanos", "Dólar", 0.058),
                            new Moneda("Pesos Mexicanos", "Euros", 0.054),
                            new Moneda("Pesos Mexicanos", "Libras Esterlinas", 0.046),
                            new Moneda("Pesos Mexicanos", "Yen Japonés", 8.53),
                            new Moneda("Pesos Mexicanos", "Won sul-coreano", 78.14)
                        });
                        break;
                    case "Conversor de Temperatura":
                        realizarConversionTemperatura();
                        break;
                    case "Salir":
                        System.exit(0);
                }
            } else {
                mostrarMensaje("No seleccionaste ninguna opción.");
            }
        }
    }

    private static String mostrarMenu(String[] opciones) {
        return (String) JOptionPane.showInputDialog(null,
                "Selecciona una opción:", "Menú", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
    }

    private static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void realizarConversion(ConversionVariables[] conversiones) {
        String[] opcionesConversion = obtenerOpcionesConversion(conversiones);
        String opcionSeleccionada = mostrarMenu(opcionesConversion);

        if (opcionSeleccionada != null) {
            int indiceSeleccionado = obtenerIndiceOpcion(opcionesConversion, opcionSeleccionada);
            double cantidad = obtenerCantidad();

            ConversionVariables conversionSeleccionada = conversiones[indiceSeleccionado];
            double resultado = conversionSeleccionada.realizarConversion(cantidad);

            mostrarMensaje(String.format("Cantidad ingresada: %.2f %s\nConvertida a: %.2f %s",
                    cantidad, conversionSeleccionada.getUnidadInicial(),
                    resultado, conversionSeleccionada.getUnidadFinal()));

            int continuar = mostrarConfirmacion("¿Quieres continuar?");
            if (continuar == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            mostrarMensaje("No seleccionaste ninguna opción de conversión.");
        }
    }

    private static void realizarConversionTemperatura() {
        String[] unidades = {"Celsius", "Fahrenheit", "Kelvin"};
        String unidadInicial = mostrarMenu(unidades, "Selecciona la unidad inicial:", "Unidades de Temperatura");
        String unidadFinal = mostrarMenu(unidades, "Selecciona la unidad final:", "Unidades de Temperatura");

        if (unidadInicial != null && unidadFinal != null) {
            ConversionVariables conversionTemperatura = new Temperatura(unidadInicial, unidadFinal);
            double cantidad = obtenerCantidad();
            double resultado = conversionTemperatura.realizarConversion(cantidad);

            mostrarMensaje(String.format("%.2f %s equivale a %.2f %s",
                    cantidad, unidadInicial, resultado, unidadFinal));
        }
    }

    private static String mostrarMenu(String[] opciones, String mensaje, String titulo) {
        return (String) JOptionPane.showInputDialog(null,
                mensaje, titulo, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
    }

    private static String[] obtenerOpcionesConversion(ConversionVariables[] conversiones) {
        String[] opciones = new String[conversiones.length];
        for (int i = 0; i < conversiones.length; i++) {
            opciones[i] = "Convertir de " + conversiones[i].getUnidadInicial() + " a " + conversiones[i].getUnidadFinal();
        }
        return opciones;
    }

    private static int obtenerIndiceOpcion(String[] opciones, String opcionSeleccionada) {
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(opcionSeleccionada)) {
                return i;
            }
        }
        return -1;
    }

    private static double obtenerCantidad() {
        String cantidadStr = JOptionPane.showInputDialog(null, "Ingresa la cantidad:", "Cantidad", JOptionPane.PLAIN_MESSAGE);
        if (cantidadStr != null && !cantidadStr.isEmpty()) {
            return Double.parseDouble(cantidadStr);
        } else {
            return 0.0;
        }
    }

    private static int mostrarConfirmacion(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
    }
}

