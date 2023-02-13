package com.example.chattest.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattest.data.constant.TestConst
import com.example.chattest.databinding.FragmentChatBinding
import com.example.chattest.presentation.adapter.ChatRoomAdapter
import com.example.chattest.presentation.extention.UiExtension.clicksWithThrottleFirst
import com.example.chattest.presentation.extention.UiExtension.showToast
import com.example.chattest.presentation.model.ChatItemUiState
import com.example.chattest.presentation.viewmodel.MainViewModel
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChatFragment : BaseFragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val chatRoomAdapter = ChatRoomAdapter()

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
        mainViewModel.setChatItemUiState(null)
    }

    private fun initializeUi() {
        addBackPressCallBack()
        val manager = LinearLayoutManager(context)
        binding.recyclerChatBubble.layoutManager = manager
        binding.recyclerChatBubble.adapter = chatRoomAdapter
    }

    private fun subscriberUi() {
        with(binding) {
            buttonClose.clicksWithThrottleFirst(200)
                .subscribe({
                    mainViewModel.moveMessage()
                }) {
                    showToast("${it.message}")
                }
                .addTo(disposables)

            buttonChatSend.clicksWithThrottleFirst(200)
                .subscribe({
                    if (editTextChat.text.isNullOrEmpty()) {
                        showToast("문자를 입력해 주세요")
                    } else {
                        mainViewModel.updateChatItemUiState(ChatItemUiState(
                            index = (mainViewModel.chatItemUiState.value?.size?.minus(1) ?: 0),
                            messageFrom = TestConst.USER_ID,
                            messageTo = mainViewModel.currentMessageUser.value!!.userId,
                            text = editTextChat.text.toString(),
                            createdDate = ""
                        ))
                        editTextChat.text.clear()
                    }
                }){
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
                val itemUiState = it.chatInfoList?.mapIndexed { index, chatInfo ->
                    ChatItemUiState(
                        index = index,
                        messageFrom = chatInfo.messageFrom,
                        messageTo = chatInfo.messageTo,
                        text = chatInfo.text,
                        createdDate = chatInfo.createdDate,
                        unRead = chatInfo.unRead
                    )
                }
                mainViewModel.setChatItemUiState(itemUiState)
            }
        }

        mainViewModel.chatItemUiState.observe(viewLifecycleOwner) {
            it?.let { chatItemUiStates ->
                chatRoomAdapter.submitList(chatItemUiStates.toMutableList())
                binding.recyclerChatBubble.scrollToPosition(chatRoomAdapter.itemCount.minus(1))
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