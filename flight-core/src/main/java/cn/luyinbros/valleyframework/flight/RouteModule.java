package cn.luyinbros.valleyframework.flight;

import android.app.Application;

public abstract class RouteModule {
    private final Application application;

    public RouteModule(Application application) {
        this.application = application;
    }

    public abstract void init(Flight flight);
}
