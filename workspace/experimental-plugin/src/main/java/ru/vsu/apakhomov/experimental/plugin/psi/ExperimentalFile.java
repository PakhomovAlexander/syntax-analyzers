package ru.vsu.apakhomov.experimental.plugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.ExperimentalFileType;
import ru.vsu.apakhomov.experimental.plugin.ExperimentalLang;

import javax.swing.*;

public class ExperimentalFile extends PsiFileBase {
    public ExperimentalFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, ExperimentalLang.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return ExperimentalFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Experimental File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}