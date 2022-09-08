package com.flexcode.devspace.github.data.entities

import com.flexcode.devspace.github.data.local.entities.UserEntity

class FakeUserEntity {

    val fakeUserEntity = UserEntity(
        avatar_url = "https://avatars.githubusercontent.com/u/61313608?v=4",
        bio = "Native Android developer",
        blog = "https://felixkariuki.netlify.app/",
        email = null,
        followers = 42,
        following = 36,
        id = 61313608,
        location = "Nairobi,Kenya",
        login = "Felix-Kariuki",
        name = "Felix Kariuki",
        public_repos = 48,
        twitter_username = "felixkariuki_",
        url = "https://api.github.com/users/Felix-Kariuki"
    )


}