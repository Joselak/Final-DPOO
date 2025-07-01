package dpooFinal.interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Component;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Insets;

import dpooFinal.inicializador.Inicializador;
import dpooFinal.logica.Clasificacion;
import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Persona;
import dpooFinal.logica.TipoPersonal;
import dpooFinal.logica.Visitante;

public class Principal extends JFrame {


	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Facultad facultad;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JComboBox<Clasificacion> comboBox_2;
    private JComboBox<TipoPersonal> comboBox_3;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton rdbtnPersonal;
    private JRadioButton rdbtnVisitante;
    private JButton btnSiguiente;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel panelDatosPersonales;
    private JPanel panelDatosAcceso;
    private JPanel panelLocalAcceso;
    private JPanel panelTipoPersona;
    private JPanel panelDatosVisitante;
    private JTextField textMotivoVisita;
    private JTextField textAreaOrigen;
    private JTextField textAutorizadoPor;	
	private JButton btnAnterior;
	private JLabel label;
	private JLabel label_1;
    //private JLabel lblMotivoVisita;
	//private JLabel lblAreaOrigen;
	//private JLabel lblAutorizadoPor;
	HorarioAcceso dialog;
	VisitasIntervalo dialogVisitasIntervalo;
	PersonaVisita dialogPersonaVisita;
	Busqueda dialogBusqueda;
	AnadirPersona dialogAnadirPersona;
	MostrarRegistros dialogMostrarRegistros;
	
