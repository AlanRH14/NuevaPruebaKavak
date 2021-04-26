package nueva.prueba.kavak.alan.andres.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class GnomoModel {
    @SerializedName("Brastlewark")
    var gnomoList: List<Brastlewark> = ArrayList()
}

class Brastlewark : Serializable {
    @SerializedName("id")
    @Expose
    val idGnomo: String = ""

    @SerializedName("name")
    @Expose
    val nameGnomo: String = ""

    @SerializedName("thumbnail")
    @Expose
    val thumbnailGnomo: String = ""

    @SerializedName("age")
    @Expose
    val ageGnomo: String = ""

    @SerializedName("weight")
    @Expose
    val weightGnomo: String = ""

    @SerializedName("height")
    @Expose
    val heightGnomo: String = ""

    @SerializedName("hair_color")
    @Expose
    val hairColorGnomo: String = ""

    @SerializedName("professions")
    @Expose
    val professionsList: List<String> = ArrayList()

    @SerializedName("friends")
    @Expose
    val friendsList: List<String> = ArrayList()
}