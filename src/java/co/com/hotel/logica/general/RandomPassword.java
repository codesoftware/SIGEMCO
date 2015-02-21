package co.com.hotel.logica.general;



public class RandomPassword {
	
	private String base;

	public RandomPassword() {
		this.base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	}
	
	public String generarContrasena(){
		String contra =  "";
		int LargoContrasena= 10;
		int longitud = this.base.length();
		
		for(int i=0; i<LargoContrasena ; i++){
			
		    int numero = (int)(Math.random()*(longitud));
		    String caracter=base.substring(numero, numero+1); 
			contra = contra + caracter;
			
		}
		
		return contra;
	}	
}
