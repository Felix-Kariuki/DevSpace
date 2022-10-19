package com.flexcode.devspace.github.data.mapper

import com.flexcode.devspace.github.data.local.entities.*
import com.flexcode.devspace.github.data.remote.dto.*

internal fun UserDto.toEntity(): UserEntity {

    return UserEntity(
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

internal fun FollowersDto.toEntity(): FollowersEntity {
    return FollowersEntity(
        avatar_url = avatar_url,
        id = id,
        login = login
    )
}

internal fun FollowingDto.toEntity(): FollowingEntity {
    return FollowingEntity(
        avatar_url = avatar_url,
        id = id,
        login = login
    )
}

internal fun RepositoryDto.toEntity(): RepositoryEntity {
    return RepositoryEntity(
        created_at = created_at,
        description = description,
        forks = forks,
        full_name = full_name,
        id = id,
        language = language,
        name = name,
        open_issues = open_issues,
        owner = owner?.toEntity(),
        size = size,
        stargazers_count = stargazers_count,
        updated_at = updated_at,
        visibility = visibility,
    )
}

internal fun OwnerDto.toEntity(): OwnerEntity {
    return OwnerEntity(
        avatar_url = avatar_url,
        login = login
    )
}
