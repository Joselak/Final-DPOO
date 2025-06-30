package dpooFinal.interfaz;



import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dpooFinal.logica.Administrativo;
import dpooFinal.logica.Directivo;
import dpooFinal.logica.Especialista;
import dpooFinal.logica.Estudiante;
import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Persona;
import dpooFinal.logica.Profesor;
import dpooFinal.logica.Registro;
import dpooFinal.logica.Tecnico;
import dpooFinal.logica.Visitante;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.awt.GridLayout;


public class VisitasIntervalo extends JDialog {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboLocales;
    private JSpinner spinnerFechaInicio;
    private JSpinner spinnerFechaFin;
    private JSpinner spinnerFecha;
    private JTable tableResultados;
    private Facultad facultad;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			//INSTANCIA DE FACULTAD
			Facultad facultad = Facultad.getInstancia();
			
			VisitasIntervalo dialog = new VisitasIntervalo(facultad);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * 
	 */
	
	public VisitasIntervalo(Facultad facultad) {
		setTitle("Visitas en intervalo");
		
		setBounds(100, 100, 657, 503);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		{
			  // PANEL SUPERIOR CON CONTROLES
	        JPanel panelControles = new JPanel();
	        panelControles.setBounds(62, 11, 347, 128);
	        contentPanel.add(panelControles);
	        panelControles.setLayout(null);

	        // SELECCIONAR LOCAL
	        JLabel label = new JLabel("Local:");
	        label.setBounds(10, 0, 171, 23);
	        panelControles.add(label);
	        comboLocales = new JComboBox<>();
	        comboLocales.setBounds(45, 0, 126, 23);
	        for (dpooFinal.logica.Local local : facultad.getLocales()) {
	            comboLocales.addItem(local.getId());
	        }
	        panelControles.add(comboLocales);

	        // SELECCIIONAR HORA INICIO
	        JLabel label_2 = new JLabel("Hora Inicio:");
	        label_2.setBounds(191, 34, 78, 23);
	        panelControles.add(label_2);
	        spinnerFechaInicio = new JSpinner(new SpinnerDateModel());
	        spinnerFechaInicio.setBounds(268, 34, 51, 23);
	        spinnerFechaInicio.setEditor(new JSpinner.DateEditor(spinnerFechaInicio, "HH:mm"));
	        panelControles.add(spinnerFechaInicio);

	        // SELECCIONAR HORA FINAL
	        JLabel lblHoraFin = new JLabel("Hora Fin:");
	        lblHoraFin.setBounds(191, 68, 61, 23);
	        panelControles.add(lblHoraFin);
	        spinnerFechaFin = new JSpinner(new SpinnerDateModel());
	        spinnerFechaFin.setBounds(268, 68, 51, 23);
	        spinnerFechaFin.setEditor(new JSpinner.DateEditor(spinnerFechaFin, "HH:mm"));
	        panelControles.add(spinnerFechaFin);

	        // GENERAR REPORTES
	        JButton btnGenerar = new JButton("Consultar");
	        btnGenerar.setBounds(45, 94, 126, 23);
	        btnGenerar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                generarReporte();
	            }
	        });
	        panelControles.add(btnGenerar);
	        
	     // SELECCIONAR FECHA
	        JLabel lblFecha = new JLabel("Fecha:");
	        lblFecha.setBounds(10, 51, 80, 20);
	        panelControles.add(lblFecha);
	        
	        spinnerFecha = new JSpinner(new SpinnerDateModel());
	        spinnerFecha.setBounds(55, 51, 120, 20);
	        panelControles.add(spinnerFecha);
	        JSpinner.DateEditor fechaEditor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
	        spinnerFecha.setEditor(fechaEditor);
	        spinnerFecha.setValue(new Date());

	        // MOSTRAR RESULTADOS
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(20, 150, 596, 260);
	        contentPanel.add(scrollPane);


	        tableResultados = new JTable() {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Esto hace que todas las celdas sean no editables
	            }
	        };
	        tableResultados.setModel(new DefaultTableModel(
	            new Object[][] {},
	            new String[] {"Carnet", "Nombre", "Apellido", "Tipo"}
	        ));
	        scrollPane.setViewportView(tableResultados);
	        
	        //ACCION DE DOLE CLICK  UN REGISTRO
	        tableResultados.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) {
	                    // Cancela cualquier edición en curso
	                    if (tableResultados.isEditing()) {
	                        tableResultados.getCellEditor().stopCellEditing();
	                    }
	                    
	                    // Asegura que hay una fila seleccionada
	                    int row = tableResultados.rowAtPoint(e.getPoint());
	                    if (row >= 0) {
	                        tableResultados.setRowSelectionInterval(row, row);
	                        mostrarDetallesRegistro();
	                    }
	                }
	            }
	        });
	        
	        
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
	}
	
	public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
	

	private void generarReporte() {
	    try {
	        String localSeleccionado = (String) comboLocales.getSelectedItem();
	        Date fecha = (Date) spinnerFecha.getValue();
	        Date horaInicio = (Date) spinnerFechaInicio.getValue();
	        Date horaFin = (Date) spinnerFechaFin.getValue();
	        
	        // A INSTANT Y a LocalDate/LocalTime
	        LocalDate fechaLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalTime horaInicioLocal = horaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
	        LocalTime horaFinLocal = horaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
	        
	        System.out.println("Fecha seleccionada: " + fechaLocal);
	        System.out.println("Hora inicio: " + horaInicioLocal);
	        System.out.println("Hora fin: " + horaFinLocal);
	        
	        // COMBINAR FECHAS CON HORAS
	        LocalDateTime fechaInicio = LocalDateTime.of(fechaLocal, horaInicioLocal);
	        LocalDateTime fechaFin = LocalDateTime.of(fechaLocal, horaFinLocal);

	        System.out.println("Fecha inicio completa: " + fechaInicio);
	        System.out.println("Fecha fin completa: " + fechaFin);

	        // DATOS DEL REPORTE
	        List<Persona> personas = facultad.obtenerPersonasEnLocalEnIntervalo(
	            localSeleccionado, fechaInicio, fechaFin
	        );

	        System.out.println("Personas encontradas: " + personas.size());

	        // ACTUALIZAR TABLA
	        DefaultTableModel model = (DefaultTableModel) tableResultados.getModel();
	        model.setRowCount(0); // Limpiar tabla

	        for (Persona persona : personas) {
	            model.addRow(new Object[]{
	                persona.getNumID(),
	                persona.getNombre(),
	                persona.getApellido(),
	                persona.getTipo().toString()
	            });
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al generar reporte: " + e.getMessage());
	    }
	}

	private void mostrarDetallesRegistro() {
	    int fila = tableResultados.getSelectedRow();
	    if (fila < 0) {
	        JOptionPane.showMessageDialog(this, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    // Obtener los datos de la fila seleccionada (solo las 4 columnas que muestra la tabla)
	    String idPersona = (String) tableResultados.getValueAt(fila, 0); // Carnet
	    String nombre = (String) tableResultados.getValueAt(fila, 1);    // Nombre
	    String apellido = (String) tableResultados.getValueAt(fila, 2);  // Apellido
	    String tipoPersona = (String) tableResultados.getValueAt(fila, 3); // Tipo
	    
	    // Buscar la persona completa en la facultad
	    Persona persona = null;
	    for (Persona p : facultad.getPersonas()) {
	        if (p.getNumID().equals(idPersona)) {
	            persona = p;
	            break;
	        }
	    }
	    
	    if (persona == null) {
	        JOptionPane.showMessageDialog(this, "No se encontraron detalles de la persona", 
	            "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    // Buscar el local y registro más reciente de esta persona
	    Local localEncontrado = null;
	    Registro registroEncontrado = null;
	    LocalDateTime ultimaEntrada = null;
	    
	    for (Local local : facultad.getLocales()) {
	        for (Registro registro : local.getRegistros()) {
	            if (registro.getPersona().getNumID().equals(idPersona)) {
	                if (ultimaEntrada == null || registro.getHoraEntrada().isAfter(ultimaEntrada)) {
	                    ultimaEntrada = registro.getHoraEntrada();
	                    registroEncontrado = registro;
	                    localEncontrado = local;
	                }
	            }
	        }
	    }
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    
	    // Crear y mostrar el diálogo con los detalles
	    JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
	    
	    // Datos básicos de la persona (que ya teníamos en la tabla)
	    panel.add(new JLabel("Carnet:"));
	    panel.add(new JLabel(idPersona));
	    panel.add(new JLabel("Nombre completo:"));
	    panel.add(new JLabel(nombre + " " + apellido));
	    panel.add(new JLabel("Tipo:"));
	    panel.add(new JLabel(tipoPersona));
	    
	    // Datos del registro (si se encontró)
	    if (localEncontrado != null && registroEncontrado != null) {
	        panel.add(new JLabel("Último local visitado:"));
	        panel.add(new JLabel(localEncontrado.getId() + " (" + localEncontrado.getTipoLocal() + ")"));
	        panel.add(new JLabel("Hora de entrada:"));
	        panel.add(new JLabel(registroEncontrado.getHoraEntrada().format(formatter)));
	        panel.add(new JLabel("Hora de salida:"));
	        panel.add(new JLabel(registroEncontrado.getHoraSalida() != null ? 
	            registroEncontrado.getHoraSalida().format(formatter) : "Pendiente"));
	    }
	    
	 // Atributos específicos según el tipo de persona
        if (persona instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) persona;
            panel.add(new JLabel("Año:"));
            panel.add(new JLabel(String.valueOf(estudiante.getAnio())));
            panel.add(new JLabel("Grupo:"));
            panel.add(new JLabel(String.valueOf(estudiante.getGrupo())));
        } 
        else if (persona instanceof Profesor) {
            Profesor profesor = (Profesor) persona;
            panel.add(new JLabel("Departamento:"));
            panel.add(new JLabel(profesor.getDepartamento()));
            panel.add(new JLabel("Categoría Docente:"));
            panel.add(new JLabel(profesor.getCategDocente()));
            panel.add(new JLabel("Categoría Científica:"));
            panel.add(new JLabel(profesor.getCategCientifica()));
            panel.add(new JLabel("Tipo de Contrato:"));
            panel.add(new JLabel(profesor.getTipoContrato()));
            
            if (persona instanceof Directivo) {
                Directivo directivo = (Directivo) persona;
                panel.add(new JLabel("Área:"));
                panel.add(new JLabel(directivo.getArea()));
                panel.add(new JLabel("Cargo:"));
                panel.add(new JLabel(directivo.getCargo()));
            }
        } 
        else if (persona instanceof Administrativo) {
            Administrativo admin = (Administrativo) persona;
            panel.add(new JLabel("Plaza:"));
            panel.add(new JLabel(admin.getPlaza()));
        }
        else if (persona instanceof Tecnico) {
            Tecnico tecnico = (Tecnico) persona;
            panel.add(new JLabel("Plaza:"));
            panel.add(new JLabel(tecnico.getPlaza()));
        }
        else if (persona instanceof Especialista) {
            Especialista especialista = (Especialista) persona;
            panel.add(new JLabel("Proyecto:"));
            panel.add(new JLabel(especialista.getProyecto()));
        }
        else if (persona instanceof Visitante) {
            Visitante visitante = (Visitante) persona;
            panel.add(new JLabel("Motivo de visita:"));
            panel.add(new JLabel(visitante.getMotivoVisita()));
            panel.add(new JLabel("Área de la universidad:"));
            panel.add(new JLabel(visitante.getAreaUniversidad()));
            panel.add(new JLabel("Autorizado por:"));
            panel.add(new JLabel(visitante.getAutorizadoPor()));
        }
        
	    
	    // Mostrar el diálogo
	    JOptionPane.showMessageDialog(this, panel, "Detalles de " + nombre + " " + apellido, 
	        JOptionPane.INFORMATION_MESSAGE);
	}
	
}
