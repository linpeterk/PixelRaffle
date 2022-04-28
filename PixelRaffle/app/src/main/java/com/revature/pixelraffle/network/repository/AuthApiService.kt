package com.revature.pixelraffle.network.repository

import com.revature.pixelraffle.network.datamodel.GetBoardState
import com.revature.pixelraffle.network.datamodel.GetGiGCategory
import com.revature.pixelraffle.network.datamodel.ResponseBoard
import com.revature.pixelraffle.network.datamodel.ResponseGig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPIService {

//    @POST("get_cat_skill1")
//    suspend fun getCat1(@Body getcat: GetCatBody): Response<ResponseTokenSkill>
//
//    @POST("get_cat_skill2")
//    suspend fun getCat2(@Body getcat:GetCatBody): Response<ResponseTokenSkill>
//
//    @POST("get_cat_skill3")
//    suspend fun getCat3(@Body getcat:GetCatBody): Response<ResponseTokenSkill>
//
//    @POST("get_cat_skill4")
//    suspend fun getCat4(@Body getcat:GetCatBody): Response<ResponseTokenSkill>
//
//    @POST("get_cat_skill5")
//    suspend fun getCat5(@Body getcat:GetCatBody): Response<ResponseTokenSkill>
//
//    @POST("get_cat_skill6")
//    suspend fun getCat6(@Body getcat:GetCatBody): Response<ResponseTokenSkill>
//
//    @POST("reviews_skillComponent")
//    suspend fun getReviews(@Body getReview: GetReviewBody): Response<ResponseTokenReview>
//
    @POST("get_gig")
    suspend fun getGigs(@Body getGig: GetGiGCategory): Response<ResponseGig>

    @POST("get_board")
    suspend fun getBoard(@Body getBoard: GetBoardState): Response<ResponseBoard>

}
