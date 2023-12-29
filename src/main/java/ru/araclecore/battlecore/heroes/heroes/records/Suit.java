package ru.araclecore.battlecore.heroes.heroes.records;

import ru.araclecore.battlecore.heroes.heroes.enums.Rarity;

public record Suit(
        String name,
        String displayname,
        Rarity rarity,
        String description,
        int cost,
        int helmet,
        int chestplate,
        int leggings,
        int boots,
        Weapon weapon
) {
    public record Weapon(
            int charged,
            int active,
            int inactive,
            int charge25,
            int charge50,
            int charge75
    ) {
    }
}
