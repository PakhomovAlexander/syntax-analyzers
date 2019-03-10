package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ExperimentalFileType extends LanguageFileType {
    public static final ExperimentalFileType INSTANCE = new ExperimentalFileType();

    private ExperimentalFileType() {
        super(ExperimentalLang.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Experimental file type";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Experimental language file type";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "exp ";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return ExperimentalIcons.FILE;
    }
}