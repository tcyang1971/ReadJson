package tw.edu.pu.csim.tcyang.readjson

import retrofit2.Call
import retrofit2.http.GET

interface JsonService {
    @GET("tcyang_3.json")
    fun getJsonData(): Call<List<JsonResponse>>
}