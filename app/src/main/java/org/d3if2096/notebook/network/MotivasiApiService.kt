package org.d3if2096.notebook.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2096.notebook.model.Motivasi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/teguharfnt/api_json/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MotivasiApiService {
    @GET("motivasi.json")
    suspend fun getMotivasi() : List<Motivasi>
}

object MotivasiApi {
    val service: MotivasiApiService by lazy {
        retrofit.create(MotivasiApiService::class.java)
    }

    fun getMotivasiUrl(motivasi : String): String {
        return "$BASE_URL$motivasi.jpg"
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}