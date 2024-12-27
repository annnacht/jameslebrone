package ru.andrewkir.testingsaturday.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
  @SerializedName("login")
  val login: String,

  @SerializedName("id")
  val id: Long,
)
