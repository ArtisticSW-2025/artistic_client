package com.snacks.nuvo.di

import com.snacks.nuvo.data.datasource.CallSessionDataSource
import com.snacks.nuvo.data.datasource.CallSessionDataSourceImpl
import com.snacks.nuvo.data.datasource.FeedbackDataSource
import com.snacks.nuvo.data.datasource.FeedbackDataSourceImpl
import com.snacks.nuvo.data.datasource.MissionRecordDataSource
import com.snacks.nuvo.data.datasource.MissionRecordDataSourceImpl
import com.snacks.nuvo.data.datasource.RankingDataSource
import com.snacks.nuvo.data.datasource.RankingDataSourceImpl
import com.snacks.nuvo.data.datasource.UserDataSource
import com.snacks.nuvo.data.datasource.UserDataSourceImpl
import com.snacks.nuvo.data.repository.CallSessionRepository
import com.snacks.nuvo.data.repository.CallSessionRepositoryImpl
import com.snacks.nuvo.data.repository.FeedbackRepository
import com.snacks.nuvo.data.repository.FeedbackRepositoryImpl
import com.snacks.nuvo.data.repository.MissionRecordRepository
import com.snacks.nuvo.data.repository.MissionRecordRepositoryImpl
import com.snacks.nuvo.data.repository.RankingRepository
import com.snacks.nuvo.data.repository.RankingRepositoryImpl
import com.snacks.nuvo.data.repository.UserRepository
import com.snacks.nuvo.data.repository.UserRepositoryImpl
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
    // abstract fun ${Feature}Repository(
    //      ${feature}Repository: ${feature}RepositoryImple,
    // ): ${Feature}Repository

    @Binds
    abstract fun UserDataSource(
        userDataSource: UserDataSourceImpl,
    ): UserDataSource

    @Binds
    abstract fun UserRepository(
        userRepository: UserRepositoryImpl,
    ): UserRepository

    @Binds
    abstract fun CallSessionDataSource(
        callSessionDataSource: CallSessionDataSourceImpl,
    ): CallSessionDataSource

    @Binds
    abstract fun CallSessionRepository(
        callSessionRepository: CallSessionRepositoryImpl,
    ): CallSessionRepository

    @Binds
    abstract fun MissionRecordDataSource(
        missionRecordDataSource: MissionRecordDataSourceImpl,
    ): MissionRecordDataSource

    @Binds
    abstract fun MissionRecordRepository(
        missionRecordRepository: MissionRecordRepositoryImpl,
    ): MissionRecordRepository

    @Binds
    abstract fun bindRankingDataSource(
        rankingDataSourceImpl: RankingDataSourceImpl
    ): RankingDataSource

    @Binds
    abstract fun bindRankingRepository(
        rankingRepositoryImpl: RankingRepositoryImpl
    ): RankingRepository
  
    @Binds
    abstract fun FeedbackRepository(
        feedbackRepository: FeedbackRepositoryImpl,
    ): FeedbackRepository
  
    @Binds
    abstract fun FeedbackDataSource(
        feedbackDataSource: FeedbackDataSourceImpl
    ): FeedbackDataSource
}