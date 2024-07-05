
public class Moneda implements ConversionVariables{
	
    private String unidadInicial;
    private String unidadFinal;
    private double tasaCambio;

    
    public Moneda(String unidadInicial, String unidadFinal, double tasaCambio) {
        this.unidadInicial = unidadInicial;
        this.tasaCambio = tasaCambio;        
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
    	if (unidadInicial.equalsIgnoreCase("Pesos Mexicanos")) {
            return cantidad * tasaCambio;
        } else if (unidadFinal.equalsIgnoreCase("Pesos Mexicanos")) {
            return cantidad / tasaCambio;
        } else {
            return cantidad; // Si las unidades son iguales, no hay conversión
        }
    }
}

