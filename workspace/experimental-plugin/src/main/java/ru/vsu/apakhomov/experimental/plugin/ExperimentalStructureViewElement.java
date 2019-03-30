package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalFile;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;
import ru.vsu.apakhomov.experimental.plugin.psi.impl.ExperimentalPropertyImpl;

import java.util.ArrayList;
import java.util.List;

public class ExperimentalStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private NavigatablePsiElement element;

    public ExperimentalStructureViewElement(NavigatablePsiElement element) {
        this.element = element;
    }

    @Override
    public Object getValue() {
        return element;
    }

    @Override
    public void navigate(boolean requestFocus) {
        element.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return element.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return element.canNavigateToSource();
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = element.getName();
        return name != null ? name : "";
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        ItemPresentation presentation = element.getPresentation();
        return presentation != null ? presentation : new PresentationData();
    }

    @Override
    public TreeElement[] getChildren() {
        if (element instanceof ExperimentalFile) {
            ExperimentalProperty[] properties = PsiTreeUtil.getChildrenOfType(element, ExperimentalProperty.class);
            List<TreeElement> treeElements = new ArrayList<TreeElement>(properties.length);
            for (ExperimentalProperty property : properties) {
                treeElements.add(new ExperimentalStructureViewElement((ExperimentalPropertyImpl) property));
            }
            return treeElements.toArray(new TreeElement[treeElements.size()]);
        } else {
            return EMPTY_ARRAY;
        }
    }
}
