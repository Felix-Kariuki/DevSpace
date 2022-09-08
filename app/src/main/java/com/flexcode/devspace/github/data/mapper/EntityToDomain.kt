package com.flexcode.devspace.github.data.mapper

import com.flexcode.devspace.github.data.local.entities.*
import com.flexcode.devspace.github.domain.model.*

internal fun UserEntity.toDomain(): User {
    return User(
        avatar_url = avatar_url,
        bio = bio,
        blog = blog,
        email = email,
        followers = followers,
        following = following,
        id = id,
        location = location,
        login = login,
        name = name,
        public_repos = public_repos,
        twitter_username = twitter_username,
        url = url
    )
}

internal fun FollowersEntity.toDomain(): Followers {
    return Followers(
        avatar_url = avatar_url,
        id = id,
        login = login
    )
}

internal fun FollowingEntity.toDomain(): Following {
    return Following(
        avatar_url = avatar_url,
        id = id,
        login = login
    )
}

internal fun RepositoryEntity.toDomain(): Repository {
    return Repository(
        created_at = created_at,
        description = description,
        forks = forks,
        full_name = full_name,
        id = id,
        language = language,
        name = name,
        open_issues = open_issues,
        owner = owner?.toDomain(),
        size = size,
        stargazers_count = stargazers_count,
        updated_at = updated_at,
        visibility = visibility,
    )
}

internal fun OwnerEntity.toDomain(): Owner {
    return Owner(
        avatar_url = avatar_url
    )
}