package games.synx.spongysb.commands.admin;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import games.synx.spongysb.commands.AbstractIslandCommand;
import games.synx.spongysb.objects.SPlayer;
import org.spongepowered.api.entity.living.player.Player;

@CommandAlias("spongesb|ssb")
public class AdminBypassCommand extends AbstractIslandCommand {

  @Subcommand("bypass")
  @Description("Bypasses PlayerGuard checks")
  @CommandPermission("spongysb.admin.bypass")
  public void onAdminBypassCommand(Player player) {

    SPlayer sPlayer = SPlayer.get(player);

    if(sPlayer.isBypassed()) {
      sPlayer.setBypassed(false);
      msg(player, getMessages().bypass.disable_admin_bypass);
      return;
    }

    sPlayer.setBypassed(true);
    msg(player, getMessages().bypass.enabled_admin_bypass);

  }

}
