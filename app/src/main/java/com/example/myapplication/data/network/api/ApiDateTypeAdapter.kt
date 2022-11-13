package ar.edu.itba.example.api.data.network.api

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.util.*

class ApiDateTypeAdapter : TypeAdapter<Date?>() {
    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) out.nullValue() else out.value(value.time)
    }

    override fun read(`in`: JsonReader): Date {
        return Date(`in`.nextLong())
    }
}