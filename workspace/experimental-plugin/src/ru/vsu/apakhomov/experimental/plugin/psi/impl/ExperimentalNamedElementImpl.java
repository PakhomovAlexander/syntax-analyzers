package ru.vsu.apakhomov.experimental.plugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalNamedElement;

public abstract class ExperimentalNamedElementImpl extends ASTWrapperPsiElement implements ExperimentalNamedElement {
    public ExperimentalNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
