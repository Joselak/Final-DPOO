package dpooFinal.src.interfaz;

import dpooFinal.src.logica.Facultad;
import dpooFinal.src.logica.Local;
import dpooFinal.src.logica.Registro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;

public class PersonaVisita extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTextField textFieldCarnet;
    private JSpinner spinnerInicio;
    private JSpinner spinnerFin;
    private JTextArea textAreaResultados;
    private Facultad facultad;

    public PersonaVisita(Facultad facultad) {
        this.facultad = facultad;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Visitas de una persona");
        setBounds(100, 100, 657, 503);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        // Panel para datos de entrada
        JPanel panelEntrada = new JPanel();
        panelEntrada.setBounds(35, 11, 363, 123);
        panelEntrada.setLayout(null);
        contentPanel.add(panelEntrada);

        // Campo para carnet
        JLabel lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(10, 10, 100, 20);
        panelEntrada.add(lblCarnet);

        textFieldCarnet = new JTextField();
        textFieldCarnet.setBounds(120, 10, 200, 20);
        panelEntrada.add(textFieldCarnet);

        // Fecha inicio
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setBounds(10, 40, 100, 20);
        panelEntrada.add(lblFechaInicio);

        spinnerInicio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditorInicio = new JSpinner.DateEditor(spinnerInicio, "dd/MM/yyyy");
        spinnerInicio.setEditor(dateEditorInicio);
        spinnerInicio.setBounds(120, 40, 120, 20);
        panelEntrada.add(spinnerInicio);

        // Fecha fin
        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaFin.setBounds(10, 70, 100, 20);
        panelEntrada.add(lblFechaFin);

        spinnerFin = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditorFin = new JSpinner.DateEditor(spinnerFin, "dd/MM/yyyy");
        spinnerFin.setEditor(dateEditorFin);
        spinnerFin.setBounds(120, 70, 120, 20);
        panelEntrada.add(spinnerFin);

        // Área de resultados
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(34, 141, 580, 280);
        contentPanel.add(scrollPane);
        
                textAreaResultados = new JTextArea();
                scrollPane.setViewportView(textAreaResultados);
                textAreaResultados.setEditable(false);

        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscarLocalesVisitados();
        	}
        });
        buttonPane.add(btnBuscar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        buttonPane.add(btnSalir);

        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void buscarLocalesVisitados() {
        boolean encontrado = true;
        
        String carnet = textFieldCarnet.getText().trim();
        
        if (carnet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un numero de carnet", "Error", JOptionPane.ERROR_MESSAGE);
            encontrado = false;
        } else if (!carnet.matches("^\\d{11}$")) {
            JOptionPane.showMessageDialog(this, "Carnet inválido: debe contener 11 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
            encontrado = false;
        }
        
        Date fechaInicio = (Date) spinnerInicio.getValue();
        Date fechaFin = (Date) spinnerFin.getValue();
        
        if (fechaInicio.after(fechaFin)) {
            textAreaResultados.setText("La fecha de inicio debe ser anterior a la fecha fin");
            encontrado = false;
        }
        
        if (!encontrado) {
            return;
        }

        // Convertir a LocalDateTime (ajustando horas para cubrir todo el día)
        LocalDateTime inicioDateTime = fechaInicio.toInstant().atZone(ZoneId.systemDefault())
                                    .toLocalDate().atStartOfDay();
        LocalDateTime finDateTime = fechaFin.toInstant().atZone(ZoneId.systemDefault())
                                  .toLocalDate().atTime(23, 59, 59);
        
        // Obtener todos los registros de la persona en el rango de fechas
        ArrayList<Object[]> registrosConLocales = new ArrayList<>();
        int totalLocales = 0;
        int totalRegistros = 0;
        
        for (Local local : facultad.getLocales()) {
            totalLocales++;
            ArrayList<Registro> accesos = local.getAccesosEnIntervalo(inicioDateTime, finDateTime);
            totalRegistros += accesos.size();
            
            for (Registro acceso : accesos) {
                if (acceso.getPersona().getNumID().equals(carnet)) {
                    registrosConLocales.add(new Object[]{acceso, local});
                }
            }
        }
        
        //Depuración: Mostrar estadísticas
        System.out.println("Total locales revisados: " + totalLocales);
        System.out.println("Total registros encontrados: " + totalRegistros);
        System.out.println("Registros coincidentes: " + registrosConLocales.size());
        
        if (registrosConLocales.isEmpty()) {
            textAreaResultados.setText("No se encontraron registros de visitas para:\n" +
                                     "Carnet: " + carnet + "\n" +
                                     "Entre: " + inicioDateTime.toLocalDate() + " y " + 
                                     finDateTime.toLocalDate());
        } else {
            StringBuilder sb = new StringBuilder();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            
            // Obtener el nombre de la persona si está registrada, o usar "Visitante" si no lo está
            String nombrePersona = facultad.buscarPersona(carnet) != null ? 
                                facultad.buscarPersona(carnet).getNombre() : "Visitante";
            
            sb.append("VISITAS HECHAS POR: ").append(nombrePersona)
              .append(" (").append(carnet).append(")\n");
            sb.append("PERÍODO DE TIEMPO: ").append(inicioDateTime.toLocalDate())
              .append(" a ").append(finDateTime.toLocalDate()).append("\n\n");
            
            sb.append(String.format("%-20s %-25s %-25s%n", 
                                  "Local", "Hora Entrada", "Hora Salida"));
            sb.append("------------------------------------------------------------\n");
            
            for (Object[] registroLocal : registrosConLocales) {
                Registro registro = (Registro) registroLocal[0];
                Local local = (Local) registroLocal[1];
                
                String nombreLocal = local.getId() + " (" + local.getTipoLocal() + ")";
                String horaEntrada = registro.getHoraEntrada().format(timeFormatter);
                String horaSalida = registro.getHoraSalida() != null ? 
                                  registro.getHoraSalida().format(timeFormatter) : "En curso";
                
                sb.append(String.format("%-20s %-25s %-25s%n", 
                                      nombreLocal, horaEntrada, horaSalida));
            }
            
            textAreaResultados.setText(sb.toString());
        }
    }

    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
    }

    public void addWindowListener(WindowListener listener) {
        super.addWindowListener(listener);
    }
    
    
}