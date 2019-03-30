package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class ExperimentalLexerAdapter extends FlexAdapter {
    public ExperimentalLexerAdapter() {
        super(new ExperimentalLexer((Reader) null));
    }
}
