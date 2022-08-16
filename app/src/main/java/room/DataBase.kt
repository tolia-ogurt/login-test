package room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInfoEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun dao(): UserInfoDao
}