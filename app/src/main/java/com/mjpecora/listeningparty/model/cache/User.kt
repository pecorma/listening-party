package com.mjpecora.listeningparty.model.cache

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "user")
data class User(
    val userName: String,
    val email: String,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = 1,
)

@Dao
interface UserDao {
    @Query("select * from user")
    fun getUser(): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
}