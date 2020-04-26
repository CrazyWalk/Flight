package cn.luyinbros.valleyframework.flight;

import android.content.Context;

import java.util.List;

class RealChain implements Interceptor.Chain {
    private final RouteRequest routeRequest;
    private final int currentIndex;
    private final List<Interceptor> interceptors;


    RealChain(RouteRequest routeRequest, int currentIndex, List<Interceptor> interceptors) {
        this.routeRequest = routeRequest;
        this.currentIndex = currentIndex;
        this.interceptors = interceptors;
    }

    @Override
    public RouteRequest request() {
        return routeRequest;
    }

    @Override
    public RouterResponse process(RouteRequest request) {
        if (currentIndex >= interceptors.size()) {
            throw new AssertionError();
        }
        RealChain nextChain = new RealChain(request, currentIndex + 1, interceptors);

        return interceptors.get(currentIndex).process(nextChain);
    }


}
