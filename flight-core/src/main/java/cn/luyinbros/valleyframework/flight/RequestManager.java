package cn.luyinbros.valleyframework.flight;

import android.content.Intent;

public interface RequestManager {

    RequestManager request(RouteRequest request);

    Intent createIntent();

    void route();




}
