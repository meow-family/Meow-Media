package com.octopus.socialnetwork.domain.mapper

import com.octopus.socialnetwork.data.repository.authentication.ParamRegisterDto
import com.octopus.socialnetwork.domain.model.user.ParamRegister

internal fun ParamRegister.toParamRegisterDto(): ParamRegisterDto {
    return ParamRegisterDto(
        firstName = firstName,
        lastName = lastName,
        email = email,
        reEmail = reEmail,
        gender = gender,
        birthDate = birthDate,
        userName = userName,
        password = password
    )
}