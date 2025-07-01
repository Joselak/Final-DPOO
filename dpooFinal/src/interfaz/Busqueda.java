package dpooFinal.interfaz;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dpooFinal.logica.Administrativo;
import dpooFinal.logica.Clasificacion;
import dpooFinal.logica.Directivo;
import dpooFinal.logica.Especialista;
import dpooFinal.logica.Estudiante;
import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Persona;
import dpooFinal.logica.Profesor;
import dpooFinal.logica.Registro;
import dpooFinal.logica.Tecnico;
import dpooFinal.logica.TipoPersonal;
import dpooFinal.logica.Visitante;
import java.awt.event.MouseEvent;



public class Busqueda extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JComboBox<String> comboBox;
    
    //COMPONENTES FECHHA Y HORA
    private JPanel panelFechaYHora;
    private JSpinner spinnerFecha;
    private JSpinner spinnerHora;
    
    // COMPONENTES PAARA LOCAL
    private JPanel panelLocal;
    
    // TIPO DE PERSONAS
    private JPanel panelTipoPersona;
    private JComboBox<String> cbTipoPersona;
    
    //NOMBRE
    private JPanel panelNombre;
    private JTextField txtNombre;
    
    // TABLA DE RESULTADOS
    private JTable tableResultados;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        try {
            Busqueda dialog = new Busqueda();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Facultad facultad;
    

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	    System.out.println("Facultad asignada: " + (facultad != null));
	}

    public Busqueda() {
        setTitle("Búsqueda por...");
        setBounds(100, 100, 650, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setResizable(false);
        
        // PANEL QUE CONTIENE A LOS DEMAS
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(10, 50, 300, 150);
        contentPanel.add(cardPanel);
        
        // PANEL PARA CADA TIPO DE BUSQUEDA
        crearPanelFechaYHora();
        crearPanelLocal();
        crearPanelTipoPersona();
        crearPanelNombre();
        
        // PANEL PARA EL COMBOBBOX DE SELECIONES
        JPanel panelSeleccion = new JPanel();
        panelSeleccion.setBounds(10, 10, 307, 40);
        contentPanel.add(panelSeleccion);
        panelSeleccion.setLayout(null);
        
        JLabel lblTipoDeBusqueda = new JLabel("Seleccione el tipo de búsqueda:");
        lblTipoDeBusqueda.setBounds(0, 10, 180, 20);
        panelSeleccion.add(lblTipoDeBusqueda);
        
        comboBox = new JComboBox<>();
        comboBox.setBounds(188, 10, 110, 20);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Fecha y hora", "Local", "Tipo de persona", "Nombre"}));
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPanel();
            }
        });
        panelSeleccion.add(comboBox);
        
        // PANEL RESULTADOS
        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBounds(10, 210, 614, 200);
        contentPanel.add(panelResultados);
        
        // TABLA PARA LOS RESULTADOS
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Local");
        tableModel.addColumn("Fecha");
        
        
        
        tableResultados = new JTable(tableModel) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto hace que todas las celdas sean no editables
            }
        };
        scrollPane = new JScrollPane(tableResultados);
        panelResultados.add(scrollPane, BorderLayout.CENTER);
        
        tableModel.fireTableDataChanged();
        tableResultados.revalidate();
        tableResultados.repaint();
        
        
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
        
        // PANEL DE BOTONES
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton okButton = new JButton("Salir");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscar();
        	}
        });
        buttonPane.add(btnBuscar);
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        
        setLocationRelativeTo(null);
        
        // MOSTRAR EL PANEL INICIAL
        cambiarPanel();
    }
        
        private void crearPanelFechaYHora() {
            panelFechaYHora = new JPanel();
            panelFechaYHora.setLayout(null);
            
            JLabel lblFecha = new JLabel("Fecha:");
            lblFecha.setBounds(10, 10, 80, 20);
            panelFechaYHora.add(lblFecha);
            
            spinnerFecha = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor fechaEditor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
            spinnerFecha.setEditor(fechaEditor);
            spinnerFecha.setBounds(106, 10, 98, 20);
            panelFechaYHora.add(spinnerFecha);
            
            JLabel lblHora = new JLabel("Hora de entrada:");
            lblHora.setBounds(10, 40, 98, 20);
            panelFechaYHora.add(lblHora);
            
            spinnerHora = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor horaEditor = new JSpinner.DateEditor(spinnerHora, "HH:mm");
            spinnerHora.setEditor(horaEditor);
            spinnerHora.setBounds(106, 68, 74, 20);
            panelFechaYHora.add(spinnerHora);
            
            cardPanel.add(panelFechaYHora, "fechaHora");
            
            JLabel lblHoraDeSalida = new JLabel("Hora de salida:");
            lblHoraDeSalida.setBounds(10, 71, 86, 14);
            panelFechaYHora.add(lblHoraDeSalida);
            
            spinnerHora = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor horaSalida = new JSpinner.DateEditor(spinnerHora, "HH:mm");
            spinnerHora.setEditor(horaSalida);
            spinnerHora.setBounds(106, 41, 72, 20);
            panelFechaYHora.add(spinnerHora);
            
            cardPanel.add(panelFechaYHora, "fechaHora");
        }
        
        private void crearPanelLocal() {
            panelLocal = new JPanel();
            panelLocal.setLayout(null);
            
            JLabel lblLocal = new JLabel("Local:");
            lblLocal.setBounds(40, 10, 47, 20);
            panelLocal.add(lblLocal);
            
            cardPanel.add(panelLocal, "local");
            
            JComboBox<Clasificacion> comboBox_1 = new JComboBox<Clasificacion>();
            comboBox_1.setModel(new DefaultComboBoxModel<Clasificacion>(Clasificacion.values()));
            comboBox_1.setBounds(85, 10, 133, 20);
            panelLocal.add(comboBox_1);
        }
        
		
		private void crearPanelTipoPersona() {
            panelTipoPersona = new JPanel();
            panelTipoPersona.setLayout(null);
            
            JLabel lblTipoPersona = new JLabel("Tipo de Persona:");
            lblTipoPersona.setBounds(21, 10, 109, 20);
            panelTipoPersona.add(lblTipoPersona);
            
            cbTipoPersona = new JComboBox<>();
            cbTipoPersona.setModel(new DefaultComboBoxModel(TipoPersonal.values()));
            cbTipoPersona.setBounds(129, 10, 150, 20);
            panelTipoPersona.add(cbTipoPersona);
            
            cardPanel.add(panelTipoPersona, "tipoPersona");
        }
        
        private void crearPanelNombre() {
            panelNombre = new JPanel();
            panelNombre.setLayout(null);
            
            JLabel lblNombre = new JLabel("Nombre:");
            lblNombre.setBounds(32, 10, 58, 20);
            panelNombre.add(lblNombre);
            
            txtNombre = new JTextField();
            txtNombre.setBounds(100, 10, 150, 20);
            panelNombre.add(txtNombre);
            txtNombre.setColumns(30);
            
            cardPanel.add(panelNombre, "nombre");
    }
    
    private void cambiarPanel() {
        String seleccion = (String) comboBox.getSelectedItem();
        switch(seleccion) {
            case "Fecha y hora":
                cardLayout.show(cardPanel, "fechaHora");
                break;
            case "Local":
                cardLayout.show(cardPanel, "local");
                break;
            case "Tipo de persona":
                cardLayout.show(cardPanel, "tipoPersona");
                break;
            case "Nombre":
                cardLayout.show(cardPanel, "nombre");
                break;
        }
        setLocationRelativeTo(null);
    }
    
    private void buscar() {
        String seleccion = (String) comboBox.getSelectedItem();
        tableModel.setRowCount(0); // LIMPIAR TABLA

        try {
            switch (seleccion) {
                case "Fecha y hora":
                    buscarPorFechaHora();
                    break;
                case "Local":
                    buscarPorLocal();
                    break;
                case "Tipo de persona":
                    buscarPorTipoPersona();
                    break;
                case "Nombre":
                    buscarPorNombre();
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPorFechaHora() {
        Date fecha = (Date) spinnerFecha.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        
        LocalDateTime fechaBusqueda = LocalDateTime.of(
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH) + 1,
            cal.get(Calendar.DAY_OF_MONTH),
            0, 0);
        
        LocalDateTime finDia = fechaBusqueda.plusDays(1);
        
        for (Local local : facultad.getLocales()) {
            for (Registro registro : local.getRegistros()) {
                if (registro.getHoraEntrada().isAfter(fechaBusqueda) && 
                    registro.getHoraEntrada().isBefore(finDia)) {
                    agregarFilaTabla(registro, local);
                }
            }
        }
    }

    
    //BUSQUEDA POR LOCAL
    private void buscarPorLocal() {
        
        Clasificacion tipoLocal = (Clasificacion) ((JComboBox<?>)panelLocal.getComponent(1)).getSelectedItem();
        
        for (Local local : facultad.getLocales()) {
            if (local.getTipoLocal() == tipoLocal) {
                for (Registro registro : local.getRegistros()) {
                    agregarFilaTabla(registro, local);
                }
            }
        }
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    //BUSQUEDA POR TIPO DE PERSONA
    private void buscarPorTipoPersona() {
        TipoPersonal tipo = (TipoPersonal) cbTipoPersona.getSelectedItem();
        
        for (Persona persona : facultad.getPersonas()) {
            if (persona.getTipo() == tipo) {
                for (Local local : facultad.getLocales()) {
                    for (Registro registro : local.getRegistros()) {
                        if (registro.getPersona().getNumID().equals(persona.getNumID())) {
                            agregarFilaTabla(registro, local);
                        }
                    }
                }
            }
        }
    }

    
    //BUSQUEDA POR NOMBRE DE LA PERSONA
    private void buscarPorNombre() {
        String nombre = txtNombre.getText().toLowerCase();
        
        for (Persona persona : facultad.getPersonas()) {
            if (persona.getNombre().toLowerCase().contains(nombre) || 
                persona.getApellido().toLowerCase().contains(nombre)) {
                for (Local local : facultad.getLocales()) {
                    for (Registro registro : local.getRegistros()) {
                        if (registro.getPersona().getNumID().equals(persona.getNumID())) {
                            agregarFilaTabla(registro, local);
                        }
                    }
                }
            }
        }
    }

    
    private void agregarFilaTabla(Registro registro, Local local) {
    	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    	
        tableModel.addRow(new Object[]{
            registro.getPersona().getNumID(),
            registro.getPersona().getNombre() + " " + registro.getPersona().getApellido(),
            registro.getPersona().getTipo().toString(),
            local.getId(),
            registro.getHoraEntrada()
        });
    }
    
    
  //MOSTRAR DETALLES DE UN REGISTRO
    private void mostrarDetallesRegistro() {
        int fila = tableResultados.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener los datos de la fila seleccionada
        String idPersona = (String) tableModel.getValueAt(fila, 0);
        String localId = (String) tableModel.getValueAt(fila, 3);
        LocalDateTime horaEntrada = (LocalDateTime) tableModel.getValueAt(fila, 4);
        
        // Buscar el registro exacto
        Registro registroEncontrado = null;
        Local localEncontrado = null;
        
        boolean encontrado = false;
        for (Local local : facultad.getLocales()) {
            if (!encontrado && local.getId().equals(localId)) {
                for (Registro registro : local.getRegistros()) {
                    if (!encontrado && 
                        registro.getPersona().getNumID().equals(idPersona) && 
                        registro.getHoraEntrada().equals(horaEntrada)) {
                        registroEncontrado = registro;
                        localEncontrado = local;
                        encontrado = true;
                    }
                }
            }
        }
        
        if (registroEncontrado == null) {
            JOptionPane.showMessageDialog(this, "No se encontraron detalles del registro", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Persona persona = registroEncontrado.getPersona();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        // Crear y mostrar el diálogo con los detalles
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // Datos de la persona
        panel.add(new JLabel("Nombre:"));
        panel.add(new JLabel(persona.getNombre() + " " + persona.getApellido()));
        panel.add(new JLabel("Tipo:"));
        panel.add(new JLabel(persona.getTipo().toString()));
        panel.add(new JLabel("ID:"));
        panel.add(new JLabel(persona.getNumID()));
        
        // Datos del registro
        panel.add(new JLabel("Local:"));
        panel.add(new JLabel(localEncontrado.getId() + " (" + localEncontrado.getTipoLocal() + ")"));
        panel.add(new JLabel("Hora de entrada:"));
        panel.add(new JLabel(registroEncontrado.getHoraEntrada().format(formatter)));
        panel.add(new JLabel("Hora de salida:"));
        panel.add(new JLabel(registroEncontrado.getHoraSalida() != null ? 
            registroEncontrado.getHoraSalida().format(formatter) : "Pendiente"));
        
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
        JOptionPane.showMessageDialog(this, panel, "Detalles del Registro", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
    }

    public void addWindowListener(WindowListener listener) {
        super.addWindowListener(listener);
    }
}

