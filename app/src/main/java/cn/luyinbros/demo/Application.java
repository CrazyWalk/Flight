package cn.luyinbros.demo;

import cn.luyinbros.valleyframework.flight.Flight;
import flight.module.AppRouteModule;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Flight.registerModule(this, "AppRouteModule");
    }


}
