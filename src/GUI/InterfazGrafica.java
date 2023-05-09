package GUI;

import Controladores.Controlador_Turno;
import Jugadores.Jugador;
import RoundButton.RoundButton;
import Tablero_Virtual.virtual_IG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {
    private Color background_color = new Color(255, 255, 255, 255);
    private JPanel panel_6x7 = new JPanel(new GridLayout(6, 7));
    private Controlador_Turno ctrl_turno = Controlador_Turno.getInstance("Rojo");
    private virtual_IG Matriz = virtual_IG.getInstance();
    private int fila = -1;
    private int columna = -1;
    private String Ganador = "";
    private int PuntacionRojo = 0;
    private int PuntacionAmarillo = 0;
    private JLabel puntuacionLabel = new JLabel("Puntación Rojo: " + PuntacionRojo + " | " + "Puntación Amarillo: " + PuntacionAmarillo);
    private JLabel labelJugadores = new JLabel("");
    private Jugador J1;
    private Jugador J2;

    public InterfazGrafica(Jugador _J1, Jugador _J2) {
        J1 = _J1;
        J2 = _J2;

        //7ancho x 6largo
        JFrame ventana = this;
        ventana.setTitle("Cuatro en Línea");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(612, 512);
        ventana.setLocationRelativeTo(null);

        //Label puntación
        puntuacionLabel.setBounds(5, 5, 450, 20);
        puntuacionLabel.setForeground(Color.white);

        //Label nombre de jugadores
        labelJugadores.setBounds(5, 450, 600, 20);
        labelJugadores.setForeground(Color.white);

        //Borde a todos los lados del panel
        panel_6x7.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel_6x7.setBackground(Color.BLUE);

        for (int i = 0; i < 42; i++) {

            RoundButton button = new RoundButton("");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    RoundButton btnActual = (RoundButton) e.getSource();

                    if (FilayColumna(btnActual)) {
                        //turno
                        if (ctrl_turno.getTurnoActual().equalsIgnoreCase("rojo")) {
                            if (btnActual.getBackground() != Color.yellow && btnActual.getBackground() != Color.red) {
                                FichaColorRojo(btnActual);

                                Matriz.AgregarFicha("Rojo", fila, columna);

                                Ganador = EjecutarWinConditions();

                                if (Ganador.equalsIgnoreCase("Rojo")) {
                                    JOptionPane.showMessageDialog(null, "El ganador es: " + Ganador);

                                    PuntacionRojo++;
                                    ActualizarPuntacion();

                                    ReiniciarJuego();
                                }

                                //Cambio de turno
                                ctrl_turno.CambiarTurno();
                            } else {
                                JOptionPane.showMessageDialog(null, "Esta casilla, ya contiene una ficha");
                            }

                        } else if (ctrl_turno.getTurnoActual().equalsIgnoreCase("amarillo")) {
                            if (btnActual.getBackground() != Color.yellow && btnActual.getBackground() != Color.red) {
                                FichaColorAmarilla(btnActual);

                                Matriz.AgregarFicha("Amarillo", fila, columna);

                                Ganador = EjecutarWinConditions();
                                if (Ganador.equalsIgnoreCase("Rojo") || Ganador.equalsIgnoreCase("Amarillo")) {
                                    JOptionPane.showMessageDialog(null, "El ganador es: " + Ganador);

                                    PuntacionAmarillo++;
                                    ActualizarPuntacion();

                                    ReiniciarJuego();
                                }

                                //Cambio de turno
                                ctrl_turno.CambiarTurno();
                            } else {
                                JOptionPane.showMessageDialog(null, "Esta casilla, ya contiene una ficha");
                            }
                        }
                    }

                    if(Matriz.Tablerolleno())
                    {
                        JOptionPane.showMessageDialog(null, "El tablero se lleno, esta ronda se considera empate");
                        ReiniciarJuego();
                    }

                }
            });


            panel_6x7.add(button);
        }

        ActualizaNombreLabel();

        add(puntuacionLabel);
        add(labelJugadores);
        add(panel_6x7);
    }

    private void FichaColorRojo(RoundButton btnActual) {
        Color color = new Color(159, 22, 22, 255);
        btnActual.setColorBorde(color);

        btnActual.setBackground(Color.red);
    }

    private void FichaColorAmarilla(RoundButton btnActual)
    {
        Color color = new Color(185, 185, 22, 255);
        btnActual.setColorBorde(color);

        btnActual.setBackground(Color.yellow);
    }

    private boolean FilayColumna(RoundButton btnActual)
    {
        boolean retorno = false;

        Component[] componentes = panel_6x7.getComponents();
        int lugar = -1;
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i].equals(btnActual)) {
                lugar = i;
                break;
            }
        }

        fila = lugar / 7;
        columna = lugar % 7;

        retorno = Matriz.Movimiento_Valido(fila, columna);

        return retorno;
    }

    private String EjecutarWinConditions() {

        if (Ganador.equals("")) {
            Ganador = Matriz.WinConditionHorizontal();
        }
        if (Ganador.equals("")) {
            Ganador = Matriz.WinConditionVertical();
        }
        if (Ganador.equals("")) {
            Ganador = Matriz.WinConditionDiagonal();
        }

        return Ganador;
    }

    public void ReiniciarJuego()
    {
        for (Component c : panel_6x7.getComponents())
        {
            if (c instanceof JButton) {
                c.setBackground(Color.WHITE);
            }
        }

        Ganador = "";
        fila = -1;
        columna = -1;
        Matriz.ReiniciarMatriz();
    }

    private void ActualizarPuntacion() {
        puntuacionLabel.setText("Puntación Rojo: " + PuntacionRojo + " | " + "Puntación Amarillo: " + PuntacionAmarillo);
    }

    private void ActualizaNombreLabel(){

        if( J1 != null && J2 != null) {
            labelJugadores.setText("Jugador 1: " + J1.getNombre() + " | " + "Jugador 2: " + J2.getNombre());
        }
    }
}
