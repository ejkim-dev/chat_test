package com.example.chattest.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chattest.databinding.FragmentMessageBinding
import com.example.chattest.presentation.adapter.ChatUserAdapter
import com.example.chattest.presentation.model.ChatUserItemUiState
import com.example.chattest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


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
            val itemUiStat = it.userMessages.map { userMessage ->
                ChatUserItemUiState(
                    id = userMessage.id,
                    imageUrl = userMessage.profileImage,
                    name = userMessage.name,
                    date = userMessage.lastChatDateTime,
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