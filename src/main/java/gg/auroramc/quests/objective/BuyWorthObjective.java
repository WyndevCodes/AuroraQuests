package gg.auroramc.quests.objective;

import gg.auroramc.quests.api.event.objective.PlayerSpendOnPurchaseEvent;
import gg.auroramc.quests.api.objective.Objective;
import gg.auroramc.quests.api.objective.ObjectiveDefinition;
import gg.auroramc.quests.api.profile.Profile;
import gg.auroramc.quests.api.quest.Quest;
import org.bukkit.event.EventPriority;

public class BuyWorthObjective extends Objective {

    public BuyWorthObjective(Quest quest, ObjectiveDefinition definition, Profile.TaskDataWrapper data) {
        super(quest, definition, data);
    }

    @Override
    protected void activate() {
        onEvent(PlayerSpendOnPurchaseEvent.class, this::handle, EventPriority.MONITOR);
    }

    public void handle(PlayerSpendOnPurchaseEvent event) {
        progress(event.getAmount(), meta());
    }
}
