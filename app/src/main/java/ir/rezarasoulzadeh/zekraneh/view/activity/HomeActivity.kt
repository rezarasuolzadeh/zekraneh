package ir.rezarasoulzadeh.zekraneh.view.activity

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.service.utils.SharedPrefs
import kotlinx.android.synthetic.main.activity_for_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var sharePrefs: SharedPrefs
    private var widgetId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharePrefs = SharedPrefs(this)

        val bundle = intent.extras

        this.widgetId = bundle!!.getInt("appWidgetId", -1)

        if (this.widgetId == -1) {
            finish()
        }

        setContentView(R.layout.activity_for_home)

        resetZekrButton.setOnClickListener {
            sharePrefs.setCounter("0")
            updateAllWidgets()
            finish()
        }

        resetSalavatButton.setOnClickListener {
            sharePrefs.setSalavat("0")
            updateAllWidgets()
            finish()
        }

        resetTasbihatButton.setOnClickListener {
            sharePrefs.setAA("0")
            sharePrefs.setHA("0")
            sharePrefs.setSA("0")
            updateAllWidgets()
            finish()
        }

        starButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_EDIT)
                intent.data = Uri.parse(this.resources.getString(R.string.bazaarStarLink))
                intent.setPackage(this.resources.getString(R.string.bazaarPackage))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "ابتدا برنامه بازار رو نصب کنید", Toast.LENGTH_SHORT).show()
            }
        }

        developerButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(this.resources.getString(R.string.bazaarDeveloperLink))
                intent.setPackage(this.resources.getString(R.string.bazaarPackage))
                startActivity(intent)
            } catch (e: java.lang.Exception) {
                Toast.makeText(this, "ابتدا برنامه بازار رو نصب کنید", Toast.LENGTH_SHORT).show()
            }
        }

        exitButton.setOnClickListener {
            updateAllWidgets()
            finish()
        }

        sharePrefs = SharedPrefs(this)

    }

    private fun updateAllWidgets() {
        val appWidgetManager = AppWidgetManager.getInstance(this)

        val zekr = appWidgetManager.getAppWidgetIds(
            ComponentName(
                this,
                ZekrActivity::class.java
            )
        )
        ZekrActivity().onUpdate(this, appWidgetManager, zekr)

        val salavat = appWidgetManager.getAppWidgetIds(
            ComponentName(
                this,
                SalavatActivity::class.java
            )
        )
        SalavatActivity().onUpdate(this, appWidgetManager, salavat)
    }

}