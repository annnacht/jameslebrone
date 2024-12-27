package ru.andrewkir.testingsaturday.presentation.goods.contract

import ru.andrewkir.testingsaturday.data.models.GoodsModel

data class GoodsState(
  val enteredText: String = "",
  val enteredUrl: String = "",
  val goods: List<GoodsModel> = emptyList()
)