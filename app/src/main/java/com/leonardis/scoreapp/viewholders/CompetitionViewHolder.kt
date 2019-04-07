package com.leonardis.scoreapp.viewholders

import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import com.leonardis.scoreapp.base.MessageEvent
import com.leonardis.scoreapp.models.Competition
import com.leonardis.scoreapp.utils.setImageResourceWithSufix
import kotlinx.android.synthetic.main.item_competition_view.view.imageView_competition
import kotlinx.android.synthetic.main.item_competition_view.view.textView_competition
import org.greenrobot.eventbus.EventBus

class CompetitionViewHolder(itemView: View): GenericViewHolder<Competition>(itemView) {

    override fun bindData(data: Competition) {
        with(itemView) {
            textView_competition.text = data.name
            imageView_competition.setImageResourceWithSufix("ic_cup_${data.id}")
        }
        itemView.setOnClickListener {
            EventBus.getDefault().post(MessageEvent(data.id))
        }
    }
}