package dpooFinal.logica;

public class Profesor extends Persona{

    protected String departamento;
	protected String categDocente;
	protected String categCientifica;
	protected String tipoContrato;
	
	
	public Profesor(String nombre, String apellido, String numID,TipoPersonal tipo,String departamento, String categDocente, String categCientifica,
			String tipoContrato) {
		super(nombre, apellido, numID,tipo);
		
		this.departamento = departamento;
		this.categDocente = categDocente;
		this.categCientifica = categCientifica;
		this.tipoContrato = tipoContrato;
		
	}



	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	
	public String getCategDocente() {
		return categDocente;
	}

	public void setCategDocente(String categDocente) {
		this.categDocente = categDocente;
	}

	
	public String getCategCientifica() {
		return categCientifica;
	}

	public void setCategCientifica(String categCientifica) {
		this.categCientifica = categCientifica;
	}

	
	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}



	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Profesor;
	}

}
