package games.synx.spongysb.integration;

import games.synx.spongysb.SpongySB;
import net.luckperms.api.LuckPerms;

public class IntegrationManager {

  LuckPermsIntegration luckPerms;

  public IntegrationManager() {
    SpongySB.get().getLogger().info("Initialising IntegrationManager");
    setup();
  }

  public void setup() {

    LuckPermsIntegration luckPerms = new LuckPermsIntegration();

  }

}
