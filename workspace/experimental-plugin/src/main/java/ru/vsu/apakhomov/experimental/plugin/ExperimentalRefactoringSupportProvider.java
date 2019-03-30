package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;

public class ExperimentalRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
        return element instanceof ExperimentalProperty;
    }
}
