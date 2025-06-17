package dpooFinal.interfaz;



import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dpooFinal.logica.Facultad;
import dpooFinal.logica.Persona;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
		
		setBounds(100, 100, 588, 452);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
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
	        lblFecha.setBounds(10, 34, 80, 20);
	        panelControles.add(lblFecha);
	        
	        spinnerFecha = new JSpinner(new SpinnerDateModel());
	        spinnerFecha.setBounds(55, 34, 120, 20);
	        panelControles.add(spinnerFecha);
	        JSpinner.DateEditor fechaEditor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
	        spinnerFecha.setEditor(fechaEditor);
	        spinnerFecha.setValue(new Date());

	        // MOSTRAR RESULTADOS
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(20, 150, 523, 230);
	        contentPanel.add(scrollPane);

	        tableResultados = new JTable();
	        tableResultados.setModel(new DefaultTableModel(
	            new Object[][] {},
	            new String[] {"Carnet", "Nombre", "Apellido", "Tipo"}
	        ));
	        scrollPane.setViewportView(tableResultados);
	        
	        
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

}
