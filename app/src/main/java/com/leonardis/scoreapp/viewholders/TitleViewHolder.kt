package com.leonardis.scoreapp.viewholders

import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import kotlinx.android.synthetic.main.item_title_view.view.textView_title

class TitleViewHolder(itemView: View): GenericViewHolder<String>(itemView) {

    override fun bindData(data: String) {
        with(itemView) {
            textView_title .text = data
        }
    }
}