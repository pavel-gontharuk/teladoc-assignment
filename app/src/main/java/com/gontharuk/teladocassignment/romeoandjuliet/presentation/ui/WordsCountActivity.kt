package com.gontharuk.teladocassignment.romeoandjuliet.presentation.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gontharuk.teladocassignment.R
import com.gontharuk.teladocassignment.core.presentation.BaseActivity
import com.gontharuk.teladocassignment.core.presentation.debouncedListener
import com.gontharuk.teladocassignment.core.state.SaveStateManager
import com.gontharuk.teladocassignment.databinding.WordsCountActivityBinding
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordFiler
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountItemMapper
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountUiState
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter.WordsCountPresenter
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter.WordsCountPresenterFactory
import java.util.regex.Pattern

class WordsCountActivity : BaseActivity() {

    private lateinit var presenter: WordsCountPresenter

    private val stateManager: SaveStateManager<WordsCountUiState> by lazy(LazyThreadSafetyMode.NONE) {
        WordsCountStateManager(WordsCountItemMapper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = WordsCountPresenterFactory().create(
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
        binding.rbCount.debouncedListener {
            presenter.fetch(WordFiler.COUNT)
        }
        binding.rbAlpha.debouncedListener {
            presenter.fetch(WordFiler.ALPHABETICALLY)
        }
        binding.rbLength.debouncedListener {
            presenter.fetch(WordFiler.LENGTH)
        }
        presenter.state.subscribe {
            when (it) {
                is WordsCountUiState.Show -> adapter.update(it.items)
                is WordsCountUiState.Loading -> Unit
            }
            when (it.filter) {
                WordFiler.COUNT -> binding.rbCount.isChecked = true
                WordFiler.ALPHABETICALLY -> binding.rbAlpha.isChecked = true
                WordFiler.LENGTH -> binding.rbLength.isChecked = true
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