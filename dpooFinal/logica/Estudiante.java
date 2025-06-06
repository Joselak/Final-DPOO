package dpooFinal.logica;

public class Estudiante extends Persona{

    private int anio;
	private int grupo;
	
	public Estudiante(String nombre, String apellido, String numID,TipoPersonal tipo, int anio,
			int grupo) {
		super(nombre, apellido, numID,tipo);
		
		this.anio = anio;
		this.grupo = grupo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Estudiante;
	}
	

}
