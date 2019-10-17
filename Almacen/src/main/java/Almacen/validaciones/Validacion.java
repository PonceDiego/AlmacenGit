package main.java.Almacen.validaciones;

public class Validacion {
	
	public static boolean esNumerico(String str) {
		try {
	        int d = Integer.parseInt(str);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}

}
