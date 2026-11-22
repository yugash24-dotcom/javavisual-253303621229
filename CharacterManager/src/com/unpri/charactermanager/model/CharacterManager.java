package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    public static final int MAX_CHARACTERS = 20; // Aturan Tambahan 2 (Model)
    private final ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();
    }

    public boolean isNameTaken(String name) {
        if (name == null) return false;
        String cleaned = name.trim();
        for (Character c : characterList) {
            if (c.getName().equalsIgnoreCase(cleaned)) {
                return true;
            }
        }
        return false;
    }

    public boolean addCharacter(Character character) {
        if (characterList.size() >= MAX_CHARACTERS) {
            throw new IllegalArgumentException("Kapasitas daftar penuh! Maksimal " + MAX_CHARACTERS + " karakter.");
        }
        if (isNameTaken(character.getName())) {
            System.out.println("❌ Gagal: karakter dengan nama '" + character.getName() + "' sudah ada!");
            return false;
        }
        characterList.add(character);
        System.out.println("✅ Karakter '" + character.getName() + "' berhasil ditambahkan.");
        return true;
    }

    public Character findByName(String name) {
        if (name == null) return null;
        String cleaned = name.trim();
        for (Character c : characterList) {
            if (c.getName().equalsIgnoreCase(cleaned)) {
                return c;
            }
        }
        return null;
    }

    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            System.out.println("❌ Gagal hapus: karakter '" + name + "' tidak ditemukan.");
            return false;
        }
        characterList.remove(target);
        System.out.println("🗑️ Karakter '" + name + "' berhasil dihapus.");
        return true;
    }

    public int getTotalCharacters() {
        return characterList.size();
    }

    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    public double getAverageLevel() {
        if (characterList.isEmpty()) return 0;
        double totalLevel = 0;
        for (Character c : characterList) {
            totalLevel += c.getLevel();
        }
        return totalLevel / characterList.size();
    }

    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> hasilFilter = new ArrayList<>();
        for (Character c : characterList) {
            if (c.getType() == type) {
                hasilFilter.add(c);
            }
        }
        return hasilFilter;
    }

    public Character findStrongest() {
        if (characterList.isEmpty()) return null;
        Character strongest = characterList.get(0);
        for (Character c : characterList) {
            if (c.getAttackPower() > strongest.getAttackPower()) {
                strongest = c;
            }
        }
        return strongest;
    }
}
