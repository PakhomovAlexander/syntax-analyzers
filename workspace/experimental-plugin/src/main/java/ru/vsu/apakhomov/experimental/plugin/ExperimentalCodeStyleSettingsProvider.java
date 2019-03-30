package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExperimentalCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new ExperimentalCodeStyleSettings(settings);
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return "Experimental";
    }


    @NotNull
    public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings, @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {
            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new ExperimentalCodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    private static class ExperimentalCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        public ExperimentalCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(ExperimentalLang.INSTANCE, currentSettings, settings);
        }
    }
}