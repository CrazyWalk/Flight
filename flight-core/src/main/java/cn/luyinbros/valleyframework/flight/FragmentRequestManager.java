package cn.luyinbros.valleyframework.flight;

import androidx.fragment.app.Fragment;

class FragmentRequestManager implements RequestManager {
    private Fragment fragment;

    FragmentRequestManager(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public RequestManager request(RouteRequest request) {
        return this;
    }

    @Override
    public void route() {

    }
}
