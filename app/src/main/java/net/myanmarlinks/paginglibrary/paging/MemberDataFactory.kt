package net.myanmarlinks.paginglibrary.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import net.myanmarlinks.networkdata.model.GsonMember

class MemberDataFactory(val dataSourceLiveData: MutableLiveData<MemberDataSource> = MutableLiveData()) : DataSource.Factory<Long, GsonMember>() {
    override fun create(): DataSource<Long, GsonMember> {
        val dataSource = MemberDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}