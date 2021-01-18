package games.synx.spongysb.listeners.islandguard;

import games.synx.spongysb.config.ConfigManager;
import games.synx.spongysb.generation.GridManager;
import games.synx.spongysb.objects.IslandPerm;
import games.synx.spongysb.objects.SPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public abstract class AbstractIslandGuard {

    private final ConfigManager configManager = ConfigManager.get();

    /**
     * Check if a Forge Minecraft Player Instance has access to said location
     * @param forgePlayer EntityPlayerMP
     * @param islandPerm Island Permission being checked
     * @return boolean (hasPerm)
     */
    protected boolean passesGenericIslandChecks(EntityPlayerMP forgePlayer, IslandPerm islandPerm) {
        return passesGenericIslandChecks(Sponge.getServer().getPlayer(forgePlayer.getUniqueID()).get(), islandPerm);
    }

    /**
     * Check if a Sponge Minecraft Player Instance has access to said location
     * @param spongePlayer Sponge Player
     * @param islandPerm Island Permission being checked
     * @return boolean (hasPerm)
     */
    protected boolean passesGenericIslandChecks(Player spongePlayer, IslandPerm islandPerm) {
        if(isBypassed(SPlayer.get(spongePlayer.getUniqueId()))) return true;
        if(isNotInWorld(spongePlayer)) return true;
        return SPlayer.get(spongePlayer).hasPerm(islandPerm, spongePlayer.getLocation());
    }


    protected boolean isNotInWorld(Entity entity) {
        return !GridManager.get().inWorld(entity.getLocation());
    }

    protected boolean isNotInWorld(Location<World> location) {
        return !GridManager.get().inWorld(location);
    }

    protected boolean isBypassed(SPlayer player) {
        return player.isBypassed();
    }

    protected ConfigManager getConfigManager() {
        return this.configManager;
    }

}
