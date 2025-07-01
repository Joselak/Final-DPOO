package dpooFinal.inicializador;

import java.awt.EventQueue;
import java.awt.Window;
import java.time.LocalDateTime;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.prefs.Preferences;


import dpooFinal.interfaz.Principal;
import dpooFinal.logica.Administrativo;
import dpooFinal.logica.Clasificacion;
import dpooFinal.logica.Directivo;
import dpooFinal.logica.Estudiante;
import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Profesor;
import dpooFinal.logica.Tecnico;
import dpooFinal.logica.TipoPersonal;

public class Inicializador {
	
	private static boolean temaOscuro = false;
	
	public static void main(String[] args) {
		
        //PARA QUE LA BARRA DE MENU NO SE INTEGRE CON EL TTITULO DE LA VENTANA 
        UIManager.put("TitlePane.menuBarEmbedded", false);
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	/*--------------------------------------------------------------------------------------------------------------------------
	 * ----------------------------------------------------------------------------------------------------------------------
	 * ------------------------------------------------------------------------------------------------------------------------
	 * --------------------------------------------------------------------------------------------------------------------------*/
	
	//PARA CAMBIAR LOS TEMAS	
	 // Método para configurar el tema inicial al arrancar la aplicación
    public static void configurarTemaInicial() {
        temaOscuro = cargarPreferenciaTema();
        aplicarTema(temaOscuro);
    }

    // Método público para cambiar el tema (será llamado desde los botones)
    public static void cambiarTema() {
        temaOscuro = !temaOscuro;
        aplicarTema(temaOscuro);
        guardarPreferenciaTema(temaOscuro);
    }

    // Método para verificar el estado actual del tema
    public static boolean isTemaOscuro() {
        return temaOscuro;
    }

    // Método privado que aplica el tema seleccionado
    private static void aplicarTema(boolean oscuro) {
        try {
            if (oscuro) {
                FlatDarkLaf.setup();
            } else {
                FlatLightLaf.setup();
            }
            actualizarVentanasAbiertas();
        } catch (Exception e) {
            System.err.println("Error al aplicar el tema: " + e.getMessage());
        }
    }

    // Actualiza todas las ventanas abiertas
    private static void actualizarVentanasAbiertas() {
        for (Window window : Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window);
        }
    }

    // Guarda la preferencia del tema
    private static void guardarPreferenciaTema(boolean oscuro) {
        Preferences prefs = Preferences.userNodeForPackage(Inicializador.class);
        prefs.putBoolean("temaOscuro", oscuro);
    }

    // Carga la preferencia guardada del tema
    private static boolean cargarPreferenciaTema() {
        Preferences prefs = Preferences.userNodeForPackage(Inicializador.class);
        return prefs.getBoolean("temaOscuro", false); // false = tema claro por defecto
    }
	
	
	
	
	/*REGISTROS INICIALIZADOS---------------------------------------------------------------------------------------------------------
    ------------------------------------------------------------------------------------------------------------------------------------
    -----------------------------------------------------------------------------------------------------------------------------------*/
    
