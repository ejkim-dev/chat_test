package com.example.chattest.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LifecycleOwner
import com.example.chattest.R
import com.example.chattest.databinding.FragmentChatBinding
import com.example.chattest.presentation.extention.UiExtension.showToast
import com.example.chattest.presentation.viewmodel.MainViewModel
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChatFragment : BaseFragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
        initializeData()
        subscriberUi()
        subscribeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        onBackPressedCallback.remove()
    }

    private fun initializeUi() {
        addBackPressCallBack()
    }

    private fun subscriberUi() {
        with(binding) {
            buttonClose.clicks()
                .subscribe({
                    mainViewModel.moveMessage()
                }) {
                    showToast("${it.message}")
                }
                .addTo(disposables)
        }
    }

    private fun initializeData() {
        if (mainViewModel.currentMessageUser.value == null) {
            showToast("해당 채팅 방이 존재하지 않습니다.")
            mainViewModel.moveMessage()
        } else {
            mainViewModel.getChatMessage(mainViewModel.currentMessageUser.value!!.id)
        }
    }

    private fun subscribeViewModel() {
        val currentRoomId = mainViewModel.currentMessageUser.value?.id

        mainViewModel.chatData.observe(viewLifecycleOwner) {
            if (currentRoomId == it.roomId) {
                Log.d("####", "subscribeViewModel: $it")
            }
        }
    }

    private fun addBackPressCallBack() {
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mainViewModel.moveMessage()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this as LifecycleOwner, onBackPressedCallback)
    }

    companion object {
        const val TAG = "ChatFragment"
    }
}