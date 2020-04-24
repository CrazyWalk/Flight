package cn.luyinbros.valleyframework.flight;

import android.app.Activity;

class ActivityRequestManager implements RequestManager {
    private Activity activity;
    private RouteRequest request;
    private Flight flight;


    ActivityRequestManager(Activity activity, Flight flight) {
        this.activity = activity;
        this.flight = flight;
    }

    @Override
    public RequestManager request(RouteRequest request) {
        return this;
    }

    @Override
    public void route() {
        if (request != null) {
            final String path = request.path();
            Component component = flight.getRouteComponentProvider().find(path);
            if (component != null) {
                component.route(activity, request);
            }
        }
    }
}
