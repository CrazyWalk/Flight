package cn.luyinbros.valleyframework.flight;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

class ActivityRequestManager extends AbstractRequestManager {
    private final Activity activity;

    ActivityRequestManager(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void route(Component component, RouteRequest request) {
        component.route(activity,request);
    }

    @Override
    public Context getContext() {
        return activity;
    }
}
