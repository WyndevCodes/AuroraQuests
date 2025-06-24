package gg.auroramc.quests.hooks.fancynpcs;

import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import gg.auroramc.aurora.api.item.TypeId;
import gg.auroramc.quests.AuroraQuests;
import gg.auroramc.quests.api.event.objective.PlayerInteractNpcEvent;
import gg.auroramc.quests.api.objective.ObjectiveType;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Map;

public class NpcListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInteractNPC(NpcInteractEvent event) {
        var npcID = event.getNpc().getData().getName();
        var id = new TypeId("fancynpcs", npcID);

        PlayerInteractNpcEvent.InteractionType interactionType = switch (event.getInteractionType()) {
            case RIGHT_CLICK -> PlayerInteractNpcEvent.InteractionType.RIGHT_CLICK;
            case LEFT_CLICK -> PlayerInteractNpcEvent.InteractionType.LEFT_CLICK;
            default -> PlayerInteractNpcEvent.InteractionType.UNKNOWN;
        };

        Bukkit.getPluginManager().callEvent(new PlayerInteractNpcEvent(event.getPlayer(), id, interactionType));
    }
}
