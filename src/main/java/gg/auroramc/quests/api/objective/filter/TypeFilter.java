package gg.auroramc.quests.api.objective.filter;

import gg.auroramc.aurora.api.item.TypeId;
import gg.auroramc.quests.api.objective.ObjectiveMeta;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class TypeFilter implements ObjectiveFilter {
    private Set<TypeId> types;

    @Override
    public boolean filter(ObjectiveMeta meta) {
        if (types == null || types.isEmpty()) return true;
        var type = meta.getVariable("type", TypeId.class);
        return type.filter(typeId -> types.contains(typeId)).isPresent();
    }
}
