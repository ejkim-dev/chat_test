package com.example.chattest.presentation.extention

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object UiExtension {
	fun Fragment.showToast(message: String) {
		Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
	}

	fun Activity.showToast(message: String) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
	}

	fun View.showToast(message: String) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
	}

	fun View.show() {
		this.visibility = View.VISIBLE
	}

	fun View.hideInvisible() {
		this.visibility = View.INVISIBLE
	}

	fun View.hide() {
		this.visibility = View.GONE
	}

	fun View.isShowing(): Boolean {
		return this.visibility == View.VISIBLE
	}

	fun View.clicksWithThrottleFirst(durationMillis: Long): Observable<Unit> {
		return this.clicks()
			.throttleFirst(durationMillis, TimeUnit.MILLISECONDS)
			.observeOn(AndroidSchedulers.mainThread())
	}
}