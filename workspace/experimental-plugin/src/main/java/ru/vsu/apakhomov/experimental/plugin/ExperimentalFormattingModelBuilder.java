package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.*;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalTypes;

public class ExperimentalFormattingModelBuilder implements FormattingModelBuilder {
    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        return FormattingModelProvider
                .createFormattingModelForPsiFile(element.getContainingFile(),
                                                 new ExperimentalBlock(element.getNode(),
                                                                 Wrap.createWrap(WrapType.NONE, false),
                                                                 Alignment.createAlignment(),
                                                                 createSpaceBuilder(settings)),
                                                 settings);
    }

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, ExperimentalLang.INSTANCE)
                .around(ExperimentalTypes.SEPARATOR)
                .spaceIf(settings.getCommonSettings(ExperimentalLang.INSTANCE.getID()).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(ExperimentalTypes.PROPERTY)
                .none();
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}
