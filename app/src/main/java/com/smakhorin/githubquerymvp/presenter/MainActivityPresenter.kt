package com.smakhorin.githubquerymvp.presenter

import android.util.Log
import com.smakhorin.githubquerymvp.presenter.adapter.JobsAdapter
import com.smakhorin.githubquerymvp.contract.*
import com.smakhorin.githubquerymvp.model.MainActivityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class MainActivityPresenter(_view: ContractInterface.View): ContractInterface.Presenter {

    private var view: ContractInterface.View = _view
    private var model: ContractInterface.Model = MainActivityModel()

    private var jobsAdapter: JobsAdapter =
        JobsAdapter()

    private var lastPage = 0

    init {
        view.initView()
        jobsAdapter.setHasStableIds(true)
    }

    override fun searchForMoreJobs() {
        lastPage++
        Log.d("Presenter","Last Page:{$lastPage}")
        view.displayBottomLoading(true)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val list = withContext(Dispatchers.IO) { model.getApiClient().getPositions(model.getQuery(), lastPage) }
                model.addJobs(list)
                updateJobs(false)
            } catch (e: Exception) {
                e.printStackTrace()
                view.displayErrorToast("Ошибка получении данных, проверьте работает ли интернет")
            } finally {
                view.displayBottomLoading(false)
            }
        }
    }

    override fun searchForJobs(query: String) {
        if(query.equals("")) {
            view.displayErrorToast("Поле для ввода пустое!")
        } else {
            lastPage = 0
            view.displayLoading(true)
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val list = withContext(Dispatchers.IO) {
                        model.getApiClient().getPositions(query, lastPage)
                    }
                    model.setJobs(list)
                    updateJobs(true)
                } catch (e: Exception) {
                    e.printStackTrace()
                    view.displayErrorToast("Ошибка получении данных, проверьте работает ли интернет")
                } finally {
                    view.displayLoading(false)
                }
            }
        }
    }

    override fun updateJobs(newList: Boolean) {
        val jobs = model.getJobs()
        if(newList) {
            jobsAdapter.setNewList(jobs)
        } else {
            jobsAdapter.addAll(jobs)
        }
        view.updateViewData()
    }

    override fun getJobsAdapter()= jobsAdapter

}