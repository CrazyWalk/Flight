package cn.luyinbros.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.luyinbros.valleyframework.flight.Flight;
import cn.luyinbros.valleyframework.flight.annotation.RouteComponent;


@RouteComponent("main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
