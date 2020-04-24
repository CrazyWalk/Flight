package cn.luyinbros.valleyframework.flight;

import android.app.Activity;

import androidx.fragment.app.Fragment;

public interface ComponentFactory {

    Component createActivityRouteComponent(Class<? extends Activity> cls);

    Component createFragmentComponent(Class<? extends Fragment> cls);


}
