package cn.luyinbros.valleyframework.flight;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

class FragmentRequestManager extends AbstractRequestManager {
    private final Fragment fragment;

    FragmentRequestManager(Fragment fragment) {
        this.fragment = fragment;
    }


    @Override
    protected void route(Component component, RouteRequest request) {
        component.route(fragment, request);
    }

    @Override
    public Context getContext() {
        return fragment.getContext();
    }
}
