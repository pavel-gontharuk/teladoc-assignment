package com.gontharuk.teladocassignment.romeoandjuliet.presentation.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gontharuk.teladocassignment.R
import com.gontharuk.teladocassignment.core.BaseActivity
import com.gontharuk.teladocassignment.core.state.SaveStateManager
import com.gontharuk.teladocassignment.databinding.WordsCountActivityBinding
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountItemMapper
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountUiState
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter.WordCountPresenterFactory
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter.WordsCountPresenter
import java.util.regex.Pattern

class WordsCountActivity : BaseActivity() {

    private lateinit var presenter: WordsCountPresenter

    private val stateManager: SaveStateManager<WordsCountUiState> by lazy(LazyThreadSafetyMode.NONE) {
        WordsCountStateManager(WordsCountItemMapper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = WordCountPresenterFactory().create(
            context = baseContext,
            rawFile = R.raw.romeo_and_juliet,
            pattern = Pattern.compile("\\b[a-zA-Z’]+\\b"),
            initState = stateManager.restore(savedInstanceState)
        )
        val binding = WordsCountActivityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        val adapter = WordsCountAdapter()
        lifecycle.subscribe(presenter)
        binding.rvWordsTable.adapter = adapter
        binding.rvWordsTable.layoutManager = LinearLayoutManager(this)
        presenter.stateObservable.subscribe {
            when (it) {
                WordsCountUiState.Loading -> Unit
                is WordsCountUiState.Show -> adapter.update(it.items)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (!::presenter.isInitialized) return
        val state = presenter.stateObservable.data ?: return
        stateManager.save(state, outState)
    }
}