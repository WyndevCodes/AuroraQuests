package gg.auroramc.quests.objective;

import gg.auroramc.quests.api.objective.Objective;
import gg.auroramc.quests.api.objective.ObjectiveDefinition;
import gg.auroramc.quests.api.profile.Profile;
import gg.auroramc.quests.api.quest.Quest;
import org.bukkit.Statistic;

public class WalkObjective extends Objective {
    private int previousValue;

    public WalkObjective(Quest quest, ObjectiveDefinition definition, Profile.TaskDataWrapper data) {
        super(quest, definition, data);
    }

    @Override
    protected void activate() {
        previousValue = data.profile().getPlayer().getStatistic(Statistic.WALK_ONE_CM);
        syncInterval(this::handler, 100, 100, false);
    }

    private void handler() {
        var current = data.profile().getPlayer().getStatistic(Statistic.WALK_ONE_CM);
        var diff = current - previousValue;
        if (diff > 0) {
            progress(diff / 100D, meta());
            previousValue = current;
        }
    }
}
