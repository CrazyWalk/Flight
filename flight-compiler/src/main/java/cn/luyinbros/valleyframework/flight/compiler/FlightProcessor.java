package cn.luyinbros.valleyframework.flight.compiler;

import com.google.auto.common.SuperficialValidation;
import com.google.auto.service.AutoService;

import net.ltgt.gradle.incap.IncrementalAnnotationProcessor;
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import cn.luyinbros.valleyframework.flight.annotation.RouteComponent;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponentFactory;
import cn.luyinbros.valleyframework.flight.annotation.RouteInterceptor;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.DYNAMIC)
@AutoService(Processor.class)
public class FlightProcessor extends AbstractProcessor {
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

        CompileMessager.setMessager(processingEnv.getMessager());
        Map<String, String> options = processingEnv.getOptions();
        moduleName = options.get("routeModule");


    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {

        for (Element element : env.getElementsAnnotatedWith(RouteComponent.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            try {
                parseRouteComponent(element);
            } catch (Exception e) {
                error(element, e);
            }
        }

        for (Element element : env.getElementsAnnotatedWith(RouteComponentFactory.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            try {
                parseRouteComponentFactory(element);
            } catch (Exception e) {
                error(element, e);
            }
        }

        for (Element element : env.getElementsAnnotatedWith(RouteInterceptor.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            try {
                parseRouteInterceptor(element);
            } catch (Exception e) {
                error(element, e);
            }
        }

        return false;
    }

    private void parseRouteComponent(Element element) {


    }

    private void parseRouteComponentFactory(Element element) {

    }


    private void parseRouteInterceptor(Element element) {

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
