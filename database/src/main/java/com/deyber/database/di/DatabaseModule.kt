package com.deyber.database.di

import android.content.Context
import androidx.room.Room
import com.deyber.database.constant.DatabaseConfig.DATABASE_NAME
import com.deyber.database.daos.HitDao
import com.deyber.database.database.DataBase
import com.deyber.database.repository.HitRepository
import com.deyber.database.repository.HitRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    @DatabaseQualifier
    fun provideDatabaseRepository(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    @HitDaoQualifier
    fun provideHitProvider(@DatabaseQualifier db: DataBase): HitDao = db.hitDao()

    @Provides
    @Singleton
    @HitRepositoryQualifier
    fun provideHitRepositoryProvider(@HitDaoQualifier dao: HitDao): HitRepository =
        HitRepositoryImp(dao)
}