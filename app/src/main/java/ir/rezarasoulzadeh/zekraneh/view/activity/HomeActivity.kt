package ir.rezarasoulzadeh.zekraneh.view.activity

import android.view.Window
import android.view.WindowManager
import ir.rezarasoulzadeh.zekraneh.base.BaseActivity
import ir.rezarasoulzadeh.zekraneh.databinding.ActivityHomeBinding
import ir.rezarasoulzadeh.zekraneh.utils.extensions.rotate
import ir.rezarasoulzadeh.zekraneh.utils.extensions.vibratePhone
import ir.rezarasoulzadeh.zekraneh.utils.managers.HawkManager
import ir.rezarasoulzadeh.zekraneh.utils.managers.IntentManager
import ir.rezarasoulzadeh.zekraneh.utils.enums.ColorType

class HomeActivity : BaseActivity<ActivityHomeBinding>(
    ActivityHomeBinding::inflate
) {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onAfterCreate() {
        enableFullScreenMode(window = window)
        configClickListeners()
        chooseSelectedTextColor()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      configs                                               //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * handle the action of clickable views.
     */
    private fun configClickListeners() = with(binding) {
        clDetails.setOnClickListener {
            if(elDetails.isExpanded) {
                elDetails.collapse()
                imgDetailsArrow.rotate(destinationRotate = 0f)
            } else {
                elDetails.expand()
                imgDetailsArrow.rotate(destinationRotate = 180f)
            }
        }
        imgZekrRefresh.setOnClickListener {
            vibratePhone()
            imgZekrRefresh.rotate(
                destinationRotate = if(imgZekrRefresh.rotation == 0f) 360f else 0f
            )
            HawkManager.saveZekr(zekr = 0)
            IntentManager.resetZekrIntent(context = this@HomeActivity)
        }
        imgSalavatRefresh.setOnClickListener {
            vibratePhone()
            imgSalavatRefresh.rotate(
                destinationRotate = if(imgSalavatRefresh.rotation == 0f) 360f else 0f
            )
            HawkManager.saveSalavat(salavat = 0)
            IntentManager.resetSalavatIntent(context = this@HomeActivity)
        }
        imgTasbihatRefresh.setOnClickListener {
            vibratePhone()
            imgTasbihatRefresh.rotate(
                destinationRotate = if(imgTasbihatRefresh.rotation == 0f) 360f else 0f
            )
            HawkManager.apply {
                saveTasbihatAA(tasbihatAA = 0)
                saveTasbihatSA(tasbihatSA = 0)
                saveTasbihatHA(tasbihatHA = 0)
            }
            IntentManager.resetTasbihatIntent(context = this@HomeActivity)
        }
        clLanguage.setOnClickListener {
            if(elLanguage.isExpanded) {
                elLanguage.collapse()
                imgLanguageArrow.rotate(destinationRotate = 0f, duration = 150)
            } else {
                elLanguage.expand()
                imgLanguageArrow.rotate(destinationRotate = 180f, duration = 150)
            }
        }
        clColor.setOnClickListener {
            if(elColor.isExpanded) {
                elColor.collapse()
                imgColorArrow.rotate(destinationRotate = 0f, duration = 150)
            } else {
                elColor.expand()
                imgColorArrow.rotate(destinationRotate = 180f, duration = 150)
            }
        }
        rbWhite.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.WHITE)
            IntentManager.apply {
                changeSalavatTextColorIntent(context = this@HomeActivity)
                changeZekrTextColorIntent(context = this@HomeActivity)
                changeTasbihatTextColorIntent(context = this@HomeActivity)
            }
        }
        rbBlack.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.BLACK)
            IntentManager.apply {
                changeSalavatTextColorIntent(context = this@HomeActivity)
                changeZekrTextColorIntent(context = this@HomeActivity)
                changeTasbihatTextColorIntent(context = this@HomeActivity)
            }
        }
        rbGreen.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.GREEN)
            IntentManager.apply {
                changeSalavatTextColorIntent(context = this@HomeActivity)
                changeZekrTextColorIntent(context = this@HomeActivity)
                changeTasbihatTextColorIntent(context = this@HomeActivity)
            }
        }
        rbRed.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.RED)
            IntentManager.apply {
                changeSalavatTextColorIntent(context = this@HomeActivity)
                changeZekrTextColorIntent(context = this@HomeActivity)
                changeTasbihatTextColorIntent(context = this@HomeActivity)
            }
        }
        clStar.setOnClickListener {
            IntentManager.rateIntent(context = this@HomeActivity)
        }
        clShare.setOnClickListener {
            IntentManager.shareTextIntent(
                context = this@HomeActivity,
                title = "معرفی به دوستان",
                description = "سلام"
            )
        }
        clExit.setOnClickListener {
            finish()
        }
    }

    /**
     * update the UI of change widget text color according to getting saved text color from hawk.
     */
    private fun chooseSelectedTextColor() = with(binding) {
        HawkManager.getTextColor().let {
            rbWhite.isChecked = it == ColorType.WHITE
            rbBlack.isChecked = it == ColorType.BLACK
            rbGreen.isChecked = it == ColorType.GREEN
            rbRed.isChecked = it == ColorType.RED
        }
    }

    /**
     * enable the full screen mode for activity.
     */
    private fun enableFullScreenMode(window: Window) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

}