package games.synx.spongysb.config.configs;

import games.synx.pscore.config.impl.AbstractConfiguration;
import games.synx.pscore.config.impl.IConfiguration;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.IOException;
import java.nio.file.Path;

public class Messages extends AbstractConfiguration<Messages.MessageSettings> implements IConfiguration {

  public Messages(Path configFile) throws IOException {
    super(configFile, MessageSettings.class);
  }

  @ConfigSerializable
  public static class MessageSettings {

    @Setting
    public Creation creation = new Creation();

    @Setting
    public Coop coop = new Coop();

    @Setting
    public Promote promote = new Promote();

    @Setting
    public Demote demote = new Demote();

    @Setting
    public SetHome setHome = new SetHome();

    @Setting
    public Lock lock = new Lock();

    @Setting
    public Invite invite = new Invite();

    @Setting
    public Bypass bypass = new Bypass();

    @Setting
    public Teleport teleport = new Teleport();

    @Setting
    public Disband disband = new Disband();

    @Setting
    public Permission permission = new Permission();

    @Setting
    public Upgrades upgrades = new Upgrades();

    @Setting
    public Ban ban = new Ban();

    @Setting
    public Kick kick = new Kick();

    @Setting
    public Leave leave = new Leave();

    @Setting
    public Admin admin = new Admin();


    @Setting
    public String player_not_in_island = "&cYou are not in an island! Type /is new <island name> to create an island!";

    @Setting
    public String player_not_online = "&e&lISLAND &cThe specified player is not online!";

    @Setting
    public String island_does_not_exist = "&e&lISLAND &eIsland &c%s &edoes not exist!";

    @Setting
    public String player_is_not_leader = "&e&lISLAND &cYou must be the Island Leader to execute this command!";

    @Setting
    public String player_is_not_in_island = "&e&lISLAND &c%s &eis not in your island!";

    @Setting
    public String must_disband_as_a_leader = "&e&lISLAND &cYou must disband your island in order to leave!";

    @Setting
    public String island_name_set_successfully = "&e&lISLAND &a%s &ehas changed your island name to &a%s!";

    @Setting
    public String must_be_in_island_to_give_leadership = "&e&lISLAND &a%s &cmust be in your island to give Leader to them!";

    @Setting
    public String leader_changed_broadcast = "&e&lISLAND &a%s &ehas given Island Ownership to &a%s!";

    @Setting
    public String no_permission = "&e&lISLAND &eYou do not have permission to perform this command (Island Permission Level must be lower!)";

    @ConfigSerializable
    public static class Creation {

      @Setting
      public String creating_island = "&aCreating your Island...";

      @Setting
      public String island_name_taken = "&e&lISLAND &eIsland name &c%s &ealready exists!";

      @Setting
      public String must_leave_current_island = "&e&lISLAND &cYou must leave your current island to join another!";

      @Setting
      public String is_in_island_error = "&cYou are already in an island!";

      @Setting
      public String island_created_successfully = "&e&lISLAND &aYour island has been created! type &e/is go&a to start!";

    }

    @ConfigSerializable
    public static class SetHome {

      @Setting
      public String can_only_set_home_in_your_island = "&e&lISLAND &cYou must be at your own island to set home!";

      @Setting
      public String island_home_set_successfully = "&e&lISLAND &aYou have successfully set your Island Home Location!";

      @Setting
      public String unsafe_home_location = "&e&lISLAND &cYou must be standing on a block to set a home!";

    }



    @ConfigSerializable
    public static class Promote {

      @Setting
      public String cannot_promote_to_leader = "&e&lISLAND &cYou cannot promote a member to leader! Use &a/is makeleader <name>";

      @Setting
      public String cannot_promote_non_island_member = "&e&lISLAND &cYou can only promote your own island members!";

      @Setting
      public String island_broadcast_promotion_success = "&e&lISLAND &a%s &ehas promoted &a%s &eto Island rank &3%s!";

    }

    @ConfigSerializable
    public static class Demote {

