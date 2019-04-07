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
import com.leonardis.scoreapp.databinding.FragmentFixtureBinding
import com.leonardis.scoreapp.models.Fixtures
import com.leonardis.scoreapp.viewholders.FixtureViewHolder
import com.leonardis.scoreapp.viewholders.TitleViewHolder
import com.leonardis.scoreapp.viewmodels.FixtureViewModel
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class FixtureFragment: BaseFragment() {

    private val fixtureAdapter = GenericAdapterBuilder()
        .addModel(
            R.layout.item_fixture_view,
            FixtureViewHolder::class.java,
            Fixtures::class.java)
        .addModel(
            R.layout.item_title_view,
            TitleViewHolder::class.java,
            String::class.java
        )
        .execute()

    private lateinit var binding: FragmentFixtureBinding

    @Inject
    lateinit var viewModel: FixtureViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        onFragmentInject()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fixture, container, false)

        initRecyclerView()
        subscribeToObservables()
        initListeners()

        return binding.root
    }

    override fun onFragmentInject() {
        DaggerFragmentComponent.builder().appComponent(getAppcomponent())
            .build()
            .injectFixtureFragment(this)
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = fixtureAdapter
        }
    }

    private fun subscribeToObservables() {
        compositeDisposable += viewModel.getFixtureObservable()
            .subscribe { handleActions(it) }
    }

    private fun handleActions(actions: DataActions) {
        when (actions) {
            is DataActions.OnDataRetrieved -> fixtureAdapter.setList(actions.data)
            is DataActions.OnError -> showError(actions.error)
        }
    }

    private fun initListeners() {
        binding.imageViewFilter.setOnClickListener {
            val competitionBottomSheet = FixtureCompetitionBottomSheet.getInstance(viewModel)
            competitionBottomSheet.show(childFragmentManager, FixtureCompetitionBottomSheet::class.java.name)

        }
    }
}