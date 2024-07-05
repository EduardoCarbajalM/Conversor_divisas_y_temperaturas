import javax.swing.*;

public class ConversorPrueba {
    public static void main(String[] args) {
            String[] opciones = {"Conversor de Moneda", "Conversor de Temperatura", "Salir"};

            while (true) {
                // Mostrar cuadro de diálogo de selección
                String opcionSeleccionada = (String) JOptionPane.showInputDialog(null,
                        "Selecciona una opción:", "Menú", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

                if (opcionSeleccionada != null) {
                    switch (opcionSeleccionada) {
                        case "Conversor de Moneda":
                            realizarConversionMoneda();
                            break;
                        case "Conversor de Temperatura":
                            realizarConversionTemperatura();
                            break;
                        case "Salir":
                            System.exit(0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionaste ninguna opción.");
                }
            }
        }

    private static void realizarConversionMoneda() {
        Moneda[] conversionesPesosAMoneda = {
            new Moneda("Pesos Mexicanos", "Dólar", 0.058),
            new Moneda("Pesos Mexicanos", "Euros", 0.054),
            new Moneda("Pesos Mexicanos", "Libras Esterlinas", 0.046),
            new Moneda("Pesos Mexicanos", "Yen Japonés", 8.53),
            new Moneda("Pesos Mexicanos", "Won sul-coreano", 78.14)
        };

        Moneda[] conversionesMonedaAPesos = {
            new Moneda("Dólar", "Pesos Mexicanos", 17.24),
            new Moneda("Euros", "Pesos Mexicanos", 18.52),
            new Moneda("Libras Esterlinas", "Pesos Mexicanos", 21.74),
            new Moneda("Yen Japonés", "Pesos Mexicanos", 0.117),
            new Moneda("Won sul-coreano", "Pesos Mexicanos", 0.0128)
        };

        String[] opcionesMoneda = new String[conversionesPesosAMoneda.length + conversionesMonedaAPesos.length];
        int indiceOpciones = 0;

        for (Moneda conversion : conversionesPesosAMoneda) {
            opcionesMoneda[indiceOpciones] = "Convertir de " + conversion.getUnidadInicial() + " a " + conversion.getUnidadFinal();
            indiceOpciones++;
        }

        for (Moneda conversion : conversionesMonedaAPesos) {
            opcionesMoneda[indiceOpciones] = "Convertir de " + conversion.getUnidadInicial() + " a " + conversion.getUnidadFinal();
            indiceOpciones++;
        }

        // Mostrar cuadro de diálogo para seleccionar opción de conversión
        String opcionMonedaSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Selecciona una opción de conversión:",
                "Opciones de Conversión de Moneda",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesMoneda,
                opcionesMoneda[0]);

        if (opcionMonedaSeleccionada != null) {
            int indiceMonedaSeleccionada = obtenerIndiceMoneda(opcionesMoneda, opcionMonedaSeleccionada);
            double cantidad = obtenerCantidad();
            double resultado = 0.0;
            String unidadInicial = "";
            String unidadFinal = "";

            if (indiceMonedaSeleccionada < conversionesPesosAMoneda.length) {
                resultado = conversionesPesosAMoneda[indiceMonedaSeleccionada].realizarConversion(cantidad);
                unidadInicial = conversionesPesosAMoneda[indiceMonedaSeleccionada].getUnidadInicial();
                unidadFinal = conversionesPesosAMoneda[indiceMonedaSeleccionada].getUnidadFinal();
            } else {
                int indiceConversionMonedaAPesos = indiceMonedaSeleccionada - conversionesPesosAMoneda.length;
                resultado = conversionesMonedaAPesos[indiceConversionMonedaAPesos].realizarConversion(cantidad);
                unidadInicial = conversionesMonedaAPesos[indiceConversionMonedaAPesos].getUnidadInicial();
                unidadFinal = conversionesMonedaAPesos[indiceConversionMonedaAPesos].getUnidadFinal();
            }

            JOptionPane.showMessageDialog(null,
                    String.format("Cantidad ingresada: %.2f %s\n"
                            + "Convertida a: %.2f %s",
                            cantidad, unidadInicial,
                            resultado, unidadFinal),
                    "Resultado de Conversión",
                    JOptionPane.INFORMATION_MESSAGE);

            int continuar = JOptionPane.showConfirmDialog(null,
                    "¿Quieres continuar?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION);

            if (continuar == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "No seleccionaste ninguna opción de conversión.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

        private static void realizarConversionTemperatura() {
                	String[] unidades = {"Celsius", "Fahrenheit", "Kelvin"};
                    
                    // Mostrar cuadro de diálogo para seleccionar unidades
                    String unidadInicial = (String) JOptionPane.showInputDialog(null,
                            "Selecciona la unidad inicial:", "Unidades de Temperatura", JOptionPane.PLAIN_MESSAGE, null, unidades, unidades[0]);

                    String unidadFinal = (String) JOptionPane.showInputDialog(null,
                            "Selecciona la unidad final:", "Unidades de Temperatura", JOptionPane.PLAIN_MESSAGE, null, unidades, unidades[0]);

                    if (unidadInicial != null && unidadFinal != null) {
                        Temperatura conversionTemperatura = new Temperatura(unidadInicial, unidadFinal);

                        String cantidadStr = JOptionPane.showInputDialog(null,
                                "Ingresa la cantidad a convertir:", "Convertir Temperatura", JOptionPane.PLAIN_MESSAGE);

                        if (cantidadStr != null && !cantidadStr.isEmpty()) {
                            try {
                                double cantidad = Double.parseDouble(cantidadStr);
                                double resultado = conversionTemperatura.realizarConversion(cantidad);

                                JOptionPane.showMessageDialog(null,
                                        String.format("%.2f %s equivale a %.2f %s",
                                                cantidad, unidadInicial, resultado, unidadFinal),
                                        "Resultado de Conversión",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null,
                                        "Ingresa una cantidad válida.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "No ingresaste ninguna cantidad.",
                                    "Información",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }               
                }

				private static int obtenerIndiceMoneda(String[] opciones, String opcionSeleccionada) {
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
}
				
				








