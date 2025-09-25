package com.snacks.nuvo.di

import com.snacks.nuvo.network.service.CallSessionService
import com.snacks.nuvo.network.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

// AuthModule에 중복 구현되어 주석처리
//    @Provides
//    @Singleton
//    fun providesOkHttpClient(
//        @ApplicationContext context: Context,
//    ): OkHttpClient {
//        val builder = OkHttpClient.Builder()
//            .addNetworkInterceptor(
//                HttpLoggingInterceptor().apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                }
//            )
//
//        builder.addNetworkInterceptor(ChuckerInterceptor(context))
//
//        return builder.build()
//    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providesCallSessionService(retrofit: Retrofit): CallSessionService =
        retrofit.create(CallSessionService::class.java)

}