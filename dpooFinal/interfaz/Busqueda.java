package dpooFinal.interfaz;


import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Clasificacion;
import dpooFinal.logica.Persona;
import dpooFinal.logica.Registro;
import dpooFinal.logica.TipoPersonal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Calendar;



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
        
        
        
        tableResultados = new JTable(tableModel);
        scrollPane = new JScrollPane(tableResultados);
        panelResultados.add(scrollPane, BorderLayout.CENTER);
        
        tableModel.fireTableDataChanged();
        tableResultados.revalidate();
        tableResultados.repaint();
    
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
            spinnerFecha.setBounds(100, 10, 80, 20);
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
            spinnerHora.setBounds(108, 40, 72, 20);
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
        
		@SuppressWarnings({ "rawtypes", "unchecked" })
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
        tableModel.addRow(new Object[]{
            registro.getPersona().getNumID(),
            registro.getPersona().getNombre() + " " + registro.getPersona().getApellido(),
            registro.getPersona().getTipo().toString(),
            local.getId(),
            registro.getHoraEntrada()
        });
    }
}



	

