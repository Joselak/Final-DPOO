package dpooFinal.logica;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Local {

    private String id;
	private Clasificacion tipoLocal;
	private ArrayList<Registro>registros;
	
	
	public Local(String id, Clasificacion tipoLocal) {
		
		this.id = id;
		this.tipoLocal = tipoLocal;
		registros = new ArrayList<>();
	}


	public ArrayList<Registro> getRegistros() {
		return registros;
	}


	public void setRegistros(ArrayList<Registro> registros) {
		this.registros = registros;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Clasificacion getTipoLocal() {
		return tipoLocal;
	}

	public void setTipoLocal(Clasificacion tipoLocal) {
		this.tipoLocal = tipoLocal;
	}
	
	
	
	
	
	
	
	 // METODO PARA REGISTRAR ACCESO
    public void registrarAcceso(Persona persona, LocalDateTime horaEntrada,LocalDateTime horaSalida) {
        Registro registro = new Registro(persona, horaEntrada,horaSalida);
        registros.add(registro);
    }

    // METODO PARA REGISTRAR SALIDA
    public boolean registrarSalida(Persona persona, LocalDateTime horaSalida) {
        for (Registro registro : registros) {
            if (registro.getPersona().getNumID().equals(persona.getNumID()) 
                && registro.getHoraSalida() == null) {
                registro.setHoraSalida(horaSalida);
                return true; 
            }
        }
        return false; 
    }

    
    
 // ACCESOS EN UN INTERVALO DE TIEMPO
    public ArrayList<Registro> getAccesosEnIntervalo(LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Registro> resultado = new ArrayList<>();
        for (Registro registro : registros) {
            if (registro.getHoraEntrada().isAfter(inicio) && 
                registro.getHoraEntrada().isBefore(fin)) {
                resultado.add(registro);
            }
        }
        return resultado;
    }


}

