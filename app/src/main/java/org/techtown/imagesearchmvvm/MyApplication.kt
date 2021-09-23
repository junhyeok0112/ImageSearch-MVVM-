package org.techtown.imagesearchmvvm

import android.app.Application
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.techtown.imagesearchmvvm.DI.myDiModule
import org.koin.android.ext.koin.androidContext as androidContext1

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //koin 시작해서 의존성 주입
        startKoin{
            androidLogger()
            androidContext1(this@MyApplication)
            androidFileProperties()
            modules(myDiModule)
        }
    }
}