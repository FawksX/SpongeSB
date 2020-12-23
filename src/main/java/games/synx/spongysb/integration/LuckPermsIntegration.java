package games.synx.spongysb.integration;

import games.synx.spongysb.SpongySB;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.types.PermissionNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.ProviderRegistration;

import java.util.Optional;

public class LuckPermsIntegration {

  LuckPerms api;


  public LuckPermsIntegration() {
    SpongySB.get().getLogger().info("Registering LuckPerms Integration");
    setup();
  }

  public void setup() {
    Optional<ProviderRegistration<LuckPerms>> provider = Sponge.getServiceManager().getRegistration(LuckPerms.class);
    if (provider.isPresent()) {
      api = provider.get().getProvider();
    }
  }

  public LuckPerms getLuckPermsAPI() {
    return api;
  }

}
