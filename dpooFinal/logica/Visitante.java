package dpooFinal.logica;

public class Visitante extends Persona{

    private String motivoVisita;
	private String areaUniversidad;
	private String autorizadoPor;
	
	
	public Visitante(String nombre, String apellido, String numID,TipoPersonal tipo,String motivoVisita, String areaUniversidad, String autorizadoPor) {
		super(nombre, apellido, numID,tipo);
		
		this.motivoVisita = motivoVisita;
		this.areaUniversidad = areaUniversidad;
		this.autorizadoPor = autorizadoPor;
	}


	
	
	public String getAutorizadoPor() {
		return autorizadoPor;
	}




	public void setAutorizadoPor(String autorizadoPor) {
		this.autorizadoPor = autorizadoPor;
	}




	public String getMotivoVisita() {
		return motivoVisita;
	}

	public void setMotivoVisita(String motivoVisita) {
		this.motivoVisita = motivoVisita;
	}


	public String getAreaUniversidad() {
		return areaUniversidad;
	}

	public void setAreaUniversidad(String areaUniversidad) {
		this.areaUniversidad = areaUniversidad;
	}


	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Visitante;
	}
	
	

}
