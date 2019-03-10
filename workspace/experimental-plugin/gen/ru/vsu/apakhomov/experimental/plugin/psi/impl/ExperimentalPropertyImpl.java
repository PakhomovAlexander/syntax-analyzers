// This is a generated file. Not intended for manual editing.
package ru.vsu.apakhomov.experimental.plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ru.vsu.apakhomov.experimental.plugin.psi.*;

public class ExperimentalPropertyImpl extends ASTWrapperPsiElement implements ExperimentalProperty {

  public ExperimentalPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ExperimentalVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ExperimentalVisitor) accept((ExperimentalVisitor)visitor);
    else super.accept(visitor);
  }

}
