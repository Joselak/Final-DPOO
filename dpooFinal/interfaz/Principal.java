
package dpooFinal.interfaz;


import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Administrativo;
import dpooFinal.logica.Clasificacion;
import dpooFinal.logica.TipoPersonal;
import dpooFinal.logica.Directivo;
import dpooFinal.logica.Estudiante;
import dpooFinal.logica.Profesor;
import dpooFinal.logica.Visitante;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;


public class Principal extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    

	 
	public static void main(String[] args) {
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
     Facultad facultad =new Facultad();
	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Gestor de Accesos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 457);
		
		// INICIALIZAR FACULTAD 		
        facultad = inicializarDatosPrueba();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Registros");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmMostrarRegistros = new JMenuItem("Mostrar registros");
		mntmMostrarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MostrarRegistros dialog = new MostrarRegistros();
					dialog.setFacultad(facultad);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmMostrarRegistros);
		
		JMenuItem mntmAadirRegistro = new JMenuItem("A\u00F1adir persona");
		mntmAadirRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            AñadirRegistro dialog = new AñadirRegistro();
		            dialog.setFacultad(facultad);  
		            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		            dialog.setVisible(true);
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
			}
		});
		mnNewMenu.add(mntmAadirRegistro);
		
		JMenu mnConsultar = new JMenu("Consultar");
		menuBar.add(mnConsultar);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnConsultar.add(mnReportes);
		
		JMenuItem mntmVisitasALocales = new JMenuItem("Visitas en un intervalos");
		mntmVisitasALocales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VisitasIntervalo dialog = new VisitasIntervalo(facultad);
					dialog.setFacultad(facultad);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		mnReportes.add(mntmVisitasALocales);
		
		JMenuItem mntmLocalesPorPersona = new JMenuItem("Visitas por una persona");
		mnReportes.add(mntmLocalesPorPersona);
		
		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//			
			}
			
		});
		menuBar.add(mnBuscar);
		
		JMenuItem mntmBusquedaPor = new JMenuItem("Busqueda por...");
		mntmBusquedaPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Busqueda dialog = new Busqueda();
					dialog.setFacultad(facultad);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		mnBuscar.add(mntmBusquedaPor);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem menuItem = new JMenuItem("Horario de accesos");
		mnAyuda.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		setLocationRelativeTo(null);
	}
	
	
	 
	 
	 private Facultad inicializarDatosPrueba() {
	        Facultad f = new Facultad();
	        
	        // PERSONAS CREADAS
	        Estudiante estudiante = new Estudiante("Ana", "González", "987654321", TipoPersonal.Estudiante, 2, 1);
	        Profesor profesor = new Profesor("Carlos", "Martínez", "456123789", TipoPersonal.Profesor, "Informática", "Asistente", "Máster", "Determinado");
	        Visitante visitante = new Visitante("Luis", "Rodríguez", "789456123", TipoPersonal.Visitante, "Reunión", "Facultad de Derecho","Alejandro");
	        Directivo directivo = new Directivo("María", "López", "123456789", TipoPersonal.Directivo, "Dirección", "Titular", "Doctor", "Indeterminado", "Directora", "Académica");
	        Administrativo administrativo = new Administrativo("Pedro", "Sánchez", "321654987", TipoPersonal.Administrativo, "Contabilidad");
	        
	        f.agregarPersona(estudiante);
	        f.agregarPersona(profesor);
	        f.agregarPersona(visitante);
	        f.agregarPersona(directivo);
	        f.agregarPersona(administrativo);

	        // LOCALES CREADOS
	        Local aula1 = new Local("AULA-101", Clasificacion.Aula);
	        Local labInformatica = new Local("LAB-001", Clasificacion.Laboratorios);
	        Local decanato = new Local("DEC-201", Clasificacion.Decano);
	        Local estudiantes = new Local("ESTU-200",Clasificacion.Estudiantes);
	        Local servidores =new Local("SERV-202",Clasificacion.Servidores);
	        
	        f.agregarLocal(aula1);
	        f.agregarLocal(labInformatica);
	        f.agregarLocal(decanato);
	        f.agregarLocal(estudiantes);
	        f.agregarLocal(servidores);

	        // PRUEBAS
	        aula1.registrarAcceso(estudiante, LocalDateTime.of(2025, 1, 10, 13, 21), LocalDateTime.of(2025, 1, 10, 14, 30));
	        aula1.registrarAcceso(directivo, LocalDateTime.of(2025, 1, 10, 10, 21), LocalDateTime.of(2025, 1, 10, 12, 30));
	        labInformatica.registrarAcceso(estudiante, LocalDateTime.of(2025, 1, 10, 10, 21), LocalDateTime.of(2025, 1, 10, 11, 30));
	        decanato.registrarAcceso(directivo, LocalDateTime.of(2025, 1, 10, 15, 21), LocalDateTime.of(2025, 1, 10, 15, 30));
	        estudiantes.registrarAcceso(visitante, LocalDateTime.of(2025, 5, 29, 11, 00),LocalDateTime.of(2025, 5, 29, 12, 00));
	        servidores.registrarAcceso(administrativo,  LocalDateTime.of(2025, 5, 29, 10, 30),LocalDateTime.of(2025, 5, 29, 15, 00));
	        
	        return f;
	    }
	}
	 


