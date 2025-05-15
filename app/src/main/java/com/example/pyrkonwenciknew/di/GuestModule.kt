package com.example.pyrkonwenciknew.di

import com.example.pyrkonwenciknew.data.api.GuestAPI
import com.example.pyrkonwenciknew.data.repository.GuestRepositoryImpl
import com.example.pyrkonwenciknew.domain.repository.GuestRepository
import com.example.pyrkonwenciknew.domain.usecase.GuestListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class GuestModule {

    @Provides
    fun provideGuestRepository(
        @Named(ApiModule.MOCK) guestAPI: GuestAPI
    ): GuestRepository =
        GuestRepositoryImpl(
            guestAPI
        )

    @Provides
    fun provideGuestListUseCase(
        guestRepository: GuestRepository
    ): GuestListUseCase =
        GuestListUseCase(guestRepository)
}
