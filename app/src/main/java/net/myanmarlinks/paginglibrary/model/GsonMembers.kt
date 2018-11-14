package net.myanmarlinks.networkdata.model

import com.google.gson.annotations.SerializedName

data class GsonMembers(
        @SerializedName("data")
        val gsonMembers: List<GsonMember>
)