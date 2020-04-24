package cn.luyinbros.valleyframework.flight.compiler;

import com.google.auto.common.MoreElements;

import org.checkerframework.checker.nullness.qual.Nullable;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

/**
 * @see MoreElements
 */
@SuppressWarnings("UnstableApiUsage")
public class ElementHelper {


    public static TypeElement asType(Element element) {
        return MoreElements.asType(element);
    }

    public static ExecutableElement asExecutable(Element element) {
        return MoreElements.asExecutable(element);
    }

    public static VariableElement asVariable(Element element) {
        return MoreElements.asVariable(element);
    }

    @Nullable
    public static TypeElement getSuperClass(TypeElement typeElement) {
        TypeMirror type = typeElement.getSuperclass();
        if (type.getKind() == TypeKind.NONE) {
            return null;
        }
        return (TypeElement) ((DeclaredType) type).asElement();
    }
}
