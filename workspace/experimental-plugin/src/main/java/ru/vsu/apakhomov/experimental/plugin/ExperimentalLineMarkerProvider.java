package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;

import java.util.Collection;
import java.util.List;

public class ExperimentalLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            Collection<? super RelatedItemLineMarkerInfo> result) {
        if (element instanceof PsiLiteralExpression) {
            final String keyword = "experimental";
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
            String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;

            if (value != null && value.startsWith(keyword + ":")) {
                Project project = element.getProject();
                final List<ExperimentalProperty> properties = ExperimentalUtil.findProperties(project, value.substring(keyword.length()+1));
                if (properties.size() > 0) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder.create(ExperimentalIcons.FILE).
                                    setTargets(properties).setTooltipText("Navigate to an experimental property");
                    result.add(builder.createLineMarkerInfo(element));
                }
            }
        }
    }
}