      @Setting
      public String cannot_demote_to_visitor = "&e&lISLAND &cYou cannot demote a member to visitor! Use &a/is kick <name>";

      @Setting
      public String cannot_demote_non_island_member = "&e&lISLAND &cYou can only demote your own island members!";

      @Setting
      public String island_broadcast_promotion_success = "&e&lISLAND &a%s &ehas demoted &a%s &efrom &3%s &eto &3%s!";

    }

    @ConfigSerializable
    public static class Coop {

      @Setting
      public String revoked_successfully = "&e&lISLAND &eYou have successfully revoked the coop for &a%s!";

      @Setting
      public String revoked_broadcast = "&e&lISLAND &a%s &ehas revoked COOP access from &a%s!";

      @Setting
      public String added_successfully = "&e&lISLAND &eYou have successfully added the coop for &a%s!";

      @Setting
      public String added_broadcast = "&e&lISLAND &a%s &ehas added COOP access for &a%s!";

      @Setting
      public String added_message = "&e&lISLAND &a%s &ehas granted you COOP access on &a%s!";

      @Setting
      public String must_be_in_island = "&e&lISLAND &cYou must be in an Island in order to COOP players!";

    }

    @ConfigSerializable
    public static class Lock {

      @Setting
      public String must_be_in_island = "&e&lISLAND &cYou must be in an island to be able to lock one!";

      @Setting
      public String island_is_now_locked_broadcast = "&e&lISLAND &c%s &ehas &c&l&nLOCKED&e your island to all visitors!";

      @Setting
      public String island_is_now_unlocked_broadcast = "&e&lISLAND &c%s &ehas &a&l&nUNLOCKED&e your island to all visitors!";

    }

    @ConfigSerializable
    public static class Invite {

      @Setting
      public String invite_revoked_successfully = "&e&lISLAND  &ePlayer &a%s &ehas had their invite &crevoked successfully!";

      @Setting
      public String invited_player_successfully = "&e&lISLAND  &ePlayer &a%s &ehas been &ainvited successfully!";

      @Setting
      public String invited_to_island = "&e&lISLAND &a%s &ehas invited you to join &a%s&e. Type &a/is join %s &eto join!";

      @Setting
      public String leader_revoked_invite = "&e&lISLAND &a%s &ehas &crevoked &a%s's &einvite.";

      @Setting
      public String leader_invited_player = "&e&lISLAND &a%s &ehas invited &a%s &eto your island!";

      @Setting
      public String joined_island_successfully = "&e&lISLAND &eYou have &asuccessfully &ejoined &a%s!";

      @Setting
      public String could_not_join_island = "&e&lISLAND &eYou do not have a pending invite to &c%s!";

      @Setting
      public String player_has_joined_island = "&e&lISLAND &ePlayer &a%s &e has joined your island!";

      @Setting
      public String must_be_in_island_to_invite = "&e&lISLAND &cYou must be in an island to invite people!";

      @Setting
      public String island_is_full = "&e&lISLAND &cYour Island is full so you could not invite %s!";

      @Setting
      public String island_full_error = "&e&lISLAND &cThis island is full!";

    }

    @ConfigSerializable
    public static class Bypass {

      @Setting
      public String enabled_admin_bypass = "&e&lISLAND &aYou have successfully &lENABLED&a Island Bypass!";

      @Setting
      public String disable_admin_bypass = "&e&lISLAND &cYou have successfully &lDISABLED&c Island Bypass!";

    }

    @ConfigSerializable
    public static class Teleport {

      @Setting
      public String teleporting_to_your_island = "&aTeleporting to your island...";

      @Setting
      public String teleporting_to_default_home_location = "&aCould not teleport to your Island, Teleporting you to default home location!";

      @Setting
      public String could_not_teleport_to_island = "&cCould not teleport to your island. Contact Staff for help!";

      @Setting
      public String not_allowed_to_teleport_here = "&e&lISLAND &cYou are not allowed to teleport to this island!";

