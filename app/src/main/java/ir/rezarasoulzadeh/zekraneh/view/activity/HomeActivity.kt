package ir.rezarasoulzadeh.zekraneh.view.activity

import android.view.Window
import android.view.WindowManager
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.base.BaseActivity
import ir.rezarasoulzadeh.zekraneh.databinding.ActivityHomeBinding
import ir.rezarasoulzadeh.zekraneh.utils.enums.ColorType
import ir.rezarasoulzadeh.zekraneh.utils.extensions.rotate
import ir.rezarasoulzadeh.zekraneh.utils.extensions.vibratePhone
import ir.rezarasoulzadeh.zekraneh.utils.managers.HawkManager
import ir.rezarasoulzadeh.zekraneh.utils.managers.IntentManager
import ir.rezarasoulzadeh.zekraneh.utils.managers.SnackbarManager

class HomeActivity : BaseActivity<ActivityHomeBinding>(
    ActivityHomeBinding::inflate
) {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onAfterCreate() {
        enableFullScreenMode(window = window)
        configMenuClickListeners()
        configDetailsClickListeners()
        configColorClickListeners()
        chooseSelectedTextColor()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      configs                                               //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * handle the action of menu clickable views.
     */
    private fun configMenuClickListeners() = with(binding) {
        clDetails.setOnClickListener {
            if(elDetails.isExpanded) {
                elDetails.collapse()
                imgDetailsArrow.rotate(destinationRotate = 0f)
            } else {
                elDetails.expand()
                imgDetailsArrow.rotate(destinationRotate = 180f)
            }
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
        clStar.setOnClickListener {
            IntentManager.rateIntent(
                context = this@HomeActivity,
                view = binding.root
            )
        }
        clShare.setOnClickListener {
            IntentManager.shareTextIntent(
                context = this@HomeActivity,
                view = binding.root,
                title = getString(R.string.introduce_to_friends),
                description = getString(R.string.share_app_with_friends)
            )
        }
        clExit.setOnClickListener {
            finish()
        }
    }

    /**
     * handle the action of details clickable views.
     */
    private fun configDetailsClickListeners() = with(binding) {
        imgZekrRefresh.setOnClickListener {
            vibratePhone()
            imgZekrRefresh.rotate(
                destinationRotate = if(imgZekrRefresh.rotation == 0f) 360f else 0f
            )
            HawkManager.saveZekr(zekr = 0)
            IntentManager.resetZekrIntent(context = this@HomeActivity)
            SnackbarManager.showSnackbar(
                context = this@HomeActivity,
                view = binding.root,
                message = getString(R.string.zekr_has_been_reset)
            )
        }
        imgSalavatRefresh.setOnClickListener {
            vibratePhone()
            imgSalavatRefresh.rotate(
                destinationRotate = if(imgSalavatRefresh.rotation == 0f) 360f else 0f
            )
            HawkManager.saveSalavat(salavat = 0)
            IntentManager.resetSalavatIntent(context = this@HomeActivity)
            SnackbarManager.showSnackbar(
                context = this@HomeActivity,
                view = binding.root,
                message = getString(R.string.salavat_has_been_reset)
            )
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
            SnackbarManager.showSnackbar(
                context = this@HomeActivity,
                view = binding.root,
                message = getString(R.string.tasbihat_has_been_reset)
            )
        }
    }

    /**
     * handle the action of color clickable views.
     */
    private fun configColorClickListeners() = with(binding) {
        rbTextWhite.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.WHITE)
            IntentManager.apply {
                changeSalavatColorIntent(context = this@HomeActivity)
                changeZekrColorIntent(context = this@HomeActivity)
                changeTasbihatColorIntent(context = this@HomeActivity)
            }
        }
        rbTextBlack.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.BLACK)
            IntentManager.apply {
                changeSalavatColorIntent(context = this@HomeActivity)
                changeZekrColorIntent(context = this@HomeActivity)
                changeTasbihatColorIntent(context = this@HomeActivity)
            }
        }
        rbTextGreen.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.GREEN)
            IntentManager.apply {
                changeSalavatColorIntent(context = this@HomeActivity)
                changeZekrColorIntent(context = this@HomeActivity)
                changeTasbihatColorIntent(context = this@HomeActivity)
            }
        }
        rbTextRed.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.RED)
            IntentManager.apply {
                changeSalavatColorIntent(context = this@HomeActivity)
                changeZekrColorIntent(context = this@HomeActivity)
                changeTasbihatColorIntent(context = this@HomeActivity)
            }
        }
    }

    /**
     * update the UI of change widget text color according to getting saved text color from hawk.
     */
    private fun chooseSelectedTextColor() = with(binding) {
        HawkManager.getTextColor().let {
            rbTextWhite.isChecked = it == ColorType.WHITE
            rbTextBlack.isChecked = it == ColorType.BLACK
            rbTextGreen.isChecked = it == ColorType.GREEN
            rbTextRed.isChecked = it == ColorType.RED
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