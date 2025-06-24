package gg.auroramc.quests.hooks.znpcs;

import gg.auroramc.aurora.api.item.TypeId;
import gg.auroramc.quests.AuroraQuests;
import gg.auroramc.quests.api.event.objective.PlayerInteractNpcEvent;
import gg.auroramc.quests.api.objective.ObjectiveType;
import io.github.gonalez.znpcs.npc.event.NPCInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;

public class ZnpcListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onNPCRightClick(NPCInteractEvent e) {

        io.github.gonalez.znpcs.npc.NPC npc = e.getNpc();
        Player player = e.getPlayer();

        PlayerInteractNpcEvent.InteractionType interactionType = e.isRightClick()
                ? PlayerInteractNpcEvent.InteractionType.RIGHT_CLICK : e.isLeftClick()
                ? PlayerInteractNpcEvent.InteractionType.LEFT_CLICK : PlayerInteractNpcEvent.InteractionType.UNKNOWN;

        var id = new TypeId("znpcs", String.valueOf(e.getNpc().getNpcPojo().getId()));

        Bukkit.getPluginManager().callEvent(new PlayerInteractNpcEvent(player, id, interactionType));
    }
}
