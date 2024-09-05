package com.corn.project002dating.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.corn.project002dating.R
import com.corn.project002dating.auth.LoginActivity
import com.corn.project002dating.databinding.FragmentChatBinding
import com.corn.project002dating.utils.ChatAdapter
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.models.Config
import io.getstream.chat.android.models.User
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import io.getstream.chat.android.ui.feature.messages.MessageListActivity
import io.getstream.chat.android.ui.viewmodel.channels.ChannelListViewModel
import io.getstream.chat.android.ui.viewmodel.channels.ChannelListViewModelFactory
import io.getstream.chat.android.ui.viewmodel.channels.bindView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.stream.Stream

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentUser: String
    private val db = FirebaseFirestore.getInstance()
    private lateinit var registration: ListenerRegistration
    private val chatList = arrayListOf<ChatLayout>()
    private lateinit var adapter: ChatAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ChatAdapter(currentUser, chatList)
        binding.rvList.adapter = adapter

        binding.etChat.addTextChangedListener { text ->
            binding.btnSend.isEnabled = text.toString().isNotEmpty()
        }
        binding.btnSend.setOnClickListener {
            val data = hashMapOf(
                "nickname" to currentUser,
                "contents" to binding.etChat.text.toString(),
                "time" to Timestamp.now()
            )
            Log.d("ChatFragment", "Sending data: $data")
            db.collection("chat").add(data)
                .addOnSuccessListener {
                    binding.etChat.text.clear()
                    Log.d("ChatFragment", "Document added: ${it.id}")
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "전송하는데 실패했습니다.", Toast.LENGTH_SHORT).show()
                    Log.e("ChatFragment", "Error occurs: $e")
                }
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val enterTime = Date(System.currentTimeMillis())

        registration = db.collection("chat")
            .orderBy("time", Query.Direction.DESCENDING)
            .limit(50)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("ChatFragment", "Listen failed: $e")
                    return@addSnapshotListener
                }

                if (snapshots?.metadata?.isFromCache == true) return@addSnapshotListener

                activity?.runOnUiThread {
                    for (doc in snapshots?.documentChanges ?: emptyList()) {
                        val timestamp = doc.document["time"] as Timestamp

                        if (doc.type == DocumentChange.Type.ADDED && timestamp.toDate() > enterTime) {
                            val nickname = doc.document["nickname"].toString()
                            val contents = doc.document["contents"].toString()

                            val sf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA)
                            sf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
                            val time = sf.format(timestamp.toDate())

                            val item = ChatLayout(nickname, contents, time)
                            chatList.add(item)
                            adapter.notifyItemInserted(chatList.size - 1)
                        }
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        registration.remove()
        _binding?.rvList?.adapter = null
        _binding = null
    }
}