   public Facultad inicializarDatosRegistros() {
    	
        Facultad f = Facultad.getInstancia();
        
        // PERSONAS CREADAS
        //ESTUDIANTES
        Estudiante estudiante = new Estudiante("Ana", "González", "98765432123", TipoPersonal.Estudiante, 2, 1);
        Estudiante estudiante2 = new Estudiante("Luis", "Ramírez", "78945612300", TipoPersonal.Estudiante, 3, 2);
        Estudiante estudiante3 = new Estudiante("Claudia", "Fernández", "12312312399", TipoPersonal.Estudiante, 1, 3);
        //PROFESORES
        Profesor profesor = new Profesor("Carlos", "Martínez", "45612378945", TipoPersonal.Profesor, "Informática", "Asistente", "Máster", "Determinado");
        Profesor profesor2 = new Profesor("Elena", "Suárez", "98732165488", TipoPersonal.Profesor, "Matemática", "Auxiliar", "Doctor", "Indeterminado");
        Profesor profesor3 = new Profesor("José", "Hernández", "74185296311", TipoPersonal.Profesor, "Física", "Titular", "Máster", "Determinado");
        //DIRECTIVOS
        Directivo directivo = new Directivo("María", "López", "12345678977", TipoPersonal.Directivo, "Dirección", "Titular", "Doctor", "Indeterminado", "Directora", "Académica");
        Directivo directivo2 = new Directivo("Raúl", "Hernández", "96325874100", TipoPersonal.Directivo, "Vicedirección", "Asistente", "Máster", "Determinado", "Vicedirector", "Investigación");
        Directivo directivo3 = new Directivo("Teresa", "Mendoza", "85296374122", TipoPersonal.Directivo, "Planificación", "Titular", "Doctor", "Indeterminado", "Coordinadora", "Académica");
        //ADMINISTRATIVOS
        Administrativo administrativo = new Administrativo("Pedro", "Sánchez", "32165498711", TipoPersonal.Administrativo, "Contabilidad");
        Administrativo administrativo2 = new Administrativo("Laura", "Pérez", "14725836900", TipoPersonal.Administrativo, "Recursos Humanos");
        Administrativo administrativo3 = new Administrativo("Daniel", "Morales", "36985214700", TipoPersonal.Administrativo, "Logística");
        //TECNICOS
        Tecnico tecnico = new Tecnico("Alejandro", "Gonzales", "05011055892",TipoPersonal.Especialista,"Tesis");
        Tecnico tecnico2 = new Tecnico("Jorge", "Cruz", "15935725800", TipoPersonal.Especialista, "Laboratorio");
        Tecnico tecnico3 = new Tecnico("Patricia", "Navarro", "25815975300", TipoPersonal.Especialista, "Mantenimiento");
        
        //AGREGAR PERSONAS A LA LISTA
        f.agregarPersona(estudiante);
        f.agregarPersona(profesor);
        f.agregarPersona(directivo);
        f.agregarPersona(administrativo);
        f.agregarPersona(tecnico);
        f.agregarPersona(estudiante2);
        f.agregarPersona(estudiante3);
        f.agregarPersona(profesor2);
        f.agregarPersona(profesor3);
        f.agregarPersona(directivo2);
        f.agregarPersona(directivo3);
        f.agregarPersona(administrativo2);
        f.agregarPersona(administrativo3);
        f.agregarPersona(tecnico2);
        f.agregarPersona(tecnico3);
        
        
        // LOCALES CREADOS
        Local aula1 = new Local("AULA-101", Clasificacion.Aula);
        Local labInformatica = new Local("LAB-001", Clasificacion.Laboratorios);
        Local decanato = new Local("DEC-201", Clasificacion.Decano);
        Local estudiantes = new Local("ESTU-200",Clasificacion.Estudiantes);
        Local servidores = new Local("SERV-202",Clasificacion.Servidores);
        
        Local aula2 = new Local("AULA-102", Clasificacion.Aula);
        Local lab2 = new Local("LAB-002", Clasificacion.Laboratorios);
        Local vicedireccion = new Local("VDEC-100", Clasificacion.Vicedecano);
        Local recursosHumanos = new Local("AAD-001", Clasificacion.AreaA);
        Local mantenimiento = new Local("SEV-001", Clasificacion.Servidores);
        
        //AGREGAR LOCALES A LA LISTA
        f.agregarLocal(aula1);
        f.agregarLocal(labInformatica);
        f.agregarLocal(decanato);
        f.agregarLocal(estudiantes);
        f.agregarLocal(servidores);
        
        f.agregarLocal(aula2);
        f.agregarLocal(lab2);
        f.agregarLocal(vicedireccion);
        f.agregarLocal(recursosHumanos);
        f.agregarLocal(mantenimiento);
        
        // PRUEBAS DE ACCESO
        
     // Estudiantes
        aula1.registrarAcceso(estudiante, LocalDateTime.of(2025, 1, 10, 13, 21), LocalDateTime.of(2025, 1, 10, 14, 30));
        labInformatica.registrarAcceso(estudiante, LocalDateTime.of(2025, 1, 10, 10, 21), LocalDateTime.of(2025, 1, 10, 11, 30));
        aula2.registrarAcceso(estudiante2, LocalDateTime.of(2025, 2, 15, 9, 0), LocalDateTime.of(2025, 2, 15, 10, 30));
        labInformatica.registrarAcceso(estudiante3, LocalDateTime.of(2025, 3, 1, 13, 15), LocalDateTime.of(2025, 3, 1, 15, 0));

        // Profesores
        lab2.registrarAcceso(profesor2, LocalDateTime.of(2025, 4, 5, 8, 0), LocalDateTime.of(2025, 4, 5, 12, 0));
        aula1.registrarAcceso(profesor3, LocalDateTime.of(2025, 4, 5, 10, 0), LocalDateTime.of(2025, 4, 5, 12, 0));

        // Directivos
         aula1.registrarAcceso(directivo, LocalDateTime.of(2025, 1, 10, 10, 21), LocalDateTime.of(2025, 1, 10, 12, 30));
         decanato.registrarAcceso(directivo, LocalDateTime.of(2025, 1, 10, 15, 21), LocalDateTime.of(2025, 1, 10, 15, 30));
        vicedireccion.registrarAcceso(directivo2, LocalDateTime.of(2025, 1, 20, 9, 30), LocalDateTime.of(2025, 1, 20, 11, 0));
        decanato.registrarAcceso(directivo3, LocalDateTime.of(2025, 1, 25, 14, 0), LocalDateTime.of(2025, 1, 25, 15, 30));

        // Administrativos
        servidores.registrarAcceso(administrativo,  LocalDateTime.of(2025, 5, 29, 10, 30),LocalDateTime.of(2025, 5, 29, 15, 0));
        recursosHumanos.registrarAcceso(administrativo2, LocalDateTime.of(2025, 2, 1, 8, 30), LocalDateTime.of(2025, 2, 1, 16, 0));
        servidores.registrarAcceso(administrativo3, LocalDateTime.of(2025, 3, 15, 9, 0), LocalDateTime.of(2025, 3, 15, 14, 30));

        // Técnicos
        mantenimiento.registrarAcceso(tecnico2, LocalDateTime.of(2025, 3, 10, 10, 0), LocalDateTime.of(2025, 3, 10, 13, 30));
        labInformatica.registrarAcceso(tecnico3, LocalDateTime.of(2025, 5, 2, 9, 0), LocalDateTime.of(2025, 5, 2, 11, 30));
        
        return f;
    }

}
