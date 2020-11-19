package ar.com.ualaentrevista.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

abstract class CustomBaseFragment<T : androidx.databinding.ViewDataBinding, P : CustomBasePresenter<V>, V : CustomBaseView> : Fragment(),
    CustomBaseView {

    lateinit var binding: T
    abstract val presenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false)
        return binding.root
    }

    abstract fun layout(): Int

    open fun init() {}

    open fun setListeners() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this as V)
        init()
        setListeners()
    }

    open fun showBackButton(show: Boolean) {
        (activity as? CustomBaseActivity)?.showBackButton(show)
    }
}

interface CustomBaseView { }