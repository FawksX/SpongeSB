package games.synx.spongysb.util;

import games.synx.spongysb.SpongySB;
import org.spongepowered.api.Sponge;

public class AsyncUtil {

    public static void async(Runnable run) {
        Sponge.getScheduler().createTaskBuilder().async().execute(run).submit(SpongySB.get());
    }

}
