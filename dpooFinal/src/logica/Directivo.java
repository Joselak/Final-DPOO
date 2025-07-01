package dpooFinal.src.logica;

public class Directivo extends Profesor{
	
	private String area;
	private String cargo;
	
	
	public Directivo(String nombre, String apellido, String numID,TipoPersonal tipo,String departamento, String categDocente, String categCientifica,
			String tipoContrato,String area, String cargo) {
		super(nombre, apellido, numID,tipo, departamento, categDocente, categCientifica,
				tipoContrato);
		
		setArea(area);
		setCargo(cargo);
		
	}


	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		if(area.isEmpty()|| area==null){
			new Exception("Error");
		}else{
		this.area = area;
		}
	}


	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		if(cargo.isEmpty()|| cargo==null){
			new Exception("Error");
		}
		this.cargo = cargo;
	}
	
	


}
