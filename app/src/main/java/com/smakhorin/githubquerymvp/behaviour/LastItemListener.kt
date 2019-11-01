package com.smakhorin.githubquerymvp.behaviour

import androidx.recyclerview.widget.RecyclerView

abstract class LastItemListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        // init
        val layoutManager = recyclerView.layoutManager
        val adapter = recyclerView.adapter

        if (layoutManager!!.childCount > 0) {
            // Calculations..
            val indexOfLastItemViewVisible = layoutManager.childCount - 1
            val lastItemViewVisible = layoutManager.getChildAt(indexOfLastItemViewVisible)
            val adapterPosition = layoutManager.getPosition(lastItemViewVisible!!)
            val isLastItemVisible = adapterPosition == adapter!!.itemCount - 1

            // check
            if (isLastItemVisible)
                onLastItemVisible() // callback
        }
    }

    /**
     * Here you should load more items because user is seeing the last item of the list.
     * Advice: you should add a bollean value to the class
     * so that the method [.onLastItemVisible] will be triggered only once
     * and not every time the user touch the screen ;)
     */
    abstract fun onLastItemVisible()

}
