package com.smakhorin.githubquerymvp.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smakhorin.githubquerymvp.R
import com.smakhorin.githubquerymvp.model.net.entity.JobDTO

class JobsAdapter: RecyclerView.Adapter<JobsViewHolder>() {

    private var presenter: JobsListPresenter = JobsListPresenter()

    fun setNewList(jobsList: List<JobDTO>) {
        presenter.setNewList(jobsList.toMutableList())
    }

    fun addAll(jobsList: List<JobDTO>) {
        presenter.addAll(jobsList.toMutableList())
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return presenter.getJobsSize()
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        presenter.onBindJobsRowViewAtPosition(position, holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        return JobsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.jobs_list_item,
                parent,
                false
            ), presenter
        )
    }


}