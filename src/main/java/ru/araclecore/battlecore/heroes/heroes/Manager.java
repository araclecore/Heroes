package ru.araclecore.battlecore.heroes.heroes;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.araclecore.battlecore.connection.utilities.Configuration;
import ru.araclecore.battlecore.connection.utilities.Utilities;
import ru.araclecore.battlecore.heroes.Heroes;
import ru.araclecore.battlecore.heroes.heroes.enums.Rarity;
import ru.araclecore.battlecore.heroes.heroes.enums.Trigger;
import ru.araclecore.battlecore.heroes.heroes.enums.Weapon;
import ru.araclecore.battlecore.heroes.heroes.records.Ability;
import ru.araclecore.battlecore.heroes.heroes.records.Hero;
import ru.araclecore.battlecore.heroes.heroes.records.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Manager implements Listener {

    private final Map<Player, Profile> heroes;

    public Manager() {
        heroes = Maps.newHashMap();
    }

    @EventHandler
    private void join(PlayerJoinEvent event) {
        if (!Utilities.connection()) return;
        Player player = event.getPlayer();
        heroes.put(player, new Profile(player));
    }

    @EventHandler
    private void quit(PlayerQuitEvent event) {
        if (!Utilities.connection()) return;
        Player player = event.getPlayer();
        heroes.remove(player);
    }

    public Profile get(Player player) {
        return heroes.get(player);
    }

    public Hero create(Player player, String hero, String suit, String ability) {
        return new Hero(
                player,
                hero,
                String(Heroes.heroes, "Heroes." + hero + ".displayname"),
                Rarity(Heroes.heroes, "Heroes." + hero + ".rarity"),
                Description(Heroes.heroes, "Heroes." + hero + ".description"),

                new Suit(
                        suit,
                        String(Heroes.suits, "Suits." + suit + ".displayname"),
                        Rarity(Heroes.suits, "Suits." + suit + ".rarity"),
                        Description(Heroes.suits, "Suits." + suit + ".description"),
                        Integer(Heroes.suits, "Suits." + suit + ".cost"),
                        Integer(Heroes.suits, "Suits." + suit + ".helmet"),
                        Integer(Heroes.suits, "Suits." + suit + ".chestplate"),
                        Integer(Heroes.suits, "Suits." + suit + ".leggings"),
                        Integer(Heroes.suits, "Suits." + suit + ".boots"),

                        new Suit.Weapon(
                                Integer(Heroes.suits, "Suits." + suit + ".weapon-charged"),
                                Integer(Heroes.suits, "Suits." + suit + ".weapon-active"),
                                Integer(Heroes.suits, "Suits." + suit + ".weapon-inactive"),
                                Integer(Heroes.suits, "Suits." + suit + ".weapon-charge-25"),
                                Integer(Heroes.suits, "Suits." + suit + ".weapon-charge-50"),
                                Integer(Heroes.suits, "Suits." + suit + ".weapon-charge-75"))),

                Strings(Heroes.heroes, "Heroes." + hero + ".suits"),

                new Ability(
                        ability,
                        String(Heroes.abilities, "Abilities." + ability + ".displayname"),
                        Rarity(Heroes.abilities, "Abilities." + ability + ".rarity"),
                        Description(Heroes.abilities, "Abilities." + ability + ".description"),
                        Integer(Heroes.abilities, "Abilities." + ability + ".cost"),
                        Triggers(Heroes.abilities, "Abilities." + ability + ".triggers")),

                Strings(Heroes.heroes, "Heroes." + hero + ".abilities"),
                Integer(Heroes.heroes, "Heroes." + hero + ".cost"),
                Weapon(Heroes.heroes, "Heroes." + hero + ".weapon"));
    }

    private String String(Configuration configuration, String path) {
        if (configuration.String(path) == null) return "none";
        return configuration.String(path);
    }

    private List<String> Strings(Configuration configuration, String path) {
        if (configuration.Strings(path) == null) return new ArrayList<>();
        return configuration.Strings(path);
    }

    private String Description(Configuration configuration, String path) {
        if (configuration.Strings(path) == null) return "none";
        StringBuilder description = new StringBuilder();
        for (String string : configuration.Strings(path)) {
            description.append("\n").append(string);
        }
        return description.toString();
    }

    private Integer Integer(Configuration configuration, String path) {
        if (configuration.Integer(path) == null) return 0;
        return configuration.Integer(path);
    }

    private Rarity Rarity(Configuration configuration, String path) {
        if (configuration.String(path) == null) return Rarity.COMMON;
        String rarity = configuration.String(path);
        switch (rarity) {
            case "RARE" -> {
                return Rarity.RARE;
            }
            case "EPIC" -> {
                return Rarity.EPIC;
            }
            case "LEGENDARY" -> {
                return Rarity.LEGENDARY;
            }
            default -> {
                return Rarity.COMMON;
            }
        }
    }

    private Weapon Weapon(Configuration configuration, String path) {
        if (configuration.String(path) == null) return Weapon.SWORD;
        String weapon = configuration.String(path);
        switch (weapon) {
            case "BOW" -> {
                return Weapon.BOW;
            }
            case "STUFF" -> {
                return Weapon.STUFF;
            }
            default -> {
                return Weapon.SWORD;
            }
        }
    }

    private List<Trigger> Triggers(Configuration configuration, String path) {
        if (configuration.String(path) == null) return new ArrayList<>();
        List<String> strings = configuration.Strings(path);
        List<Trigger> triggers = new ArrayList<>();
        strings.forEach(trigger -> {
            switch (trigger) {
                case "R" -> triggers.add(Trigger.R);
                case "L" -> triggers.add(Trigger.L);
                case "SHIFT" -> triggers.add(Trigger.SHIFT);
                case "TARGET" -> triggers.add(Trigger.TARGET);
                case "ARROW" -> triggers.add(Trigger.ARROW);
                case "DAMAGE" -> triggers.add(Trigger.DAMAGE);
                default -> triggers.add(Trigger.F);
            }
        });
        return triggers;
    }
}
