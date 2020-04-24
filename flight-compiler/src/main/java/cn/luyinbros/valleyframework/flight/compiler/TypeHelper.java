package cn.luyinbros.valleyframework.flight.compiler;

import com.google.auto.common.MoreTypes;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import static javax.lang.model.element.ElementKind.INTERFACE;

public class TypeHelper {

    public static DeclaredType asDeclared(TypeMirror maybeDeclaredType) {
        return MoreTypes.asDeclared(maybeDeclaredType);
    }

    public static ArrayType asArray(TypeMirror maybeArrayType) {
        return MoreTypes.asArray(maybeArrayType);
    }

    public static boolean isArray(TypeMirror typeMirror, TypeKind typeKind) {
        if (typeMirror.getKind() == TypeKind.ARRAY) {
            return getArrayComponentType(typeMirror).getKind() == typeKind;
        }
        return false;
    }

    public static boolean isArray(TypeMirror typeMirror, String otherType) {
        if (typeMirror.getKind() == TypeKind.ARRAY) {
            return isTypeEqual(getArrayComponentType(typeMirror), otherType);
        }
        return false;
    }


    public static TypeMirror getArrayComponentType(TypeMirror typeMirror) {
        return TypeHelper.asArray(typeMirror).getComponentType();
    }

    public static boolean isSubtypeOfType(TypeMirror typeMirror, String otherType) {
        if (isTypeEqual(typeMirror, otherType)) {
            return true;
        }
        if (typeMirror.getKind() != TypeKind.DECLARED) {
            return false;
        }
        DeclaredType declaredType = (DeclaredType) typeMirror;
        List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
        if (typeArguments.size() > 0) {
            StringBuilder typeString = new StringBuilder(declaredType.asElement().toString());
            typeString.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                if (i > 0) {
                    typeString.append(',');
                }
                typeString.append('?');
            }
            typeString.append('>');
            if (typeString.toString().equals(otherType)) {
                return true;
            }
        }
        Element element = declaredType.asElement();
        if (!(element instanceof TypeElement)) {
            return false;
        }
        TypeElement typeElement = (TypeElement) element;
        TypeMirror superType = typeElement.getSuperclass();
        if (isSubtypeOfType(superType, otherType)) {
            return true;
        }
        for (TypeMirror interfaceType : typeElement.getInterfaces()) {
            if (isSubtypeOfType(interfaceType, otherType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInterface(TypeMirror typeMirror) {
        return typeMirror instanceof DeclaredType
                && ((DeclaredType) typeMirror).asElement().getKind() == INTERFACE;
    }

    public static boolean isTypeEqual(TypeMirror typeMirror, String otherType) {
        return otherType.equals(typeMirror.toString());
    }

    public static boolean isTypeEqualIgnoreTypeVar(TypeMirror typeMirror, String otherType) {
        final String typeFullName = typeMirror.toString();
        if (typeFullName.contains("<")) {
            final String sourceType = typeFullName.substring(0, typeFullName.indexOf("<"));
            return otherType.equals(sourceType);
        } else {
            return isTypeEqual(typeMirror, otherType);
        }

    }


}
