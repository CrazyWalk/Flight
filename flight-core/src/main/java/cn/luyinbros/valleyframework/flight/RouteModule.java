package cn.luyinbros.valleyframework.flight;

import android.app.Application;

public abstract class RouteModule {
    protected final Application mApplication;

    public RouteModule(Application application) {
        this.mApplication = application;
    }

    public abstract void init(Flight flight);

    public Application getApplication(){
        return mApplication;
    }
}
