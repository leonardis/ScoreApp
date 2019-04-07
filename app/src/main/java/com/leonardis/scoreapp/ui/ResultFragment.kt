package com.leonardis.scoreapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder
import com.leonardis.scoreapp.R
import com.leonardis.scoreapp.actions.DataActions
import com.leonardis.scoreapp.base.BaseFragment
import com.leonardis.scoreapp.base.di.component.DaggerFragmentComponent
import com.leonardis.scoreapp.databinding.FragmentResultBinding
import com.leonardis.scoreapp.models.Results
import com.leonardis.scoreapp.viewholders.ResultViewHolder
import com.leonardis.scoreapp.viewholders.TitleViewHolder
import com.leonardis.scoreapp.viewmodels.ResultViewModel
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class ResultFragment: BaseFragment() {

    private val resultAdapter = GenericAdapterBuilder()
        .addModel(
            R.layout.item_result_view,
            ResultViewHolder::class.java,
            Results::class.java)
        .addModel(
            R.layout.item_title_view,
            TitleViewHolder::class.java,
            String::class.java
        )
        .execute()

    private lateinit var binding: FragmentResultBinding

    @Inject
    lateinit var viewModel: ResultViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        onFragmentInject()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        initRecyclerView()
        subscribeToObservables()
        initListeners()

        return binding.root
    }

    override fun onFragmentInject() {
        DaggerFragmentComponent.builder().appComponent(getAppcomponent())
            .build()
            .injectResultFragment(this)
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = resultAdapter
        }
    }

    private fun subscribeToObservables() {
        compositeDisposable += viewModel.getFixtureObservable()
            .subscribe { handleActions(it) }
    }

    private fun handleActions(actions: DataActions) {
        when (actions) {
            is DataActions.OnDataRetrieved -> resultAdapter.setList(actions.data)
            is DataActions.OnError -> showError(actions.error)
        }
    }

    private fun initListeners() {
        binding.imageViewFilter.setOnClickListener {
            val competitionBottomSheet = ResultCompetitionBottomSheet.getInstance(viewModel)
            competitionBottomSheet.show(childFragmentManager, ResultCompetitionBottomSheet::class.java.name)

        }
    }
}