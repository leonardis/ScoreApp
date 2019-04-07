package com.leonardis.scoreapp.base.di.component

import com.leonardis.scoreapp.base.di.ActivityScope
import com.leonardis.scoreapp.ui.FixtureCompetitionBottomSheet
import com.leonardis.scoreapp.ui.FixtureFragment
import com.leonardis.scoreapp.ui.ResultFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface  FragmentComponent {
    fun injectFixtureFragment(fixtureFragment: FixtureFragment)
    fun injectResultFragment(resultFragment: ResultFragment)
    fun injectCompetitionsFragment(fixtureCompetitionBottomSheet: FixtureCompetitionBottomSheet)
}