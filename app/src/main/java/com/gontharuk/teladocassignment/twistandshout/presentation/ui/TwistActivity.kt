package com.gontharuk.teladocassignment.twistandshout.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gontharuk.teladocassignment.core.log
import com.gontharuk.teladocassignment.core.presentation.BaseActivity
import com.gontharuk.teladocassignment.core.presentation.debouncedListener
import com.gontharuk.teladocassignment.core.state.SaveStateManager
import com.gontharuk.teladocassignment.databinding.TwistActivityBinding
import com.gontharuk.teladocassignment.twistandshout.presentation.entity.TwistItemJsonMapper
import com.gontharuk.teladocassignment.twistandshout.presentation.entity.TwistUiState
import com.gontharuk.teladocassignment.twistandshout.presentation.presenter.TwistPresenter
import com.gontharuk.teladocassignment.twistandshout.presentation.presenter.TwistPresenterFactory

class TwistActivity : BaseActivity() {

    companion object {
        const val SEARCH = "search"
    }

    private val stateManager: SaveStateManager<TwistUiState> by lazy(LazyThreadSafetyMode.NONE) {
        TwistStateManager(TwistItemJsonMapper())
    }

    private lateinit var presenter: TwistPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getString(SEARCH)?.log(SEARCH)
        presenter = TwistPresenterFactory()
            .create(
                initState = stateManager.restore(savedInstanceState),
                search = intent.extras?.getString(SEARCH)
            )
        val binding = TwistActivityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        val adapter = TwistAdapter()
        binding.rvTwist.adapter = adapter
        binding.rvTwist.layoutManager = LinearLayoutManager(this)
        binding.btnSearch.debouncedListener {
            presenter.search(binding.etSearch.text.toString())
        }
        lifecycle.subscribe(presenter)
        presenter.state.subscribe {
            when (it) {
                is TwistUiState.Show -> {
                    adapter.update(it.items)
                    binding.etSearch.setText(it.search)
                    binding.rvTwist.visibility = View.VISIBLE
                    binding.pbCircle.visibility = View.GONE
                }

                is TwistUiState.Loading -> {
                    binding.rvTwist.visibility = View.INVISIBLE
                    binding.pbCircle.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (!::presenter.isInitialized) return
        val state = presenter.state.data ?: return
        stateManager.save(state, outState)
    }
}