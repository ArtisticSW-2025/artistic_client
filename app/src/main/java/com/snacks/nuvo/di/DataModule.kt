package com.snacks.nuvo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// 직접 정의한 DataSource/Repository를 싱글톤으로 관리&주입하기 위해 모듈화
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    // DataSource/Repository 생성 시 모두 추가
    // 예:
    // @Binds
    // abstract fun ${feature}Repository(
    //      ${feature}Repository: ${feature}RepositoryImple,
    // ): ${feature}Repository
}