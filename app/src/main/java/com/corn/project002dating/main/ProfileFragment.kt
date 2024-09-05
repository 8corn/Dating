package com.corn.project002dating.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.corn.project002dating.R
import com.corn.project002dating.utils.CardStackAdapter
import com.corn.project002dating.databinding.FragmentProfileBinding
import com.corn.project002dating.utils.Person
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class ProfileFragment: Fragment() {
    private var _binding:FragmentProfileBinding ?= null
    private val binding get() = _binding!!
    private lateinit var cardStackAdapter: CardStackAdapter
    private lateinit var manager: CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val people = listOf(
            Person("봄",25,"서울", R.drawable.persons),
            Person("여름",31,"수원",R.drawable.girl1),
            Person("가을",23,"인천",R.drawable.girl2),
            Person("겨울",26,"부산", R.drawable.girl3),
            Person("바다",29,"대구", R.drawable.girl4),
            Person("예슬", 32, "광주", R.drawable.girl5)
        )
        val initList = people.toMutableList()

        manager = CardStackLayoutManager(requireContext(), object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {
                val currentIndex = cardStackAdapter.itemCount % people.size
                val newPerson = people[currentIndex]
                if (!cardStackAdapter.items.contains(newPerson).not()) {
                    cardStackAdapter.addItems(listOf(newPerson))
                    Log.d("ProfileFragment", "Card swiped, adding: $newPerson")
                }
            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {
            }

            override fun onCardDisappeared(view: View?, position: Int) {
            }
        })

        cardStackAdapter = CardStackAdapter(requireContext(), initList)
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.adapter = cardStackAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}