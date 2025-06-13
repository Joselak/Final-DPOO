package dpooFinal.interfaz;


import dpooFinal.logica.Facultad;
import dpooFinal.logica.Local;
import dpooFinal.logica.Registro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MostrarRegistros extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Facultad facultad;
    private DefaultTableModel model;
    

	private final JPanel contentPanel = new JPanel();
	private JTable tableResultados;
	private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarRegistros dialog = new MostrarRegistros();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarRegistros() {
		setTitle("Tabla de registros");	
		setBounds(100, 100, 595, 423);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBounds(10, 60, 550, 200);
        contentPanel.add(panelResultados);
		
		 model = new DefaultTableModel();
	    
	    model.addColumn("Carnet");
	    model.addColumn("Nombre");
	    model.addColumn("Local");
	    model.addColumn("Hora Entrada");
	    model.addColumn("Hora Salida");
        
        tableResultados = new JTable(model);
        scrollPane = new JScrollPane(tableResultados);
        panelResultados.add(scrollPane, BorderLayout.CENTER);
		
        
        model.fireTableDataChanged();
        tableResultados.revalidate();
        tableResultados.repaint();
    
		
		JLabel lblRegistros = new JLabel("Registros:");
		lblRegistros.setBounds(30, 27, 69, 14);
		contentPanel.add(lblRegistros);
		{
					
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Mostrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarRegistros();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
	                horaSalida
	            });
	        }
	    }

	    tableResultados.setModel(model);
	}
}





