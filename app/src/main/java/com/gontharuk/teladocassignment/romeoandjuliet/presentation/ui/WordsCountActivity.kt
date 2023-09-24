package com.gontharuk.teladocassignment.romeoandjuliet.presentation.ui

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gontharuk.teladocassignment.R
import com.gontharuk.teladocassignment.databinding.WordsCountActivityBinding
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountUiState
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter.WordCountPresenterFactory
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter.WordsCountPresenter
import java.util.regex.Pattern

class WordsCountActivity : Activity() {

    private val presenter: WordsCountPresenter by lazy(LazyThreadSafetyMode.NONE) {
        WordCountPresenterFactory().create(
            context = baseContext,
            rawFile = R.raw.romeo_and_juliet,
            pattern = Pattern.compile("\\b[a-zA-Z’]+\\b")
        )
    }

    private val adapter: WordsCountAdapter = WordsCountAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = WordsCountActivityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding.rvWordsTable.adapter = adapter
        binding.rvWordsTable.layoutManager = LinearLayoutManager(this)

    }

    override fun onStart() {
        super.onStart()
        presenter.stateObservable.subscribe {
            when (it) {
                WordsCountUiState.Loading -> Unit
                is WordsCountUiState.Show -> adapter.update(it.items)
            }
        }
        presenter.attach()
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }
}