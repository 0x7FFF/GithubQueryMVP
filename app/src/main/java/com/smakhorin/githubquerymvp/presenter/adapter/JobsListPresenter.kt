package com.smakhorin.githubquerymvp.presenter.adapter

import com.smakhorin.githubquerymvp.model.net.entity.JobDTO

//Presenter doesn't know anything except the data
class JobsListPresenter {

    private var jobs: MutableList<JobDTO> = mutableListOf()

    //if we pressed OK to form a new list
    fun setNewList(jobsList: MutableList<JobDTO>) {
        if(!jobs.isEmpty()) {
            jobs.clear()
        }
        addAll(jobsList)
    }

    //adds everything to the new list (collision checking omitted)
    fun addAll(jobsList: MutableList<JobDTO>) {
        jobs.addAll(jobsList)
    }

    fun onBindJobsRowViewAtPosition(position: Int, rowView: JobsRowView) {
        val job = jobs.get(position)
        rowView.setCompanyCity(job.location)
        if(job.company_logo != null) {
            rowView.setCompanyLogo(job.company_logo)
        } else {
            rowView.setCompanyLogo("")
        }
        rowView.setCompanyName(job.company)
    }

    fun getJobsSize(): Int {
        return jobs.size
    }

    fun getJobAt(position: Int): JobDTO {
        return jobs.get(position)
    }
}