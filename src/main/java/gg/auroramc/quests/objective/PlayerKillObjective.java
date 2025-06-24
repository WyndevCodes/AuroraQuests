package gg.auroramc.quests.objective;

import gg.auroramc.quests.api.objective.Objective;
import gg.auroramc.quests.api.objective.ObjectiveDefinition;
import gg.auroramc.quests.api.profile.Profile;
import gg.auroramc.quests.api.quest.Quest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerKillObjective extends Objective {

    public PlayerKillObjective(Quest quest, ObjectiveDefinition definition, Profile.TaskDataWrapper data) {
        super(quest, definition, data);
    }

    @Override
    public void start() {
        onEvent(EntityDeathEvent.class, this::handle, EventPriority.MONITOR);
    }

    public void handle(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != data.profile().getPlayer()) return;
        Entity mob = event.getEntity();

        if (!(mob instanceof Player victim) || mob == data.profile().getPlayer()) {
            return;
        }

        progress(1, meta());
    }
}
