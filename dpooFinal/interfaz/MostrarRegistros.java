package dpooFinal.interfaz;


import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Registro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarRegistros();
            }
        });
        btnMostrar.setActionCommand("OK");
        buttonPane.add(btnMostrar);
        getRootPane().setDefaultButton(btnMostrar);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnSalir.setActionCommand("Cancel");
        buttonPane.add(btnSalir);
        
        setLocationRelativeTo(null);
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    
    private void mostrarRegistros() {
        model.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        
        for (Local local : facultad.getLocales()) {
            for (Registro registro : local.getRegistros()) {
                String horaEntrada = registro.getHoraEntrada() != null ? 
                    registro.getHoraEntrada().format(formatter) : "N/A";
                String horaSalida = registro.getHoraSalida() != null ? 
                    registro.getHoraSalida().format(formatter) : "N/A";
                
                model.addRow(new Object[]{
                    registro.getPersona().getNumID(),
                    registro.getPersona().getNombre() + " " + registro.getPersona().getApellido(),
                    local.getId(),
                    horaEntrada,
                    horaSalida,
                    registro  // Almacenar objeto Registro en columna oculta
                });
            }
        }
    }
    
    private void eliminarRegistro() {
        int filaSeleccionada = tableResultados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Registro registro = (Registro) model.getValueAt(filaSeleccionada, 5);
        int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar este registro?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            for (Local local : facultad.getLocales()) {
                if (local.getRegistros().remove(registro)) {
                    model.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(this, "Registro eliminado");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "No se encontró el registro", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modificarRegistro() {
        int filaSeleccionada = tableResultados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Registro registro = (Registro) model.getValueAt(filaSeleccionada, 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        
        // Crear formulario de modificación
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JTextField txtSalida = new JTextField();
        if (registro.getHoraSalida() != null) {
            txtSalida.setText(registro.getHoraSalida().format(formatter));
        }
        
        panel.add(new JLabel("Hora de Salida (D/M/Agit add dpooFinal/-HH:mm):"));
        panel.add(txtSalida);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Modificar Registro", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                LocalDateTime nuevaSalida = LocalDateTime.parse(txtSalida.getText(), formatter);
                registro.setHoraSalida(nuevaSalida);
                mostrarRegistros(); // Actualizar tabla
                JOptionPane.showMessageDialog(this, "Registro actualizado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}



