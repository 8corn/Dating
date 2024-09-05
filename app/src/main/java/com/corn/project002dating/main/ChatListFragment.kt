package com.corn.project002dating.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.corn.project002dating.auth.LoginActivity
import com.corn.project002dating.databinding.FragmentChatListBinding
import com.corn.project002dating.utils.ChatListAdapter
import com.corn.project002dating.utils.ChatRoom
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class ChatListFragment : Fragment() {
    private var _binding: FragmentChatListBinding ?= null
    private val binding get() = _binding!!
    private lateinit var currentUser: String
    private val db = FirebaseFirestore.getInstance()
    private lateinit var registration: ListenerRegistration
    private lateinit var adapter: ChatListAdapter
    private val chatRooms = mutableListOf<ChatRoom>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            currentUser = firebaseUser.displayName ?: "Unknow"
        }
//        else {
//            Toast.makeText(context, "로그인을 안했다 좀 해라!!", Toast.LENGTH_SHORT).show()
//
//            val intent = Intent(context, LoginActivity::class.java)
//            startActivity(intent)
//            activity?.finish()
//            return
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChatListAdapter(chatRooms) {chatRoom ->
            val action = ChatListFragmentDirections.chattolist(chatRoom.id)
            findNavController().navigate(action)
        }
        binding.rvChatList.layoutManager = LinearLayoutManager(context)
        binding.rvChatList.adapter = adapter

        loadChatRooms()
    }

    private fun loadChatRooms() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}