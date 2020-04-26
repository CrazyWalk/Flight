package cn.luyinbros.valleyframework.flight;

import android.content.Context;
import android.content.Intent;

public interface Interceptor {

    RouterResponse process(Chain chain);


    interface Chain {

        RouteRequest request();

        RouterResponse process(RouteRequest request);


    }
}
