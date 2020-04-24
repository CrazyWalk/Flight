package cn.luyinbros.valleyframework.flight.compiler;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

public class CompileMessager {
    private static Messager mMessager;

    public static void setMessager(Messager messager) {
        mMessager = messager;
    }

    public static void warn(String message) {
        if (mMessager != null) {
            mMessager.printMessage(Diagnostic.Kind.WARNING, "\n" + message);
        }

    }

    public static void note(String msg) {
        if (mMessager != null) {
            mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
        }
    }

    public static void error(Element element, String message, Object... args) {
        printMessage(Diagnostic.Kind.ERROR, element, message, args);
    }

    public static void printMessage(Diagnostic.Kind kind, Element element, String message, Object[] args) {
        if (mMessager != null) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            mMessager.printMessage(kind, message, element);
        }

    }
}
