package dpooFinal.src.logica;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        

        //CREACION DE LA FACULTAD
		Facultad f = Facultad.getInstancia();
		
		/*
		Profesor responsable = new Profesor("Juan", "Pérez", "123456789",TipoPersonal.Profesor,"Informática", "Titular", "Doctor", "Indeterminado");
        */
		
		// CREAR PERSONAS
        Estudiante estudiante = new Estudiante("Ana", "González", "987654321",TipoPersonal.Estudiante,2, 1);
        Profesor profesor = new Profesor("Carlos", "Martínez", "456123789",TipoPersonal.Profesor, "Informática", "Asistente", "Máster", "Determinado");
        Visitante visitante = new Visitante("Luis", "Rodríguez", "789456123",TipoPersonal.Visitante ,"Reunión", "Facultad de Derecho","Alejandro");
        Directivo directivo = new Directivo("Carlos", "Martínez", "456123789",TipoPersonal.Directivo, "Informática", "Asistente", "Máster", "Determinado","Docente","Conteble");
        Administrativo administrativo = new Administrativo("Carlos", "Martínez", "456123789",TipoPersonal.Administrativo,"Contable");
        
        f.agregarPersona(administrativo);
        f.agregarPersona(directivo);
        f.agregarPersona(visitante);
        f.agregarPersona(profesor);
        f.agregarPersona(estudiante);
       
        
        
        
		// CREAR LOCAALES
        Local aula1 = new Local("AULA-101", Clasificacion.Aula);
        Local labInformatica = new Local("LAB-001", Clasificacion.Laboratorios);
        Local decanato = new Local("DEC-201", Clasificacion.Decano);
        Local aula2 = new Local("AULA-102", Clasificacion.Aula);
        Local labInformatica2 = new Local("DEC-002", Clasificacion.Laboratorios);
        Local decanato2 = new Local("LAB-202", Clasificacion.Decano);
        
        f.agregarLocal(aula1);
        f.agregarLocal(labInformatica);
        f.agregarLocal(decanato);
        f.agregarLocal(aula2);
        f.agregarLocal(labInformatica2);
        f.agregarLocal(decanato2);
        
        

        //REGISTRAR ACCESOS
        aula1.registrarAcceso(estudiante,LocalDateTime.of(2025, 1, 10, 13, 21), LocalDateTime.of(2025, 1, 10, 14, 30));
        aula1.registrarAcceso(directivo,LocalDateTime.of(2025, 1, 10, 10, 21), LocalDateTime.of(2025, 1, 10, 12, 30));
        labInformatica.registrarAcceso(estudiante,LocalDateTime.of(2025, 1, 10, 10, 21), LocalDateTime.of(2025, 1, 10, 11, 30));
        decanato.registrarAcceso(directivo, LocalDateTime.of(2025, 1, 10, 15, 21), LocalDateTime.of(2025, 1, 10, 15, 30));
        //REGISTRAR SALIDAS
        labInformatica2.registrarSalida(visitante, LocalDateTime.of(2025, 1, 10, 9, 30));
        aula2.registrarSalida(administrativo, LocalDateTime.of(2025, 1, 10, 10, 30));
       

        
        
     // EJEMPLO DEL REPORTE 1
        System.out.println("\n--- REPORTE 1 ---");
        LocalDateTime inicio = LocalDateTime.of(2025, 1, 10, 10, 00); 
        LocalDateTime fin = LocalDateTime.of(2025, 1, 10, 16, 00); 
        ArrayList<Persona> personasEnLocal = f.obtenerPersonasEnLocalEnIntervalo("DEC-201", inicio, fin);
        if (personasEnLocal.isEmpty()) {
            System.out.println("No se encontraron personas en el local AULA-101 en ese intervalo.");
        }else{
        System.out.println("Personas que entraron al local DEC-201:");
        for (Persona p : personasEnLocal) {
            System.out.println("- " + p.getNombre() + " " + p.getApellido());
        }
        }  
        
        
        
        
        
        System.out.println("\n--- REPORTE 2 ---");
        String idPersona = "987654321"; // ID del estudiante Ana González
        LocalDateTime inicio1 = LocalDateTime.of(2025, 1, 10, 8, 0);
        LocalDateTime fin1 = LocalDateTime.of(2025, 1, 10, 18, 0);

        ArrayList<Local> localesVisitados = f.obtenerLocalesVisitadosPorPersona(idPersona, inicio1, fin1);

        if (localesVisitados.isEmpty()) {
            System.out.println("La persona con ID " + idPersona + " no visitó ningún local en ese intervalo.");
        } else {
            // Buscar el nombre de la persona para mostrarlo
            String nombrePersona = "";
            for (Persona p : f.getPersonas()) {
                if (p.getNumID().equals(idPersona)) {
                    nombrePersona = p.getNombre() + " " + p.getApellido();
                    break;
                }
            }
            
            System.out.println("Locales visitados por " + nombrePersona + " (ID: " + idPersona + "):");
            for (Local local : localesVisitados) {
                System.out.println("- " + local.getId() + " (" + local.getTipoLocal() + ")");
            }
        }
    }

    }


