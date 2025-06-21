package dpooFinal.logica;

public class Visitante extends Persona {
	
	private String motivoVisita;
	private String areaUniversidad;
	private String autorizadoPor;
	
	
	public Visitante(String nombre, String apellido, String numID,TipoPersonal tipo,String motivoVisita, String areaUniversidad, String autorizadoPor) {
		super(nombre, apellido, numID,tipo);
		
		setMotivoVisita(motivoVisita);
		setAreaUniversidad(areaUniversidad);
		setAutorizadoPor(autorizadoPor);
	}


	
	
	public String getAutorizadoPor() {
		return autorizadoPor;
	}




	public void setAutorizadoPor(String autorizadoPor) {
		if(autorizadoPor.isEmpty() || autorizadoPor==null){
			new Exception("Error");
		}else{
		this.autorizadoPor = autorizadoPor;
		}
	}




	public String getMotivoVisita() {
		return motivoVisita;
	}

	public void setMotivoVisita(String motivoVisita) {
		if(motivoVisita.isEmpty() || motivoVisita==null){
			new Exception("Error");
		}else{
		this.motivoVisita = motivoVisita;
		}
	}


	public String getAreaUniversidad() {
		return areaUniversidad;
	}

	public void setAreaUniversidad(String areaUniversidad) {
		if(areaUniversidad.isEmpty() || areaUniversidad==null){
			new Exception("Error");
		}else{
		this.areaUniversidad = areaUniversidad;
		}
	}


	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Visitante;
	}
	
	

}
