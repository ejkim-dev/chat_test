package com.example.chattest.presentation.view.fragment

import androidx.fragment.app.Fragment
import com.example.chattest.presentation.extention.UiExtension.showToast
import com.example.chattest.presentation.viewmodel.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

open class BaseFragment : Fragment() {
	protected val disposables = CompositeDisposable()

	fun subscribeViewModelError(viewModel: BaseViewModel) {
		viewModel.error
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe({
					showToast(it)
				}) {
					showToast("${it.message}")
				}
				.addTo(disposables)
	}

	override fun onDestroyView() {
		disposables.clear()
		super.onDestroyView()
	}
}