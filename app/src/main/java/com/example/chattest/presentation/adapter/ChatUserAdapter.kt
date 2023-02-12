package com.example.chattest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chattest.R
import com.example.chattest.databinding.ItemChatUserBinding
import com.example.chattest.presentation.model.ChatUserItemUiState

class ChatUserAdapter : ListAdapter<ChatUserItemUiState, ChatUserAdapter.ViewHolder>(
    ChatUserDiffUtil
) {
    var listener : OnChatUserItemClickListener? = null

    inner class ViewHolder(private val binding: ItemChatUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chatUserItemUiState: ChatUserItemUiState) {
            with(binding) {
                item = chatUserItemUiState
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemChatUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_chat_user, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener?.onItemClicked(getItem(position).id)
        }
    }

    interface OnChatUserItemClickListener {
        fun onItemClicked (id: Int)
    }
}

object ChatUserDiffUtil : DiffUtil.ItemCallback<ChatUserItemUiState>() {
    override fun areItemsTheSame(
        oldItem: ChatUserItemUiState,
        newItem: ChatUserItemUiState
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ChatUserItemUiState,
        newItem: ChatUserItemUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }

}