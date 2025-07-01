package dpooFinal.src.logica;

public class Profesor extends Persona {
	

	protected String departamento;
	protected String categDocente;
	protected String categCientifica;
	protected String tipoContrato;
	
	
	public Profesor(String nombre, String apellido, String numID,TipoPersonal tipo,String departamento, String categDocente, String categCientifica,
			String tipoContrato) {
		super(nombre, apellido, numID,tipo);
		
		setDepartamento(departamento);
		setCategDocente(categDocente);
		setCategCientifica(categCientifica);
		setTipoContrato(tipoContrato);
		
	}



	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		if(departamento.isEmpty() || departamento==null){
			new Exception("Error");
		}else{
		this.departamento = departamento;
		}
	}

	
	public String getCategDocente() {
		return categDocente;
	}

	public void setCategDocente(String categDocente) {
		if(categDocente.isEmpty() || categDocente==null){
			new Exception("Error");
		}else{
		this.categDocente = categDocente;
		}
	}

	
	public String getCategCientifica() {
		return categCientifica;
	}

	public void setCategCientifica(String categCientifica) {
		if(categCientifica.isEmpty() || categCientifica==null){
			new Exception("Error");
		}else{
		this.categCientifica = categCientifica;
		}
	}

	
	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		if(tipoContrato.isEmpty() || tipoContrato==null){
			new Exception("Error");
		}else{
		this.tipoContrato = tipoContrato;
		}
	}



	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Profesor;
	}
	
	
	

}
