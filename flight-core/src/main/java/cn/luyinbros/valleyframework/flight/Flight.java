package cn.luyinbros.valleyframework.flight;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private RouteComponentProvider routeComponentProvider = new RouteComponentProvider();
    private ComponentFactory routeComponentFactory;
    private List<Interceptor> interceptors = new ArrayList<>();
    private volatile static Flight INSTANCE;
    private Application application;


    private Flight(Application application) {
        this.application = application;
    }


    public static void registerModule(RouteModule... routeModules) {
        Application application = routeModules[0].getApplication();
        Flight flight = get(application);
        for (RouteModule routeModule : routeModules) {
            flight._registerModule(routeModule);
        }
    }

    public static void registerModule(Application application, String... classNames) {
        Flight flight = get(application);
        for (String s : classNames) {
            try {
                Class<?> cls = Class.forName("flight.module." + s);
                RouteModule routeModule = (RouteModule) cls.getConstructors()[0].newInstance(application);
                flight._registerModule(routeModule);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    public static RequestManager with(Context context) {
        return new ContextRequestManager(context);
    }

    public static RequestManager with(Activity activity) {
        return new ActivityRequestManager(activity);
    }

    public static RequestManager with(Fragment fragment) {
        return new FragmentRequestManager(fragment);
    }

    public void registerComponent(String path, Component component) {
        routeComponentProvider.register(path, component);
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public void setRouteComponentFactory(ComponentFactory factory) {
        this.routeComponentFactory = factory;
    }

    public RouteComponentProvider getRouteComponentProvider() {
        return routeComponentProvider;
    }


    public ComponentFactory getRouteComponentFactory() {
        if (routeComponentFactory == null) {
            routeComponentFactory = new DefaultRouteComponentFactory();
        }
        return routeComponentFactory;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }


    static Flight get(Context context) {
        if (INSTANCE == null) {
            synchronized (Flight.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Flight((Application) context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }


    private void _registerModule(RouteModule routeModule) {
        routeModule.init(this);
    }

}
