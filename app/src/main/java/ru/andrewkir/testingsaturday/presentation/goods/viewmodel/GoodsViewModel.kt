package ru.andrewkir.testingsaturday.presentation.goods.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.andrewkir.testingsaturday.App
import ru.andrewkir.testingsaturday.R
import ru.andrewkir.testingsaturday.data.api.GithubApi
import ru.andrewkir.testingsaturday.data.dao.User
import ru.andrewkir.testingsaturday.data.models.GoodsModel
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEffect
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEffect.OpenDetails
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEvent
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEvent.OnAddButtonClicked
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEvent.OnCardClicked
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEvent.OnTextUpdated
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsEvent.OnUrlUpdated
import ru.andrewkir.testingsaturday.presentation.goods.contract.GoodsState
import kotlin.random.Random

class GoodsViewModel : ViewModel() {

  val state = MutableStateFlow(GoodsState())

  private val _effect = Channel<GoodsEffect>()
  val effect = _effect.receiveAsFlow()

  init {
    state.value = state.value.copy(
      goods = state.value.goods + listOf(
        GoodsModel(
          name = "Женя Медведева йоу",
          age = 19,
          year = 1992,
          win_olymp = true,
          coverId = R.drawable.bmw,
          imageURL = state.value.enteredUrl,
        ),
        GoodsModel(
          name = "Алина Загитова",
          age = 19,
          year = 1992,
          win_olymp = false,
          coverId = R.drawable.bmw,
          imageURL = state.value.enteredUrl,
        ),
      ),
      enteredText = "",
      enteredUrl = "",
    )
  }

  private fun getApi(): GithubApi {
    val okHttpClient = Builder()
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    okHttpClient.addInterceptor(logging)

    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.github.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .client(okHttpClient.build())
      .build()

    return retrofit.create(GithubApi::class.java)
  }

  fun handleEvent(event: GoodsEvent) {
    when (event) {
      is OnTextUpdated -> {
        state.value = state.value.copy(enteredText = event.text)
      }

//      OnAddButtonClicked -> {
//        val api = getApi()
//        viewModelScope.launch {
//          try {
//            val users = api.getUsers()
//            users.forEach {
//              state.value = state.value.copy(
//                goods = state.value.goods + listOf(
//                  GoodsModel(
//                    name = it.login,
//                    rating = 4,
//                    price = 1000000,
//                    comment = "comment",
//                    coverId = R.drawable.bmw,
//                    imageURL = state.value.enteredUrl,
//                  ),
//                ),
//              )
//            }
//          } catch (e: Exception) {
//            e.printStackTrace()
//          }
//        }
////        state.value = state.value.copy(
////          goods = state.value.goods + listOf(
////            GoodsModel(
////              name = state.value.enteredText,
////              rating = 4,
////              price = 1000000,
////              comment = "Немецкое качество",
////              coverId = R.drawable.bmw,
////              imageURL = state.value.enteredUrl,
////            ),
////          ),
////          enteredText = "",
////          enteredUrl = "",
////        )
//      }

      OnAddButtonClicked -> {
        val model = GoodsModel(
          name = state.value.enteredText,
          age = 19,
          year = 1992,
          win_olymp = false,
          coverId = R.drawable.bmw,
          imageURL = state.value.enteredUrl,
        )
        state.value = state.value.copy(
          goods = state.value.goods + listOf(
            model
          ),
          enteredText = "",
          enteredUrl = "",
        )
        val db = App.getDatabase()
        db?.let {
          val usersDao = db.userDao()
          usersDao.insert(
            User(
              Random.nextInt().toString(),
              model.name,
            )
          )
        }
      }

      is OnUrlUpdated -> {
        state.value = state.value.copy(enteredUrl = event.url)
      }

      is OnCardClicked -> {
        viewModelScope.launch {
          _effect.send(OpenDetails(event.goodsModel))
        }
//        App.getDatabase()?.let { Log.d("MYTAG", it.userDao().getAll().toString()) }
      }
    }
  }
}