package tw.edu.pu.csim.tcyang.readjson

import com.google.gson.annotations.SerializedName

class JsonResponse {
    @SerializedName("校名")
    var school: String? = null
    @SerializedName("地址")
    var address: String? = null
}
