package com.eternel.jet.demo.di.module;

import com.eternel.jet.demo.model.bean.Student;
import com.eternel.jet.demo.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @ActivityScope
    @Provides
    Student student() {
        return new Student();
    }
}
