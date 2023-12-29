package ru.araclecore.battlecore.heroes.heroes.records;

import ru.araclecore.battlecore.heroes.heroes.enums.Rarity;
import ru.araclecore.battlecore.heroes.heroes.enums.Trigger;

import java.util.List;

public record Ability(
        String name,
        String displayname,
        Rarity rarity,
        String description,
        int cost,
        List<Trigger> trigger
) {
}
