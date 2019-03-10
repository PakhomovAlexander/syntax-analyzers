package ru.vsu.apakhomov.experimental.plugin.psi.impl;

import com.intellij.lang.ASTNode;
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
}
