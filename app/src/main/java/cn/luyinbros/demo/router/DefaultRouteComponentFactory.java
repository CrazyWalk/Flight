package cn.luyinbros.demo.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import cn.luyinbros.valleyframework.flight.Component;
import cn.luyinbros.valleyframework.flight.ComponentFactory;
import cn.luyinbros.valleyframework.flight.RouteRequest;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponentFactory;


//@RouteComponentFactory
public class DefaultRouteComponentFactory implements ComponentFactory {

    @Override
    public Component createActivityRouteComponent(String activityCls) {
        return null;
    }




}
