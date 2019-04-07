package com.leonardis.scoreapp.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import com.leonardis.scoreapp.R
import com.leonardis.scoreapp.models.Fixtures
import com.leonardis.scoreapp.models.Results
import com.leonardis.scoreapp.utils.DAY_FORMAT
import com.leonardis.scoreapp.utils.MONTH_FORMAT
import com.leonardis.scoreapp.utils.WEEK_DAY_FORMAT
import com.leonardis.scoreapp.utils.getDateWithFormat
import com.leonardis.scoreapp.utils.setImageResourceWithSufix
import kotlinx.android.synthetic.main.item_result_view.view.imageView_away
import kotlinx.android.synthetic.main.item_result_view.view.imageView_competition
import kotlinx.android.synthetic.main.item_result_view.view.imageView_home
import kotlinx.android.synthetic.main.item_result_view.view.textView_away
import kotlinx.android.synthetic.main.item_result_view.view.textView_awayScore
import kotlinx.android.synthetic.main.item_result_view.view.textView_competition
import kotlinx.android.synthetic.main.item_result_view.view.textView_date
import kotlinx.android.synthetic.main.item_result_view.view.textView_home
import kotlinx.android.synthetic.main.item_result_view.view.textView_homeScore
import kotlinx.android.synthetic.main.item_result_view.view.textView_state
import kotlinx.android.synthetic.main.item_result_view.view.textView_venue

class ResultViewHolder(itemView: View): GenericViewHolder<Results>(itemView) {

    override fun bindData(data: Results) {
        with(itemView) {
            textView_competition.text = data.competitionStage.competition.name
            textView_home.text = data.homeTeam.shortName
            textView_away.text = data.awayTeam.shortName
            textView_venue.text = data.venue.name
            textView_date.text = getDateWithFormat(data.date, MONTH_FORMAT, resources.configuration.locale)
            textView_homeScore.text = data.score.home.toString()
            textView_awayScore.text = data.score.away.toString()

            imageView_home.setImageResourceWithSufix("ic_${data.homeTeam.abbr.toLowerCase()}")
            imageView_away.setImageResourceWithSufix("ic_${data.awayTeam.abbr.toLowerCase()}")
            imageView_competition.setImageResourceWithSufix("ic_cup_${data.competitionStage.competition.id}")

            textView_state.visibility = if (data.state == resources.getString(R.string.postponed).toLowerCase()) {
                textView_date.setTextColor(ContextCompat.getColor(itemView.context, R.color.state))
                View.VISIBLE
            } else {
                textView_date.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                View.GONE
            }

            data.score.winner?.let {
                if (it == resources.getString(R.string.home).toLowerCase()) {
                    textView_homeScore.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
                    textView_awayScore.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorSecondaryDark))
                } else {
                    textView_homeScore.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorSecondaryDark))
                    textView_awayScore.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
                }
            } ?: run {
                textView_homeScore.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorSecondaryDark))
                textView_awayScore.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorSecondaryDark))
            }
        }
    }
}