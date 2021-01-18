package games.synx.spongysb.config.configs;

import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.IslandPermissionLevel;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class Conf extends AbstractConfiguration<Conf.ConfSettings> implements IConfiguration {

    public Conf(Path configFile) throws IOException {
        super(configFile, ConfSettings.class);
    }

    @ConfigSerializable
    public static class ConfSettings {

        @Setting
        public ConfSettings.World world = new World();

        @Setting
        public ConfSettings.Database database = new Database();

        @Setting
        public ConfSettings.SpawnWorld spawnWorld = new SpawnWorld();

        @Setting
        public ConfSettings.IslandPermissions islandPermissions = new IslandPermissions();

        @Setting
        public long invite_timeout_in_seconds = 900;

        @Setting
        public long autosave_task_time_seconds = 300;

        /**
         * Configuration Section Dedicated to MySQL Connections
         * The only place where this is referenced is games.synx.spongysb.storage.DatabaseManager
         */
        @ConfigSerializable
        public static class Database {

            @Setting
            public String address = "london.enchantmc.com";

            @Setting
            public int port = 3306;

            @Setting
            public String username = "FawksX";

            @Setting
            public String password = "password";

            @Setting
            public String database = "SpongySB";

        }

        @ConfigSerializable
        public static class World {

            @Setting
            public String worldName = "SpongeSkyblock";

            @Setting
            public int islandDistance = 600;

            @Setting
            public int island_paste_height = 100;

        }

        @ConfigSerializable
        public static class SpawnWorld {

            @Setting
            public String world = "world";

            @Setting
            public int x = 0;

            @Setting
            public int y = 100;

            @Setting
            public int z = 0;

        }

        @ConfigSerializable
        public static class IslandPermissions {

            @Setting
            public Map<IslandPerm, IslandPermissionLevel> defaultIslandPermissions = new HashMap<IslandPerm, IslandPermissionLevel>() {{
                put(IslandPerm.PLACE, IslandPermissionLevel.COOP);
                put(IslandPerm.BREAK, IslandPermissionLevel.COOP);
                put(IslandPerm.ENTRY, IslandPermissionLevel.NONE);
                put(IslandPerm.SPAWNER_PLACE, IslandPermissionLevel.COOP);
                put(IslandPerm.SPAWNER_BREAK, IslandPermissionLevel.COOP);
                put(IslandPerm.DOOR, IslandPermissionLevel.COOP);
                put(IslandPerm.BUTTON, IslandPermissionLevel.COOP);
                put(IslandPerm.LEVER, IslandPermissionLevel.COOP);
                put(IslandPerm.GATE, IslandPermissionLevel.COOP);
                put(IslandPerm.CONTAINER, IslandPermissionLevel.COOP);
                put(IslandPerm.ARMORSTAND, IslandPermissionLevel.COOP);
                put(IslandPerm.PRESSURE_PLATE, IslandPermissionLevel.COOP);
                put(IslandPerm.NAME, IslandPermissionLevel.LEADER);
                put(IslandPerm.VIEW_PERMS, IslandPermissionLevel.MEMBER);
                put(IslandPerm.SET_PERMS, IslandPermissionLevel.LEADER);
                put(IslandPerm.INVITE, IslandPermissionLevel.ADMIN);
                put(IslandPerm.KICK, IslandPermissionLevel.LEADER);
                put(IslandPerm.WARP, IslandPermissionLevel.NONE);
                put(IslandPerm.VIEW_UPGRADES, IslandPermissionLevel.MEMBER);
                put(IslandPerm.SET_UPGRADES, IslandPermissionLevel.LEADER);
                put(IslandPerm.PROMOTE, IslandPermissionLevel.LEADER);
                put(IslandPerm.DEMOTE, IslandPermissionLevel.LEADER);
                put(IslandPerm.BAN, IslandPermissionLevel.ADMIN);
                put(IslandPerm.HOME, IslandPermissionLevel.MEMBER);
                put(IslandPerm.IS_LOCK, IslandPermissionLevel.ADMIN);
                put(IslandPerm.SET_HOME, IslandPermissionLevel.MOD);
                put(IslandPerm.ITEM_DROP, IslandPermissionLevel.NONE);
                put(IslandPerm.ITEM_PICKUP, IslandPermissionLevel.NONE);
                put(IslandPerm.HURT_ANIMALS, IslandPermissionLevel.COOP);
                put(IslandPerm.HURT_MOBS, IslandPermissionLevel.COOP);
                put(IslandPerm.SPAWN_EGGS, IslandPermissionLevel.COOP);
                put(IslandPerm.BREED, IslandPermissionLevel.COOP);
                put(IslandPerm.ENDER_PEARL, IslandPermissionLevel.COOP);
                put(IslandPerm.MUSIC, IslandPermissionLevel.COOP);
                put(IslandPerm.ANIMALS, IslandPermissionLevel.COOP);
                put(IslandPerm.SEND_OUT_POKEMON, IslandPermissionLevel.COOP);
            }};


        }

    }

}


