package gg.auroramc.quests.api.objective.filter;

import gg.auroramc.quests.api.objective.ObjectiveMeta;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class StringTypeFilter implements ObjectiveFilter {
    private Set<String> types;

    @Override
    public boolean filter(ObjectiveMeta meta) {
        if (types == null || types.isEmpty()) return true;
        var type = meta.getVariable("type", String.class);
        return type.filter(typeId -> types.contains(typeId)).isPresent();
    }
}
