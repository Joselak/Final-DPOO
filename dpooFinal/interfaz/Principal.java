package dpooFinal.interfaz;

import dpooFinal.inicializador.Inicializador;
import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Clasificacion;
import dpooFinal.logica.Persona;
import dpooFinal.logica.TipoPersonal;
import dpooFinal.logica.Visitante;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
                    "Información Personal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(23, 114, 251, 208);
        contentPane.add(panel);
        
        JLabel label = new JLabel("Nombre(s):");
        label.setBounds(10, 81, 66, 14);
        panel.add(label);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 107, 194, 20);
        panel.add(textField);
        
        JLabel label_1 = new JLabel("Apellido(s)");
        label_1.setBounds(10, 139, 78, 14);
        panel.add(label_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(10, 165, 194, 20);
        panel.add(textField_1);
        
        JLabel lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(10, 28, 148, 14);
        panel.add(lblCarnet);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(10, 49, 194, 20);
        panel.add(textField_2);
        
        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tipo de persona", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panel_2.setBounds(332, 114, 202, 81);
        contentPane.add(panel_2);
        
        comboBox_3 = new JComboBox<TipoPersonal>();
        comboBox_3.setModel(new DefaultComboBoxModel<TipoPersonal>(TipoPersonal.values()));
        comboBox_3.setBounds(28, 32, 145, 20);
        panel_2.add(comboBox_3);
        
        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBorder(new TitledBorder(null, "Local de acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.setBounds(332, 224, 202, 81);
        contentPane.add(panel_3);
        
        comboBox_2 = new JComboBox<>(new DefaultComboBoxModel<>(Clasificacion.values()));
        cargarLocales(); 
        comboBox_2.setBounds(29, 34, 145, 20);
        panel_3.add(comboBox_2);
        comboBox_2.setRenderer(new DefaultListCellRenderer() {
            /**
			 * 
			 */
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
        
        // Deshabilitar combo de tipo persona
        comboBox_3.setEnabled(false);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnSalir.setBounds(523, 406, 89, 23);
        contentPane.add(btnSalir);
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(422, 406, 89, 23);
        contentPane.add(btnRegistrar);
        
        rdbtnPersonal = new JRadioButton("Personal");
        buttonGroup.add(rdbtnPersonal);
        rdbtnPersonal.setBounds(67, 39, 121, 24);
        contentPane.add(rdbtnPersonal);
        
        rdbtnVisitante = new JRadioButton("Visitante");
        buttonGroup.add(rdbtnVisitante);
        rdbtnVisitante.setBounds(216, 39, 121, 24);
        contentPane.add(rdbtnVisitante);
        
        // Estado inicial: deshabilitar campos de nombre y apellido
        textField.setEnabled(false);
        textField_1.setEnabled(false);
        
        // Listeners para radio buttons
        rdbtnPersonal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setEnabled(false);     // Deshabilitar nombre
                textField_1.setEnabled(false);   // Deshabilitar apellido
                comboBox_3.setEnabled(false);
                buscarPersonaPorCarnet();
            }
        });
        
        textField_2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (rdbtnPersonal.isSelected()) {
                    buscarPersonaPorCarnet();
                }
            }
        });
        
        rdbtnVisitante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setEnabled(true);      // Habilitar nombre
                textField_1.setEnabled(true);    // Habilitar apellido
                comboBox_3.setSelectedItem(TipoPersonal.Visitante);
                comboBox_3.setEnabled(true);
                limpiarCamposPersona();
            }
        });
        
        // Listener para el botón Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarAcceso();
            }
        });
        
        setLocationRelativeTo(null);
    }
    
    private void buscarPersonaPorCarnet() {
        String carnet = textField_2.getText().trim();
        
        
        
        if (!carnet.isEmpty()) {
            Persona persona = facultad.buscarPersona(carnet);
            if (persona != null && persona.getTipo() != TipoPersonal.Visitante) {
                textField.setText(persona.getNombre());
                textField_1.setText(persona.getApellido());
                comboBox_3.setSelectedItem(persona.getTipo());
            } else {
                limpiarCamposPersona();
                if (persona == null) {
                    JOptionPane.showMessageDialog(Principal.this, 
                        "No se encontró personal con ese carnet", 
                        "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
    }
    
    private void limpiarCamposPersona() {
        textField.setText("");
        textField_1.setText("");
        comboBox_3.setSelectedItem(null);
    }
    
    private void cargarLocales() {
        // Crear modelo con TODAS las clasificaciones disponibles
        DefaultComboBoxModel<Clasificacion> model = new DefaultComboBoxModel<>(Clasificacion.values());
        comboBox_2.setModel(model);
        
        // Configurar cómo se muestran las clasificaciones
        comboBox_2.setRenderer(new DefaultListCellRenderer() {
          
			private static final long serialVersionUID = 1L;

			@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Clasificacion) {
                    Clasificacion clasificacion = (Clasificacion) value;
                    // Muestra la clasificación + el primer local disponible (si existe)
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
    
    //CALCULAR UNA HORA DE SALIDA APROXIMADA
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
    
    
    //METODO PARA EL BOTON DE REGISTRAR
    private void registrarAcceso() {
        String carnet = textField_2.getText().trim();
        Clasificacion clasificacionSeleccionada = (Clasificacion) comboBox_2.getSelectedItem();
        
        String nombreLocal = generarNombreLocal(clasificacionSeleccionada);
        Local nuevoLocal = new Local(nombreLocal, clasificacionSeleccionada);
        facultad.agregarLocal(nuevoLocal);

        boolean datosValidos = true;
        
        if (!rdbtnPersonal.isSelected() && !rdbtnVisitante.isSelected()){
        	JOptionPane.showMessageDialog(this,"Seleccione si es Personal de la Facultad o es un Visitante", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{ 
        
        if (rdbtnPersonal.isSelected()) {
            if (carnet.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el carnet del personal", "Error", JOptionPane.ERROR_MESSAGE);
                datosValidos = false;
            }else if (!textField_2.getText().matches("^\\d{11}$")) {
                JOptionPane.showMessageDialog(this,
                        "Carnet inválido: debe contener 11 dígitos numéricos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                datosValidos = false;
            } 

            Persona persona = facultad.buscarPersona(carnet);
            if (datosValidos && (persona == null || persona.getTipo() == TipoPersonal.Visitante)) {
                datosValidos = false;
            }

            if (datosValidos && !facultad.verificarAcceso(nuevoLocal, persona)) {
                JOptionPane.showMessageDialog(this,
                        "Acceso denegado: esta persona no puede entrar a este local",
                        "Error", JOptionPane.ERROR_MESSAGE);
                datosValidos = false;
            }

            if (datosValidos) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime horaEntrada = LocalDateTime.now();
                LocalDateTime horaSalida = calcularHoraSalida(persona);

                nuevoLocal.registrarAcceso(persona, horaEntrada, horaSalida);

                JOptionPane.showMessageDialog(this,
                        "Acceso registrado para: " + persona.getNombreCompleto() +
                                "\nHora de entrada: " + horaEntrada.format(formato) +
                                "\nHora de salida estimada: " + horaSalida.format(formato),
                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (rdbtnVisitante.isSelected()) {
            String nombre = textField.getText().trim();
            String apellido = textField_1.getText().trim();

            if (carnet.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos del visitante", "Error", JOptionPane.ERROR_MESSAGE);
                datosValidos = false;
            }else if (!textField_2.getText().matches("^\\d{11}$")) {
                JOptionPane.showMessageDialog(this,
                        "Carnet inválido: debe contener 11 dígitos numéricos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                datosValidos = false;
            } else if (!validarNombreApellido(nombre) || !validarNombreApellido(apellido)) {
                JOptionPane.showMessageDialog(this,
                        "Nombre inválido",
                        "Error", JOptionPane.ERROR_MESSAGE);
                datosValidos = false;
            }

            if (datosValidos) {
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
        }
        limpiarCampos();
    }
    

    //VALIDACION DEL NOMBRE Y EL APELLIDO
    private boolean validarNombreApellido(String texto) {
        // Verifica que el texto no esté vacío, solo contenga letras y tenga <= 50 caracteres
        return texto.matches("^[\\p{L} .'-]{1,50}$");
    }
    
    
    
    //METODO PARA GENERAR NOMBRES PARA LOS LOCALES
    private String generarNombreLocal(Clasificacion clasificacion) {
        // Genera un nombre de local basado en la clasificación y un número aleatorio
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
        // Genera un número aleatorio entre 100 y 200 para el sufijo
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
        textField.setEnabled(false);
        textField_1.setEnabled(false);
    } 
    
}