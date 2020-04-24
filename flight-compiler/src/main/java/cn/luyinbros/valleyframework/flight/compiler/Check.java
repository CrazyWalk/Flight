package cn.luyinbros.valleyframework.flight.compiler;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import static javax.lang.model.element.ElementKind.CLASS;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.STATIC;

public class Check {

    public static boolean isInvalidateRouteComponent(Element element) {
        if (element.getKind() != CLASS) {
            CompileMessager.error(element, "routeComponent only be class");
            return true;
        }
        TypeMirror typeMirror = element.asType();
        if (!(TypeHelper.isSubtypeOfType(typeMirror, Constants.TYPE_ACTIVITY) ||
                TypeHelper.isSubtypeOfType(typeMirror, Constants.TYPE_ROUTE_COMPONENT))) {
            CompileMessager.error(element, "routeComponent only support activity Component");
            return true;
        }

        return false;
    }

    public static boolean isInvalidateRouteComponentFactory(Element element) {
        if (element.getKind() != CLASS) {
            CompileMessager.error(element, "routeComponent only be class");
            return true;
        }
        TypeMirror typeMirror = element.asType();
        if (!TypeHelper.isSubtypeOfType(typeMirror, Constants.TYPE_ROUTE_COMPONENT_FACTORY)) {
            CompileMessager.error(element, "routeComponent only support activity fragment Component");
            return true;
        }
        return false;
    }

    public static boolean isInvalidateRouteInterceptor(Element element) {
        if (element.getKind() != CLASS) {
            CompileMessager.error(element, "routeComponent only be class");
            return true;
        }
        return false;
    }


    private static boolean isInaccessibleElement(Element element,
                                                 Class<? extends Annotation> annotationClass,
                                                 String targetThing) {
        Set<Modifier> modifiers = element.getModifiers();
        if (modifiers.contains(PRIVATE) || modifiers.contains(STATIC)) {
            TypeElement enclosingElement = ElementHelper.asType(element);
            CompileMessager.error(element, "@%s %s must not be private or static. (%s.%s)",
                    annotationClass.getSimpleName(), targetThing, enclosingElement.getQualifiedName(),
                    element.getSimpleName());
            return true;
        } else {
            return false;
        }

    }
}
