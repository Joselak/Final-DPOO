package dpooFinal.interfaz;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
    
    public Principal() {
        setTitle("Gestor de Accesos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 657, 503);
        setResizable(false);

        // INICIALIZAR FACULTAD 
        Inicializador ini= new Inicializador();
        facultad = ini.inicializarDatosRegistros();
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("Registros");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmMostrarRegistros = new JMenuItem("Mostrar registros");
        mntmMostrarRegistros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MostrarRegistros dialog = new MostrarRegistros(facultad);
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
                    AnadirPersona dialog = new AnadirPersona();
                    dialog.setFacultad(facultad);  
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
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
        
        JMenu mnConsultar = new JMenu("Reportes");
        menuBar.add(mnConsultar);
        
        JMenuItem mntmVisitasALocales = new JMenuItem("Visitas en un intervalos");
        mnConsultar.add(mntmVisitasALocales);
        
        JMenuItem mntmLocalesPorPersona = new JMenuItem("Visitas por una persona");
        mnConsultar.add(mntmLocalesPorPersona);
        mntmLocalesPorPersona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PersonaVisita dialog = new PersonaVisita(facultad);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
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
        
        JMenu mnAyuda = new JMenu("Ayuda");
        menuBar.add(mnAyuda);
        
        JMenuItem menuItem = new JMenuItem("Horario de accesos");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    HorarioAcceso dialog = new HorarioAcceso();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
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
        lblCarnet.setBounds(10, 28, 148, 14);
        panelDatosPersonales.add(lblCarnet);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(10, 49, 194, 20);
        panelDatosPersonales.add(textField_2);
        
        JLabel label = new JLabel("Nombre(s):");
        label.setBounds(10, 81, 66, 14);
        panelDatosPersonales.add(label);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 107, 194, 20);
        panelDatosPersonales.add(textField);
        
        JLabel label_1 = new JLabel("Apellido(s)");
        label_1.setBounds(10, 139, 78, 14);
        panelDatosPersonales.add(label_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(10, 165, 194, 20);
        panelDatosPersonales.add(textField_1);
        
        // Panel para datos de acceso
        panelDatosAcceso = new JPanel();
        panelDatosAcceso.setBorder(new TitledBorder(null, "Datos de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDatosAcceso.setLayout(null);
        
        // Panel para local de acceso (siempre visible)
        panelLocalAcceso = new JPanel();
        panelLocalAcceso.setLayout(null);
        panelLocalAcceso.setBorder(new TitledBorder(null, "Local de acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelLocalAcceso.setBounds(10, 34, 212, 64);
        panelDatosAcceso.add(panelLocalAcceso);
        
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
        comboBox_3.setModel(new DefaultComboBoxModel<TipoPersonal>(TipoPersonal.values()));
        comboBox_3.setBounds(31, 22, 149, 20);
        panelTipoPersona.add(comboBox_3);
        
        // Añadir paneles al cardPanel
        cardPanel.add(panelDatosPersonales, "datosPersonales");
        cardPanel.add(panelDatosAcceso, "datosAcceso");
        
        // Botones
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
    
    private void configurarVisibilidadCampos() {
    	
    	textField.setVisible(false);
        textField_1.setVisible(false);
        panelDatosPersonales.getComponent(2).setVisible(false); // Label nombre
        panelDatosPersonales.getComponent(4).setVisible(false); // Label apellido
    	
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
            panelDatosPersonales.getComponent(2).setVisible(true); // Label nombre
            panelDatosPersonales.getComponent(4).setVisible(true); // Label apellido
            
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
    
    private void siguientePaso() {
        if (!rdbtnPersonal.isSelected() && !rdbtnVisitante.isSelected()) {
            JOptionPane.showMessageDialog(this, "Seleccione si es Personal de la Facultad o es un Visitante", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Validar datos personales
        String carnet = textField_2.getText().trim();
        if (carnet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el carnet", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (rdbtnPersonal.isSelected()) {
            Persona persona = facultad.buscarPersona(carnet);
            if (persona == null || persona.getTipo() == TipoPersonal.Visitante) {
                JOptionPane.showMessageDialog(this, "No se encontró personal con ese carnet", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Autocompletar nombre y apellido para personal
            textField.setText(persona.getNombre());
            textField_1.setText(persona.getApellido());
        } else if (rdbtnVisitante.isSelected()) {
            String nombre = textField.getText().trim();
            String apellido = textField_1.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos del visitante", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!validarNombreApellido(nombre) || !validarNombreApellido(apellido)) {
                JOptionPane.showMessageDialog(this, "Nombre inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        if (cardPanel.getComponent(0).isVisible()) {
            cardLayout.show(cardPanel, "datosAcceso");
            btnSiguiente.setText("Registrar");
            
            // Si es personal, establecer el tipo automáticamente
            if (rdbtnPersonal.isSelected()) {
                Persona persona = facultad.buscarPersona(textField_2.getText().trim());
                if (persona != null) {
                    comboBox_3.setSelectedItem(persona.getTipo());
                }
            }
        } else {
            registrarAcceso();
            cardLayout.show(cardPanel, "datosPersonales");
            btnSiguiente.setText("Siguiente");
            limpiarCampos();
        }
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
        } else if (rdbtnVisitante.isSelected()) {
            String nombre = textField.getText().trim();
            String apellido = textField_1.getText().trim();

            Visitante visitante = new Visitante(nombre, apellido, carnet, TipoPersonal.Visitante, 
                    "Visita", "Institución no especificada", "Contacto no especificado");
            
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
    }
}