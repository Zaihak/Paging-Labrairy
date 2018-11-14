package net.myanmarlinks.paginglibrary.newpaging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import net.myanmarlinks.networkdata.model.GsonMember
import net.myanmarlinks.networkdata.model.GsonMembers
import net.myanmarlinks.paginglibrary.model.DataLoadState
import net.myanmarlinks.paginglibrary.network.MainService
import net.myanmarlinks.paginglibrary.network.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MemDataSource(val loadState: MutableLiveData<DataLoadState> = MutableLiveData()): PageKeyedDataSource<Long, GsonMember>() {
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, GsonMember>) {
        loadState.postValue(DataLoadState.LOADING)

        val request = RestApi.createService(MainService::class.java).getMembers(1)
        request.enqueue(object: Callback<GsonMembers> {
            override fun onFailure(call: Call<GsonMembers>, t: Throwable) {
                loadState.postValue(DataLoadState.FAILED)
            }

            override fun onResponse(call: Call<GsonMembers>, response: Response<GsonMembers>) {
                if(response.isSuccessful) {
                    callback.onResult(response.body()!!.gsonMembers, 1, 2)
                    loadState.postValue(DataLoadState.LOADED)
                } else {
                    loadState.postValue(DataLoadState.FAILED)
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, GsonMember>) {
        loadState.postValue(DataLoadState.LOADING)

        val request = RestApi.createService(MainService::class.java).getMembers(1)
        request.enqueue(object: Callback<GsonMembers> {
            override fun onFailure(call: Call<GsonMembers>, t: Throwable) {
                loadState.postValue(DataLoadState.FAILED)
            }

            override fun onResponse(call: Call<GsonMembers>, response: Response<GsonMembers>) {
                if(response.isSuccessful) {

                    callback.onResult(response.body()!!.gsonMembers, params.key + 1)
                    loadState.postValue(DataLoadState.LOADED)
                } else {
                    loadState.postValue(DataLoadState.FAILED)
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, GsonMember>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}