package dpooFinal.logica;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Facultad {
		
	
	private ArrayList<Local>locales;
	private ArrayList<Persona>personas;
    boolean encontrar;

	private static Facultad instancia;
	
    private Facultad() {
        locales = new ArrayList<>();
        personas = new ArrayList<>();
    }
 
    public static Facultad getInstancia() {
        if (instancia == null) {
            instancia = new Facultad();
        }
        return instancia;
    }
    
    
    public ArrayList<Local> getLocales() {
		return locales;
	}



	public void setLocales(ArrayList<Local> locales) {
		this.locales = locales;
	}



	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}



	public ArrayList<Persona> getPersonas() {
        return personas;
    }
    

    //BUSCAR EL LOCAL
    public boolean buscarLocal(String id) {
    	boolean encontrado=false;
        for (Local local : locales) {
            if (local.getId().equals(id)) {
                encontrado= true; 
            }
            
        }
        return encontrado;
    }
    
    
    public boolean existeLocalConClasificacion(Clasificacion clasificacion) {
        for (Local local : locales) {
            if (local.getTipoLocal() == clasificacion) {
                return true;
            }
        }
        return false;
    }
    
    
    public Local getPrimerLocalPorClasificacion(Clasificacion clasificacion) {
        for (Local local : locales) {
            if (local.getTipoLocal() == clasificacion) {
                return local;
            }
        }
        return null;
    }
    
    
    //BUSCAR PERSONA
    public Persona buscarPersona(String carnet) {
        for (Persona persona : personas) {
            if (persona.getNumID().equals(carnet)) {
                return persona;
            }
        }
        return null;
    }
    
    //AGREGAR LOCAL
     public void agregarLocal(Local local) { 
    	locales.add(local);  	
    }
     
     public void agregarPersona(Persona persona) { 
     	personas.add(persona); 
     }
    
     //VERIFICAR EL ACCESO AL LOCAL
     public boolean verificarAcceso(Local local, Persona persona) {
    	 
    	    if (!buscarLocal(local.getId())) {
    	    encontrar= false;
    	    }
    	    return encontrar=revisarAcceso(persona, local, LocalDateTime.now());
    	}
    
    
    
    
    
 // VERIFICAR SI UNA PERSONA PUEDE ACCEDER A UN LOCAL DADO
    public boolean revisarAcceso(Persona persona, Local local, LocalDateTime hora) {


    	boolean retorno=false;
    	LocalTime horaActual = hora.toLocalTime();
        TipoPersonal tipoPersona = persona.getTipo();
        Clasificacion tipoLocal = local.getTipoLocal();

        // DIRECTIVOS Y ADMINISTRATIVOS TIENEN ACCESO LIBRE
        if (tipoPersona == TipoPersonal.Directivo || tipoPersona == TipoPersonal.Administrativo) {
            retorno= true;
        }
        else{
     // REGLAS DE ACCESO SEGÚN LA HORA Y EL LOCAL
        switch (persona.getTipo()) {
            case Profesor:
                if (tipoLocal.equals(Clasificacion.Estudiantes) || tipoLocal.equals(Clasificacion.Profesores) || 
                    tipoLocal.equals(Clasificacion.Aula) || tipoLocal.equals(Clasificacion.Laboratorios)) {
                    retorno= true;
                } else if (horaActual.isAfter(LocalTime.of(8, 0)) && horaActual.isBefore(LocalTime.of(17, 0))) {
                    retorno= true;
                }
                break;
                
            case Especialista:
            case Tecnico:
                if (tipoLocal.equals(Clasificacion.Servidores) || tipoLocal.equals(Clasificacion.Laboratorios) || 
                    tipoLocal.equals(Clasificacion.Estudiantes) || tipoLocal.equals(Clasificacion.Especialistas)) {
                    retorno = true;
                } else if (horaActual.isAfter(LocalTime.of(8, 0)) &&  horaActual.isBefore(LocalTime.of(17, 0))) {
                	
                    retorno = true;
                }
                break;
                
            case Estudiante:
                if ((tipoLocal.equals(Clasificacion.Estudiantes) || tipoLocal.equals(Clasificacion.Aula) || 
                     tipoLocal.equals(Clasificacion.Laboratorios) || tipoLocal.equals(Clasificacion.Profesores)) &&
                    horaActual.isAfter(LocalTime.of(8, 0)) && horaActual.isBefore(LocalTime.of(17, 0))) {
                	
                    retorno = true;
                }
                break;
                
            case Visitante:
                if (horaActual.isAfter(LocalTime.of(8, 0)) && horaActual.isBefore(LocalTime.of(12, 0))) {
                    retorno = true;
                }
                break;
		default:
			retorno=false;
			break;
        }       
    }
		return retorno;
    }
    
    

   
    
    // REPORTE 1
    public ArrayList<Persona> obtenerPersonasEnLocalEnIntervalo(String codigoLocal, LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Persona> resultado = new ArrayList<>();
        for (Local local : locales) {
            if (local.getId().equals(codigoLocal)) {
                ArrayList<Registro> accesos = local.getAccesosEnIntervalo(inicio, fin);
                for (Registro acceso : accesos) {
                    resultado.add(acceso.getPersona());
                }
            }
        }
        return resultado;
    }


    
    // REPORTE 2
    public ArrayList<Local> obtenerLocalesVisitadosPorPersona(String numeroId, LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Local> resultado = new ArrayList<>();
        for (Local local : locales) {
            ArrayList<Registro> accesos = local.getAccesosEnIntervalo(inicio, fin);
            for (Registro acceso : accesos) {
                if (acceso.getPersona().getNumID().equals(numeroId)) {
                    resultado.add(local);
                    
                }
            }
        }
        return resultado;
    }
    

}
