package nueva.prueba.kavak.alan.andres.interfaces

import io.reactivex.Observable
import nueva.prueba.kavak.alan.andres.models.GnomoModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    @GET("rrafols/mobile_test/master/data.json")
    fun getGnomoList(): Observable<GnomoModel>

    companion object {
        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://raw.githubusercontent.com/")
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}