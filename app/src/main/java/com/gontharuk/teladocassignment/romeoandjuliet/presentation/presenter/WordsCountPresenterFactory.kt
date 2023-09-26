package com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter

import android.content.Context
import androidx.annotation.RawRes
import com.gontharuk.teladocassignment.romeoandjuliet.data.TextIterator
import com.gontharuk.teladocassignment.romeoandjuliet.data.WordsCountRepositoryImpl
import com.gontharuk.teladocassignment.romeoandjuliet.data.repository.WordsCountRepository
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountUiState
import java.util.regex.Pattern

class WordsCountPresenterFactory {

    fun create(
        context: Context,
        @RawRes rawFile: Int,
        pattern: Pattern,
        initState: WordsCountUiState
    ): WordsCountPresenter {
        val iterator: Iterator<String> = TextIterator(
            context = context,
            rawFileId = rawFile
        )
        val repository: WordsCountRepository = WordsCountRepositoryImpl(
            stringIterator = iterator,
            pattern = pattern
        )
        return WordsCountPresenter(repository, initState)
    }
}