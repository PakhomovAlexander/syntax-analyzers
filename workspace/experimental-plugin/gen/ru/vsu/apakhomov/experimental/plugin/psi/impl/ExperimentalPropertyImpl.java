// This is a generated file. Not intended for manual editing.
package ru.vsu.apakhomov.experimental.plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalTypes.*;
import ru.vsu.apakhomov.experimental.plugin.psi.*;

public class ExperimentalPropertyImpl extends ExperimentalNamedElementImpl implements ExperimentalProperty {

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

  public String getKey() {
    return ExperimentalPsiImplUtil.getKey(this);
  }

  public String getValue() {
    return ExperimentalPsiImplUtil.getValue(this);
  }

  public String getName() {
    return ExperimentalPsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return ExperimentalPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return ExperimentalPsiImplUtil.getNameIdentifier(this);
  }

}
