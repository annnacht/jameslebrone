package ru.andrewkir.testingsaturday.presentation.goods.contract

import ru.andrewkir.testingsaturday.data.models.GoodsModel

sealed class GoodsEvent {
  data class OnTextUpdated(val text: String): GoodsEvent()
  data class OnUrlUpdated(val url: String): GoodsEvent()
  data class OnCardClicked(val goodsModel: GoodsModel): GoodsEvent()

  data object OnAddButtonClicked: GoodsEvent()
}