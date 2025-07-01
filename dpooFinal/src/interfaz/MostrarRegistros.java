package dpooFinal.src.interfaz;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dpooFinal.src.logica.Administrativo;
import dpooFinal.src.logica.Directivo;
import dpooFinal.src.logica.Especialista;
import dpooFinal.src.logica.Estudiante;
import dpooFinal.src.logica.Facultad;
import dpooFinal.src.logica.Local;
import dpooFinal.src.logica.Persona;
import dpooFinal.src.logica.Profesor;
import dpooFinal.src.logica.Registro;
import dpooFinal.src.logica.Tecnico;
import dpooFinal.src.logica.Visitante;
import java.awt.event.WindowListener;


public class MostrarRegistros extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private Facultad facultad;
    private DefaultTableModel model;
    private JTable tableResultados;
    private JScrollPane scrollPane;
    private final JPanel contentPanel = new JPanel();

    public static void main(String[] args) {
            try {
                Facultad facultad = Facultad.getInstancia();
                MostrarRegistros dialog = new MostrarRegistros(facultad);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public MostrarRegistros(Facultad facultad) {
        setTitle("Tabla de registros");
        setBounds(100, 100, 598, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setResizable(false);
        setFacultad(facultad);

        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBounds(10, 60, 550, 250);
        contentPanel.add(panelResultados);
        
        model = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("Carnet");
        model.addColumn("Nombre");
        model.addColumn("Local");
        model.addColumn("Hora Entrada");
        model.addColumn("Hora Salida");
        model.addColumn("Registro"); // Columna oculta para el objeto Registro
        
        tableResultados = new JTable(model);
        scrollPane = new JScrollPane(tableResultados);
        panelResultados.add(scrollPane, BorderLayout.CENTER);
        
        // Ocultar columna de objetos Registro
        tableResultados.removeColumn(tableResultados.getColumnModel().getColumn(5));
        
        // Agregar listener para doble click
        tableResultados.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarDetallesRegistro();
                }
            }
        });
        
        JLabel lblRegistros = new JLabel("Registros:");
        lblRegistros.setBounds(30, 27, 69, 14);
        contentPanel.add(lblRegistros);
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 modificarRegistro();
            }
        });
        btnModificar.setBounds(471, 329, 89, 23);
        contentPanel.add(btnModificar);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnSalir.setBounds(243, 413, 89, 23);
        contentPanel.add(btnSalir);
        
        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        buttonPane.setLayout(null);
        
        setLocationRelativeTo(null);
        
        mostrarRegistros();
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    
    private void mostrarRegistros() {
        model.setRowCount(0); // Limpiar tabla antes de mostrar
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (Local local : facultad.getLocales()) {
            for (Registro registro : local.getRegistros()) {
                model.addRow(new Object[]{
                    registro.getPersona().getNumID(),
                    registro.getPersona().getNombreCompleto(),
                    local.getId(),
                    registro.getHoraEntrada().format(formatter),
                    registro.getHoraSalida() != null ? registro.getHoraSalida().format(formatter) : "Pendiente",
                    registro // Guardar objeto para operaciones posteriores
                });
            }
        }
    }

    private void modificarRegistro() {
        int fila = tableResultados.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Registro registro = (Registro) model.getValueAt(fila, 5);
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JTextField txtSalida = new JTextField();
        
        if (registro.getHoraSalida() != null) {
            txtSalida.setText(registro.getHoraSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        }
        
        panel.add(new JLabel("Nueva hora de salida (D/M/A H:m):"));
        panel.add(txtSalida);
        
        int opcion = JOptionPane.showConfirmDialog(
            this, 
            panel, 
            "Modificar Registro", 
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                LocalDateTime nuevaSalida = LocalDateTime.parse(txtSalida.getText(), 
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                
                registro.setHoraSalida(nuevaSalida);
                mostrarRegistros(); // Refrescar la tabla
                JOptionPane.showMessageDialog(this, "Registro actualizado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    //MOSTRAR DETALLES DE UN REGISTRO
    private void mostrarDetallesRegistro() {
        int fila = tableResultados.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Registro registro = (Registro) model.getValueAt(fila, 5);
       Persona persona = registro.getPersona();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        Local localRegistro = null;
        boolean encontrado = false;
        for (int i = 0; i < facultad.getLocales().size() && !encontrado; i++) {
            Local local = facultad.getLocales().get(i);
            if (local.getRegistros().contains(registro)) {
                localRegistro = local;
                encontrado = true;
            }
        }
        
        // Crear panel para mostrar los detalles
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // Datos del registro
        panel.add(new JLabel("Datos del Registro:"));
        panel.add(new JLabel(""));
        if (localRegistro != null) {
            panel.add(new JLabel("Local:"));
            panel.add(new JLabel(localRegistro.getId()));
        }
        panel.add(new JLabel("Hora de entrada:"));
        panel.add(new JLabel(registro.getHoraEntrada().format(formatter)));
        panel.add(new JLabel("Hora de salida:"));
        panel.add(new JLabel(registro.getHoraSalida() != null ? registro.getHoraSalida().format(formatter) : "Pendiente"));
        
        
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Datos de la Persona:"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Carnet:"));
        panel.add(new JLabel(persona.getNumID()));
        panel.add(new JLabel("Nombre completo:"));
        panel.add(new JLabel(persona.getNombreCompleto()));
        panel.add(new JLabel("Tipo de persona:"));
        panel.add(new JLabel(persona.getClass().getSimpleName()));
        
        // Atributos específicos según el tipo de persona
        if (persona instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) persona;
            System.out.println("Datos estudiante - Grupo: " + estudiante.getGrupo() + 
                    ", Año: " + estudiante.getAnio());
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
        
        
        
        // Mostrar el diálogo con los detalles
        JOptionPane.showMessageDialog(
            this, 
            panel, 
            "Detalles del Registro", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    
 // método para permitir el control desde Principal
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
    }

    // WindowListener para limpiar la referencia al cerrar
    public void addWindowListener(WindowListener listener) {
        super.addWindowListener(listener);
    }
}