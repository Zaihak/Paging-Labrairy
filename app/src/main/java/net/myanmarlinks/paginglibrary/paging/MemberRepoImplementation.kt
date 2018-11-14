package net.myanmarlinks.paginglibrary.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import net.myanmarlinks.networkdata.model.GsonMember
import net.myanmarlinks.paginglibrary.model.DataLoadState
import net.myanmarlinks.paginglibrary.util.Const
import java.util.concurrent.Executors

class MemberRepoImplementation(private val dataSourceFactory: MemberDataFactory = MemberDataFactory()) : MemberRepository {
    override fun getMembers(): LiveData<PagedList<GsonMember>> {
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(Const.MEMBER_PAGE_SIZE)
                .setPageSize(Const.MEMBER_PAGE_SIZE)
                .build()
        val executer = Executors.newFixedThreadPool(5)
        val mymembers = LivePagedListBuilder(dataSourceFactory, config)
                .setInitialLoadKey(1)
                .setFetchExecutor(executer)
                .build()
        return mymembers
    }

    override fun getDataLoadStatus(): LiveData<DataLoadState> {
        return Transformations.switchMap(dataSourceFactory.dataSourceLiveData
        ) { dataSource -> dataSource.loadState }
    }
}