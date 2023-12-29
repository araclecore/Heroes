package ru.araclecore.battlecore.heroes.heroes.records;

import org.bukkit.entity.Player;
import ru.araclecore.battlecore.heroes.heroes.enums.Rarity;
import ru.araclecore.battlecore.heroes.heroes.enums.Weapon;

import java.util.List;

public record Hero(
        Player player,
        String name,
        String displayname,
        Rarity rarity,
        String description,
        Suit suit,
        List<String> suits,
        Ability ability,
        List<String> abilities,
        int cost,
        Weapon weapon) {
}
