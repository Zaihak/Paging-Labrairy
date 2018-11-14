package net.myanmarlinks.paginglibrary.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import net.myanmarlinks.networkdata.model.GsonMember
import net.myanmarlinks.networkdata.model.GsonMembers
import net.myanmarlinks.paginglibrary.model.DataLoadState
import net.myanmarlinks.paginglibrary.network.MainService
import net.myanmarlinks.paginglibrary.network.RestApi
import retrofit2.Response
import java.io.IOException

class MemberDataSource(val loadState: MutableLiveData<DataLoadState> = MutableLiveData()) : PageKeyedDataSource<Long, GsonMember>() {

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, GsonMember>) {
        loadState.postValue(DataLoadState.LOADING)

        val request = RestApi.createService(MainService::class.java).getMembers(1)
        val response: Response<GsonMembers>

        try {
            response = request.execute()
            if(response != null) {
                callback.onResult(response.body()!!.gsonMembers, 1, 2)
            } else {
                callback.onResult(ArrayList(), null, 2)
            }
            loadState.postValue(DataLoadState.LOADED)
        } catch (ex: IOException) {
            loadState.postValue(DataLoadState.FAILED)
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, GsonMember>) {
        loadState.postValue(DataLoadState.LOADING)
        val request = RestApi.createService(MainService::class.java).getMembers(params.key)
        var response: Response<GsonMembers>
        try {
            response = request.execute()
            if(response != null) {
                callback.onResult(response.body()!!.gsonMembers, params.key + 1)
            } else {
                callback.onResult(ArrayList(), params.key + 1)
            }
            loadState.postValue(DataLoadState.LOADED)
        } catch (ex: IOException) {
            loadState.postValue(DataLoadState.FAILED)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, GsonMember>) {
        loadState.postValue(DataLoadState.LOADING)

        val request = RestApi.createService(MainService::class.java).getMembers(params.key)
        var response: Response<GsonMembers>
        try {
            response = request.execute()
            if(response != null) {
                val adjacentKey = if (params.key > 1) params.key - 1 else null
                callback.onResult(response.body()!!.gsonMembers, adjacentKey)
            } else {
                callback.onResult(ArrayList(),  params.key -1)
            }
            loadState.postValue(DataLoadState.LOADED)
        } catch (ex: IOException) {
            loadState.postValue(DataLoadState.FAILED)
        }
    }
}