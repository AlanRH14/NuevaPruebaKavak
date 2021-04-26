package nueva.prueba.kavak.alan.andres.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nueva.prueba.kavak.alan.andres.adapters.GnomoAdapter
import nueva.prueba.kavak.alan.andres.databinding.ActivityGnomoListBinding
import nueva.prueba.kavak.alan.andres.interfaces.APIService
import nueva.prueba.kavak.alan.andres.models.Brastlewark

class GnomoListActivity : AppCompatActivity() {
    lateinit var gnomoAdapter: GnomoAdapter

    private lateinit var binding: ActivityGnomoListBinding
    private var disposable: Disposable? = null
    private var gnomoList: ArrayList<Brastlewark> = ArrayList()
    private var gnomoSearch: ArrayList<Brastlewark> = ArrayList()
    private val apiService by lazy { APIService.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGnomoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar!!.title = "Bienvenido"

            gnomoAdapter = object : GnomoAdapter(this@GnomoListActivity, gnomoList) {
                override fun getDataGnomo(gnomo: Brastlewark) {
                    val intent = Intent(this@GnomoListActivity, GnomoDetailActivity::class.java)
                    intent.putExtra("gnomo", gnomo)
                    startActivity(intent)
                }
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gnomoAdapter
            }

            edtSearch.editText!!.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    gnomoList = ArrayList()

                    if (text.toString().isNotEmpty()) {
                        for (x in gnomoSearch) {
                            if (x.nameGnomo.toUpperCase().contains(text.toString().toUpperCase()))
                                gnomoList.add(x)
                        }

                        gnomoAdapter.refreshData(gnomoList)
                    } else {
                        gnomoList.addAll(gnomoSearch)
                        gnomoAdapter.refreshData(gnomoList)
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

            btnAceptMsg.setOnClickListener { pgdMsgError.visibility = View.GONE }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.pgdEspera.visibility = View.VISIBLE
        parseJSON()
    }

    private fun parseJSON() {
        disposable = apiService.getGnomoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                gnomoList.clear()
                gnomoList.addAll(result.gnomoList)
                gnomoAdapter.notifyDataSetChanged()

                gnomoSearch.clear()
                gnomoSearch.addAll(result.gnomoList)

                binding.pgdEspera.visibility = View.GONE
            },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                    with(binding){

                        txtMsg.text = error.message
                        pgdEspera.visibility = View.GONE
                        pgdMsgError.visibility = View.VISIBLE
                    }
                })
    }

    fun Activity.hideKeyBoard() = hideKeyBoard(currentFocus ?: View(this))

    fun Context.hideKeyBoard(view: View) {
        val inptMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inptMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}