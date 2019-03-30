package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.codeInsight.lookup.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.*;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;

import java.util.*;

public class ExperimentalReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String key;

    public ExperimentalReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<ExperimentalProperty> properties = ExperimentalUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (ExperimentalProperty property : properties) {
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {//todo: this code has never been called...
        Project project = myElement.getProject();
        List<ExperimentalProperty> properties = ExperimentalUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final ExperimentalProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                variants.add(LookupElementBuilder.create(property)
                                                 .withIcon(ExperimentalIcons.FILE)
                                                 .withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}