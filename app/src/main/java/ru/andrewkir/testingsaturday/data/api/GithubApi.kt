package ru.andrewkir.testingsaturday.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.andrewkir.testingsaturday.data.models.UserModel

interface GithubApi {

  @GET("/users?since=<string>&per_page=30")
  suspend fun getUsers(): List<UserModel>

  @GET("/users/{login}/followers")
  suspend fun getFollowers(@Path("login") login: String): List<UserModel>
}