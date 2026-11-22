package com.unpri.charactermanager.model;

public class Character {

    private int id;
    private final String name;
    private final CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // CONSTRUCTOR 1: dengan validasi lengkap di lapisan Model
    public Character(String name, CharacterType type, int level) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama karakter tidak boleh kosong!");
        }
        String cleanedName = name.trim();

        // Aturan Tambahan 1 (Model): Larangan nama menyerupai role pengelola sistem
        String lowerName = cleanedName.toLowerCase();
        if (lowerName.equals("admin") || lowerName.equals("system") || lowerName.equals("gm")) {
            throw new IllegalArgumentException("Nama '" + cleanedName + "' dicadangkan oleh sistem!");
        }

        if (type == null) {
            throw new IllegalArgumentException("Role karakter tidak boleh null!");
        }
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException("Level harus berada di antara 1 sampai 99!");
        }

        // Mini-Challenge (Model): MAGE tidak boleh di atas level 60
        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException("Role MAGE tidak boleh memiliki level di atas 60!");
        }

        this.name = cleanedName;
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        this.mana = 0;
    }

    // CONSTRUCTOR 2: memuat dari database
    public Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower) {
        this(name, type, level);
        this.id = id;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
    }

    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }
    public int getMana() { return mana; }

    public void setId(int id) { this.id = id; }

    public void setMana(int mana) {
        if (mana < 0) {
            System.out.println("Error: Mana tidak boleh negatif!");
        } else {
            this.mana = mana;
        }
    }

    public void setLevel(int level) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException("Level harus berada di antara 1 sampai 99!");
        }
        if (this.type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException("Role MAGE tidak boleh memiliki level di atas 60!");
        }
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    public boolean isAlive() {
        return this.hitPoints > 0;
    }

    @Override
    public String toString() {
        return "Character{id=" + id +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", level=" + level +
               ", hp=" + hitPoints +
               ", atk=" + attackPower +
               ", mana=" + mana + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Character)) return false;
        Character other = (Character) obj;
        return this.name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
