package com.example.chattest.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.example.chattest.databinding.FragmentMessageBinding
import com.example.chattest.presentation.adapter.ChatUserAdapter
import com.example.chattest.presentation.model.ChatUserItemUiState
import com.example.chattest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MessageFragment : Fragment(), ChatUserAdapter.OnChatUserItemClickListener {

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
        subscriberUi()
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
        mainViewModel.messageUser.observe(this as LifecycleOwner) {
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

    private fun subscriberUi() {
    }
    
    companion object {
        const val TAG = "MessageFragment"
    }

    override fun onItemClicked(id: Int) {
        mainViewModel.moveChatRoom(id)
    }

}