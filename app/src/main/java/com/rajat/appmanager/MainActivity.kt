package com.rajat.appmanager

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.rajat.alldemoapp.app_manager.AppsManager
import com.rajat.alldemoapp.app_manager.UninstallAppsAdapter

class MainActivity : AppCompatActivity() {

    private var mActivity: Activity? = null
    private var mContext: Context? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_manager)

        // Get the application context
        mContext = this
        mActivity = this

        // Get the widget reference from XML layout
        val mFab = findViewById<FloatingActionButton>(R.id.fab_refresh)
        mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // Define a layout for RecyclerView
        mLayoutManager = GridLayoutManager(mActivity, 1)
        if (mRecyclerView != null) {
            mRecyclerView!!.layoutManager = mLayoutManager
        }

        // Initialize a new adapter for RecyclerView
        mAdapter = UninstallAppsAdapter(
                AppsManager(mActivity as MainActivity).getInstalledPackages(), mActivity as MainActivity
        )

        // Set the adapter for RecyclerView
        mRecyclerView!!.adapter = mAdapter

        // Set a click listener for floating action button
        mFab.setOnClickListener(View.OnClickListener {
            resetAdapter(mContext as MainActivity, mRecyclerView!!)
        })
    }

    private fun resetAdapter(mContext: MainActivity, mRecyclerView: RecyclerView) {
        mAdapter = UninstallAppsAdapter(
                AppsManager(mContext).getInstalledPackages(), mContext
        )

        // Set the adapter for Recycler view
        // Refresh recycler view with updated data
        mRecyclerView.adapter = mAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 0){
//                mAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        resetAdapter(mActivity as MainActivity, this!!.mRecyclerView!!)
    }
}
