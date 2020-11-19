package ar.com.ualaentrevista.ui.home

import android.content.DialogInterface
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import ar.com.ualaentrevista.R
import ar.com.ualaentrevista.base.CustomBaseActivity

class SearchListActivity : CustomBaseActivity() {

    override fun init() {
        replaceFragmentWithoutBackstack(R.id.fragmentContainer, SearchListFragment.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        showBackButton(false)
        showToolbarCloseIcon(false)
        return true
    }

    override fun setUpToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.back_icon_white)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setToolbarTextColor(R.color.white)
        setToolbarTitle(resources.getString(R.string.home_search_list_toolbar_title))
        setToolbarBackgroundColor(R.color.toolbar_background)
        setStatusBarColor(R.color.toolbar_background)
    }

    override fun onCancel(p0: DialogInterface?) {
        //TODO("Not yet implemented")
    }

    fun goBack() {
        when (supportFragmentManager.backStackEntryCount) {
            HOME_STEP -> {
                finish()
            }
            DETAIL_STEP -> {
                setToolbarTitle(resources.getString(R.string.home_search_list_toolbar_title))
                showBackButton(false)
                this.supportFragmentManager.popBackStack()
            }
            else -> {
                setToolbarTitle(resources.getString(R.string.home_search_list_toolbar_title))
                this.supportFragmentManager.popBackStack()
            }
        }
    }

    override fun onBackPressed() {
        goBack()
    }

    companion object {
        const val HOME_STEP = 0
        const val DETAIL_STEP = 1
    }

}