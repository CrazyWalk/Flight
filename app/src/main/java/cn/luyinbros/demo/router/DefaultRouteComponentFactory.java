package cn.luyinbros.demo.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import cn.luyinbros.valleyframework.flight.Component;
import cn.luyinbros.valleyframework.flight.ComponentFactory;
import cn.luyinbros.valleyframework.flight.RouteRequest;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponentFactory;


@RouteComponentFactory
public class DefaultRouteComponentFactory implements ComponentFactory {

    @Override
    public Component createActivityRouteComponent(Class<? extends Activity> cls) {
        return new ActivityRouteComponent(cls);
    }

    @Override
    public Component createFragmentComponent(Class<? extends Fragment> cls) {
        return null;
    }

    private class ActivityRouteComponent implements Component {
        private final Class<? extends Activity> cls;

        public ActivityRouteComponent(Class<? extends Activity> cls) {
            this.cls = cls;
        }

        @Override
        public void route(Context context, RouteRequest request) {

        }

        @Override
        public void route(Activity activity, RouteRequest request) {

        }

        @Override
        public void route(Fragment fragment, RouteRequest request) {

        }

        @Override
        public Intent newIntent(Context context, RouteRequest request) {
            return null;
        }
    }
}
