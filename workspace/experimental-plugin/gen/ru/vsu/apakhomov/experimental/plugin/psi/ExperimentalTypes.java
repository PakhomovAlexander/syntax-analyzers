// This is a generated file. Not intended for manual editing.
package ru.vsu.apakhomov.experimental.plugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ru.vsu.apakhomov.experimental.plugin.psi.impl.*;

public interface ExperimentalTypes {

  IElementType PROPERTY = new ExperimentalElementType("PROPERTY");

  IElementType COMMENT = new ExperimentalTokenType("COMMENT");
  IElementType CRLF = new ExperimentalTokenType("CRLF");
  IElementType KEY = new ExperimentalTokenType("KEY");
  IElementType SEPARATOR = new ExperimentalTokenType("SEPARATOR");
  IElementType VALUE = new ExperimentalTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new ExperimentalPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
