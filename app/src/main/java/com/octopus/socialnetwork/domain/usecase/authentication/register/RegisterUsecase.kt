package com.octopus.socialnetwork.domain.usecase.authentication.register

import android.util.Log
import com.octopus.socialnetwork.data.remote.firebase.FirebaseRepository
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.domain.mapper.user.toUserFirebaseDTO
import com.octopus.socialnetwork.domain.model.user.UserFirebase
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val signInRepository: AuthenticationRepository,
    private val CreateUserAccountInFirebase: FirebaseRepository
) {
    suspend operator fun invoke(params: Params): Boolean {

        val response = signInRepository.register(
            firstName = params.firstName,
            lastName = params.lastName,
            gender = params.gender,
            email = params.email,
            birthDate = params.birthDate,
            password = params.password,
            reEmail = params.reEmail,
            userName = params.userName
        )

        return if (response.code == "100") {
            val userResponse = response.result
            val userInfo = UserFirebase(
                userId = userResponse.userId ?: 0,
                fullName = userResponse.fullname ?: "",
                username = userResponse.username ?: "",
                token = ""
            ).toUserFirebaseDTO()
            CreateUserAccountInFirebase.createUser(userInfo)
            true
        } else {
            Log.v("ameer", "error create account  ${response.code} ${response.message}")
            false
        }
    }

    data class Params(
        var firstName: String,
        var lastName: String,
        var email: String,
        var reEmail: String,
        var gender: String,
        var birthDate: String,
        var userName: String,
        var password: String,
    )

}