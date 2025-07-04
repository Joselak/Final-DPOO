package dpooFinal.src.logica;

public class Administrativo extends Persona {
	
	private String plaza;

	public Administrativo(String nombre, String apellido, String numID,TipoPersonal tipo,String plaza) {
		super(nombre, apellido, numID,tipo);
		
		setPlaza(plaza);
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		if(plaza.isEmpty() || plaza==null){
			new Exception("Error");
		}else{
		this.plaza = plaza;
		}
	}

	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Administrativo;
	}


}
