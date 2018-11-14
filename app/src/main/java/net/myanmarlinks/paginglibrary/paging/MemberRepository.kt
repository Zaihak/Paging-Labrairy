package net.myanmarlinks.paginglibrary.paging

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import net.myanmarlinks.networkdata.model.GsonMember
import net.myanmarlinks.paginglibrary.model.DataLoadState

interface MemberRepository {
    fun getMembers(): LiveData<PagedList<GsonMember>>
    fun getDataLoadStatus(): LiveData<DataLoadState>
}