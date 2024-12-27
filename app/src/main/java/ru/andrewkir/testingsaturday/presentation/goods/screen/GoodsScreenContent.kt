package ru.andrewkir.testingsaturday.presentation.goods.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andrewkir.testingsaturday.R
import ru.andrewkir.testingsaturday.data.models.GoodsModel
import ru.andrewkir.testingsaturday.presentation.goods.components.GoodsCard
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEvent
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsState

@Composable
fun GoodsScreenContent(
  state: GoodsState,
  onEvent: (GoodsEvent) -> Unit,
) {
  Column(modifier = Modifier.padding(start = 12.dp)) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(verticalArrangement = Arrangement.Bottom) {
        OutlinedTextField(
          value = state.enteredText,
          onValueChange = { changedValue -> onEvent(GoodsEvent.OnTextUpdated(changedValue)) },
          label = { Text("Enter") })

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
          OutlinedTextField(
            modifier = Modifier
              .padding(vertical = 16.dp)
              .width(300.dp),
            value = state.enteredUrl,
            onValueChange = { changedValue -> onEvent(GoodsEvent.OnUrlUpdated(changedValue)) },
            label = { Text("Url") })

          Button(
            modifier = Modifier.padding(start = 14.dp),
            onClick = {
              onEvent(GoodsEvent.OnAddButtonClicked)
            }) {
            Text(text = "Add")
          }
        }
      }
    }
    LazyColumn(modifier = Modifier.padding(horizontal = 6.dp, vertical = 12.dp)) {
      state.goods.forEach { goodsModel ->
        item {
          GoodsCard(
            item = goodsModel,
            onEvent = onEvent
          )
          Spacer(modifier = Modifier.padding(6.dp))
        }
      }
    }
  }
}

@Preview
@Composable
private fun GoodsScreenContentPreview() {
  GoodsScreenContent(
    GoodsState(
      goods = listOf(
        GoodsModel(
          name = "Евгения Медведева",
          age = 20,
          year = 1999,
          win_olymp = true,
          coverId = R.drawable.bmw
        ),
        GoodsModel(
          name = "Евгения Немедведева",
          age = 1,
          year = 22,
          win_olymp = false,
          coverId = R.drawable.gaz
        )),
      enteredText = "",
      enteredUrl = "https://i.pinimg.com/736x/4d/ca/43/4dca433d1f1bdfaa4224a08f3c2dc1d0.jpg",
    )
  ) {}
}