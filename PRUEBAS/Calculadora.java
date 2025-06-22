package PRUEBAS;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Calculadora extends JFrame {
    private static final long serialVersionUID = 1L;
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine se = sem.getEngineByName("JavaScript");
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Calculadora frame = new Calculadora();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Calculadora() {
        setTitle("Calculadora B\u00E1sica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 337, 486);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
            
        lblNewLabel = new JLabel("");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBounds(10, 11, 301, 30);
        contentPane.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lblNewLabel_1.setBackground(Color.BLACK);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setBounds(10, 55, 301, 44);
        contentPane.add(lblNewLabel_1);
        
        JButton button_10 = new JButton("=");
        button_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String resultado = null;
                try {
                    if(!lblNewLabel.getText().isEmpty()) {
                        resultado = se.eval(lblNewLabel.getText()).toString();
                        lblNewLabel_1.setText(resultado);
                    }
                } catch (ScriptException e1) {
                    lblNewLabel_1.setText("Error");
                }
            }
        });
        button_10.setBackground(Color.BLACK);
        button_10.setForeground(Color.WHITE);
        button_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button_10.setBounds(242, 384, 55, 52);
        contentPane.add(button_10);
        
        // Método auxiliar para añadir dígitos
        ActionListener numberListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton)e.getSource();
                lblNewLabel.setText(lblNewLabel.getText() + source.getText());
            }
        };
        
        // Método auxiliar para añadir operadores
        ActionListener operatorListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton)e.getSource();
                String operator = source.getText();
                if(operator.equals("x")) operator = "*";
                lblNewLabel.setText(lblNewLabel.getText() + operator);
            }
        };
        
        // Botones numéricos
        for(int i = 0; i <= 9; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.addActionListener(numberListener);
            btn.setBackground(Color.BLACK);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
            
            switch(i) {
                case 1: btn.setBounds(153, 321, 55, 52); break;
                case 2: btn.setBounds(88, 321, 55, 52); break;
                case 3: btn.setBounds(23, 321, 55, 52); break;
                case 4: btn.setBounds(153, 258, 55, 52); break;
                case 5: btn.setBounds(88, 258, 55, 52); break;
                case 6: btn.setBounds(23, 258, 55, 52); break;
                case 7: btn.setBounds(153, 195, 55, 52); break;
                case 8: btn.setBounds(88, 195, 55, 52); break;
                case 9: btn.setBounds(23, 195, 55, 52); break;
                case 0: btn.setBounds(88, 384, 55, 52); break;
            }
            
            if(i != 0 || i == 0) contentPane.add(btn);
        }
        
        // Botón C (limpiar)
        JButton btnC = new JButton("C");
        btnC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel.setText("");
                lblNewLabel_1.setText("");
            }
        });
        btnC.setBackground(Color.BLACK);
        btnC.setForeground(Color.WHITE);
        btnC.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnC.setBounds(23, 135, 55, 52);
        contentPane.add(btnC);
        
        // Botón retroceso
        JButton button_13 = new JButton("<-");
        button_13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texto = lblNewLabel.getText();
                if(!texto.isEmpty()) {
                    lblNewLabel.setText(texto.substring(0, texto.length()-1));
                }
            }
        });
        button_13.setForeground(Color.WHITE);
        button_13.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button_13.setBackground(Color.BLACK);
        button_13.setBounds(88, 135, 55, 52);
        contentPane.add(button_13);
        
        // Botón punto
        JButton button_8 = new JButton(".");
        button_8.addActionListener(numberListener);
        button_8.setForeground(Color.WHITE);
        button_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button_8.setBackground(Color.BLACK);
        button_8.setBounds(153, 135, 55, 52);
        contentPane.add(button_8);
        
        // Botones de operadores
        String[] operators = {"+", "-", "x", "/", "(", ")"};
        int[] xPos = {242, 242, 242, 242, 23, 153};
        int[] yPos = {135, 198, 261, 324, 384, 384};
        
        for(int i = 0; i < operators.length; i++) {
            JButton btn = new JButton(operators[i]);
            btn.addActionListener(operatorListener);
            btn.setBackground(Color.BLACK);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
            btn.setBounds(xPos[i], yPos[i], 55, 52);
            contentPane.add(btn);
        }
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(0, 122, 321, 325);
        contentPane.add(panel);
    }
}