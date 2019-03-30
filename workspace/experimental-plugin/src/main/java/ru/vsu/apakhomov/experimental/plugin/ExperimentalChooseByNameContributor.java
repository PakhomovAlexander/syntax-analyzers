package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.navigation.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;
import ru.vsu.apakhomov.experimental.plugin.psi.impl.ExperimentalPropertyImpl;

import java.util.*;

public class ExperimentalChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<ExperimentalProperty> properties = ExperimentalUtil.findProperties(project);
        List<String> names = new ArrayList<String>(properties.size());
        for (ExperimentalProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                names.add(property.getKey());
            }
        }
        return names.toArray(new String[names.size()]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // todo include non project items
        List<ExperimentalProperty> properties = ExperimentalUtil.findProperties(project, name);
        return properties.toArray(new NavigationItem[properties.size()]);
    }
}
