package com.example.chattest.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.chattest.R
import com.example.chattest.databinding.ActivityMainBinding
import com.example.chattest.presentation.constant.KeyConstant
import com.example.chattest.presentation.extention.UiExtension.hide
import com.example.chattest.presentation.extention.UiExtension.show
import com.example.chattest.presentation.extention.UiExtension.showToast
import com.example.chattest.presentation.model.MainUiState
import com.example.chattest.presentation.view.fragment.*
import com.example.chattest.presentation.viewmodel.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscriberUi()
        subscribeViewModel()
    }

    private fun subscriberUi() {
        val navigationHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment

        with(binding) {
            navigationBottom.setupWithNavController(navigationHostFragment.navController)
            navigationBottom.run {
                setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_home -> mainViewModel.moveHome()
                        R.id.navigation_people -> mainViewModel.movePeople()
                        R.id.navigation_message -> mainViewModel.moveMessage()
                        R.id.navigation_my_page -> mainViewModel.moveMyPage()
                        R.id.navigation_recruit -> mainViewModel.moveRecruit()
                    }
                    true
                }
                selectedItemId = R.id.navigation_message
            }
        }
    }

    private fun showNavigationFragment(fragment: Fragment, tag: String) {
        binding.navigationBottom.show()
        showFragment(fragment, tag)
    }

    private fun showChatFragment() {
        binding.navigationBottom.hide()
     /*   val bundle = Bundle().apply {
            putInt(KeyConstant.KEY_CHAT, id!!)
        }

        val fragment = ChatFragment().apply { arguments = bundle }*/
        showFragment(ChatFragment(), ChatFragment.TAG)
    }

    private fun subscribeViewModel() {
        mainViewModel.error
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showToast(it)
            }) {
                showToast("${it.message}")
            }
            .addTo(disposables)

        mainViewModel.mainUiState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               when(it.status) {
                   MainUiState.ViewStatus.HOME -> showNavigationFragment(HomeFragment(), HomeFragment.TAG)
                   MainUiState.ViewStatus.PEOPLE -> showNavigationFragment(PeopleFragment(), PeopleFragment.TAG)
                   MainUiState.ViewStatus.MESSAGE -> showNavigationFragment(MessageFragment(), MessageFragment.TAG)
                   MainUiState.ViewStatus.MYPAGE -> showNavigationFragment(MyPageFragment(), MyPageFragment.TAG)
                   MainUiState.ViewStatus.RECRUIT -> showNavigationFragment(RecruitFragment(), RecruitFragment.TAG)
                   MainUiState.ViewStatus.CHAT -> showChatFragment()
               }
            }) {
                showToast("${it.message}")
            }
            .addTo(disposables)
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.navigation_host_fragment, fragment, tag)
            .commit()
    }
}