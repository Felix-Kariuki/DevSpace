package com.flexcode.devspace.github.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexcode.devspace.github.data.local.dao.FollowersDao
import com.flexcode.devspace.github.data.local.dao.FollowingDao
import com.flexcode.devspace.github.data.local.dao.RepositoryDao
import com.flexcode.devspace.github.data.local.dao.UserDao
import com.flexcode.devspace.github.data.local.entities.FollowersEntity
import com.flexcode.devspace.github.data.local.entities.FollowingEntity
import com.flexcode.devspace.github.data.local.entities.RepositoryEntity
import com.flexcode.devspace.github.data.local.entities.UserEntity

@Database(
    entities = [
        UserEntity::class, FollowersEntity::class, FollowingEntity::class, RepositoryEntity::class
    ],
    version = 7, exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val followersDao: FollowersDao
    abstract val followingDao: FollowingDao
    abstract val repositoryDao: RepositoryDao
}
