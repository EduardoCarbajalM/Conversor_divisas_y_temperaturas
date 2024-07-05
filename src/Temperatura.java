
public class Temperatura implements ConversionVariables {
	
    private String unidadInicial;
    private String unidadFinal;

    
    public Temperatura(String unidadInicial, String unidadFinal) {
        this.unidadInicial = unidadInicial;
        this.unidadFinal = unidadFinal;
    }

    @Override
    public String getUnidadInicial() {
        return unidadInicial;
    }

    @Override
    public String getUnidadFinal() {
        return unidadFinal;
    }

    @Override
    public double realizarConversion(double cantidad) {
        return convertir(cantidad);
    }
    
    private double convertir(double cantidad) {
        if (unidadInicial.equalsIgnoreCase("Celsius") && unidadFinal.equalsIgnoreCase("Fahrenheit")) {
            return (cantidad * 9/5) + 32;
        } else if (unidadInicial.equalsIgnoreCase("Fahrenheit") && unidadFinal.equalsIgnoreCase("Celsius")) {
            return (cantidad - 32) * 5/9;
        } else if (unidadInicial.equalsIgnoreCase("Celsius") && unidadFinal.equalsIgnoreCase("Kelvin")) {
            return cantidad + 273.15;
        } else if (unidadInicial.equalsIgnoreCase("Kelvin") && unidadFinal.equalsIgnoreCase("Celsius")) {
            return cantidad - 273.15;
        } else if (unidadInicial.equalsIgnoreCase("Fahrenheit") && unidadFinal.equalsIgnoreCase("Kelvin")) {
            return (cantidad - 32) * 5/9 + 273.15;
        } else if (unidadInicial.equalsIgnoreCase("Kelvin") && unidadFinal.equalsIgnoreCase("Fahrenheit")) {
            return (cantidad - 273.15) * 9/5 + 32;
        } else {
            return cantidad; // Si las unidades son iguales, no hay conversión
        }
    }
}


