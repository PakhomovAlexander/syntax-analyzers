package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.lang.Language;

public class ExperimentalLang extends Language {
    public static final ExperimentalLang INSTANCE = new ExperimentalLang();

    private ExperimentalLang() {
        super("ExperimentalLang");
    }
}
