package com.codedestroyers;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class HitoPanel extends JPanel {
    private static final Color COLOR_ANIO = new Color(0, 122, 255);
    private static final Color COLOR_BORDE = new Color(220, 220, 220);

    public HitoPanel(Hito hito) {
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel contenedor = new JPanel();
        contenedor.setBackground(Color.WHITE);
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBorder(
                BorderFactory.createCompoundBorder(
                        new RoundedBorder(15, 2, COLOR_BORDE),
                        new EmptyBorder(15, 20, 15, 20)
                )
        );

        JLabel anioLabel = new JLabel(hito.getAnio());
        anioLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        anioLabel.setForeground(COLOR_ANIO);
        anioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tituloLabel = new JLabel(hito.getTitulo());
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        tituloLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descripcionArea = new JTextArea(hito.getDescripcion());
        descripcionArea.setWrapStyleWord(true);
        descripcionArea.setLineWrap(true);
        descripcionArea.setOpaque(false);
        //descripcionArea.setEditable(false);
        //descripcionArea.setFocusable(false);
        descripcionArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descripcionArea.setForeground(Color.DARK_GRAY);
        descripcionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        contenedor.add(anioLabel);
        contenedor.add(Box.createRigidArea(new Dimension(0, 2)));
        contenedor.add(tituloLabel);
        contenedor.add(Box.createRigidArea(new Dimension(0, 8)));
        contenedor.add(descripcionArea);

        add(contenedor, BorderLayout.CENTER);
    }

    private static final int CARD_WIDTH = 350;

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = CARD_WIDTH;
        return size;
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension size = super.getMaximumSize();
        size.width = CARD_WIDTH;
        return size;
    }

    private static class RoundedBorder implements Border {
        private int radius;
        private int thickness;
        private Color color;

        RoundedBorder(int radius, int thickness, Color color) {
            this.radius = radius;
            this.thickness = thickness;
            this.color = color;
        }

        public Insets getBorderInsets(Component c) {
            int value = radius / 2 + thickness;
            return new Insets(value, value, value, value);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(
                Component c,
                Graphics g,
                int x,
                int y,
                int width,
                int height
        ) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.draw(
                    new RoundRectangle2D.Double(
                            x + thickness / 2.0,
                            y + thickness / 2.0,
                            width - thickness,
                            height - thickness,
                            radius,
                            radius
                    )
            );
            g2d.dispose();
        }
    }
}
