package com.smakhorin.githubquerymvp.contract

import com.smakhorin.githubquerymvp.presenter.adapter.JobsAdapter
import com.smakhorin.githubquerymvp.model.net.ApiClient
import com.smakhorin.githubquerymvp.model.net.entity.JobDTO

interface ContractInterface {

    interface View {
        fun initView()
        fun updateViewData()
        fun displayLoading(show: Boolean)
        fun displayBottomLoading(show: Boolean)
        fun displayErrorToast(message: String)
    }

    interface Presenter {
        fun searchForJobs(query: String)
        fun searchForMoreJobs()
        fun getJobsAdapter(): JobsAdapter
        fun updateJobs(newList: Boolean)
    }

    interface Model {
        fun setQuery(query: String)
        fun getQuery(): String
        fun getApiClient(): ApiClient
        fun addJobs(jobs: List<JobDTO>)
        fun setJobs(jobs: List<JobDTO>)
        fun getJobs(): List<JobDTO>
    }

}