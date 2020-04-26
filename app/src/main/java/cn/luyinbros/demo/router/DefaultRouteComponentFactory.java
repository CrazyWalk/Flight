package cn.luyinbros.demo.router;


import android.app.Application;


import cn.luyinbros.valleyframework.flight.Component;
import cn.luyinbros.valleyframework.flight.ComponentFactory;



//@RouteComponentFactory
public class DefaultRouteComponentFactory implements ComponentFactory {

    public DefaultRouteComponentFactory(Application application) {

    }

    @Override
    public Component createActivityRouteComponent(Class<?> activityCls) {
        return null;
    }
}
