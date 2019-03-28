package ru.vsu.apakhomov.experimental.plugin.psi;


import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import ru.vsu.apakhomov.experimental.plugin.ExperimentalFileType;

public class ExperimentalElementFactory {
    public static ExperimentalProperty createProperty(Project project, String name) {
        final ExperimentalFile file = createFile(project, name);
        return (ExperimentalProperty) file.getFirstChild();
    }

    public static ExperimentalFile createFile(Project project, String text) {
        String name = "dummy.exp";
        return (ExperimentalFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, ExperimentalFileType.INSTANCE, text);
    }
}
