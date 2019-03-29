package ru.vsu.apakhomov.experimental.plugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import ru.vsu.apakhomov.experimental.plugin.ExperimentalIcons;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalElementFactory;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalTypes;

import javax.swing.*;

public class ExperimentalPsiImplUtil {
    public static String getKey(ExperimentalProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(ExperimentalTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(ExperimentalProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(ExperimentalTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(ExperimentalProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(ExperimentalProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(ExperimentalTypes.KEY);
        if (keyNode != null) {

            ExperimentalProperty property = ExperimentalElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(ExperimentalProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(ExperimentalTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final ExperimentalProperty element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return element.getContainingFile().getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return ExperimentalIcons.FILE;//todo: this code has never been called...
            }
        };
    }
}
