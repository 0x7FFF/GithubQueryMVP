package com.smakhorin.githubquerymvp.presenter.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smakhorin.githubquerymvp.R
import com.smakhorin.githubquerymvp.model.net.entity.JobDTO
import com.smakhorin.githubquerymvp.view.DetailsActivity
import org.jetbrains.anko.find

class JobsViewHolder(itemView: View, presenter: JobsListPresenter) : RecyclerView.ViewHolder(itemView),
    JobsRowView {
    private var companyLogo: ImageView = itemView.find(R.id.iv_company_logo)

    private var companyCity: TextView = itemView.find(R.id.tv_company_city)
    private var companyName: TextView = itemView.find(R.id.tv_company_name)
    init {
        itemView.setOnClickListener {
            val job = presenter.getJobAt(adapterPosition)
            openJobVacancy(job)
        }
    }

    override fun openJobVacancy(jobDTO: JobDTO) {
        val intent = Intent(itemView.context, DetailsActivity::class.java)
        intent.putExtra("jobType",jobDTO.type)
        intent.putExtra("jobUrl",jobDTO.url)
        intent.putExtra("jobDate",jobDTO.created_at)
        intent.putExtra("jobCompanyName",jobDTO.company)
        intent.putExtra("jobCompanyUrl",jobDTO.company_url)
        intent.putExtra("jobLocation",jobDTO.location)
        intent.putExtra("jobDescription",jobDTO.description)
        itemView.context.startActivity(intent)
    }

    override fun setCompanyLogo(logoUrl: String) {
        Glide.with(itemView)
            .load(logoUrl)
            .into(companyLogo)
    }

    override fun setCompanyCity(city: String) {
        companyCity.text = city
    }

    override fun setCompanyName(name: String) {
        companyName.text = name
    }

}