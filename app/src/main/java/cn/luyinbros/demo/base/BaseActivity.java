package cn.luyinbros.demo.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.luyinbros.valleyframework.controller.ControllerActivityDelegate;
import cn.luyinbros.valleyframework.controller.ControllerDelegate;

public abstract class BaseActivity extends AppCompatActivity {
    private final ControllerActivityDelegate mDelegate = ControllerDelegate.create(this);


    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
    }


    @Override
    protected final void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mDelegate.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        mDelegate.setIntent(newIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelegate.unbind();
    }
}
