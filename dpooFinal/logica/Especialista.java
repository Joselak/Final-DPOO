package dpooFinal.logica;

public class Especialista extends Persona{

    private String proyecto;

	public Especialista(String nombre, String apellido, String numID ,TipoPersonal tipo,String proyecto) {
		super(nombre, apellido, numID,tipo);
		
		this.proyecto=proyecto;

	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Especialista;
	}
	

}
