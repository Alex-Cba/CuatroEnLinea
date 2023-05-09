package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentEmergente extends JDialog implements ActionListener {

    private JLabel jugador1Label;
    private JLabel jugador2Label;
    private JTextField jugador1TextField;
    private JTextField jugador2TextField;
    private JButton okButton;
    private String jugador1Nombre;
    private String jugador2Nombre;

    public VentEmergente(JFrame parent) {
        super(parent, true);
        setTitle("Ingresar nombres");
        setSize(300, 200);
        setLocationRelativeTo(parent);

        jugador1Label = new JLabel("Jugador 1:");
        jugador2Label = new JLabel("Jugador 2:");
        jugador1TextField = new JTextField(20);
        jugador2TextField = new JTextField(20);
        okButton = new JButton("OK");

        JPanel panelPrincipal = new JPanel(new GridLayout(3, 2));
        panelPrincipal.add(jugador1Label);
        panelPrincipal.add(jugador1TextField);
        panelPrincipal.add(jugador2Label);
        panelPrincipal.add(jugador2TextField);
        panelPrincipal.add(new JLabel(""));
        panelPrincipal.add(okButton);

        okButton.addActionListener(this);

        setContentPane(panelPrincipal);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            jugador1Nombre = jugador1TextField.getText();
            jugador2Nombre = jugador2TextField.getText();
            dispose();
        }
    }

    public String getJugador1Nombre() {
        return jugador1Nombre;
    }

    public String getJugador2Nombre() {
        return jugador2Nombre;
    }
}
