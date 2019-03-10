package ru.vsu.apakhomov.experimental.plugin.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.ExperimentalLang;

public class ExperimentalElementType extends IElementType {
    public ExperimentalElementType(@NotNull @NonNls String debugName) {
        super(debugName, ExperimentalLang.INSTANCE);
    }
}
