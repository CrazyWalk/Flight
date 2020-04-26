package cn.luyinbros.demo;

import cn.luyinbros.demo.base.BaseActivity;
import cn.luyinbros.valleyframework.controller.annotation.Controller;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponent;

@RouteComponent("/needLogin")
@Controller(R.layout.activity_need_login)
public class NeedLoginActivity extends BaseActivity {

}
