package ir.rezarasoulzadeh.zekraneh.view.activity

import android.content.Context
import android.content.Intent
import android.view.Window
import android.view.WindowManager
import ir.rezarasoulzadeh.zekraneh.base.BaseActivity
import ir.rezarasoulzadeh.zekraneh.databinding.ActivityHomeBinding
import ir.rezarasoulzadeh.zekraneh.service.utils.extensions.rotate
import ir.rezarasoulzadeh.zekraneh.service.utils.extensions.vibratePhone
import ir.rezarasoulzadeh.zekraneh.utils.Constants.RESET_SALAVAT
import ir.rezarasoulzadeh.zekraneh.utils.Constants.RESET_TASBIHAT
import ir.rezarasoulzadeh.zekraneh.utils.Constants.RESET_ZEKR
import ir.rezarasoulzadeh.zekraneh.utils.HawkManager
import ir.rezarasoulzadeh.zekraneh.utils.IntentManager

class HomeActivity : BaseActivity<ActivityHomeBinding>(
    ActivityHomeBinding::inflate
) {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onAfterCreate() {
        enableFullScreenMode(window = window)
        configClickListeners()
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
                imgDetailsArrow.rotate(destinationRotate = 0f, duration = 150)
            } else {
                elDetails.expand()
                imgDetailsArrow.rotate(destinationRotate = 180f, duration = 150)
            }
        }
        imgZekrRefresh.setOnClickListener {
            vibratePhone()
            HawkManager.saveZekr(zekr = 0)
            IntentManager.resetZekrIntent(context = this@HomeActivity)
        }
        imgSalavatRefresh.setOnClickListener {
            vibratePhone()
            HawkManager.saveSalavat(salavat = 0)
            IntentManager.resetSalavatIntent(context = this@HomeActivity)
        }
        imgTasbihatRefresh.setOnClickListener {
            vibratePhone()
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
            // nothing to do yet
        }
        rbBlack.setOnClickListener {
            // nothing to do yet
        }
        rbGreen.setOnClickListener {
            // nothing to do yet
        }
        rbRed.setOnClickListener {
            // nothing to do yet
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
     * enable the full screen mode for activity.
     */
    private fun enableFullScreenMode(window: Window) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

}