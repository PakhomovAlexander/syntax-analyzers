package ru.vsu.apakhomov.experimental.plugin;
import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.parser.ExperimentalParser;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalFile;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalTypes;

public class ExperimentalParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(ExperimentalTypes.COMMENT);

    public static final IFileElementType FILE = new IFileElementType(ExperimentalLang.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new ExperimentalLexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new ExperimentalParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new ExperimentalFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return ExperimentalTypes.Factory.createElement(node);
    }
}
