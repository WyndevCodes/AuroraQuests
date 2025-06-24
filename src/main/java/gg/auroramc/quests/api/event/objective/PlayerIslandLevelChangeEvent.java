package gg.auroramc.quests.api.event.objective;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerIslandLevelChangeEvent extends PlayerEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    private final double level;

    public PlayerIslandLevelChangeEvent(@NotNull Player who, double level) {
        super(who);
        this.level = level;
    }
}
