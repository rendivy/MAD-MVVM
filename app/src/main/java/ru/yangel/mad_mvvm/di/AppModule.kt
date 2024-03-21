package ru.yangel.mad_mvvm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yangel.domain.usecase.ConvertPhoneNumberUseCase
import ru.yangel.domain.usecase.ValidatePhoneNumberUseCase


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideValidatePhoneNumberUseCase(): ValidatePhoneNumberUseCase =
        ValidatePhoneNumberUseCase()

    @Provides
    fun provideConvertPhoneNumberUseCase(): ConvertPhoneNumberUseCase =
        ConvertPhoneNumberUseCase()


}