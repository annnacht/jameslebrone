package ru.andrewkir.testingsaturday.presentation.goods.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import ru.andrewkir.testingsaturday.presentation.destinations.DetailScreenContentDestination
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEffect.OpenDetails
import ru.andrewkir.testingsaturday.presentation.goods.viewmodel.GoodsViewModel

@Composable
@RootNavGraph(start = true)
@Destination
fun GoodsScreen(
  navigator: DestinationsNavigator,
) {
  val viewModel = viewModel<GoodsViewModel>()
  val state by viewModel.state.collectAsState()

  GoodsScreenContent(
    state = state,
    onEvent = {
      viewModel.handleEvent(it)
    },
  )

  LaunchedEffect(viewModel.effect) {
    viewModel.effect.collectLatest {effect ->
      when(effect) {
        is OpenDetails -> {
          navigator.navigate(DetailScreenContentDestination(item = effect.item))
        }
      }
    }
  }
}