package cn.luyinbros.demo.router;


import android.app.Application;

import cn.luyinbros.valleyframework.flight.Interceptor;
import cn.luyinbros.valleyframework.flight.RouteRequest;
import cn.luyinbros.valleyframework.flight.RouterResponse;
import cn.luyinbros.valleyframework.flight.annotation.RouteInterceptor;

@RouteInterceptor
public class LoginInterceptor implements Interceptor {
    private static final boolean isLogin = false;

    @Override
    public RouterResponse process(Chain chain) {
        if (!isLogin) {
            String path = chain.request().path();
            if (path.startsWith("/needLogin")) {
                return chain.process(new RouteRequest.Builder()
                        .path("/login")
                        .build());
            }
        }
        return chain.process(chain.request());
    }
}
