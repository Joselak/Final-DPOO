package dpooFinal.interfaz;



import dpooFinal.logica.Facultad;
import dpooFinal.logica.Administrativo;
import dpooFinal.logica.Persona;
import dpooFinal.logica.TipoPersonal;
import dpooFinal.logica.Directivo;
import dpooFinal.logica.Especialista;
import dpooFinal.logica.Estudiante;
import dpooFinal.logica.Profesor;
import dpooFinal.logica.Tecnico;
import dpooFinal.logica.Visitante;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;

import java.awt.Color;


import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import java.awt.CardLayout;
import javax.swing.SpinnerNumberModel;





public class AnadirPersona extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<TipoPersonal> comboBoxTipo; 
    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtIdentidad;
    
    // Paneles específicos
    private JPanel panelEstudiante;
    private JPanel panelProfesor;
    private JPanel panelAdministrativo;
    private JPanel panelVisitante;
    private JPanel panelTecnico;
    private JPanel panelDirectivo;
    private JPanel panelEspecialista;
    private JPanel panelContenedor;
    
    // Componentes para Profesor
    private JTextField txtDepartamento;
    private JTextField textCDocente;
    private JTextField textCientifica;
    private JTextField textTContrato;

    
    // Componentes para Administrativo
    private JTextField txtPlaza;
    
 // Componentes para Estudiante
    private JTextField textGrupo;
 // Componentes para especialista
    private JTextField textProyecto;
    
    private JLabel labelCDD;
    private JTextField textField;
    private JTextField textField_1;
 // Componentes para directivo
    private JTextField textDepartamentoD;
    private JTextField textCDocenteD;
    private JTextField textCCientificaD;
    private JTextField textTContratoD;
    
 // Componentes para Visitante
    private JTextField textMVisita;
    private JTextField textAOrigen;
    private JTextField textAPor;
    
    public static void main(String[] args) {
        try {
            AnadirPersona dialog = new AnadirPersona();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Facultad facultad;
    

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public AnadirPersona() {
        setTitle("Añadir Persona");
        setBounds(100, 100, 576, 337);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        crearPanelInformacionPersonal();
        crearPanelTipoPersona();
        crearPanelBotones();
        
        setLocationRelativeTo(null);
    }

    private void crearPanelInformacionPersonal() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Información Personal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(10, 11, 251, 206);
        contentPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre(s):");
        lblNombre.setBounds(10, 21, 66, 14);
        panel.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(10, 41, 194, 20);
        panel.add(txtNombre);
        txtNombre.setColumns(10);
        
        JLabel lblApellidos = new JLabel("Apellido(s)");
        lblApellidos.setBounds(10, 72, 78, 14);
        panel.add(lblApellidos);
        
        txtApellidos = new JTextField();
        txtApellidos.setBounds(10, 97, 194, 20);
        panel.add(txtApellidos);
        txtApellidos.setColumns(10);
        
        JLabel lblNumeroDeIdentidad = new JLabel("Numero de Identidad:");
        lblNumeroDeIdentidad.setBounds(10, 128, 148, 14);
        panel.add(lblNumeroDeIdentidad);
        
        txtIdentidad = new JTextField();
        txtIdentidad.setBounds(10, 152, 194, 20);
        panel.add(txtIdentidad);
        txtIdentidad.setColumns(10);
    }

    private void crearPanelTipoPersona() {
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Informacion de la Persona", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(280, 11, 269, 206);
        contentPanel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblTipo = new JLabel("Tipo de persona:");
        lblTipo.setBounds(10, 27, 96, 14);
        panel_1.add(lblTipo);
        
        comboBoxTipo = new JComboBox<>();
        comboBoxTipo.setModel(new DefaultComboBoxModel<TipoPersonal>(TipoPersonal.values()));
        comboBoxTipo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    TipoPersonal tipo = (TipoPersonal)comboBoxTipo.getSelectedItem();
                    mostrarCamposEspecificos(tipo);
                }
            }
        });
        comboBoxTipo.setBounds(114, 24, 145, 20);
        panel_1.add(comboBoxTipo);
        
        // Crear paneles específicos
        crearPanelesEspecificos();
        
        // Panel contenedor con CardLayout
        panelContenedor = new JPanel();
        panelContenedor.setBounds(10, 52, 249, 143);
        panel_1.add(panelContenedor);
        panelContenedor.setLayout(new CardLayout(0, 0));
        
        // Agregar paneles al contenedor
                
        
        textGrupo.setColumns(10);
        panelContenedor.add(panelAdministrativo, "Administrativo");
        panelContenedor.add(panelEstudiante, "Estudiante");
        panelContenedor.add(panelProfesor, "Profesor");
        panelContenedor.add(panelEspecialista, "Especialista");
        panelContenedor.add(panelTecnico, "Tecnico");
        panelContenedor.add(panelVisitante, "Visitante");
        panelContenedor.add(panelDirectivo, "Directivo");
        
        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(10, 95, 46, 14);
        panelDirectivo.add(lblCargo);
        
        JLabel lblrea = new JLabel("\u00C1rea:");
        lblrea.setBounds(120, 95, 46, 14);
        panelDirectivo.add(lblrea);
        
        textField = new JTextField();
        textField.setBounds(10, 112, 107, 20);
        panelDirectivo.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(120, 112, 119, 20);
        panelDirectivo.add(textField_1);
        textField_1.setColumns(10);
        
              
        // Mostrar panel vacío inicialmente
        ((CardLayout)panelContenedor.getLayout()).show(panelContenedor, "Vacio");
        
              
    }

    private void crearPanelesEspecificos() {
        // Panel para Estudiante
        panelEstudiante = new JPanel();
        panelEstudiante.setLayout(null);
        
        JLabel lblGrupo = new JLabel("Grupo:");
        lblGrupo.setBounds(20, 13, 46, 14);
        panelEstudiante.add(lblGrupo);
        
        textGrupo = new JTextField();
        textGrupo.setBounds(65, 10, 46, 20);
        panelEstudiante.add(textGrupo);
        
        JLabel lblAo = new JLabel("A\u00F1o:");
        lblAo.setBounds(121, 13, 38, 14);
        panelEstudiante.add(lblAo);
        
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
        spinner.setBounds(153, 10, 29, 20);
        panelEstudiante.add(spinner);
     
        
        // Panel para Profesor
        panelProfesor = new JPanel();
        panelProfesor.setLayout(null);
        
        JLabel lblDepartamento = new JLabel("Departamento:");
        lblDepartamento.setBounds(10, 13, 100, 14);
        panelProfesor.add(lblDepartamento);
        
        txtDepartamento = new JTextField();
        txtDepartamento.setBounds(120, 10, 120, 20);
        panelProfesor.add(txtDepartamento);
        
        JLabel lblCDocente = new JLabel("Categoria Docente:");
        lblCDocente.setBounds(10, 44, 111, 14);
        panelProfesor.add(lblCDocente);
        
        textCDocente = new JTextField();
        textCDocente.setBounds(120, 41, 120, 20);
        panelProfesor.add(textCDocente);
        textCDocente.setColumns(10);
        
        
        JLabel lblCCientifica = new JLabel("Categoria Cientifica:");
        lblCCientifica.setBounds(10, 75, 111, 14);
        panelProfesor.add(lblCCientifica);
        
        textCientifica = new JTextField();
        textCientifica.setBounds(120, 72, 120, 20);
        panelProfesor.add(textCientifica);
        textCientifica.setColumns(10);
        
        JLabel lblTContrato = new JLabel("Tipo de Contrato:");
        lblTContrato.setBounds(10, 107, 100, 14);
        panelProfesor.add(lblTContrato);
        
        textTContrato = new JTextField();
        textTContrato.setBounds(120, 104, 120, 20);
        panelProfesor.add(textTContrato);
        textTContrato.setColumns(10);
        
   
        
        
        // Panel para Administrativo
        panelAdministrativo = new JPanel();
        panelAdministrativo.setLayout(null);
        
        JLabel lblPlaza = new JLabel("Plaza ocupada:");
        lblPlaza.setBounds(10, 13, 91, 14);
        panelAdministrativo.add(lblPlaza);
        
        txtPlaza = new JTextField();
        txtPlaza.setBounds(100, 10, 120, 20);
        panelAdministrativo.add(txtPlaza);
        
        
     // Panel para Especialista
        panelEspecialista = new JPanel();
        panelEspecialista.setLayout(null);
        
        JLabel lblespecialista = new JLabel("Proyecto:");
        lblespecialista.setBounds(10, 13, 62, 14);
        panelEspecialista.add(lblespecialista);
        
        textProyecto = new JTextField();
        textProyecto.setBounds(72, 10, 153, 20);
        panelEspecialista.add(textProyecto);
        textProyecto.setColumns(10);
        
     // Panel para Visitante
        panelVisitante = new JPanel();
        panelVisitante.setLayout(null);
        
        JLabel lblMotivoDeVisita = new JLabel("Motivo de visita:");
        lblMotivoDeVisita.setBounds(10, 26, 95, 14);
        panelVisitante.add(lblMotivoDeVisita);
        
        JLabel lblAutorizadoaPor = new JLabel("Autorizado(a) por:");
        lblAutorizadoaPor.setBounds(10, 87, 118, 14);
        panelVisitante.add(lblAutorizadoaPor);
        
        JLabel lblAreaDeOrigen = new JLabel("\u00C1rea de origen:");
        lblAreaDeOrigen.setBounds(10, 54, 95, 14);
        panelVisitante.add(lblAreaDeOrigen);
        
        textMVisita = new JTextField();
        textMVisita.setBounds(104, 23, 135, 20);
        panelVisitante.add(textMVisita);
        textMVisita.setColumns(10);
        
        textAOrigen = new JTextField();
        textAOrigen.setBounds(104, 51, 135, 20);
        panelVisitante.add(textAOrigen);
        textAOrigen.setColumns(10);
        
        textAPor = new JTextField();
        textAPor.setBounds(10, 112, 182, 20);
        panelVisitante.add(textAPor);
        textAPor.setColumns(10);
        
     // Panel para Tecnico
        panelTecnico = new JPanel();
        panelTecnico.setLayout(null);
        
        JLabel lblPlaza2 = new JLabel("Plaza ocupada:");
        lblPlaza2.setBounds(10, 13, 99, 14);
        panelTecnico.add(lblPlaza2);
        
        txtPlaza = new JTextField();
        txtPlaza.setBounds(108, 10, 120, 20);
        panelTecnico.add(txtPlaza);
        
        
        
        // Panel para Directivo
        panelDirectivo = new JPanel();
        panelDirectivo.setLayout(null);
        
        
        JLabel labelDD = new JLabel("Departamento:");
        labelDD.setBounds(10, 11, 100, 14);
        panelDirectivo.add(labelDD);
        
        textDepartamentoD = new JTextField();
        textDepartamentoD.setBounds(10, 27, 107, 20);
        panelDirectivo.add(textDepartamentoD);
        
        labelCDD = new JLabel("Categoria Docente:");
        labelCDD.setBounds(120, 11, 119, 14);
        panelDirectivo.add(labelCDD);
        
        textCDocenteD = new JTextField();
        textCDocenteD.setColumns(10);
        textCDocenteD.setBounds(120, 27, 119, 20);
        panelDirectivo.add(textCDocenteD);
        
        JLabel labeLCCD = new JLabel("Categoria Cientifica:");
        labeLCCD.setBounds(10, 58, 107, 14);
        panelDirectivo.add(labeLCCD);
        
        textCCientificaD = new JTextField();
        textCCientificaD.setColumns(10);
        textCCientificaD.setBounds(10, 72, 107, 20);
        panelDirectivo.add(textCCientificaD);
        
        JLabel labelTCD = new JLabel("Tipo de Contrato:");
        labelTCD.setBounds(120, 58, 100, 14);
        panelDirectivo.add(labelTCD);
        
        textTContratoD = new JTextField();
        textTContratoD.setColumns(10);
        textTContratoD.setBounds(119, 72, 120, 20);
        panelDirectivo.add(textTContratoD);
        
        
        
        
        
    }
