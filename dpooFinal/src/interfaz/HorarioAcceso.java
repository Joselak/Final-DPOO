package dpooFinal.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowListener;

public class HorarioAcceso extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HorarioAcceso dialog = new HorarioAcceso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	 public HorarioAcceso() {
	        setTitle("Horarios de Acceso");
	        setBounds(100, 100, 600, 400);
	        setModal(true);
	        getContentPane().setLayout(new BorderLayout());
	        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        getContentPane().add(contentPanel, BorderLayout.CENTER);
	        contentPanel.setLayout(new BorderLayout(0, 0));
	        
	        JTextArea textArea = new JTextArea();
	        textArea.setEditable(false);
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setText(getHorariosTexto());
	        
	        JScrollPane scrollPane = new JScrollPane(textArea);
	        contentPanel.add(scrollPane, BorderLayout.CENTER);
	        
	        JPanel buttonPane = new JPanel();
	        getContentPane().add(buttonPane, BorderLayout.SOUTH);
	        
	        JButton salirButton = new JButton("Salir");
	        salirButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
	        buttonPane.add(salirButton);
	        getRootPane().setDefaultButton(salirButton);
	        
	        setLocationRelativeTo(null);
	    }
	    
	 
	 private String getHorariosTexto() {
	        return "HORARIOS DE ACCESO POR TIPO DE PERSONAL\n\n" +
	               "1. Directivos y Administrativos:\n" +
	               "   - Acceso libre a cualquier local en cualquier horario.\n\n" +
	               "2. Profesores:\n" +
	               "   - Acceso libre permanente a:\n" +
	               "     * Local de estudiantes\n" +
	               "     * Local de profesores\n" +
	               "     * Aulas\n" +
	               "     * Laboratorios\n" +
	               "   - Acceso de 8:00 am a 5:00 pm a:\n" +
	               "     * Local del decano\n" +
	               "     * Local de vicedecano(a)\n" +
	               "     * Local de especialistas\n" +
	               "     * Local de área administrativa\n" +
	               "     * Local de jefe de departamento\n\n" +
	               "3. Especialistas y Técnicos:\n" +
	               "   - Acceso libre permanente a:\n" +
	               "     * Local de servidores\n" +
	               "     * Laboratorios\n" +
	               "     * Local de estudiantes\n" +
	               "     * Local de especialistas\n" +
	               "   - Acceso de 8:00 am a 5:00 pm a:\n" +
	               "     * Local del decano\n" +
	               "     * Local de vicedecano(a)\n" +
	               "     * Local de profesores\n" +
	               "     * Local de área administrativa\n" +
	               "     * Local de jefe de departamento\n\n" +
	               "4. Estudiantes:\n" +
	               "   - Acceso de 8:00 am a 5:00 pm a:\n" +
	               "     * Local de estudiantes\n" +
	               "     * Aulas\n" +
	               "     * Laboratorios\n" +
	               "     * Local de profesores\n" +
	               "   - No tienen acceso a los demás locales.\n\n" +
	               "5. Visitantes:\n" +
	               "   - Acceso solo de 8:00 am a 12:00 m (mediodía)\n" +
	               "   - El acceso específico depende del tipo de visitante y se ajusta\n" +
	               "     a las condiciones de acceso según su categoría similar.";
	    }
	 
	//este método para permitir el control desde Principal
	 public void setDefaultCloseOperation(int operation) {
	     super.setDefaultCloseOperation(operation);
	 }

	 //este WindowListener para limpiar la referencia al cerrar
	 public void addWindowListener(WindowListener listener) {
	     super.addWindowListener(listener);
	 }

}
