package cn.luyinbros.demo.router;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import cn.luyinbros.valleyframework.flight.Component;
import cn.luyinbros.valleyframework.flight.RouteRequest;

public abstract class AbstractRouteComponent implements Component {

    @Override
    public void route(Context context, RouteRequest request) {
        Activity activity = findActivity(context);
        if (activity != null) {
            route(activity, request);
        } else {
            context.startActivity(newIntent(context, request));
        }

    }

    @Override
    public void route(Activity activity, RouteRequest request) {
        if (request.requestCode() >= 1) {
            activity.startActivityForResult(newIntent(activity, request), request.requestCode(), request.options());
        } else {
            activity.startActivity(newIntent(activity, request), request.options());
        }
    }

    @Override
    public void route(Fragment fragment, RouteRequest request) {
        if (request.requestCode() >= 1) {
            fragment.startActivityForResult(newIntent(fragment.requireActivity(), request), request.requestCode(), request.options());
        } else {
            fragment.startActivity(newIntent(fragment.requireActivity(), request), request.options());
        }
    }

    @Override
    public abstract Intent newIntent(Context context, RouteRequest request);

    private static Activity findActivity(@NonNull Context context) {

        Objects.requireNonNull(context);
        if (context instanceof Activity) {
            return (Activity) context;
        } else {
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            return null;
        }

    }
}
