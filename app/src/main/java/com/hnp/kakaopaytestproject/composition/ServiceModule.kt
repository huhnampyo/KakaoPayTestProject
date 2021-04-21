package com.hnp.kakaopaytestproject.composition

import com.hnp.kakaopaytestproject.BuildConfig
import com.hnp.kakaopaytestproject.KakaoApp
import com.hnp.kakaopaytestproject.application.AppService
import com.hnp.kakaopaytestproject.data.AppServiceImpl
import com.hnp.kakaopaytestproject.data.remote.RemoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
abstract class ServiceModule {
    @Module
    companion object {
        @Provides
        fun providesRetrofit(okHttpClient: OkHttpClient, app: KakaoApp): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.GRADLE_API_BASE_URL)
            .build()

        @Provides
        fun providesOkHttpClient(app: KakaoApp): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(if(BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            }else{
                HttpLoggingInterceptor.Level.NONE
            })

            return OkHttpClient.Builder()
                .addInterceptor(AccessTokenInterceptor(app))
                .addInterceptor(logging)
                .followRedirects(true)
                .followSslRedirects(true)
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        fun bindsRemoteService(retrofit: Retrofit): RemoteService = retrofit.create(RemoteService::class.java)
    }

    @Binds
    abstract fun bindsAppService(appServiceImpl: AppServiceImpl): AppService
}