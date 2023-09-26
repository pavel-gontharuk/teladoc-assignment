package com.gontharuk.teladocassignment.twistandshout.presentation.ui

import android.os.Bundle
import com.gontharuk.teladocassignment.core.mapper.JsonMapper
import com.gontharuk.teladocassignment.core.state.SaveStateManager
import com.gontharuk.teladocassignment.twistandshout.presentation.entity.TwistItem
import com.gontharuk.teladocassignment.twistandshout.presentation.entity.TwistUiState
import org.json.JSONArray
import org.json.JSONObject
import java.util.LinkedList

class TwistStateManager(
    private val mapper: JsonMapper<TwistItem>
) : SaveStateManager<TwistUiState> {

    private val query: String = "words_count_ui_state"
    private val stateKey: String = "state"
    private val searchKey: String = "search"
    private val itemsKey: String = "items"

    override fun save(state: TwistUiState, outState: Bundle) {
        Bundle().apply {
            putString(stateKey, state::class.qualifiedName)
            when (state) {
                is TwistUiState.Show -> {
                    val array = JSONArray()
                    state.items.forEach {
                        array.put(mapper.map(it))
                    }
                    putString(itemsKey, array.toString())
                    putString(searchKey, state.search)
                }

                is TwistUiState.Loading -> Unit
            }
        }.also { outState.putBundle(query, it) }
    }

    override fun restore(savedState: Bundle?): TwistUiState = try {
        val bundle = savedState!!.getBundle(query) ?: throw NullPointerException("No data for [$query]")
        when (bundle.getString(stateKey, "__UNKNOWN__")) {
            TwistUiState.Show::class.qualifiedName -> {
                val array = JSONArray(bundle.getString(itemsKey))
                val list = LinkedList<TwistItem>()
                for (index in 0 until array.length()) {
                    val next = array.getString(index)
                    list.add(mapper.map(JSONObject(next)))
                }
                TwistUiState.Show(
                    search = bundle.getString(searchKey, ""),
                    items = list
                )
            }

            else -> TwistUiState.Loading()
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
        TwistUiState.Loading()
    }

}