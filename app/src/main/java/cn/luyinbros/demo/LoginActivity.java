package cn.luyinbros.demo;


import cn.luyinbros.demo.base.BaseActivity;
import cn.luyinbros.valleyframework.controller.annotation.Controller;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponent;

@RouteComponent("/login")
@Controller(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
}
