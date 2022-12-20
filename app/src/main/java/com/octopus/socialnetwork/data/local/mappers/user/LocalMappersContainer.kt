package com.octopus.socialnetwork.data.local.mappers.user

import javax.inject.Inject

class LocalMappersContainer @Inject constructor(
    val userMapper: UserMapper,
)