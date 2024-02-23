package ir.rezarasoulzadeh.zekraneh.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

typealias InflateActivity<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding>(
    private var inflate: InflateActivity<VB>
) : AppCompatActivity() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     variables                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    protected lateinit var binding: VB

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate.invoke(layoutInflater)
        setContentView(binding.root)
        onAfterCreate()
    }

    override fun onResume() {
        super.onResume()
        onAfterResume()
    }

    override fun onStop() {
        super.onStop()
        onAfterStop()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     helpers                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * after fragment created, this function will be run.
     */
    protected open fun onAfterCreate() {}

    /**
     * after every time we return to fragment, this function will be run.
     */
    protected open fun onAfterResume() {}

    /**
     * after every time fragment stop, this function will be run.
     */
    protected open fun onAfterStop() {}

}