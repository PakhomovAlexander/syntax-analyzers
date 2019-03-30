package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalElementFactory;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalFile;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;

import java.util.Arrays;
import java.util.Collection;

public class CreatePropertyQuickFix extends BaseIntentionAction {
    private String key;

    CreatePropertyQuickFix(String key) {
        this.key = key;
    }

    @NotNull
    @Override
    public String getText() {
        Project project = StreamEx.of(ProjectManager.getInstance().getOpenProjects())
                                  .filter(project1 -> project1.getName().contains("experimental"))
                                  .findFirst().orElse(ProjectManager.getInstance().getDefaultProject());

        ExperimentalService projectService = ServiceManager.getService(project, ExperimentalService.class);

        //only for learning services purposes
        return "Create " + projectService.getLanguageName() + " property";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return  "Experimental properties";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws
            IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            Collection<VirtualFile> virtualFiles =
                    FileTypeIndex.getFiles(ExperimentalFileType.INSTANCE, GlobalSearchScope.allScope(project));
            if (virtualFiles.size() == 1) {
                createProperty(project, virtualFiles.iterator().next());
            } else {
                final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(ExperimentalFileType.INSTANCE);
                descriptor.setRoots(ProjectUtil.guessProjectDir(project));
                final VirtualFile file1 = FileChooser.chooseFile(descriptor, project, null);
                if (file1 != null) {
                    createProperty(project, file1);
                }
            }
        });
    }

    private void createProperty(final Project project, final VirtualFile file) {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            ExperimentalFile simpleFile = (ExperimentalFile) PsiManager.getInstance(project).findFile(file);
            ASTNode lastChildNode = simpleFile.getNode().getLastChildNode();
            // TODO: Add another check for CRLF
            if (lastChildNode != null/* && !lastChildNode.getElementType().equals(ExperimentalTypes.CRLF)*/) {
                simpleFile.getNode().addChild(ExperimentalElementFactory.createCRLF(project).getNode());
            }
            // IMPORTANT: change spaces to escaped spaces or the new node will only have the first word for the key
            ExperimentalProperty property = ExperimentalElementFactory.createProperty(project, key.replaceAll(" ", "\\\\ "), "");
            simpleFile.getNode().addChild(property.getNode());
            ((Navigatable) property.getLastChild().getNavigationElement()).navigate(true);
            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
        });
    }
}

