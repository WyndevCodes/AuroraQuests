package gg.auroramc.quests.api;

import gg.auroramc.quests.AuroraQuests;
import gg.auroramc.quests.api.profile.ProfileManager;
import gg.auroramc.quests.api.questpool.PoolManager;

public class AuroraQuestsProvider {

    public PoolManager getPoolManager() {
        return AuroraQuests.getInstance().getPoolManager();
    }

    public ProfileManager getProfileManager() {
        return AuroraQuests.getInstance().getProfileManager();
    }
}
