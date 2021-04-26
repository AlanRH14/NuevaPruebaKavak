package nueva.prueba.kavak.alan.andres.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import nueva.prueba.kavak.alan.andres.R
import nueva.prueba.kavak.alan.andres.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    private var init = 0
    private val splashTime = 3000
    private var multiplePermission = 1

    var permissions = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissions.add("android.permission.INTERNET")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            multiplePermission -> {
                if (grantResults.isNotEmpty()) {
                    var isValid = true
                    var i = 0

                    while (i < grantResults.size) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            isValid = false
                            break
                        }
                        i++
                    }

                    if (isValid) carga()
                    else mDialogPermission()
                } else mDialogPermission()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (checkPermissions()) carga()
    }

    fun checkPermissions(): Boolean {
        var result: Int
        var isValid = true
        val listPermissionNeeded: MutableList<String> = ArrayList()
        init = 1

        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(this, p)

            if (result != PackageManager.PERMISSION_GRANTED) listPermissionNeeded.add(p)
        }

        if (listPermissionNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionNeeded.toTypedArray(),
                multiplePermission
            )

            isValid = false
        }

        return isValid
    }

    private fun mDialogPermission() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)

        dialog.setTitle(getString(R.string.app_name)).setCancelable(false)
            .setNegativeButton("Cancelar") {
                dialog, _ ->
                dialog.cancel()
                finish()
            }.setPositiveButton("Ir a configuración") { dialog, _ ->
                val callSettingIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                callSettingIntent.data = Uri.parse("nueva.prueba.kavak.alan.andres")
                startActivity(callSettingIntent)
                dialog.cancel()
                init = 0
            }

        val alert = dialog.create()

        if (alert.isShowing) {
            alert.setMessage("Para el correcto funcinamiento de " + getString(R.string.app_name) + " debe de activar los permisos/accesos de la aplicación")
            alert.show()
        }
    }

    private fun carga() {
        Handler().postDelayed({
            val intent = Intent(applicationContext, GnomoListActivity::class.java)
            startActivity(intent)
        }, splashTime.toLong())
    }
}