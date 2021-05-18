package nueva.prueba.kavak.alan.andres.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.squareup.picasso.Picasso
import nueva.prueba.kavak.alan.andres.R
import nueva.prueba.kavak.alan.andres.databinding.ActivityGnomoDetailBinding
import nueva.prueba.kavak.alan.andres.models.Brastlewark

class GnomoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGnomoDetailBinding

    companion object {
        lateinit var colors: HashMap<String, Int>
    }

    init {
        colors = object : HashMap<String, Int>() {
            init {
                put("BLACK", Color.BLACK)
                put("GRAY", Color.GRAY)
                put("WHITE", Color.WHITE)
                put("RED", Color.RED)
                put("GREEN", Color.GREEN)
                put("BLUE", Color.BLUE)
                put("YELLOW", Color.YELLOW)
                put("CYAN", Color.CYAN)
                put("PINK", Color.MAGENTA)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGnomoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            toolbar.navigationIcon =
                ContextCompat.getDrawable(
                    this@GnomoDetailActivity,
                    R.drawable.ic_arrow_back
                )
            val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null)

            supportActionBar!!.setHomeAsUpIndicator(drawable)
            supportActionBar!!.title = "Detalle Gnomo"

            toolbar.setNavigationOnClickListener { finish() }

            val gnomo: Brastlewark =
                intent.extras!!.getSerializable("gnomo") as Brastlewark

            //Sale este error en el Log: Picasso: Error 503 Service Temporarily Unavailable
            /*val picasso = Picasso.Builder(this@GnomoDetailActivity)
                .listener { picasso, uri, exception ->
                    Log.e("Picasso", "Error ${exception.message}")
                }
                .build()

            picasso.load(gnomo.thumbnail)
                .fit()
                .into(imgGnomo)*/

            Picasso.get().load(gnomo.thumbnailGnomo)
                .error(R.drawable.ic_gnomo).into(imgGnomo)

            txtIDGnomo.text = "ID: ${gnomo.idGnomo}"
            txtNameGnomo.text = gnomo.nameGnomo
            txtAge.text = "Edad: ${gnomo.ageGnomo}"
            txtWeight.text = "Peso: ${gnomo.weightGnomo}"
            txtHeight.text = "Altura: ${gnomo.heightGnomo}"
            txtTextColor.text = "Color: "
            txtColor.background.setTint(colors[gnomo.hairColorGnomo.toUpperCase()]!!)

            if (gnomo.professionsList.isNotEmpty()) {
                txtTextProfessions.text = "Profesión"
                lytProfessionBtn.visibility = View.VISIBLE

                val professionList: ArrayList<String> = ArrayList()
                professionList.addAll(gnomo.professionsList)

                lytProfessionBtn.setOnClickListener {
                    lytProfessions.removeAllViews()

                    if (lytProfessions.visibility == View.GONE) {
                        lytProfessions.visibility = View.VISIBLE

                        val sizeInDP = 2
                        val marginInDp = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, sizeInDP.toFloat(), resources
                                .displayMetrics
                        ).toInt()
                        val paramsLyt = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1f
                        )

                        paramsLyt.setMargins(marginInDp, marginInDp, marginInDp, marginInDp)
                        var valorIni = 0
                        var cuenta = 0
                        var lytHoriPro: LinearLayout? = null

                        for (x in professionList) {
                            valorIni++
                            cuenta++
                            val txtProfession = TextView(this@GnomoDetailActivity)

                            if (valorIni == 1) {
                                lytHoriPro = LinearLayout(this@GnomoDetailActivity)
                            }

                            lytHoriPro!!.orientation = LinearLayout.HORIZONTAL

                            txtProfession.text = x.trim()
                            txtProfession.setTextColor(Color.BLACK)
                            txtProfession.isAllCaps = false
                            txtProfession.textSize = 22f
                            txtProfession.layoutParams = paramsLyt
                            txtProfession.setTextAppearance(
                                this@GnomoDetailActivity,
                                android.R.style.TextAppearance_Material
                            )

                            if (valorIni <= 2) {
                                lytHoriPro.addView(txtProfession)
                                if (cuenta == professionList.size) {
                                    lytProfessions.addView(lytHoriPro)
                                } else if (valorIni == 2) {
                                    valorIni = 0
                                    lytProfessions.addView(lytHoriPro)
                                }
                            }
                        }
                    } else lytProfessions.visibility = View.GONE
                }
            }

            if (gnomo.friendsList.isNotEmpty()) {
                txtTextFriends.text = "Amigos en común"
                lytFriendsBtn.visibility = View.VISIBLE

                val friendList: ArrayList<String> = ArrayList()
                friendList.addAll(gnomo.friendsList)

                lytFriendsBtn.setOnClickListener {
                    lytFriends.removeAllViews()

                    if (lytFriends.visibility == View.GONE) {
                        lytFriends.visibility = View.VISIBLE

                        val sizeInDP = 2
                        val marginInDp = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, sizeInDP.toFloat(), resources
                                .displayMetrics
                        ).toInt()
                        val paramsLyt = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1f
                        )

                        paramsLyt.setMargins(marginInDp, marginInDp, marginInDp, marginInDp)
                        var valorIni = 0
                        var cuenta = 0
                        var lytHoriFrie: LinearLayout? = null

                        for (x in friendList) {
                            valorIni++
                            cuenta++
                            val txtFriends = TextView(this@GnomoDetailActivity)

                            if (valorIni == 1) {
                                lytHoriFrie = LinearLayout(this@GnomoDetailActivity)
                            }

                            lytHoriFrie!!.orientation = LinearLayout.HORIZONTAL

                            txtFriends.text = x.trim()
                            txtFriends.setTextColor(Color.BLACK)
                            txtFriends.isAllCaps = false
                            txtFriends.textSize = 22f
                            txtFriends.layoutParams = paramsLyt
                            txtFriends.setTextAppearance(
                                this@GnomoDetailActivity,
                                android.R.style.TextAppearance_Material
                            )

                            if (valorIni <= 2) {
                                lytHoriFrie.addView(txtFriends)
                                if (cuenta == friendList.size) {
                                    lytFriends.addView(lytHoriFrie)
                                } else if (valorIni == 2) {
                                    valorIni = 0
                                    lytFriends.addView(lytHoriFrie)
                                }
                            }
                        }
                    } else lytFriends.visibility = View.GONE
                }
            }
        }
    }
}