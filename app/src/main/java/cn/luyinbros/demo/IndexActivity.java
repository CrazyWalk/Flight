package cn.luyinbros.demo;

import cn.luyinbros.demo.base.BaseActivity;
import cn.luyinbros.valleyframework.controller.annotation.Controller;
import cn.luyinbros.valleyframework.controller.annotation.OnClick;
import cn.luyinbros.valleyframework.flight.Flight;
import cn.luyinbros.valleyframework.flight.RouteRequest;

@Controller(R.layout.activity_index)
public class IndexActivity extends BaseActivity {

    @OnClick(R.id.dialPhoneButton)
    void dialPhoneClick() {
        Flight.with(this)
                .request(new RouteRequest.Builder()
                        .path("/system/dial")
                        .putString("phone", "18850528262")
                        .build())
                .route();
    }

    @OnClick(R.id.needLoginButton)
    void needLoginClick() {
        System.out.println(
                Flight.with(this)
                        .request(new RouteRequest.Builder()
                                .path("/needLogin")
                                .build())
                        .createIntent());
//        Flight.with(this)
//                .request(new RouteRequest.Builder()
//                        .path("/needLogin")
//                        .build())
//                .route();
    }
}
