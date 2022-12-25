package com.octopus.socialnetwork.domain.usecase.user.edituser

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUserDetails
import com.octopus.socialnetwork.domain.model.user.UserDetails
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.last
import java.io.File
import javax.inject.Inject

class ChangeCoverImageUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(coverImage: File): UserDetails {
        val myUserId = fetchUserIdUseCase().last()
        return socialRepository.addCoverPicture(myUserId, coverImage).toUserDetails()
    }
}