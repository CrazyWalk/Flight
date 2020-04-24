package cn.luyinbros.valleyframework.flight;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class RouteComponentProvider {
    private Map<String, Component> routeComponentMap = new HashMap<>(16);


    @Nullable
    public Component find(String path) {
        return routeComponentMap.get(path);
    }

    public void register(String path, Component component) {
        routeComponentMap.put(path, component);
    }

}
