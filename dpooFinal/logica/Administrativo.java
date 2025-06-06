package dpooFinal.logica;

public class Administrativo  extends Persona{

    private String plaza;

	public Administrativo(String nombre, String apellido, String numID,TipoPersonal tipo,String plaza) {
		super(nombre, apellido, numID,tipo);
		
		this.plaza=plaza;
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}

	@Override
	public TipoPersonal getTipo() {
		return TipoPersonal.Administrativo;
	}

	
	
	
}
