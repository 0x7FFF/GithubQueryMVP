package com.smakhorin.githubquerymvp.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.smakhorin.githubquerymvp.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val bundle :Bundle? = intent.extras
        if(bundle != null) {
            tv_job_type.text = intent.getStringExtra("jobType")
            tv_job_url.text = intent.getStringExtra("jobUrl")
            tv_job_date_posted.text = intent.getStringExtra("jobDate")
            tv_job_company_name.text = intent.getStringExtra("jobCompanyName")
            tv_job_company_website.text = intent.getStringExtra("jobCompanyUrl")
            tv_job_city.text = intent.getStringExtra("jobLocation")
            tv_job_full_description.movementMethod = ScrollingMovementMethod()
            //Format the HTML text
            val unformattedDescription = intent.getStringExtra("jobDescription")
            setSpannableText(unformattedDescription,tv_job_full_description)
        }

    }

    fun setSpannableText(str: String, textView: TextView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(str,  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE)
        } else {
            textView.setText(Html.fromHtml(str), TextView.BufferType.SPANNABLE);
        }
    }
}
