package ru.vsu.apakhomov.experimental.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalFile;
import ru.vsu.apakhomov.experimental.plugin.psi.ExperimentalProperty;

import java.util.*;

public class ExperimentalUtil {
    public static List<ExperimentalProperty> findProperties(Project project, String key) {
        List<ExperimentalProperty> result = null;
        Collection<VirtualFile> virtualFiles =
                FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, ExperimentalFileType.INSTANCE,
                                                                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            ExperimentalProperty simpleFile = (ExperimentalProperty) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                ExperimentalProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, ExperimentalProperty.class);
                if (properties != null) {
                    for (ExperimentalProperty property : properties) {
                        if (key.equals(property.getKey())) {
                            if (result == null) {
                                result = new ArrayList<ExperimentalProperty>();
                            }
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<ExperimentalProperty>emptyList();
    }

    public static List<ExperimentalProperty> findProperties(Project project) {
        List<ExperimentalProperty> result = new ArrayList<ExperimentalProperty>();
        Collection<VirtualFile> virtualFiles =
                FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, ExperimentalFileType.INSTANCE,
                                                                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            ExperimentalFile simpleFile = (ExperimentalFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                ExperimentalProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, ExperimentalProperty.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }
}
