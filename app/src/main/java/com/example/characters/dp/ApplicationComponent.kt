package com.example.characters.dp

import android.content.Context
import com.example.characters.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppAssistedModule::class,
        ApiModule::class,
        ActivityFragmentBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: App)

    override fun inject(instance: DaggerApplication?)
}
