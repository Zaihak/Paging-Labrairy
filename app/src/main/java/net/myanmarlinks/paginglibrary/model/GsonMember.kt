package net.myanmarlinks.networkdata.model

import android.support.v7.util.DiffUtil
import com.google.gson.annotations.SerializedName

data class GsonMember(
        @SerializedName("_id") val _id: String,
        @SerializedName("title") val title: String,
        @SerializedName("name") val name: String,
        @SerializedName("profile_pic") val profile_pic: String,
        @SerializedName("party_name") val party_name: String
) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GsonMember> = object : DiffUtil.ItemCallback<GsonMember>() {
            override fun areItemsTheSame(oldItem: GsonMember, newItem: GsonMember): Boolean {
                return oldItem!!._id == newItem!!._id
            }

            override fun areContentsTheSame(oldItem: GsonMember, newItem: GsonMember): Boolean {
                return oldItem == newItem
            }
        }
    }
}