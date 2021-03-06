package cn.luyinbros.valleyframework.flight.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import org.omg.PortableInterceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;

import afu.org.checkerframework.checker.nullness.qual.Nullable;
import sun.awt.ComponentFactory;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

public class RouteModuleSet {
    private List<Component> componentList = new ArrayList<>();
    private ComponentFactory componentFactory;
    private List<Interceptor> interceptors = new ArrayList<>();
    private final String moduleName;
    private static final String packageName = "flight.module";

    public RouteModuleSet(String moduleName) {
        this.moduleName = moduleName;
    }


    public void addComponent(Component component) {
        componentList.add(component);
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public void setComponentFactory(ComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
    }


    JavaFile brewJava() {
        final TypeSpec bindingConfiguration = createTypeSpec();
        return JavaFile.builder(packageName, bindingConfiguration)
                .addFileComment("Generated code . Do not modify!")
                .build();
    }

    private TypeSpec createTypeSpec() {
        TypeSpec.Builder result = TypeSpec.classBuilder(moduleName)
                .addModifiers(PUBLIC, FINAL)
                .superclass(Constants.CLASS_ROUTE_MODULE);

        {
            result.addMethod(MethodSpec.constructorBuilder()
                    .addModifiers(PUBLIC)
                    .addParameter(Constants.CLASS_APPLICATION, "application")
                    .addStatement("super(application)")
                    .build());
        }


        {

            MethodSpec.Builder builder = MethodSpec.methodBuilder("init")
                    .addModifiers(PUBLIC)
                    .addParameter(Constants.CLASS_FLIGHT, "flight");

            if (componentFactory != null) {
                builder.addCode(componentFactory.code());
            }

            for (Interceptor interceptor:interceptors){
                builder.addCode(interceptor.code());
            }


            builder.addStatement("final $L routeComponentFactory=flight.getRouteComponentFactory()",
                    Constants.TYPE_ROUTE_COMPONENT_FACTORY);

            for (Component component : componentList) {
                builder.addCode(component.code());
            }

            result.addMethod(builder.build());
        }


        return result.build();
    }

    public enum ComponentType {
        ACTIVITY,
        FRAGMENT,
        OTHER
    }

    public static class Component {
        public String path;
        public ComponentType type;
        public ClassName className;
        public boolean hasApplication;

        public CodeBlock code() {
            if (type == ComponentType.ACTIVITY) {
                return CodeBlock.builder()
                        .addStatement("flight.registerComponent($S,routeComponentFactory.createActivityRouteComponent($L.class))",
                                path,
                                className)
                        .build();
            } else {
                if (hasApplication){
                    return CodeBlock.builder()
                            .addStatement("flight.registerComponent($S,new $L(mApplication))",
                                    path,
                                    className)
                            .build();
                }

                return CodeBlock.builder()
                        .addStatement("flight.registerComponent($S,new $L())",
                                path,
                                className)
                        .build();
            }

        }

    }

    public static class ComponentFactory {
        public ClassName className;
        public boolean hasApplication = false;


        public CodeBlock code() {
            if (hasApplication) {
                return CodeBlock.builder()
                        .addStatement("flight.setRouteComponentFactory(new $L(mApplication))",
                                className)
                        .build();
            } else {
                return CodeBlock.builder()
                        .addStatement("flight.setRouteComponentFactory(new $L())",
                                className)
                        .build();
            }
        }
    }


    public static class Interceptor {
        public ClassName className;
        public boolean hasApplication = false;

        public CodeBlock code() {
            if (hasApplication) {
                return CodeBlock.builder()
                        .addStatement("flight.addInterceptor(new $L(mApplication))",
                                className)
                        .build();
            } else {
                return CodeBlock.builder()
                        .addStatement("flight.addInterceptor(new $L())",
                                className)
                        .build();
            }
        }
    }

}
