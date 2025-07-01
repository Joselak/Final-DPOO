package dpooFinal.src.logica;

public class Estudiante extends Persona {
	
	private int anio;
	private int grupo;
	
	public Estudiante(String nombre, String apellido, String numID,TipoPersonal tipo, int anio,
			int grupo) {
		super(nombre, apellido, numID,tipo);
		
		setAnio(anio);
		setGrupo(grupo);
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
	    if(anio<=0 ||anio>5){
	    	new Exception("Error");
	    }else{			
		this.anio = anio;
	    }
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		if(grupo<=0){
			new Exception("Error");
		}else{
		this.grupo = grupo;
		}
	}

	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Estudiante;
	}
	
	

}
