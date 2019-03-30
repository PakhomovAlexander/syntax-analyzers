package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalTypes;

public class ExperimentalFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(
                new ExperimentalLexerAdapter(),
                TokenSet.create(ExperimentalTypes.KEY),
                TokenSet.create(ExperimentalTypes.COMMENT),
                TokenSet.EMPTY
        );
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof PsiNamedElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof ExperimentalProperty) {
            return "experimental property";
        }

        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof ExperimentalProperty) {
            return ((ExperimentalProperty) element).getKey();
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof ExperimentalProperty) {
            return ((ExperimentalProperty) element).getKey() + ":" + ((ExperimentalProperty) element).getValue();
        } else {
            return "";
        }
    }
}
