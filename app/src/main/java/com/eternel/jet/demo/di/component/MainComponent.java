package com.eternel.jet.demo.di.component;

import com.eternel.jet.demo.MainActivity;
import com.eternel.jet.demo.di.module.MainModule;
import com.eternel.jet.demo.scope.ActivityScope;

import dagger.Component;
@ActivityScope
@Component(modules = MainModule.class )
public interface MainComponent {
    void inject(MainActivity activity);
}
