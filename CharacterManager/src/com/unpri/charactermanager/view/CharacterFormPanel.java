package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class CharacterFormPanel extends JPanel {

    private final CharacterManager manager;
    private final Runnable onCharacterAdded;

    private final JTextField txtName;
    private final JComboBox<CharacterType> cmbRole;
    private final JSpinner spnLevel;
    private final JLabel lblError;

    private final Border defaultNameBorder;
    private final Border defaultLevelBorder;
    private final Border errorBorder = BorderFactory.createLineBorder(Color.decode("#ff7675"), 2);

    public CharacterFormPanel(CharacterManager manager, Runnable onCharacterAdded) {
        this.manager = manager;
        this.onCharacterAdded = onCharacterAdded;

        setLayout(new BorderLayout(0, 4));
        setBackground(AppThema.BG_STATUS);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(6, 16, 8, 16),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(AppThema.TEXT_MUTED),
                        "Tambah Karakter Baru", 0, 0, AppThema.FONT_LEGENDA, AppThema.TEXT_MAIN)
        ));

        // Baris Input
        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        inputRow.setBackground(AppThema.BG_STATUS);

        JLabel lblName = new JLabel("Nama:");
        lblName.setFont(AppThema.FONT_BODY);
        lblName.setForeground(AppThema.TEXT_MAIN);

        txtName = new JTextField(14);
        txtName.setFont(AppThema.FONT_BODY);
        defaultNameBorder = txtName.getBorder(); // Simpan border asli

        JLabel lblRole = new JLabel("Role:");
        lblRole.setFont(AppThema.FONT_BODY);
        lblRole.setForeground(AppThema.TEXT_MAIN);

        cmbRole = new JComboBox<>(CharacterType.values());
        cmbRole.setFont(AppThema.FONT_BODY);

        JLabel lblLevel = new JLabel("Level:");
        lblLevel.setFont(AppThema.FONT_BODY);
        lblLevel.setForeground(AppThema.TEXT_MAIN);

        spnLevel = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        spnLevel.setFont(AppThema.FONT_BODY);
        defaultLevelBorder = spnLevel.getBorder(); // Simpan border asli

        JButton btnAdd = new JButton("+ Tambah");
        btnAdd.setFont(AppThema.FONT_BODY_BOLD);
        btnAdd.addActionListener(e -> handleAddCharacter());

        inputRow.add(lblName);
        inputRow.add(txtName);
        inputRow.add(lblRole);
        inputRow.add(cmbRole);
        inputRow.add(lblLevel);
        inputRow.add(spnLevel);
        inputRow.add(btnAdd);

        // Baris Pesan Error Inline
        lblError = new JLabel(" ");
        lblError.setFont(AppThema.FONT_LEGENDA);
        lblError.setForeground(Color.decode("#ff7675"));
        lblError.setBorder(BorderFactory.createEmptyBorder(0, 10, 2, 10));

        add(inputRow, BorderLayout.CENTER);
        add(lblError, BorderLayout.SOUTH);
    }

    /**
     * Memvalidasi nama secara berurutan:
     * 1. Kelengkapan -> 2. Panjang -> 3. Format -> 4. Keunikan.
     * Hanya mengembalikan pesan error (null jika valid), tidak menyentuh komponen UI.
     */
    public String validateName(String rawName) {
        String name = (rawName == null) ? "" : rawName.trim();

        // 1. Kelengkapan
        if (name.isEmpty()) {
            return "Nama karakter wajib diisi!";
        }
        // 2. Panjang (3 - 20 karakter)
        if (name.length() < 3 || name.length() > 20) {
            return "Panjang nama harus antara 3 hingga 20 karakter!";
        }
        // 3. Format (hanya huruf, angka, spasi, atau underscore)
        if (!name.matches("^[a-zA-Z0-9_ ]+$")) {
            return "Format nama hanya boleh berisi huruf, angka, spasi, atau underscore!";
        }
        // 4. Keunikan
        if (manager.isNameTaken(name)) {
            return "Nama '" + name + "' sudah digunakan oleh karakter lain!";
        }

        return null; // Valid
    }

    private void handleAddCharacter() {
        // Pulihkan border asli sebelum pengecekan ulang
        txtName.setBorder(defaultNameBorder);
        spnLevel.setBorder(defaultLevelBorder);
        lblError.setText(" ");

        // 1. Validasi Nama
        String cleanedName = txtName.getText().trim();
        String nameError = validateName(cleanedName);
        if (nameError != null) {
            lblError.setText("⚠ " + nameError);
            txtName.setBorder(errorBorder);
            txtName.requestFocusInWindow();
            return;
        }

        // 2. Commit & Validasi Spinner Level
        int level;
        try {
            spnLevel.commitEdit();
            level = (Integer) spnLevel.getValue();
            if (level < 1 || level > 99) {
                throw new IllegalArgumentException("Level harus antara 1 sampai 99!");
            }
        } catch (ParseException | IllegalArgumentException ex) {
            lblError.setText("⚠ Level tidak valid! Masukkan angka bulat 1–99.");
            spnLevel.setBorder(errorBorder);
            spnLevel.requestFocusInWindow();
            return;
        }

        // 3. Buat objek & tambahkan ke manager
        CharacterType selectedRole = (CharacterType) cmbRole.getSelectedItem();
        try {
            Character newChar = new Character(cleanedName, selectedRole, level);
            if (manager.addCharacter(newChar)) {
                // Reset form & pulihkan state
                txtName.setText("");
                cmbRole.setSelectedIndex(0);
                spnLevel.setValue(1);
                txtName.setBorder(defaultNameBorder);
                spnLevel.setBorder(defaultLevelBorder);
                lblError.setText(" ");
                txtName.requestFocusInWindow();

                // Trigger refresh daftar & statistik di MainWindow
                if (onCharacterAdded != null) {
                    onCharacterAdded.run();
                }
            }
        } catch (IllegalArgumentException ex) {
            lblError.setText("⚠ " + ex.getMessage());
            txtName.setBorder(errorBorder);
            txtName.requestFocusInWindow();
        }
    }
}
