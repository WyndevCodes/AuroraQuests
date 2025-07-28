package gg.auroramc.quests.hooks.znpcsplus;

import gg.auroramc.aurora.api.item.TypeId;
import gg.auroramc.quests.AuroraQuests;
import gg.auroramc.quests.api.quest.TaskType;
import lol.pyr.znpcsplus.api.event.NpcInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;

public class ZnpcPlusListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onNPCRightClick(NpcInteractEvent e) {

        lol.pyr.znpcsplus.api.npc.Npc npc = e.getNpc();
        Player player = e.getPlayer();

        var id = new TypeId("znpcsplus", String.valueOf(npc.getUuid()));
        AuroraQuests.getInstance().getQuestManager().progress(player, TaskType.INTERACT_NPC, 1, Map.of("type", id));
    }
}
