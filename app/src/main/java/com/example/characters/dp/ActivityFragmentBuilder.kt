package com.example.characters.dp

import com.example.characters.MainActivity
import com.example.characters.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityFragmentBuilder {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment


}
