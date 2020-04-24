package cn.luyinbros.valleyframework.flight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public interface Component {

    void route(Context context, RouteRequest request);

    void route(Activity activity, RouteRequest request);

    void route(Fragment fragment, RouteRequest request);

    Intent newIntent(Context context, RouteRequest request);


}
