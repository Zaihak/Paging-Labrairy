package net.myanmarlinks.paginglibrary.network

import net.myanmarlinks.networkdata.model.GsonMembers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("members")
    fun getMembers(@Query("page") page: Long) : Call<GsonMembers>
}