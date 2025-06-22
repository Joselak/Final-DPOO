package PRUEBAS;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PreguntaImportante {
    private JFrame frame;
    private JButton btnSi;
    private JButton btnNo;
    private Random random = new Random();
    private final int margenInferior = 100;
    private final int margenLateral = 120;
    private final int movimientoMinimo = 50;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PreguntaImportante().crearInterfaz());
    }

    private void crearInterfaz() {
        // Configuración de la ventana principal
        frame = new JFrame("Pregunta importante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Texto de la pregunta
        JLabel pregunta = new JLabel("¿Quieres ser mi novia?");
        pregunta.setFont(new Font("Arial", Font.BOLD, 30));
        pregunta.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(null);
        panelBotones.setPreferredSize(new Dimension(400, 200));

        // Botón Sí
        btnSi = new JButton("Sí");
        btnSi.setBounds(50, 100, 100, 30);
        btnSi.addActionListener(e -> mostrarRespuesta());

        // Botón No
        btnNo = new JButton("No");
        btnNo.setBounds(200, 100, 100, 30);
        btnNo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                moverBotonNo();
            }
        });

        panelBotones.add(btnSi);
        panelBotones.add(btnNo);

        // Añadir componentes al panel principal
        panel.add(pregunta);
        panel.add(panelBotones);

        // Añadir panel a la ventana
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setVisible(true);
    }

    private void moverBotonNo() {
        int maxTop = frame.getHeight() - margenInferior;
        int maxLeft = frame.getWidth() - margenLateral;

        Point posActual = btnNo.getLocation();
        int nuevoX, nuevoY;

        do {
            nuevoX = random.nextInt(maxLeft);
            nuevoY = random.nextInt(maxTop);
        } while (Math.abs(nuevoX - posActual.x) < movimientoMinimo || 
                Math.abs(nuevoY - posActual.y) < movimientoMinimo);

        btnNo.setLocation(nuevoX, nuevoY);
    }

    private void mostrarRespuesta() {
        JOptionPane.showMessageDialog(frame, 
            "¡Sabía que dirías que sí! 🥰", 
            "Respuesta", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Si quieres un diálogo personalizado como en el ejemplo de Python:
        
       /* JDialog dialog = new JDialog(frame, "Respuesta", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(new JLabel("¡Sabía que dirías que sí! 🥰", SwingConstants.CENTER), BorderLayout.CENTER);
        
        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> dialog.dispose());
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(cerrar);
        dialog.add(panelBoton, BorderLayout.SOUTH);
        
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
        */ 
    }
}