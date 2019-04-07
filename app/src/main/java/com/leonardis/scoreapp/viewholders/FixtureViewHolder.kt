package com.leonardis.scoreapp.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import com.leonardis.scoreapp.R
import com.leonardis.scoreapp.models.Fixtures
import com.leonardis.scoreapp.utils.DAY_FORMAT
import com.leonardis.scoreapp.utils.MONTH_FORMAT
import com.leonardis.scoreapp.utils.WEEK_DAY_FORMAT
import com.leonardis.scoreapp.utils.getDateWithFormat
import com.leonardis.scoreapp.utils.setImageResourceWithSufix
import kotlinx.android.synthetic.main.item_fixture_view.view.imageView_away
import kotlinx.android.synthetic.main.item_fixture_view.view.imageView_competition
import kotlinx.android.synthetic.main.item_fixture_view.view.imageView_home
import kotlinx.android.synthetic.main.item_fixture_view.view.textView_away
import kotlinx.android.synthetic.main.item_fixture_view.view.textView_competition
import kotlinx.android.synthetic.main.item_fixture_view.view.textView_date
import kotlinx.android.synthetic.main.item_fixture_view.view.textView_day
import kotlinx.android.synthetic.main.item_fixture_view.view.textView_home
import kotlinx.android.synthetic.main.item_fixture_view.view.textView_state
import kotlinx.android.synthetic.main.item_fixture_view.view.textView_venue
import kotlinx.android.synthetic.main.item_fixture_view.view.textView_weekDay

class FixtureViewHolder(itemView: View): GenericViewHolder<Fixtures>(itemView) {

    override fun bindData(data: Fixtures) {
        with(itemView) {
            textView_competition.text = data.competitionStage.competition.name
            textView_home.text = data.homeTeam.shortName
            textView_away.text = data.awayTeam.shortName
            textView_venue.text = data.venue.name
            textView_date.text = getDateWithFormat(data.date, MONTH_FORMAT, resources.configuration.locale)
            textView_day.text = getDateWithFormat(data.date, DAY_FORMAT)
            textView_weekDay.text = getDateWithFormat(data.date, WEEK_DAY_FORMAT)

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
        }
    }
}