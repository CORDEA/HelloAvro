package jp.cordea.objects

import kotlinx.serialization.Serializable

@Serializable
data class UserDetails(
    val id: Int,
    val name: String,
    val emailAddress: String,
    val pets: List<UserPet>
)

@Serializable
data class UserPet(
    val type: UserPetType,
    val name: String,
    val breed: String
)

@Serializable
enum class UserPetType { CAT, DOG }
