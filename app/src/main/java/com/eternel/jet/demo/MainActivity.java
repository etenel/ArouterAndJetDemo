package com.eternel.jet.demo;

import androidx.appcompat.app.AppCompatActivity;
import dagger.internal.DaggerCollections;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternel.jet.commonsdk.RouterHub;
import com.eternel.jet.commonsdk.service.GankService;
import com.eternel.jet.demo.di.component.DaggerMainComponent;
import com.eternel.jet.demo.di.module.MainModule;
import com.eternel.jet.demo.model.bean.Student;
import com.eternel.jet.demo.ui.main.MainFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Student student;
    @Autowired(name = RouterHub.Gank_Service)
    GankService mGankService;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow();
//        }
        DaggerMainComponent
                .create()
                .inject(this);
        ARouter.getInstance().inject(this);
        button = findViewById(R.id.bt_dagger);
        if(mGankService!=null) {
            button.setText(mGankService.getInfo().getGankName());
        }else{
            button.setText("gankService==null");
        }
    }

    public void ontest(View view) {
        if(mGankService!=null) {
        ARouter.getInstance().build("/gank/GankActivity").navigation(this);
        }else{
            Toast.makeText(MainActivity.this, student.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
