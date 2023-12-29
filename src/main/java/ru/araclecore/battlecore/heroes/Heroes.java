package ru.araclecore.battlecore.heroes;

import org.bukkit.plugin.java.JavaPlugin;
import ru.araclecore.battlecore.connection.utilities.Configuration;
import ru.araclecore.battlecore.heroes.heroes.Manager;

public final class Heroes extends JavaPlugin {

    public static Heroes instance;

    public static Configuration heroes;
    public static Configuration suits;
    public static Configuration abilities;

    public static Manager manager;

    @Override
    public void onEnable() {
        instance = this;
        heroes = new Configuration(instance, "heroes.yml");
        suits = new Configuration(instance, "suits.yml");
        abilities = new Configuration(instance, "abilities.yml");
        manager = new Manager();
        getServer().getPluginManager().registerEvents(manager, instance);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
