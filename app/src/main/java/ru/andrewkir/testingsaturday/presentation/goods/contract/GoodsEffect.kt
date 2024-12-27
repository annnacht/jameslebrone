package ru.andrewkir.testingsaturday.presentation.goods.contract

import ru.andrewkir.testingsaturday.data.models.GoodsModel

sealed interface GoodsEffect {
  data class OpenDetails(val item: GoodsModel): GoodsEffect
}