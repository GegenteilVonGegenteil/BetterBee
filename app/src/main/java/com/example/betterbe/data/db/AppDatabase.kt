package com.example.betterbe.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDate

// database with converters for the local date

@Database(entities = [HabitEntity::class, CompletionStatusEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao() : HabitDao
    abstract fun completionStatusDao() : CompletionStatusDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return Instance ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, "betterbee_database")
                        .fallbackToDestructiveMigration()
                        .build()
                Instance = instance
                return instance
            }
        }
    }
}

class Converters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }
}