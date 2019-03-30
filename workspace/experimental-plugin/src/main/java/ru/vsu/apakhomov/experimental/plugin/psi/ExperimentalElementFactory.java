package ru.vsu.apakhomov.experimental.plugin.psi;


import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import ru.vsu.apakhomov.experimental.plugin.ExperimentalFileType;

public class ExperimentalElementFactory {
    public static ExperimentalProperty createProperty(Project project, String name, String value) {
        final ExperimentalFile file = createFile(project, name + " = " + value);
        return (ExperimentalProperty) file.getFirstChild();
    }

    public static ExperimentalProperty createProperty(Project project, String name) {
        final ExperimentalFile file = createFile(project, name);
        return (ExperimentalProperty) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final ExperimentalFile file = createFile(project, "\n");
        return file.getFirstChild();
    }
    
    public static ExperimentalFile createFile(Project project, String text) {
        String name = "dummy.exp";
        return (ExperimentalFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, ExperimentalFileType.INSTANCE, text);
    }
}
