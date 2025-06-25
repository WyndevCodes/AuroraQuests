package gg.auroramc.quests.objective;

import gg.auroramc.aurora.api.util.BukkitPotionType;
import gg.auroramc.aurora.api.util.Version;
import gg.auroramc.quests.AuroraQuests;
import gg.auroramc.quests.api.objective.ObjectiveDefinition;
import gg.auroramc.quests.api.objective.ObjectiveType;
import gg.auroramc.quests.api.objective.StringTypedObjective;
import gg.auroramc.quests.api.profile.Profile;
import gg.auroramc.quests.api.quest.Quest;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class BrewingObjective extends StringTypedObjective {
    private static final NamespacedKey potionKey = new NamespacedKey(AuroraQuests.getInstance(), "counted");
    private final Map<Location, Player> brewingStands = new ConcurrentHashMap<>();

    public BrewingObjective(Quest quest, ObjectiveDefinition definition, Profile.TaskDataWrapper data) {
        super(quest, definition, data);
    }

    @Override
    protected void activate() {
        onEvent(InventoryOpenEvent.class, this::onInventoryOpen, EventPriority.MONITOR);
        onEvent(BrewEvent.class, this::onBrew, EventPriority.MONITOR);
    }


    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player player)) {
            return;
        }

        final Inventory inventory = event.getInventory();
        if (!(inventory instanceof BrewerInventory)) {
            return;
        }

        final InventoryHolder holder = inventory.getHolder();
        if (holder == null) {
            return;
        }

        if (player == data.profile().getPlayer()) {
            brewingStands.put(inventory.getLocation(), player);
        } else {
            brewingStands.remove(inventory.getLocation());
        }
    }


    public void onBrew(BrewEvent event) {
        var player = brewingStands.remove(event.getBlock().getLocation());
        if (player == null) return;

        for (ItemStack item : event.getResults()) {
            if (item != null && item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta meta) {
                if (meta.getPersistentDataContainer().has(potionKey, PersistentDataType.BYTE)) {
                    continue;
                }

                var type = new BukkitPotionType(meta);
                var typeString = type.getType().name().toLowerCase(Locale.ROOT);

                if (!Version.isAtLeastVersion(20, 2)) {
                    if (type.isExtended()) {
                        typeString = "long_" + typeString;
                    } else if (type.isUpgraded()) {
                        typeString = "strong_" + typeString;
                    }
                }

                progress(1, meta(typeString));

                meta.getPersistentDataContainer().set(potionKey, PersistentDataType.BYTE, (byte) 1);
                item.setItemMeta(meta);
            }
        }
    }
}
