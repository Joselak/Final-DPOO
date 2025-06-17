package dpooFinal.interfaz;


import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Registro;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MostrarRegistros extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private Facultad facultad;
    private DefaultTableModel model;
    private JTable tableResultados;
    private JScrollPane scrollPane;
    private final JPanel contentPanel = new JPanel();

    public static void main(String[] args) {
            try {
                MostrarRegistros dialog = new MostrarRegistros();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    public MostrarRegistros() {
        setTitle("Tabla de registros");
        setBounds(100, 100, 595, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBounds(10, 60, 550, 250);
        contentPanel.add(panelResultados);
        
        model = new DefaultTableModel() {
            /**
			 * 
			 */
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
        
        JLabel lblRegistros = new JLabel("Registros:");
        lblRegistros.setBounds(30, 27, 69, 14);
        contentPanel.add(lblRegistros);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarRegistro();
            }
        });
        btnEliminar.setBounds(471, 330, 89, 23);
        contentPanel.add(btnEliminar);
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 modificarRegistro();
            }
        });
        btnModificar.setBounds(372, 330, 89, 23);
        contentPanel.add(btnModificar);
        
        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(279, 330, 83, 23);
        contentPanel.add(btnMostrar);
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistros();
            }
        });
        btnMostrar.setActionCommand("OK");
        getRootPane().setDefaultButton(btnMostrar);
        
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
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    
    private void mostrarRegistros() {
        model.setRowCount(0); // Limpiar tabla antes de mostrar
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        // Recorrer todos los locales y sus registros
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

    private void eliminarRegistro() {
        int fila = tableResultados.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Registro registro = (Registro) model.getValueAt(fila, 5);
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "¿Eliminar registro de " + registro.getPersona().getNombreCompleto() + "?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Buscar y eliminar el registro de su local correspondiente
            for (Local local : facultad.getLocales()) {
                if (local.getRegistros().remove(registro)) {
                    model.removeRow(fila);
                    JOptionPane.showMessageDialog(this, "Registro eliminado");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
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
}

