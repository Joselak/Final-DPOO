package dpooFinal.logica;

public abstract class Persona {
	
	protected String nombre;
	protected String apellido;
	protected String numID;
	protected TipoPersonal tipo;
	
	


	public Persona(String nombre, String apellido, String numID,TipoPersonal tipo) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.numID = numID;
		this.tipo = tipo;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNumID() {
		return numID;
	}
	public void setNumID(String numID) {
		this.numID = numID;
		
	}
	
	public String getNombreCompleto() {
	    return nombre + " " + apellido;
	}
	
	

	public abstract TipoPersonal getTipo();
	

}
