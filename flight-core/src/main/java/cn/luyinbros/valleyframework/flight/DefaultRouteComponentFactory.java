package cn.luyinbros.valleyframework.flight;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.fragment.app.Fragment;

import java.util.Objects;

class DefaultRouteComponentFactory implements ComponentFactory {


    @Override
    public Component createActivityRouteComponent(Class<?> activityCls) {
        return new ActivityComponent(activityCls);
    }


    private static class ActivityComponent implements Component {
        private final Class<?> cls;

        public ActivityComponent(Class<?> cls) {
            this.cls = cls;
        }

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
        public Intent newIntent(Context context, RouteRequest request) {
            Intent intent = new Intent(context, cls);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            return intent;
        }

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
}
