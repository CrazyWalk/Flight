package cn.luyinbros.valleyframework.flight.compiler;


import com.squareup.javapoet.ClassName;

public class Constants {
    private static final String LIB = "cn.luyinbros.valleyframework.flight.";

    public static final String TYPE_ACTIVITY = "android.app.Activity";
    public static final String TYPE_FRAGMENT = "androidx.fragment.app.Fragment";
    public static final String TYPE_ROUTE_COMPONENT = LIB + "Component";
    public static final String TYPE_ROUTE_COMPONENT_FACTORY = LIB + "ComponentFactory";
    public static final String TYPE_APPLICATION = "android.app.Application";


    public static final ClassName CLASS_ROUTE_MODULE = ClassName.bestGuess(LIB + "RouteModule");
    public static final ClassName CLASS_APPLICATION = ClassName.bestGuess(TYPE_APPLICATION);
    public static final ClassName CLASS_FLIGHT = ClassName.bestGuess(LIB + "Flight");
}
