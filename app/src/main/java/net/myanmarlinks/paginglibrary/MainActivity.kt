package net.myanmarlinks.paginglibrary

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.myanmarlinks.paginglibrary.adapter.MemberAdapter
import net.myanmarlinks.paginglibrary.model.DataLoadState
import net.myanmarlinks.paginglibrary.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var memberAdapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        memberAdapter = MemberAdapter(MainActivity@this)
        member_recycler.adapter = memberAdapter
        member_recycler.setHasFixedSize(true)


        val mainModel = ViewModelProviders.of(MainActivity@this).get(MainViewModel::class.java)
        mainModel.getMembers().observe(this, Observer {
            memberAdapter.submitList(it)
        })

        mainModel.dataLoadStatus().observe(this, Observer {
            when(it) {
                DataLoadState.LOADING -> d("MY_DATA", "Data is loading")
                DataLoadState.LOADED -> d("MY_DATA", "Data is loaded")
                DataLoadState.FAILED -> d("MY_DATA", "Data is failed")
            }
        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
