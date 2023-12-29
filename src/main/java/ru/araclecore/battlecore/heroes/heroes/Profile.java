package ru.araclecore.battlecore.heroes.heroes;

import org.bukkit.entity.Player;
import ru.araclecore.battlecore.connection.Connection;
import ru.araclecore.battlecore.connection.profile.Data;
import ru.araclecore.battlecore.heroes.Heroes;
import ru.araclecore.battlecore.heroes.heroes.records.Hero;

public class Profile {

    public Player player;
    public Hero hero;

    public Profile(Player player) {
        this.player = player;
        records();
    }

    private void records() {
        Data data = Connection.manager.data(player);
        String hero = data.hero.hero;
        String suit = data.hero.suit;
        String ability = data.hero.ability;
        this.hero = Heroes.manager.create(player, hero, suit, ability);
    }

}
