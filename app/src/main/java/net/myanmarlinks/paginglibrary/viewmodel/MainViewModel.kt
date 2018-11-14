package net.myanmarlinks.paginglibrary.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import net.myanmarlinks.networkdata.model.GsonMember
import net.myanmarlinks.paginglibrary.model.DataLoadState
import net.myanmarlinks.paginglibrary.paging.MemberRepoImplementation

class MainViewModel(val respository: MemberRepoImplementation = MemberRepoImplementation()) : ViewModel() {

    fun getMembers(): LiveData<PagedList<GsonMember>> {
        return respository.getMembers()
    }

    fun dataLoadStatus(): LiveData<DataLoadState> {
        return respository.getDataLoadStatus()
    }
}