package dpooFinal.logica;

public class Directivo extends Profesor{

    private String area;
	private String cargo;
	
	
	public Directivo(String nombre, String apellido, String numID,TipoPersonal tipo,String departamento, String categDocente, String categCientifica,
			String tipoContrato,String area, String cargo) {
		super(nombre, apellido, numID,tipo, departamento, categDocente, categCientifica,
				tipoContrato);
		
		this.area=area;
		this.cargo=cargo;
		
	}


	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	


}
