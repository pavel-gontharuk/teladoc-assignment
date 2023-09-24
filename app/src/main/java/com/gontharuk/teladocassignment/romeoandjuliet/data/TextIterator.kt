package com.gontharuk.teladocassignment.romeoandjuliet.data

import android.content.Context
import androidx.annotation.RawRes
import java.util.Scanner

class TextIterator(
    context: Context,
    @RawRes rawFileId: Int
) : Iterator<String> {

    private val reader: Scanner = Scanner(context.resources.openRawResource(rawFileId))

    override fun hasNext(): Boolean {
        return reader.hasNextLine()
    }

    override fun next(): String {
        return reader.nextLine()
    }
}