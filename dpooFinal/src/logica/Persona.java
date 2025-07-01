package dpooFinal.src.logica;

public abstract class Persona {
	
	protected String nombre;
	protected String apellido;
	protected String numID;
	protected TipoPersonal tipo;
	
	


	public Persona(String nombre, String apellido, String numID,TipoPersonal tipo) {
		
		setNombre(nombre);
		setApellido(apellido);
		setNumID(numID);
		this.tipo = tipo;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if(nombre.isEmpty() || nombre==null){
			new Exception("Error");
		}else{
		this.nombre = nombre;
		}
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		if(apellido.isEmpty() ||apellido==null){
			new Exception("Error");
		}else{
		this.apellido = apellido;
		}
	}
	public String getNumID() {
		return numID;
	}
	public void setNumID(String numID) {
		if(numID.isEmpty() || numID==null){
			new Exception("Error");
		}else{
		this.numID = numID;
		}
		
	}
	
	public String getNombreCompleto() {
	    return nombre + " " + apellido;
	}
	
	

	public abstract TipoPersonal getTipo();
	

}
