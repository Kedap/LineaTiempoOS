package com.codedestroyers;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TimelineGUI extends JFrame {
    private static final Color COLOR_FONDO = new Color(240, 242, 245);

    public TimelineGUI(List<Hito> hitos) {
        setTitle("Línea de Tiempo Histórica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO);

        // Panel de Título
        JPanel tituloPanel = new JPanel();
        tituloPanel.setBackground(COLOR_FONDO);
        tituloPanel.setLayout(new BoxLayout(tituloPanel, BoxLayout.Y_AXIS));
        tituloPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JLabel tituloPrincipal = new JLabel("Línea del Tiempo Histórica");
        tituloPrincipal.setFont(new Font("SansSerif", Font.BOLD, 32));
        tituloPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subTitulo = new JLabel("Mucho más que Windows Bv");
        subTitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subTitulo.setForeground(Color.GRAY);
        subTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        tituloPanel.add(tituloPrincipal);
        tituloPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        tituloPanel.add(subTitulo);

        TimelineContainerPanel timelinePanel = new TimelineContainerPanel(
                hitos
        );

        JScrollPane scrollPane = new JScrollPane(timelinePanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        setLayout(new BorderLayout());
        add(tituloPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private static class TimelineContainerPanel extends JPanel {

        TimelineContainerPanel(List<Hito> hitos) {
            setBackground(COLOR_FONDO);

            GridBagLayout gbl = new GridBagLayout();
            // Columna Izquierda | Centro | Derecha
            gbl.columnWeights = new double[]{0.5, 0.0, 0.5};
            gbl.columnWidths = new int[]{0, 100, 0};
            setLayout(gbl);

            GridBagConstraints gbc = new GridBagConstraints();

            for (int i = 0; i < hitos.size(); i++) {
                gbc.gridy = i;
                gbc.insets = new Insets(0, 20, 80, 20);

                HitoPanel hitoPanel = new HitoPanel(hitos.get(i));

                if (i % 2 != 0) {
                    gbc.gridx = 0;
                    gbc.anchor = GridBagConstraints.LINE_END;
                } else {
                    gbc.gridx = 2;
                    gbc.anchor = GridBagConstraints.LINE_START;
                }
                add(hitoPanel, gbc);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            GridBagLayout layout = (GridBagLayout) getLayout();
            int[][] dimensions = layout.getLayoutDimensions();

            if (dimensions[0].length < 3) return; // Asegurarse de que hay 3 columnas

            Point origin = layout.getLayoutOrigin();
            int[] columnWidths = dimensions[0];
            int centerX = origin.x + columnWidths[0] + (columnWidths[1] / 2);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2d.setColor(new Color(200, 200, 200));
            g2d.setStroke(new BasicStroke(2));

            if (getComponentCount() > 0) {
                int startY =
                        getComponent(0).getY() + getComponent(0).getHeight() / 2;
                int endY =
                        getComponent(getComponentCount() - 1).getY() +
                                getComponent(getComponentCount() - 1).getHeight() / 2;
                g2d.drawLine(centerX, startY, centerX, endY);
            }

            for (Component comp : getComponents()) {
                if (comp instanceof HitoPanel) {
                    int y = comp.getY() + comp.getHeight() / 2;
                    g2d.setColor(new Color(0, 122, 255));
                    g2d.fillOval(centerX - 7, y - 7, 14, 14);
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval(centerX - 4, y - 4, 8, 8);
                }
            }
            g2d.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            if (size.height > 0) {
                size.height += 80;
            }
            return size;
        }
    }
}