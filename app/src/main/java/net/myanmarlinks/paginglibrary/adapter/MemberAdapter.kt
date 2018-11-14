package net.myanmarlinks.paginglibrary.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.member_card.view.*
import me.myatminsoe.mdetect.MDetect
import net.myanmarlinks.networkdata.model.GsonMember
import net.myanmarlinks.paginglibrary.R
import net.myanmarlinks.paginglibrary.util.GlideApp
import net.myanmarlinks.paginglibrary.util.Rabbit
import net.myanmarlinks.paginglibrary.util.TypefaceManager

class MemberAdapter(val context: Context): PagedListAdapter<GsonMember, MemberAdapter.MemberViewHolder>(GsonMember.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_card, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = getItem(position)
        if (member != null) {
            holder.bindTo(member)
        } else {
            holder.clear()
        }
    }


    inner class MemberViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val memberImg = itemView.member_img
        private val memberName =  itemView.member_name

        fun bindTo(gsonMember: GsonMember) {
            val typefaceManager = TypefaceManager(context.assets)
            if(MDetect.isUnicode()) {
                memberName.text = gsonMember.name
                memberName.typeface = typefaceManager.sagar
            } else {
                memberName.text = Rabbit.uni2zg(gsonMember.name)
                memberName.typeface = typefaceManager.zawgyi
            }

            GlideApp.with(context)
                    .load(gsonMember.profile_pic)
                    .placeholder(R.mipmap.ic_launcher)
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(memberImg)

            itemView.setOnClickListener { Toast.makeText(context, gsonMember.name, Toast.LENGTH_LONG).show() }

        }

        fun clear() {
            memberImg.setImageURI(null)
            memberName.text = null
        }
    }
}