package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;

import java.util.List;

public class ExperimentalAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiLiteralExpression) {
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
            String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
            final String keyword = "experimental";

            if (value != null && value.startsWith(keyword + ":")) {
                Project project = element.getProject();
                String key = value.substring(keyword.length()+1);
                List<ExperimentalProperty> properties = ExperimentalUtil.findProperties(project, key);
                if (properties.size() == 1) {
                    TextRange range = new TextRange(
                            element.getTextRange().getStartOffset() + keyword.length(),
                            element.getTextRange().getStartOffset() + keyword.length()
                    );
                    Annotation annotation = holder.createInfoAnnotation(range, null);
                    annotation.setTextAttributes(DefaultLanguageHighlighterColors.LINE_COMMENT);
                } else if (properties.size() == 0) {
                    TextRange range = new TextRange(
                            element.getTextRange().getStartOffset() + keyword.length() + 1,
                            element.getTextRange().getEndOffset()
                    );
                    holder.createErrorAnnotation(range, "Unresolved property");
                }
            }
        }
    }
}