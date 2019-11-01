package com.smakhorin.githubquerymvp.model

import com.smakhorin.githubquerymvp.contract.ContractInterface
import com.smakhorin.githubquerymvp.model.net.ApiClient
import com.smakhorin.githubquerymvp.model.net.entity.JobDTO

class MainActivityModel: ContractInterface.Model {
    //Api Client
    private var apiClient = ApiClient()
    //Jobs List
    private lateinit var jobsList: MutableList<JobDTO>

    //Keeps track of the query to use it when it needs to load more
    private var mQuery = ""

    override fun setQuery(query: String) {
        mQuery = query
    }

    override fun addJobs(jobs: List<JobDTO>) {
        jobsList.addAll(jobs)
    }

    override fun setJobs(jobs: List<JobDTO>) {
        jobsList = jobs.toMutableList()
    }

    override fun getQuery()=mQuery

    override fun getApiClient()= apiClient

    override fun getJobs()=jobsList
}