package cn.luyinbros.demo.router;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import cn.luyinbros.valleyframework.flight.Component;
import cn.luyinbros.valleyframework.flight.RouteRequest;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponent;

@RouteComponent("/system/dial")
public class DialPhoneRouteComponent extends AbstractRouteComponent {

    @Override
    public Intent newIntent(Context context, RouteRequest request) {
        Bundle extra = request.extra();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + extra.getString("phone", "")));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}
