package ru.andrewkir.testingsaturday.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import ru.andrewkir.testingsaturday.data.models.GoodsModel

@Composable
@Destination
fun DetailScreenContent(
  item: GoodsModel
) {
  Column {
    Text("Hello ${item.name}!", fontSize = 26.sp)
  }
}