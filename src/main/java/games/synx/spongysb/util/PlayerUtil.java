package games.synx.spongysb.util;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.Optional;
import java.util.UUID;

public class PlayerUtil {

    public static UUID getOfflineUserUUID(String user) {
        return getOfflineUser(user).getUniqueId();
    }

    public static User getOfflineUser(String user) {
        Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
        User offlinePlayer = userStorage.get().get(user).get();
        return offlinePlayer;
    }

}
