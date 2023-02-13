package com.example.chattest.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chattest.databinding.FragmentMessageBinding
import com.example.chattest.presentation.adapter.ChatUserAdapter
import com.example.chattest.presentation.model.ChatUserItemUiState
import com.example.chattest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class MessageFragment : BaseFragment(), ChatUserAdapter.OnChatUserItemClickListener {

    private lateinit var binding: FragmentMessageBinding
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val chatUserAdapter = ChatUserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
        initializeData()
        subscribeViewModel()
    }

    private fun initializeUi() {
        binding.recyclerUser.adapter = chatUserAdapter
        chatUserAdapter.listener = this
    }

    private fun initializeData() {
        mainViewModel.getMessageUsers()
    }

    private fun subscribeViewModel() {
        mainViewModel.messageUser.observe(viewLifecycleOwner) {
            val currentDate = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

            val itemUiStat = it.userMessages.map { userMessage ->
                val date = LocalDate.parse(userMessage.lastChatDateTime, formatter)
                val lastChatDate : String = when (ChronoUnit.DAYS.between(date, currentDate)) {
                    0L -> "오늘"
                    1L -> "어제"
                    else -> date.toString()
                }

                ChatUserItemUiState(
                    id = userMessage.id,
                    userId = userMessage.userId,
                    imageUrl = userMessage.profileImage,
                    name = userMessage.name,
                    date = lastChatDate,
                    job = userMessage.job,
                    company = userMessage.company,
                    message = userMessage.lastChatMessage,
                    messageCount = userMessage.countUnreadChat
                )
            }
            chatUserAdapter.submitList(itemUiStat)
        }
    }

    override fun onItemClicked(chatUserItemUiState: ChatUserItemUiState) {
        mainViewModel.moveChatRoom(chatUserItemUiState)
    }

    companion object {
        const val TAG = "MessageFragment"
    }

}