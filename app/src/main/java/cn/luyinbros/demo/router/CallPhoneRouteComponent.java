package cn.luyinbros.demo.router;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import cn.luyinbros.valleyframework.flight.Component;
import cn.luyinbros.valleyframework.flight.RouteRequest;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponent;

@RouteComponent("/system/call")
public class CallPhoneRouteComponent extends AbstractRouteComponent {

    @Override
    public Intent newIntent(Context context, RouteRequest request) {
        return null;
    }
}
