package ar.com.ualaentrevista.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ar.com.ualaentrevista.R
import ar.com.ualaentrevista.databinding.BaseActivityBinding

abstract class CustomBaseActivity : AppCompatActivity(), CustomBaseView, DialogInterface.OnCancelListener {

    private lateinit var binding: BaseActivityBinding
    private lateinit var closeIcon: MenuItem
    private lateinit var progressDialog: Dialog

    fun layout(): Int = R.layout.base_activity

    abstract fun init()

    open fun setListeners() {}

    private fun getToolbar(): Toolbar? = binding.toolBar

    private fun getToolbarTitleTextView(): TextView? = binding.toolBarTitle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout())
        getToolbar()?.let {
            setUpToolbar(it)
        }

        progressDialog = Dialog(this)
        init()
    }

    protected fun replaceFragmentWithoutBackstack(resId: Int, fragment: Fragment) {
        this.supportFragmentManager.beginTransaction()
            .replace(resId, fragment)
            .commit()
    }

    fun setToolbarTitle(title: String) {
        getToolbarTitleTextView()?.apply {
            text = title
        }
    }

    fun setToolbarBackgroundColor(color: Int) {
        supportActionBar?.let {
            getToolbar()?.setBackgroundColor(ContextCompat.getColor(this, color))
        }
    }

    fun setToolbarTextColor(@ColorRes color: Int) {
        supportActionBar?.let {
            getToolbarTitleTextView()?.run {
                setTextColor(ContextCompat.getColor(this@CustomBaseActivity, color))
            }
            getToolbar()?.run {
                setTitleTextColor(ContextCompat.getColor(this@CustomBaseActivity, color))
                navigationIcon?.run {
                    setColorFilter(ContextCompat.getColor(this@CustomBaseActivity, color), PorterDuff.Mode.SRC_ATOP)
                }
            }
        }
    }

    fun setStatusBarColor(@ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = ContextCompat.getColor(this@CustomBaseActivity, color)
            }
        }
    }

    open fun onFinish() { }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.base_menu, menu)
            closeIcon = it.findItem(R.id.vCloseToolbarButton)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.vCloseToolbarButton -> {
                onFinish()
                return true
            }
            else -> true
        }
    }


    fun showBackButton(showButton: Boolean) = supportActionBar?.setDisplayHomeAsUpEnabled(showButton)

    fun showToolbarCloseIcon(show: Boolean) = closeIcon.setVisible(show)

    abstract fun setUpToolbar(toolbar: Toolbar)

    companion object { }
}