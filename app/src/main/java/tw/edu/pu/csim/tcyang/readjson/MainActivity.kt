package tw.edu.pu.csim.tcyang.readjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var BaseUrl = "https://www1.pu.edu.tw/~tcyang/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener{ ReadData() }
    }

    fun ReadData(){
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(JsonService::class.java)
        val call = service.getJsonData()

        call.enqueue(object : Callback<List<JsonResponse>> {
            override fun onResponse(call: Call<List<JsonResponse>>, response: Response<List<JsonResponse>>) {
                if (response.code() == 200) {
                    val Result = response.body()!!
                    var msg = ""
                    for (element in Result) {
                        msg += "校名：" + element.school +
                                "\n地址："+ element.address + "\n\n"
                    }
                    txv.text = msg
                }
                else{
                    txv.text = "找不到檔案"
                }
            }

            override fun onFailure(call: Call<List<JsonResponse>>, t: Throwable) {
                txv.text = t.message
            }
        })
    }
}