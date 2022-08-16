package room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfoEntity(
    val name: String,
    val surName: String,
    val phoneCode: String,
    @PrimaryKey val phoneNumber: String,
)