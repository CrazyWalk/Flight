package cn.luyinbros.valleyframework.flight.compiler;

import com.google.auto.common.SuperficialValidation;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import cn.luyinbros.valleyframework.flight.annotation.RouteComponent;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponentFactory;
import cn.luyinbros.valleyframework.flight.annotation.RouteInterceptor;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
//@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.DYNAMIC)
@AutoService(Processor.class)
public class FlightProcessor extends AbstractProcessor {
    private Filer mFilter;
    private String moduleName = "";


    //    @Override
//    public Set<String> getSupportedOptions() {
//        Set<String> result = new HashSet<>();
//        if (trees != null) {
//            result.add(IncrementalAnnotationProcessorType.ISOLATING.getProcessorOption());
//        }
//        return result;
//    }
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<Class<? extends Annotation>> annotationCls = new HashSet<>();
        annotationCls.add(RouteComponent.class);
        annotationCls.add(RouteComponentFactory.class);
        annotationCls.add(RouteInterceptor.class);
        Set<String> result = new HashSet<>();
        for (Class<? extends Annotation> cls : annotationCls) {
            result.add(cls.getCanonicalName());
        }
        return result;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFilter = processingEnv.getFiler();
        CompileMessager.setMessager(processingEnv.getMessager());
        Map<String, String> options = processingEnv.getOptions();
        moduleName = options.get("flightModule");

    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        RouteModuleSet routeModuleSet = new RouteModuleSet(moduleName);
        for (Element element : env.getElementsAnnotatedWith(RouteComponent.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            try {
                parseRouteComponent(routeModuleSet, element);
            } catch (Exception e) {
                error(element, e);
            }
        }

        for (Element element : env.getElementsAnnotatedWith(RouteComponentFactory.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            try {
                parseRouteComponentFactory(routeModuleSet,element);
            } catch (Exception e) {
                error(element, e);
            }
        }

        for (Element element : env.getElementsAnnotatedWith(RouteInterceptor.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            try {
                parseRouteInterceptor(routeModuleSet,element);
            } catch (Exception e) {
                error(element, e);
            }
        }
        JavaFile javaFile = routeModuleSet.brewJava();
        try {
            javaFile.writeTo(mFilter);
        } catch (IOException e) {
            // CompileMessager.error(typeElement, "Unable to write binding for type %s: %s", typeElement, e.getMessage());
        }
        return false;
    }

    private void parseRouteComponent(RouteModuleSet routeModuleSet, Element element) {
        if (Check.isInvalidateRouteComponent(element)) {
            return;
        }

        RouteModuleSet.Component component = new RouteModuleSet.Component();
        RouteComponent routeComponent = element.getAnnotation(RouteComponent.class);
        component.path = routeComponent.value();

        TypeMirror typeMirror = element.asType();

        if (TypeHelper.isSubtypeOfType(typeMirror, Constants.TYPE_ACTIVITY)) {
            component.type = RouteModuleSet.ComponentType.ACTIVITY;
        } else {
            component.type = RouteModuleSet.ComponentType.OTHER;
        }
        component.className = ClassName.get((TypeElement) element);

        {
            TypeElement typeElement = ElementHelper.asType(element);

            for (Element enclosedElement : typeElement.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
                    ExecutableElement executableElement = ElementHelper.asExecutable(enclosedElement);
                    List<? extends VariableElement> vars = executableElement.getParameters();
                    if (vars.size() == 1) {
                        VariableElement variableElement = vars.get(0);
                        if (TypeHelper.isTypeEqual(variableElement.asType(), Constants.TYPE_APPLICATION)) {
                            component.hasApplication = true;
                        }
                    }
                    break;
                }
            }
        }
        routeModuleSet.addComponent(component);

    }

    private void parseRouteComponentFactory(RouteModuleSet routeModuleSet, Element element) {
        if (Check.isInvalidateRouteComponentFactory(element)) {
            return;
        }
        RouteModuleSet.ComponentFactory factory = new RouteModuleSet.ComponentFactory();
        factory.className = ClassName.get((TypeElement) element);
        TypeElement typeElement = ElementHelper.asType(element);

        for (Element enclosedElement : typeElement.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement executableElement = ElementHelper.asExecutable(enclosedElement);
                List<? extends VariableElement> vars = executableElement.getParameters();
                if (vars.size() == 1) {
                    VariableElement variableElement = vars.get(0);
                    if (TypeHelper.isTypeEqual(variableElement.asType(), Constants.TYPE_APPLICATION)) {
                        factory.hasApplication = true;
                    }
                }
                break;
            }
        }
        routeModuleSet.setComponentFactory(factory);

    }


    private void parseRouteInterceptor(RouteModuleSet routeModuleSet,Element element) {
        if (Check.isInvalidateRouteInterceptor(element)) {
            return;
        }
        RouteModuleSet.Interceptor interceptor = new RouteModuleSet.Interceptor();
        interceptor.className = ClassName.get((TypeElement) element);
        TypeElement typeElement = ElementHelper.asType(element);

        for (Element enclosedElement : typeElement.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement executableElement = ElementHelper.asExecutable(enclosedElement);
                List<? extends VariableElement> vars = executableElement.getParameters();
                if (vars.size() == 1) {
                    VariableElement variableElement = vars.get(0);
                    if (TypeHelper.isTypeEqual(variableElement.asType(), Constants.TYPE_APPLICATION)) {
                        interceptor.hasApplication = true;
                    }
                }
                break;
            }
        }
        routeModuleSet.addInterceptor(interceptor);
    }


    private static void error(Element element, Throwable e) {
//        StackTraceElement[] stackTraceElementArray = e.getStackTrace();
//        StringBuilder sb = new StringBuilder();
//        for (StackTraceElement stackTraceElement : stackTraceElementArray) {
//            sb.append(stackTraceElement).append("\n");
//        }
//        CompileMessager.warn(e + " " + sb.toString());
        CompileMessager.error(element, "invalidate " + e.getMessage());
    }

}
