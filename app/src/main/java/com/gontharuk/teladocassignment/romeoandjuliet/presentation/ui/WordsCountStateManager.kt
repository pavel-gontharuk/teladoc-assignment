package com.gontharuk.teladocassignment.romeoandjuliet.presentation.ui

import android.os.Bundle
import android.util.Log
import com.gontharuk.teladocassignment.core.mapper.JsonMapper
import com.gontharuk.teladocassignment.core.state.SaveStateManager
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

    override fun save(state: WordsCountUiState, outState: Bundle) {
        Bundle().apply {
            putString(stateKey, state::class.simpleName)
            when (state) {
                is WordsCountUiState.Show -> {
                    val array = JSONArray()
                    state.items.forEach {
                        array.put(mapper.map(it))
                    }
                    putString("items", array.toString())
                }

                WordsCountUiState.Loading -> Unit
            }
        }.also { outState.putBundle(query, it) }
    }

    override fun restore(savedState: Bundle?): WordsCountUiState = try {
        val bundle = savedState!!.getBundle(query) ?: throw NullPointerException("No data for [$query]")
        when (bundle.getString(stateKey, "__UNKNOWN__")) {
            WordsCountUiState.Show::class.simpleName -> {
                val array = JSONArray(bundle.getString("items"))
                val list = LinkedList<WordsCountItem>()
                for (index in 0 until array.length()) {
                    val next = array.getString(index)
                    list.add(mapper.map(JSONObject(next)))
                }
                WordsCountUiState.Show(list)
            }

            else -> WordsCountUiState.Loading
        }
    } catch (ex: Exception) {
        Log.e("${this::class.simpleName}", ex.stackTraceToString())
        WordsCountUiState.Loading
    }
}