// This is a generated file. Not intended for manual editing.
package ru.vsu.apakhomov.experimental.plugin.psi;

import java.util.List;

import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface ExperimentalProperty extends ExperimentalNamedElement {

  String getKey();

  String getValue();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

  ItemPresentation getPresentation();

  @NotNull
  @Override
  PsiReference[] getReferences();
}
