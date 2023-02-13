package com.example.chattest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chattest.R
import com.example.chattest.data.constant.TestConst
import com.example.chattest.databinding.ItemChatBubbleBinding
import com.example.chattest.databinding.ItemMyChatBubbleBinding
import com.example.chattest.presentation.model.ChatItemUiState

class ChatRoomAdapter : ListAdapter<ChatItemUiState, RecyclerView.ViewHolder>(
    chatRoomDiffUtil
) {

    inner class MyChatViewHolder(private val binding: ItemMyChatBubbleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatItemUiState: ChatItemUiState) {
            with(binding) {
                item = chatItemUiState
            }
        }
    }

    inner class ChatViewHolder(private val binding: ItemChatBubbleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatItemUiState: ChatItemUiState) {
            with(binding) {
                item = chatItemUiState
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == MY_CHAT) {
            MyChatViewHolder(
                DataBindingUtil.inflate(layoutInflater, R.layout.item_my_chat_bubble, parent,false)
            )
        } else {
            ChatViewHolder(
                DataBindingUtil.inflate(layoutInflater, R.layout.item_chat_bubble, parent,false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == MY_CHAT) {
            (holder as MyChatViewHolder).bind(getItem(position))
        } else {
            (holder as ChatViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).messageFrom == TestConst.USER_ID) {
            MY_CHAT
        } else {
            OTHER_CHAT
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    companion object {
        val chatRoomDiffUtil = object : DiffUtil.ItemCallback<ChatItemUiState>() {
            override fun areItemsTheSame(oldItem: ChatItemUiState, newItem: ChatItemUiState): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ChatItemUiState, newItem: ChatItemUiState): Boolean {
                return oldItem.index == newItem.index
            }
        }

        private const val MY_CHAT = 1
        private const val OTHER_CHAT = 2
    }
}