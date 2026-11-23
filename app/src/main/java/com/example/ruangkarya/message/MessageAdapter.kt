package com.example.ruangkarya.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.ruangkarya.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val binding: ItemMessageBinding
        val view: View

        if (convertView == null) {
            binding = ItemMessageBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as ItemMessageBinding
        }

        val data = messages[position]

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        Glide.with(context)
            .load(data.avatarUrl)
            .circleCrop()
            .into(binding.avatarImg)

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}
