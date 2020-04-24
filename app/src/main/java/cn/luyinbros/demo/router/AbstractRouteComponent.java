package cn.luyinbros.demo.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import cn.luyinbros.valleyframework.flight.Component;
import cn.luyinbros.valleyframework.flight.RouteRequest;

public abstract class AbstractRouteComponent implements Component {

    @Override
    public void route(Context context, RouteRequest request) {
        context.startActivity(newIntent(context, request));
    }

    @Override
    public void route(Activity activity, RouteRequest request) {
        activity.startActivity(newIntent(activity, request));
    }

    @Override
    public void route(Fragment fragment, RouteRequest request) {
        fragment.startActivity(newIntent(fragment.requireActivity(), request));
    }

    @Override
    public abstract Intent newIntent(Context context, RouteRequest request);
}
