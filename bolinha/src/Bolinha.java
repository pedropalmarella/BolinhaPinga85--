import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bolinha {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bola Quicante");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 500);
            frame.setResizable(false);

            PainelBolinha painel = new PainelBolinha();
            frame.add(painel);

            JButton botaoIniciar = new JButton("Iniciar");
            botaoIniciar.addActionListener(new ActionListener() {
               //o
                public void actionPerformed(ActionEvent e) {
                    painel.quique();
                }
            });

            frame.add(botaoIniciar, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}

class PainelBolinha extends JPanel {
    private int diametroBola = 30;
    private int xBola = 0;
    private int yBola = 0;
    private double alturaQuicada = 1.0;
    private boolean subindo = false;

    public void quique() {
        Timer temporizador = new Timer(18, new ActionListener() {
            //o
            public void actionPerformed(ActionEvent e) {
                quicar();
                repaint();
            }
        });
        temporizador.start();
    }

    private void quicar() {
    	//se subindo é falso
        if (!subindo) {
            // Movendo para baixo
            if (yBola + diametroBola < getHeight()) {
                yBola += 20; // Ajusta a velocidade de queda 
            } else {
                // Quicada
                yBola = getHeight() - diametroBola;
                alturaQuicada *= 0.85;

                if (alturaQuicada > 0.1) {
                    subindo = true;
                } else {
                    subindo = false;
                }
            }
        } else {
            // Movendo para cima
            if (yBola > getHeight() * (1.0 - alturaQuicada)) {
                yBola -= 20; // Ajusta a velocidade de subida
            } else {
                subindo = false;
            }
        }
    }

    //o
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(xBola, yBola, diametroBola, diametroBola);
    }
}
