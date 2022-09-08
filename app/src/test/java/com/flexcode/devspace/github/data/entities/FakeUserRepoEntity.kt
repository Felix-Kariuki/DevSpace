package com.flexcode.devspace.github.data.entities

import com.flexcode.devspace.github.data.local.entities.OwnerEntity
import com.flexcode.devspace.github.data.local.entities.RepositoryEntity

val fkeOwner = OwnerEntity(
    avatar_url = "https://avatars.githubusercontent.com/u/61313608?v=4"
)
val fakeUserRepoEntity =
    listOf(
        RepositoryEntity(
            created_at = "2022-04-27T03:32:40Z",
            description = null,
            forks = 0,
            full_name = "Felix-Kariuki/AdanianLabs-Task",
            id = 486032592,
            language = "Kotlin",
            name = "AdanianLabs-Task",
            open_issues = 0,
            owner = fkeOwner,
            size = 841,
            stargazers_count = 0,
            updated_at = "2022-05-03T11:14:55Z",
            visibility = "public"
        )

    )
