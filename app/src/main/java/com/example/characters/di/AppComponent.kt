package com.example.characters.di

import android.content.Context
import com.example.characters.MainActivity
import com.example.characters.di.modules.ApiModule
import com.example.characters.di.modules.AppModule
import com.example.characters.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)

}