package com.example.characters.dp

import com.example.characters.data.api.CharacterApi
import com.example.characters.utils.Const
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideUserService(): CharacterApi = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(CharacterApi::class.java)
}