//PANEL PARA PONER LA FECHA Y HORA 
    

    private void crearPanelBotones() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton buttonGuardar = new JButton("Guardar");
        buttonGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guardarRegistro();
        	}
        });
        buttonGuardar.setActionCommand("OK");
        buttonPane.add(buttonGuardar);
        getRootPane().setDefaultButton(buttonGuardar);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
        
        
    }

    private void mostrarCamposEspecificos(TipoPersonal tipo) {
        CardLayout cl = (CardLayout)(panelContenedor.getLayout());
        
        switch(tipo) {
        
            case Visitante:
        	    cl.show(panelContenedor, "Visitante");
        	    break; 
            case Tecnico:
        	    cl.show(panelContenedor, "Tecnico");
        	    break; 
            case Especialista:
        	    cl.show(panelContenedor,"Especialista");
        	    break;
            case Directivo:
        	    cl.show(panelContenedor, "Directivo");
        	    break;
            case Estudiante:
                cl.show(panelContenedor, "Estudiante");
                break;
            case Profesor:
                cl.show(panelContenedor, "Profesor");
                break;
            case Administrativo:
                cl.show(panelContenedor, "Administrativo");
                break;
            default:
                cl.show(panelContenedor, "Vacio");
        }
    }
    
    private void guardarRegistro() {
        try {
            // Validaciones
            if (txtNombre.getText().trim().isEmpty() || 
                txtApellidos.getText().trim().isEmpty() || 
                txtIdentidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre, apellidos e identidad son obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (comboBoxTipo.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un tipo de persona", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Obtener datos
            String nombre = txtNombre.getText();
            String apellido = txtApellidos.getText();
            String numID = txtIdentidad.getText();
            TipoPersonal tipo = (TipoPersonal) comboBoxTipo.getSelectedItem();
            
            // Crear persona
            Persona persona = crearPersonaSegunTipo(nombre, apellido, numID, tipo);
            
            // AÑADIDO: Guardar persona en la facultad
            if (facultad != null) {
                facultad.agregarPersona(persona);
            } else {
                JOptionPane.showMessageDialog(this, "Error: Facultad no inicializada", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this, "Persona registrada exitosamente\nID: " + numID);
            dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private Persona crearPersonaSegunTipo(String nombre, String apellido, String numID, TipoPersonal tipo) {
        switch(tipo) {
            case Estudiante:
                return new Estudiante(nombre, apellido, numID, tipo, 
                    Integer.parseInt(textGrupo.getText()), 1);
                
            case Profesor:
                return new Profesor(nombre, apellido, numID, tipo, 
                    txtDepartamento.getText(), 
                    textCDocente.getText(), 
                    textCientifica.getText(), 
                    textTContrato.getText());
                    
            case Administrativo:
                return new Administrativo(nombre, apellido, numID, tipo, 
                    txtPlaza.getText());
                    
            case Tecnico:
                return new Tecnico(nombre, apellido, numID, tipo, 
                    txtPlaza.getText());
                    
            case Especialista:
                return new Especialista(nombre, apellido, numID, tipo, 
                    textProyecto.getText());
                    
            case Directivo:
                return new Directivo(nombre, apellido, numID, tipo,
                    textDepartamentoD.getText(),
                    textCDocenteD.getText(),
                    textCCientificaD.getText(),
                    textTContratoD.getText(),
                    textField.getText(),  // Cargo
                    textField_1.getText() // Área
                );
                
            case Visitante:
                return new Visitante(nombre, apellido, numID, tipo,
                    textMVisita.getText(),  // Motivo visita
                    textAOrigen.getText(),  // Área origen
                    textAPor.getText()     // Autorizado por
                );
                
            default:
                throw new IllegalArgumentException("Tipo de persona no soportado: " + tipo);
        }
    }
}

