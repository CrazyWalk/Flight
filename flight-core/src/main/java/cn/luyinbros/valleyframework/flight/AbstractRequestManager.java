package cn.luyinbros.valleyframework.flight;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractRequestManager implements RequestManager {
    private RouteRequest mRequest;

    @Override
    public final RequestManager request(RouteRequest request) {
        mRequest = request;
        return this;
    }

    @Override
    public final void route() {
        if (mRequest != null) {
            Component component = getComponent();
            if (component != null) {
                route(component, mRequest);
            }
        }

    }

    @Nullable
    private Component getComponent() {
        final Flight flight = Flight.get(getContext());
        List<Interceptor> resultInterceptor = new ArrayList<>(flight.getInterceptors());
        resultInterceptor.add(ResultInterceptor.INSTANCE);
        final RouteRequest request =
                new RealChain(mRequest, 0, new ArrayList<>(resultInterceptor))
                        .process(mRequest)
                        .getRequest();
        return flight.getRouteComponentProvider().find(request.path());
    }

    @Override
    public Intent createIntent() {
        if (mRequest != null) {
            Component component = getComponent();
            if (component != null) {
                return component.newIntent(getContext(), mRequest);
            }
        }
        return new Intent();

    }

    protected abstract void route(Component component, RouteRequest request);

    public abstract Context getContext();


    private static class ResultInterceptor implements Interceptor {

        private static Interceptor INSTANCE = new ResultInterceptor();

        @Override
        public RouterResponse process(Chain chain) {
            return new RouterResponse(chain.request());
        }
    }

}
