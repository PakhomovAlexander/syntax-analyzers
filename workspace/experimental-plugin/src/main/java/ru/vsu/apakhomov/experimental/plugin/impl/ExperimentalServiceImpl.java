package ru.vsu.apakhomov.experimental.plugin.impl;

import ru.vsu.apakhomov.experimental.plugin.ExperimentalService;
import com.intellij.openapi.project.Project;

public class ExperimentalServiceImpl implements ExperimentalService {
    private final Project project;

    public ExperimentalServiceImpl(Project project) {
        this.project = project;
    }

    @Override
    public String getLanguageName() {
        return "Experimental";
    }
}
