package room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserInfo(userInfoEntity: UserInfoEntity)

    @Query("SELECT * FROM user_info")
    suspend fun getUserInfo(): UserInfoEntity
}