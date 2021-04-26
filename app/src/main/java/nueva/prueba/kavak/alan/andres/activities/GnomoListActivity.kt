package nueva.prueba.kavak.alan.andres.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nueva.prueba.kavak.alan.andres.R
import nueva.prueba.kavak.alan.andres.databinding.ActivityGnomoListBinding

class GnomoListActivity : AppCompatActivity() {
    lateinit var binding: ActivityGnomoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGnomoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}