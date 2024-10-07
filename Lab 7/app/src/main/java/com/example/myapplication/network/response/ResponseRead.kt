package com.example.myapplication.network.response

import com.google.gson.annotations.SerializedName

data class ReadTodoResponse(
    @SerializedName("resultCode")
    val resultCode: Int,
    @SerializedName("data")
    val data: ArrayList<RToDoCat>
)
data class ReadTodoResponse2(
    @SerializedName("resultCode")
    val resultCode: Int,
    @SerializedName("data")
    val data: ArrayList<RecipieRes>
)

data class MealsCategoriesResponse(val categories: List<RToDoCat>)
data class MealsRecipiesResponse(val recipies: List<RecipieRes>)

data class RToDoCat(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryDescription") val description: String,
    @SerializedName("strCategoryThumb") val imageUrl: String
)
data class RecipieRes(
    @SerializedName("idRecipie") val id: String,
    @SerializedName("strRecipie") val name: String,
    @SerializedName("strRecipieDescription") val description: String,
    @SerializedName("strRecipieThumb") val imageUrl: String
)
