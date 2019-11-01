package com.smakhorin.githubquerymvp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smakhorin.githubquerymvp.behaviour.LastItemListener
import com.smakhorin.githubquerymvp.R
import com.smakhorin.githubquerymvp.contract.ContractInterface
import com.smakhorin.githubquerymvp.presenter.MainActivityPresenter
import com.smakhorin.githubquerymvp.util.KeyboardUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContractInterface.View {

    private var presenter: MainActivityPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)
    }

    override fun initView() {
        btn_search.setOnClickListener {
            KeyboardUtils.hideKeyboard(this)
            presenter?.searchForJobs(tv_query.text.toString())
        }
        rv_jobs_list.setItemAnimator(null)
        rv_jobs_list.addOnScrollListener(object: LastItemListener() {
            override fun onLastItemVisible() {
                presenter?.searchForMoreJobs()
            }
        })
    }

    override fun updateViewData() {
        rv_jobs_list.adapter = presenter?.getJobsAdapter()
    }

    override fun displayLoading(show: Boolean) {
        if(show) {
            rl_loading.visibility = View.VISIBLE
            rv_jobs_list.visibility = View.GONE
        } else {
            rl_loading.visibility = View.GONE
            rv_jobs_list.visibility = View.VISIBLE
        }
    }

    override fun displayBottomLoading(show: Boolean) {
        if(show) {
            rl_bottom_loading.visibility = View.VISIBLE
        } else {
            rl_bottom_loading.visibility = View.GONE
        }
    }

    override fun displayErrorToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
