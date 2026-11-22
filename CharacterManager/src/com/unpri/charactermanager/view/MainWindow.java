package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    // Komponen dinamis yang di-update saat refreshList()
    private JLabel lblTitle;
    private JLabel lblTotalChars;
    private JLabel lblAvgLevel;
    private JLabel lblStrongest;
    private JPanel cardPanel;

    public MainWindow(CharacterManager manager) {
        super("Character Manager v0.6");
        this.manager = manager;

        initComponents();
        refreshList(); // Muat data awal ke UI

        pack();
        setMinimumSize(new Dimension(760, 540));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 1. NORTH: Header + Stats + Form Tambah Karakter
        JPanel northContainer = new JPanel();
        northContainer.setLayout(new BoxLayout(northContainer, BoxLayout.Y_AXIS));
        northContainer.setBackground(AppThema.BG_MAIN);

        northContainer.add(createHeaderLabelPanel());
        northContainer.add(createStatsPanel());
        northContainer.add(new CharacterFormPanel(manager, this::refreshList));

        add(northContainer, BorderLayout.NORTH);

        // 2. CENTER: Daftar Berscroll
        add(createScrollableListPanel(), BorderLayout.CENTER);

        // 3. EAST: Legenda Panel
        add(createLegendPanel(), BorderLayout.EAST);

        // 4. SOUTH: Status Bar
        add(createStatusBarPanel(), BorderLayout.SOUTH);
    }

    // ===== METHOD REFRESH LIST (revalidate & repaint) =====
    public final void refreshList() {
        ArrayList<Character> currentList = manager.getAllCharacters();

        // Update Header & Statistik
        lblTitle.setText("Daftar Karakter (" + currentList.size() + ")");
        lblTotalChars.setText(" Total Karakter: " + currentList.size());
        lblAvgLevel.setText(" Rata-rata Level: " + String.format("%.2f", manager.getAverageLevel()));

        Character strongest = manager.findStrongest();
        String strongestName = (strongest != null)
                ? strongest.getName() + " (Atk: " + strongest.getAttackPower() + ")"
                : "-";
        lblStrongest.setText(" Karakter Terkuat: " + strongestName);

        // Render ulang kartu karakter
        cardPanel.removeAll();
        for (Character c : currentList) {
            cardPanel.add(createCharacterLabel(c));
        }

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private JPanel createHeaderLabelPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppThema.BG_MAIN);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(14, 16, 4, 16));

        lblTitle = new JLabel("Daftar Karakter (0)", SwingConstants.CENTER);
        lblTitle.setFont(AppThema.FONT_TITLE);
        lblTitle.setForeground(AppThema.TEXT_MAIN);

        headerPanel.add(lblTitle, BorderLayout.CENTER);
        return headerPanel;
    }

    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 8, 6));
        statsPanel.setBackground(AppThema.BG_STATUS);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(6, 16, 6, 16),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(AppThema.TEXT_MUTED),
                        "Rangkuman Statistik", 0, 0, AppThema.FONT_LEGENDA, AppThema.TEXT_MAIN)
        ));

        lblTotalChars = createStatLabel(" Total Karakter: 0");
        lblAvgLevel = createStatLabel(" Rata-rata Level: 0.00");
        lblStrongest = createStatLabel(" Karakter Terkuat: -");
        JLabel lblLogicStatus = createStatLabel(" Status Logika: Aman (100% OOP)");

        statsPanel.add(lblTotalChars);
        statsPanel.add(lblAvgLevel);
        statsPanel.add(lblStrongest);
        statsPanel.add(lblLogicStatus);

        return statsPanel;
    }

    private JLabel createStatLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(AppThema.FONT_BODY);
        lbl.setForeground(AppThema.TEXT_LIGHT);
        return lbl;
    }

    private JScrollPane createScrollableListPanel() {
        cardPanel = new JPanel(new GridLayout(0, 1, 0, 8));
        cardPanel.setBackground(AppThema.BG_MAIN);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(8, 16, 16, 16));

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        scrollPane.setPreferredSize(new Dimension(380, 220));

        return scrollPane;
    }

    private JPanel createLegendPanel() {
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBackground(AppThema.BG_STATUS);
        legendPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLegend = new JLabel("LEGENDA ROLE");
        titleLegend.setFont(AppThema.FONT_BODY_BOLD);
        titleLegend.setForeground(AppThema.TEXT_MAIN);
        titleLegend.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        legendPanel.add(titleLegend);

        legendPanel.add(Box.createVerticalStrut(12));

        for (CharacterType type : CharacterType.values()) {
            JLabel lblRole = new JLabel(type.name());
            lblRole.setFont(AppThema.FONT_LEGENDA);
            lblRole.setForeground(AppThema.TEXT_LIGHT);
            lblRole.setBackground(AppThema.colorFor(type));
            lblRole.setOpaque(true);
            lblRole.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            lblRole.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            lblRole.setToolTipText(AppThema.describe(type));

            legendPanel.add(lblRole);
            legendPanel.add(Box.createVerticalStrut(6));
        }

        legendPanel.add(Box.createVerticalGlue());
        return legendPanel;
    }

    private JPanel createStatusBarPanel() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(AppThema.BG_STATUS);
        statusBar.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));

        JLabel lblStatusLeft = new JLabel("System Ready | Mode: Dark Theme | Form Validation: Active");
        lblStatusLeft.setFont(AppThema.FONT_MONO);
        lblStatusLeft.setForeground(AppThema.TEXT_MUTED);

        JLabel lblStatusRight = new JLabel("v0.6");
        lblStatusRight.setFont(AppThema.FONT_MONO);
        lblStatusRight.setForeground(AppThema.TEXT_MUTED);

        statusBar.add(lblStatusLeft, BorderLayout.WEST);
        statusBar.add(lblStatusRight, BorderLayout.EAST);
        return statusBar;
    }

    private JLabel createCharacterLabel(Character c) {
        String info = String.format(" %s  |  %s  |  Level %d", c.getName(), c.getType(), c.getLevel());

        JLabel lbl = new JLabel(info);
        lbl.setBackground(AppThema.colorFor(c.getType()));
        lbl.setOpaque(true);
        lbl.setBorder(AppThema.CARD_BORDER);

        if (c.getLevel() >= AppThema.HIGH_LEVEL_THRESHOLD) {
            lbl.setForeground(AppThema.GOLD);
            lbl.setFont(AppThema.FONT_BODY_BOLD);
        } else {
            lbl.setForeground(AppThema.TEXT_LIGHT);
            lbl.setFont(AppThema.FONT_BODY);
        }

        if (c.getLevel() <= 1) {
            lbl.setEnabled(false);
        } else {
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        String tooltipText = String.format("<html><b>%s</b> (%s)<br/>%s<br/>[ HP: %d | ATK: %d | Mana: %d ]</html>",
                c.getName(), c.getType(), AppThema.describe(c.getType()), c.getHitPoints(), c.getAttackPower(), c.getMana());
        lbl.setToolTipText(tooltipText);

        return lbl;
    }
}
