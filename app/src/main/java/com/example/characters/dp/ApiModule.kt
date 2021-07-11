package com.example.characters.dp

import com.example.characters.data.api.CharacterApi
import com.example.characters.utils.Const.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
object ApiModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providesRetrofit(): CharacterApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(CharacterApi::class.java)
}