      @Setting
      public String void_to_spawn = "&e&lISLAND &bYou have been teleported to spawn!";

    }

    @ConfigSerializable
    public static class Disband {

      @Setting
      public String disband_must_be_in_island = "&e&lISLAND &eYou must be in an island to be able to disband!";

      @Setting
      public String only_leader_can_disband = "&e&lISLAND &cOnly the Island Leader can disband your island!";

      @Setting
      public String island_disbanded = "&e&lISLAND &eYour island has been disbanded by &a%s&e!";

    }

    @ConfigSerializable
    public static class Permission {

      @Setting
      public String cannot_put_less_than_visitor = "&e&lISLAND &cYou cannot put a permission lower than visitor!";

      @Setting
      public String cannot_put_more_than_leader = "&e&lISLAND &cYou cannot put a permission higher than leader!";

      @Setting
      public String can_only_view_permissions = "&e&lISLAND &cYou may only view Island Permissions!";

    }

    @ConfigSerializable
    public static class Upgrades {

      @Setting
      public String cannot_upgrade = "&e&lISLAND &cYou do not have permission to upgrade your island!";

      @Setting
      public String max_upgrade = "&e&lISLAND &cYou have maxxed out this upgrade!";

      @Setting
      public String upgraded = "&e&lISLAND &aYour Island has been upgraded by &d%s&e!";

      @Setting
      public String no_funds = "&e&lISLAND &cYou do not have enough funds for this upgrade!";
    }

    @ConfigSerializable
    public static class Ban {

      @Setting
      public String teleport_ban_notification = "&e&lISLAND &cYou are banned from Island %s!";

      @Setting
      public String must_be_in_island = "&e&lISLAND &cYou must be in an island to ban!";

      @Setting
      public String unban_success = "&e&lISLAND &aYou have successfully unbanned %s from your island!";

      @Setting
      public String player_unbanned_broadcast = "&e&lISLAND &a%s has unbanned %s from your island!";

      @Setting
      public String ban_success = "&e&lISLAND &cYou have &lBANNED &c%s from your island!";

      @Setting
      public String success_broadcast = "&e&lISLAND &e%s has &lBANNED &e%s &cfrom your island!";

      @Setting
      public String player_not_banned = "&e&lISLAND &c%s is not banned from your island!";

      @Setting
      public String player_is_banned = "&e&lISLAND &c%s is already banned from your island!";

      @Setting
      public String cannot_ban_member = "&e&lISLAND &cYou cannot ban your island members from your island!";
    }

    @ConfigSerializable
    public static class Kick {

      @Setting
      public String player_has_been_removed_from_island = "&e&lISLAND &a%s &chas been removed from your island successfully!";

      @Setting
      public String you_have_been_removed_from_your_island = "&e&lISLAND &cYou have been removed from &e%s &cby &e%s";

      @Setting
      public String leader_kicked_player = "&e&lISLAND &a%s &ehas kicked &a%s &efrom your island!";

    }

    @ConfigSerializable
    public static class Leave {

      @Setting
      public String left_island = "&e&lISLAND &eYou have left the island &a%s.";

      @Setting
      public String leave_confirm = "&e&lISLAND &eType &a/is leave confirm &eto leave your island.";

      @Setting
      public String player_left_island = "&e&lISLAND &a%s &ehas left your island!";

      @Setting
      public String must_be_in_island_to_leave = "&e&lISLAND &cYou must be in an island to leave!";

    }

    @ConfigSerializable
    public static class Admin {

      @Setting
      public Disband disband = new Disband();

      @Setting
      public Rename rename = new Rename();

      @ConfigSerializable
      public static class Disband {

        @Setting
        public String admin_command_confirmation = "&3&lADMIN &eYou have successfully disbanded the island &b%s&e!";

      }

      @ConfigSerializable
      public static class Rename {

        @Setting
        public String admin_command_notification = "&3&lADMIN &eYou have successfully changed %s's name to %s!";
      }

    }



  }
}
