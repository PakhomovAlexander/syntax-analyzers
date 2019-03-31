package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public interface ExperimentalService {
    static ExperimentalService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, ExperimentalService.class);
    }

    String getLanguageName();
}
