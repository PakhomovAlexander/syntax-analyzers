package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalFile;

public class ExperimentalStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
    public ExperimentalStructureViewModel(PsiFile psiFile) {
        super(psiFile, new ExperimentalStructureViewElement(psiFile));
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }


    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element instanceof ExperimentalFile;
    }
}