    public Principal() {
        setTitle("Gestor de Accesos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 657, 503);
        setResizable(false);

        // INICIALIZAR FACULTAD 
        Inicializador ini= new Inicializador();
        facultad = ini.inicializarDatosRegistros();
        
        Inicializador.configurarTemaInicial();
        
        
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("Registros");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmMostrarRegistros = new JMenuItem("Mostrar registros");
        mntmMostrarRegistros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                	if (dialogMostrarRegistros == null || !dialogMostrarRegistros.isVisible()){
                	dialogMostrarRegistros= new MostrarRegistros(facultad);
                	dialogMostrarRegistros.setFacultad(facultad);
                	dialogMostrarRegistros.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                	dialogMostrarRegistros.setVisible(true);
                	
                	dialogMostrarRegistros.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        	dialogMostrarRegistros = null;
                        }
                    });
                	}else {
                        // Traer al frente la ventana existente
                		dialogMostrarRegistros.toFront();
                    }
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
                	
                	if (dialogAnadirPersona == null || !dialogAnadirPersona.isVisible()){
                		dialogAnadirPersona = new AnadirPersona();
                	dialogAnadirPersona.setFacultad(facultad);  
                	dialogAnadirPersona.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                	dialogAnadirPersona.setVisible(true);
                	
                	dialogAnadirPersona.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        	dialogAnadirPersona = null;
                        }
                    });
                	}else {
                        // Traer al frente la ventana existente
                		dialogAnadirPersona.toFront();
                    }
       	
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        mnNewMenu.add(mntmAadirRegistro);
        
        JMenu mnBuscar = new JMenu("Buscar");
        menuBar.add(mnBuscar);
        
        JMenuItem mntmBusquedaPor = new JMenuItem("Busqueda por...");
        mntmBusquedaPor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                	if (dialogBusqueda == null || !dialogBusqueda.isVisible()){
                		dialogBusqueda= new Busqueda();
                	dialogBusqueda.setFacultad(facultad);
                	dialogBusqueda.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                	dialogBusqueda.setVisible(true);
                	
                	dialogBusqueda.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        	dialogBusqueda = null;
                        }
                    });
                	}else {
                        // Traer al frente la ventana existente
                		dialogBusqueda.toFront();
                    }
	
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        mnBuscar.add(mntmBusquedaPor);
        
        JMenu mnConsultar = new JMenu("Reportes");
        menuBar.add(mnConsultar);
              
        
        //VISITAS POR UUNA PERSONA
        JMenuItem mntmLocalesPorPersona = new JMenuItem("Visitas por una persona");
        mnConsultar.add(mntmLocalesPorPersona);
        mntmLocalesPorPersona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                	if (dialogPersonaVisita == null || !dialogPersonaVisita.isVisible()){
                		dialogPersonaVisita= new PersonaVisita(facultad);
                	dialogPersonaVisita.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                	dialogPersonaVisita.setVisible(true);
                	
                	dialogPersonaVisita.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        	dialogPersonaVisita = null;
                        }
                    });
                		
                	}else {
                        // Traer al frente la ventana existente
                		dialogPersonaVisita.toFront();
                    }
                	
                	
                	
                	
                	
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        
        
        
        JMenuItem mntmVisitasALocales = new JMenuItem("Visitas en un intervalos");
        mnConsultar.add(mntmVisitasALocales);
        
        mntmVisitasALocales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                	if (dialogVisitasIntervalo == null || !dialogVisitasIntervalo.isVisible()) {
                		dialogVisitasIntervalo = new VisitasIntervalo(facultad);
                    	dialogVisitasIntervalo.setFacultad(facultad);
                    	dialogVisitasIntervalo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    	dialogVisitasIntervalo.setVisible(true);
                        // Listener para limpiar la referencia al cerrar
                		dialogVisitasIntervalo.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            	dialogVisitasIntervalo = null;
                            }
                        });
                    } else {
                        // Traer al frente la ventana existente
                    	dialogVisitasIntervalo.toFront();
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        JMenu mnAyuda = new JMenu("Ayuda");
        menuBar.add(mnAyuda);
        
        JMenuItem menuItem = new JMenuItem("Horario de accesos");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    if (dialog == null || !dialog.isVisible()){
                    	 dialog = new HorarioAcceso();
                    	 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                         dialog.setVisible(true);
                         
                         
                      // Listener para limpiar la referencia al cerrar
                         dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                             @Override
                             public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                 dialog = null;
                             }
                         });
                    }else{
                        // Traer al frente la ventana existente
                        dialog.toFront();
                    }                   
        
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        mnAyuda.add(menuItem);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Panel de selección de tipo de persona
        JPanel panelTipoPersonaSeleccion = new JPanel();
        panelTipoPersonaSeleccion.setBounds(161, 11, 326, 68);
        contentPane.add(panelTipoPersonaSeleccion);
        panelTipoPersonaSeleccion.setLayout(null);
        
        rdbtnPersonal = new JRadioButton("Personal");
        buttonGroup.add(rdbtnPersonal);
        rdbtnPersonal.setBounds(6, 27, 109, 23);
        panelTipoPersonaSeleccion.add(rdbtnPersonal);
        
        rdbtnVisitante = new JRadioButton("Visitante");
        buttonGroup.add(rdbtnVisitante);
        rdbtnVisitante.setBounds(217, 27, 109, 23);
        panelTipoPersonaSeleccion.add(rdbtnVisitante);
        
        // Configuración del CardLayout para los paneles de datos
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        cardPanel.setBounds(181, 90, 251, 208);
        contentPane.add(cardPanel);
        
        // Panel para datos personales
        panelDatosPersonales = new JPanel();
        panelDatosPersonales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
                "Información Personal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelDatosPersonales.setLayout(null);
        
        JLabel lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(30, 28, 148, 14);
        panelDatosPersonales.add(lblCarnet);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(30, 50, 194, 20);
        panelDatosPersonales.add(textField_2);
        
        label = new JLabel("Nombre(s):");
        label.setBounds(30, 81, 66, 14);
        panelDatosPersonales.add(label);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(30, 106, 194, 20);
        panelDatosPersonales.add(textField);
        
        label_1 = new JLabel("Apellido(s)");
        label_1.setBounds(30, 139, 78, 14);
        panelDatosPersonales.add(label_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(30, 164, 194, 20);
        panelDatosPersonales.add(textField_1);
        

        
        
        // Panel para datos de acceso
        panelDatosAcceso = new JPanel();
        panelDatosAcceso.setBorder(new TitledBorder(null, "Datos de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDatosAcceso.setLayout(null);
        
        //PANEL PARA LOS OTROS DATOS DEL VISITANTE
        panelDatosVisitante = new JPanel();
        panelDatosVisitante.setBorder(new TitledBorder(null, "Datos del Visitante", 
            TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDatosVisitante.setLayout(null);

        JLabel lblMotivoVisita = new JLabel("Motivo de visita:");
        lblMotivoVisita.setBounds(30, 30, 150, 14);
        panelDatosVisitante.add(lblMotivoVisita);

        textMotivoVisita = new JTextField();
        textMotivoVisita.setBounds(30, 50, 194, 20);
        panelDatosVisitante.add(textMotivoVisita);
        textMotivoVisita.setColumns(10);

        JLabel lblAreaOrigen = new JLabel("Área de origen:");
        lblAreaOrigen.setBounds(30, 80, 150, 14);
        panelDatosVisitante.add(lblAreaOrigen);

        textAreaOrigen = new JTextField();
        textAreaOrigen.setBounds(30, 100, 194, 20);
        panelDatosVisitante.add(textAreaOrigen);
        textAreaOrigen.setColumns(10);

        JLabel lblAutorizadoPor = new JLabel("Autorizado por:");
        lblAutorizadoPor.setBounds(30, 130, 150, 14);
        panelDatosVisitante.add(lblAutorizadoPor);

        textAutorizadoPor = new JTextField();
        textAutorizadoPor.setBounds(30, 150, 194, 20);
        panelDatosVisitante.add(textAutorizadoPor);
        textAutorizadoPor.setColumns(10);

        
        // Panel para local de acceso (siempre visible)
        panelLocalAcceso = new JPanel();
        panelLocalAcceso.setLayout(null);
        panelLocalAcceso.setBorder(new TitledBorder(null, "Local de acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelLocalAcceso.setBounds(10, 33, 212, 64);
        panelDatosAcceso.add(panelLocalAcceso);
        //COMBOBOX PARA ELEGIR UN TIPO DE LOCAL
        comboBox_2 = new JComboBox<>(new DefaultComboBoxModel<>(Clasificacion.values()));
        cargarLocales();
        comboBox_2.setBounds(30, 27, 153, 20);
        panelLocalAcceso.add(comboBox_2);
        comboBox_2.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Clasificacion) {
                    setText(value.toString());
                }
                return this;
            }
        });
        
        // Panel para tipo de persona (solo para visitantes)
        panelTipoPersona = new JPanel();
        panelTipoPersona.setLayout(null);
        panelTipoPersona.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tipo de persona", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panelTipoPersona.setBounds(10, 120, 212, 64);
        panelDatosAcceso.add(panelTipoPersona);
        
        comboBox_3 = new JComboBox<TipoPersonal>();
     // Crear lista de tipos excluyendo Visitante
        List<TipoPersonal> tiposFiltrados = new ArrayList<>(Arrays.asList(TipoPersonal.values()));
        tiposFiltrados.remove(TipoPersonal.Visitante);

        // Configurar modelo con tipos filtrados
        comboBox_3.setModel(new DefaultComboBoxModel<>(tiposFiltrados.toArray(new TipoPersonal[0])));
        comboBox_3.setBounds(31, 22, 149, 20);
        panelTipoPersona.add(comboBox_3);
        
        // Añadir paneles al cardPanel
        cardPanel.add(panelDatosPersonales, "datosPersonales");
        cardPanel.add(panelDatosAcceso, "datosAcceso");
        cardPanel.add(panelDatosVisitante, "datosVisitante");
        
        // Botones
        
        btnAnterior = new JButton("Anterior");
        btnAnterior.setBounds(323, 406, 89, 23);
        btnAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAtras();
            }
        });
        contentPane.add(btnAnterior);
        btnAnterior.setVisible(false); // Inicialmente oculto
        
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(422, 406, 89, 23);
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                siguientePaso();
            }
        });
        contentPane.add(btnSiguiente);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnSalir.setBounds(523, 406, 89, 23);
        contentPane.add(btnSalir);
        
        
        //BOTON PARA CAMIAR DE TEMA
        final JButton btnCambiarTema = new JButton(){
			private static final long serialVersionUID = 1L;

			@Override
        	    public void setText(String text) {
        	        // Bloquea cualquier intento de establecer texto
        	        super.setText("");
        	    }
        };
        btnCambiarTema.setBorderPainted(false); // Para mejor apariencia
        btnCambiarTema.setContentAreaFilled(true); // Fondo transparente
        btnCambiarTema.setFocusPainted(false);
        btnCambiarTema.setMargin(new Insets(0, 0, 0, 0));  // Elimina márgenes internos
        btnCambiarTema.setBorder(null);  // Elimina bordes ocultos
        
     // Determinar el icono inicial según el tema actual
        ImageIcon iconoInicial;
        if (Inicializador.isTemaOscuro()) {
            // Si el tema es oscuro, mostrar el icono de sol (para cambiar a claro)
            iconoInicial = new ImageIcon(getClass().getResource("/icons/sun.png"));
        } else {
            // Si el tema es claro, mostrar el icono de luna (para cambiar a oscuro)
            iconoInicial = new ImageIcon(getClass().getResource("/icons/moon.png"));
        }

        // Redimensionar y asignar el icono
        Image imgInicial = iconoInicial.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnCambiarTema.setIcon(new ImageIcon(imgInicial));   
        
        btnCambiarTema.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Inicializador.cambiarTema();
        	    actualizarBotonTema(btnCambiarTema);
        	}
        });
        btnCambiarTema.setBounds(579, 11, 25, 23);
        contentPane.add(btnCambiarTema);
        
        
        // Listeners para radio buttons
        rdbtnPersonal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                configurarVisibilidadCampos();
            }
        });

        rdbtnVisitante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                configurarVisibilidadCampos();
            }
        });
        
        // Configuración inicial
        configurarVisibilidadCampos();
        
        setLocationRelativeTo(null);
    }
    
    
    /*---------------------------------------------------------------------------------------------
     * --------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------
     * -------------------------------------METODOS*/
    
    
    
    private void configurarVisibilidadCampos() {
    	
    	textField.setVisible(false);//nombre
        textField_1.setVisible(false);//apellido
        label.setVisible(false); // Label nombre
        label_1.setVisible(false); // Label apellido
        
        
    	
        if (rdbtnPersonal.isSelected()) {
            // Ocultar nombre y apellido en panel de datos personales
            textField.setVisible(false);
            textField_1.setVisible(false);
            panelDatosPersonales.getComponent(2).setVisible(false); // Label nombre
            panelDatosPersonales.getComponent(4).setVisible(false); // Label apellido
            
            // Ocultar panel de tipo de persona y mostrar solo local de acceso
            panelTipoPersona.setVisible(false);
            panelLocalAcceso.setVisible(true);
            comboBox_3.setEnabled(false);
            
            
            
        } else if (rdbtnVisitante.isSelected()) {
            // Mostrar todo para visitante
            textField.setVisible(true);
            textField_1.setVisible(true);
            label.setVisible(true); // Label nombre
            label_1.setVisible(true); // Label apellido
            
            // Mostrar ambos paneles
            panelTipoPersona.setVisible(true);
            panelLocalAcceso.setVisible(true);           
            comboBox_3.setEnabled(true);
            comboBox_3.setSelectedItem(TipoPersonal.Visitante);
            
        }
        
        // Mostrar siempre el panel de datos personales al inicio
        cardLayout.show(cardPanel, "datosPersonales");
        btnSiguiente.setText("Siguiente");
    }
    
    
    
    //METODO PARA UTILIZAR EL BOTON SIGUIENTE
    private void siguientePaso() {
        String carnet;
        boolean continuar = true;
        String errorMessage = null;
        int messageType = JOptionPane.ERROR_MESSAGE;

        // Validar selección de tipo
        if (!rdbtnPersonal.isSelected() && !rdbtnVisitante.isSelected()) {
            errorMessage = "Seleccione si es Personal de la Facultad o es un Visitante";
            messageType = JOptionPane.INFORMATION_MESSAGE;
            continuar = false;
        }

        // Validar carnet si no hay error previo
        if (continuar) {
            carnet = textField_2.getText().trim();
            if (carnet.isEmpty()) {
                errorMessage = "Complete los campos";
                continuar = false;
            } 
            else if (!carnet.matches("^\\d{11}$")) {
                errorMessage = "Carnet inválido. Debe contener 11 dígitos";
                continuar = false;
            }
        }

        // Mostrar error si existe
        if (!continuar) {
            JOptionPane.showMessageDialog(this, errorMessage, "Error", messageType);
            return;
        }

        carnet = textField_2.getText().trim();  // Recuperar carnet validado

        // Manejar panel visible
        if (panelDatosPersonales.isVisible()) {
            continuar = manejarPanelDatosPersonales(carnet);
        } 
        else if (panelDatosAcceso.isVisible()) {
            manejarPanelDatosAcceso();
        } 
        else if (panelDatosVisitante.isVisible()) {
            continuar = manejarPanelDatosVisitante();
            if (continuar) {
                registrarAcceso();
                reiniciarFlujo();
            }
        }
    }

    
    
    private boolean manejarPanelDatosPersonales(String carnet) {
        boolean continuar = true;
        String errorMessage = null;
        
        if (rdbtnPersonal.isSelected()) {
            Persona persona = facultad.buscarPersona(carnet);
            if (persona == null || persona.getTipo() == TipoPersonal.Visitante) {
                errorMessage = "No se encontró personal con ese carnet";
                continuar = false;
            } else {
                textField.setText(persona.getNombre());
                textField_1.setText(persona.getApellido());
            }
        } 
        else if (rdbtnVisitante.isSelected()) {
            String nombre = textField.getText().trim();
            String apellido = textField_1.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty()) {
                errorMessage = "Complete todos los campos del visitante";
                continuar = false;
            } 
            else if (!validarNombreApellido(nombre) || !validarNombreApellido(apellido)) {
                errorMessage = "Nombre inválido";
                continuar = false;
            }
        }

        // Si todo está bien, avanzar
        if (continuar) {
            cardLayout.show(cardPanel, "datosAcceso");
            btnSiguiente.setText("Siguiente");
            btnAnterior.setVisible(true);
            
            if (rdbtnPersonal.isSelected()) {
                Persona persona = facultad.buscarPersona(carnet);
                if (persona != null) {
                    comboBox_3.setSelectedItem(persona.getTipo());
                }
            }
        } 
        else if (errorMessage != null) {
            JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return continuar;
    }
    
    

    private void manejarPanelDatosAcceso() {
        if (rdbtnVisitante.isSelected()) {
            cardLayout.show(cardPanel, "datosVisitante");
            btnSiguiente.setText("Registrar");
            btnAnterior.setVisible(true);
        } else {
            registrarAcceso();
            reiniciarFlujo();
        }
    }

    
    
    private boolean manejarPanelDatosVisitante() {
        String motivo = textMotivoVisita.getText().trim();
        String area = textAreaOrigen.getText().trim();
        String autorizado = textAutorizadoPor.getText().trim();
        boolean continuar = true;
        
        if (motivo.isEmpty() || area.isEmpty() || autorizado.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Complete todos los campos del visitante", 
                "Error", JOptionPane.ERROR_MESSAGE);
            continuar = false;
        }
        
        return continuar;
    }
    

    private void reiniciarFlujo() {
        cardLayout.show(cardPanel, "datosPersonales");
        btnSiguiente.setText("Siguiente");
        btnAnterior.setVisible(false);
        limpiarCampos();
    }
    
    
    
    private void cargarLocales() {
        DefaultComboBoxModel<Clasificacion> model = new DefaultComboBoxModel<>(Clasificacion.values());
        comboBox_2.setModel(model);
        
        comboBox_2.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Clasificacion) {
                    Clasificacion clasificacion = (Clasificacion) value;
                    Local primerLocal = facultad.getPrimerLocalPorClasificacion(clasificacion);
                    if (primerLocal != null) {
                        setText(clasificacion.toString() + " (" + primerLocal.getId() + ")");
                    } else {
                        setText(clasificacion.toString() + " (Sin locales)");
                    }
                }
                return this;
            }
        });
    }
    
    
    
    private LocalDateTime calcularHoraSalida(Persona persona) {
        LocalDateTime ahora = LocalDateTime.now();
        
        switch (persona.getTipo()) {
            case Estudiante:
                return ahora.plusHours(3);
            case Profesor:
                return ahora.plusHours(5);
            case Directivo:
                return ahora.plusHours(8);
            case Administrativo:
                return ahora.plusHours(6);
            case Visitante:
                return ahora.plusHours(4);
            default:
                return ahora.plusHours(1);
        }
    }
    
    
    
    
    private void registrarAcceso() {
        String carnet = textField_2.getText().trim();
        Clasificacion clasificacionSeleccionada = (Clasificacion) comboBox_2.getSelectedItem();
        
        String nombreLocal = generarNombreLocal(clasificacionSeleccionada);
        Local nuevoLocal = new Local(nombreLocal, clasificacionSeleccionada);
        facultad.agregarLocal(nuevoLocal);

        if (rdbtnPersonal.isSelected()) {
            Persona persona = facultad.buscarPersona(carnet);
            
            if (!facultad.verificarAcceso(nuevoLocal, persona)) {
                JOptionPane.showMessageDialog(this,
                        "Acceso denegado: esta persona no puede entrar a este local",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime horaEntrada = LocalDateTime.now();
            LocalDateTime horaSalida = calcularHoraSalida(persona);

            nuevoLocal.registrarAcceso(persona, horaEntrada, horaSalida);

            JOptionPane.showMessageDialog(this,
                    "Acceso registrado para: " + persona.getNombreCompleto() +
                            "\nHora de entrada: " + horaEntrada.format(formato) +
                            "\nHora de salida estimada: " + horaSalida.format(formato),
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            
        } else if (rdbtnVisitante.isSelected()) {//SI EL VISITANTE ES SELECCCIONADO
            String nombre = textField.getText().trim();
            String apellido = textField_1.getText().trim();
            String motivo = textMotivoVisita.getText().trim();
            String area = textAreaOrigen.getText().trim();
            String autorizado = textAutorizadoPor.getText().trim();

            Visitante visitante = new Visitante(nombre, apellido, carnet, TipoPersonal.Visitante, 
            		motivo,area,autorizado);
            
            facultad.agregarPersona(visitante);
            
            if (!facultad.verificarAcceso(nuevoLocal, visitante)) {
                JOptionPane.showMessageDialog(this, 
                    "Acceso denegado: los visitantes solo pueden acceder en horario de 8:00 a 12:00", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            LocalDateTime horaEntrada = LocalDateTime.now();
            LocalDateTime horaSalida = calcularHoraSalida(visitante);

            nuevoLocal.registrarAcceso(visitante, horaEntrada, horaSalida);

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            JOptionPane.showMessageDialog(this,
                    "Acceso registrado para el visitante: " + visitante.getNombreCompleto() +
                            "\nHora de entrada: " + horaEntrada.format(formato) +
                            "\nHora de salida estimada: " + horaSalida.format(formato),
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    private boolean validarNombreApellido(String texto) {
        return texto.matches("^[\\p{L} .'-]{1,50}$");
    }
    
    
    
    private String generarNombreLocal(Clasificacion clasificacion) {
        String prefijo;
        switch (clasificacion) {
            case Aula:
                prefijo = "AULA";
                break;
            case Laboratorios:
                prefijo = "LAB";
                break;
            case Decano:
                prefijo = "DEC";
                break;
            case Vicedecano:
                prefijo = "VDEC";
                break;
            case Estudiantes:
                prefijo = "ESTU";
                break;
            case Servidores:
                prefijo = "SERV";
                break;
            case Profesores:
                prefijo = "PROF";
                break;
            case Especialistas:
                prefijo = "ESP";
                break;
            case AreaA:
                prefijo = "AAD";
                break;
            case JefeD:
                prefijo = "JDD";
                break;
            default:
                prefijo = "LOCAL";
        }
        int sufijo = 100 + (int) (Math.random() * 200);
        return prefijo + "-" + sufijo;
    }
    
    
    
    private void limpiarCampos() {
        textField_2.setText("");
        textField.setText("");
        textField_1.setText("");
        comboBox_3.setSelectedItem(null);
        buttonGroup.clearSelection();
        rdbtnPersonal.setSelected(false);
        rdbtnVisitante.setSelected(false);
        textMotivoVisita.setText("");
        textAreaOrigen.setText("");
        textAutorizadoPor.setText("");
    }
    
    
    
    
    private void volverAtras() {
        if (panelDatosVisitante.isVisible()) {
            cardLayout.show(cardPanel, "datosAcceso");
            btnSiguiente.setText("Siguiente");
            btnAnterior.setVisible(false);
        } else if (panelDatosAcceso.isVisible()) {
            cardLayout.show(cardPanel, "datosPersonales");
            btnSiguiente.setText("Siguiente");
            btnAnterior.setVisible(false);
        }
    }
    
    
    private void actualizarBotonTema(JButton boton) {
        // Cargar los iconos
        ImageIcon iconoLuna = new ImageIcon(getClass().getResource("/icons/moon.png"));
        ImageIcon iconoSol = new ImageIcon(getClass().getResource("/icons/sun.png"));

        // Redimensionar si es necesario
        Image imgLuna = iconoLuna.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imgSol = iconoSol.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        if (Inicializador.isTemaOscuro()) {
            boton.setText("Modo Claro");
            boton.setIcon(new ImageIcon(imgSol)); // Usar icono de sol para tema oscuro
        } else {
            boton.setText("Modo Oscuro");
            boton.setIcon(new ImageIcon(imgLuna)); // Usar icono de luna para tema claro
        }
    }
}