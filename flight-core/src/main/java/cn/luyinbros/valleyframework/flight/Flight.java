package cn.luyinbros.valleyframework.flight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class Flight {
    private RouteComponentProvider routeComponentProvider = new RouteComponentProvider();
    private ComponentFactory routeComponentFactory;
    private volatile static Flight INSTANCE;


    private Flight() {

    }

    public static Flight get() {
        if (INSTANCE == null) {
            synchronized (Flight.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Flight();
                }
            }
        }
        return INSTANCE;
    }

    public static RequestManager with(Activity activity) {
        return new ActivityRequestManager(activity, get());
    }

    public static RequestManager with(Fragment fragment) {
        return new FragmentRequestManager(fragment);
    }

    public void register(String path, Class<? extends Activity> cls) {
        routeComponentProvider.register(path, getRouteComponentFactory().createActivityRouteComponent(cls));
    }

    public void setRouteComponentFactory(ComponentFactory factory) {
        this.routeComponentFactory = factory;
    }

    public RouteComponentProvider getRouteComponentProvider() {
        return routeComponentProvider;
    }


    public ComponentFactory getRouteComponentFactory() {
        if (routeComponentFactory == null) {
            routeComponentFactory = new DefaultRouteComponentFactory();
        }
        return routeComponentFactory;
    }


    private static class DefaultRouteComponentFactory implements ComponentFactory {

        @Override
        public Component createActivityRouteComponent(Class<? extends Activity> cls) {
            return new ActivityRouteComponent(cls);
        }

        @Override
        public Component createFragmentComponent(Class<? extends Fragment> cls) {
            return null;
        }



        private static class ActivityRouteComponent implements Component {
            private final Class<? extends Activity> cls;

            private ActivityRouteComponent(Class<? extends Activity> cls) {
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

}
