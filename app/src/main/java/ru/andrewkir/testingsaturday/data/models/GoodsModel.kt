package ru.andrewkir.testingsaturday.data.models

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class GoodsModel(
  val name: String,
  val age: Int,
  val year: Int,
  val win_olymp: Boolean,
  @DrawableRes
  val coverId: Int,
  val imageURL: String = "",
)