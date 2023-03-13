package com.bbox.bboxjournal.di

import android.app.Application
import androidx.room.Room
import com.bbox.bboxjournal.data.JournalRepositoryImp
import com.bbox.bboxjournal.data.db.BBoxJournalDB
import com.bbox.bboxjournal.data.db.dao.BBoxJournalDAO
import com.bbox.bboxjournal.domain.repository.JournalRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideJournalRepository(journalRepository: JournalRepositoryImp): JournalRepository

    companion object {
        @Singleton
        @Provides
        fun provideAppDatabase(context: Application): BBoxJournalDB = Room.databaseBuilder(
            context, BBoxJournalDB::class.java, "bbox-journal-db"
        ).fallbackToDestructiveMigration().build()

        @Provides
        fun provideGitRepoDAO(database: BBoxJournalDB): BBoxJournalDAO =
            database.getBBoxJournalDao()
    }
}