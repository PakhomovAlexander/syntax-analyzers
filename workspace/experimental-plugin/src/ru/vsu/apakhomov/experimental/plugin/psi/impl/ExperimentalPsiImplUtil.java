package ru.vsu.apakhomov.experimental.plugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalElementFactory;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalTypes;

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
}
