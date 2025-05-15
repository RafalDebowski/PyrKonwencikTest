package com.example.pyrkonwenciknew.di

import android.content.Context
import com.example.pyrkonwenciknew.data.api.GuestAPI
import com.example.pyrkonwenciknew.data.api.MockGuestAPI
import com.example.pyrkonwenciknew.data.api.RetrofitClientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    companion object {
        const val REAL = "Real"
        const val MOCK = "Mock"
    }

    @Provides
    fun bindRetrofitClientRepository(): RetrofitClientRepository =
        RetrofitClientRepository()

    @Provides
    fun provideRetrofitInterface(retrofitClientRepository: RetrofitClientRepository): Retrofit =
        retrofitClientRepository.getRetrofitInterface()

    @Provides
    @Named(MOCK)
    fun provideMockGuestAPIImpl(
        @ApplicationContext context: Context
    ): GuestAPI = MockGuestAPI(context)

    @Provides
    @Named(REAL)
    fun provideGuestApi(retrofit: Retrofit): GuestAPI =
        retrofit.create(GuestAPI::class.java)
}