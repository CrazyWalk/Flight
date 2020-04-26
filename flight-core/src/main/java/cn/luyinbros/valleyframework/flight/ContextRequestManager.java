package cn.luyinbros.valleyframework.flight;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

class ContextRequestManager extends AbstractRequestManager{
    private final Context context;


    ContextRequestManager(Context context) {
        this.context = context;
    }

    @Override
    protected void route(Component component, RouteRequest request) {
        component.route(context,request);
    }

    @Override
    public Context getContext() {
        return context;
    }
}
