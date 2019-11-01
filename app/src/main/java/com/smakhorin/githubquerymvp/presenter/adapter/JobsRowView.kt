package com.smakhorin.githubquerymvp.presenter.adapter

import com.smakhorin.githubquerymvp.model.net.entity.JobDTO

interface JobsRowView {
    fun setCompanyName(name: String)

    fun setCompanyCity(city: String)

    fun setCompanyLogo(logoUrl: String)

    fun openJobVacancy(jobDTO: JobDTO)
}