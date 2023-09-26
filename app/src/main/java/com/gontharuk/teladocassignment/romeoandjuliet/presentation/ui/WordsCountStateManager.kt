package com.gontharuk.teladocassignment.romeoandjuliet.presentation.ui

import android.os.Bundle
import com.gontharuk.teladocassignment.core.mapper.JsonMapper
import com.gontharuk.teladocassignment.core.state.SaveStateManager
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordFiler
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountItem
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountUiState
import org.json.JSONArray
import org.json.JSONObject
import java.util.LinkedList

class WordsCountStateManager(
    private val mapper: JsonMapper<WordsCountItem>
) : SaveStateManager<WordsCountUiState> {

    private val query: String = "words_count_ui_state"
    private val stateKey: String = "state"
    private val itemsKey: String = "items"
    private val filterKey: String = "filter"

    override fun save(state: WordsCountUiState, outState: Bundle) {
        Bundle().apply {
            putString(stateKey, state::class.qualifiedName)
            when (state) {
                is WordsCountUiState.Show -> {
                    val array = JSONArray()
                    state.items.forEach {
                        array.put(mapper.map(it))
                    }
                    putString(itemsKey, array.toString())
                    putString(filterKey, state.filter.name)
                }

                is WordsCountUiState.Loading -> {
                    putString(filterKey, state.filter.name)
                }
            }
        }.also { outState.putBundle(query, it) }
    }

    override fun restore(savedState: Bundle?): WordsCountUiState = try {
        val bundle = savedState!!.getBundle(query) ?: throw NullPointerException("No data for [$query]")
        when (bundle.getString(stateKey, "__UNKNOWN__")) {
            WordsCountUiState.Show::class.qualifiedName -> {
                val array = JSONArray(bundle.getString(itemsKey))
                val list = LinkedList<WordsCountItem>()
                for (index in 0 until array.length()) {
                    val next = array.getString(index)
                    list.add(mapper.map(JSONObject(next)))
                }

                val filter = WordFiler.valueOf(bundle.getString(filterKey) ?: WordFiler.COUNT.name)
                WordsCountUiState.Show(
                    filter = filter,
                    items = list
                )
            }

            else -> {
                val filter = WordFiler.valueOf(bundle.getString(filterKey) ?: WordFiler.COUNT.name)
                WordsCountUiState.Loading(filter)
            }
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
        WordsCountUiState.Loading()
    }
}