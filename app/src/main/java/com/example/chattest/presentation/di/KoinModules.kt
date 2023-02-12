package com.example.chattest.presentation.di

import com.example.chattest.BuildConfig
import com.example.chattest.data.datasource.SharedPreferenceDataSource
import com.example.chattest.data.repository.MessageUserRepository
import com.example.chattest.data.repository.impl.MessageUserRepositoryImpl
import com.example.chattest.domain.usecase.MessageUserUseCase
import com.example.chattest.domain.usecase.impl.MessageUserUseCaseImpl
import com.example.chattest.presentation.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(androidApplication(), get()) }
}

val useCaseModule = module {
    single { MessageUserUseCaseImpl(get()) as MessageUserUseCase }
}

val repositoryModules = module {
    single { MessageUserRepositoryImpl() as MessageUserRepository }
}

val sharedPreferenceModule = module {
    single { SharedPreferenceDataSource(androidContext(), BuildConfig.APPLICATION_ID) }
}

val mobileModules = listOf(
    sharedPreferenceModule,
    repositoryModules,
    useCaseModule,
    viewModelModule
)