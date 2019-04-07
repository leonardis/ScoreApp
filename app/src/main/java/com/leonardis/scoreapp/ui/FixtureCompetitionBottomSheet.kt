package com.leonardis.scoreapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder
import com.leonardis.scoreapp.R
import com.leonardis.scoreapp.actions.DataActions
import com.leonardis.scoreapp.base.BaseBottomSheetDialogFragment
import com.leonardis.scoreapp.base.di.component.DaggerFragmentComponent
import com.leonardis.scoreapp.databinding.FragmentCompetitionsBinding
import com.leonardis.scoreapp.models.Competition
import com.leonardis.scoreapp.viewholders.CompetitionViewHolder
import com.leonardis.scoreapp.viewmodels.FixtureViewModel
import io.reactivex.rxkotlin.plusAssign
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.leonardis.scoreapp.base.MessageEvent

class FixtureCompetitionBottomSheet: BaseBottomSheetDialogFragment() {

    private lateinit var binding: FragmentCompetitionsBinding

    private val competitionsAdapter = GenericAdapterBuilder()
        .addModel(
            R.layout.item_competition_view,
            CompetitionViewHolder::class.java,
            Competition::class.java)
        .execute()

    //This must to be injected
    lateinit var viewModel: FixtureViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCompetitionsBinding.inflate(inflater, container, false)

        initRecyclerView()
        subscribeToObservables()
        viewModel.getCompetitions()
        binding.button.setOnClickListener {
            viewModel.getFixturesFilteredBy()
            dismiss()
        }

        return binding.root
    }

    override fun onFragmentInject() {
        DaggerFragmentComponent.builder().appComponent(getAppcomponent())
            .build()
            .injectCompetitionsFragment(this)
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = competitionsAdapter
        }
    }


    private fun subscribeToObservables() {
        compositeDisposable += viewModel.getFixtureObservable()
            .subscribe { handleActions(it) }
    }

    private fun handleActions(actions: DataActions) {
        when (actions) {
            is DataActions.OnCompetitionsRetrieved -> competitionsAdapter.setList(actions.competitions)
            is DataActions.OnError -> showError(actions.error)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        viewModel.getFixturesFilteredBy(event.id)
        dismiss()
    }

    companion object {
        fun getInstance(viewModel: FixtureViewModel) : FixtureCompetitionBottomSheet {
            return FixtureCompetitionBottomSheet().apply {
                this.viewModel = viewModel
            }

        }
    }

}