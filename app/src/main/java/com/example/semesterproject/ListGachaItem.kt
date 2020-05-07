package com.example.semesterproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListGachaItem : Fragment() {
    private lateinit var gachaRecyclerView: RecyclerView
    private val gachaListViewModel: GachaItemViewModel by activityViewModels()
    private var adapter: GachaAdapter? = GachaAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_gacha_fragment, container, false)

        gachaRecyclerView =
            view.findViewById(R.id.recycler_view) as RecyclerView
        gachaRecyclerView.layoutManager = LinearLayoutManager(context)
        gachaRecyclerView.adapter = adapter

        activity?.title = "List of previous rolls"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gachaListViewModel.gachaLiveData.observe(
            viewLifecycleOwner,
            Observer { items ->
                items?.let {
                    updateUI(items)
                }
            }
        )
    }

    private fun updateUI(items: List<GachaItem>) {
        adapter = GachaAdapter(items)
        gachaRecyclerView.adapter = adapter
    }

    private inner class GachaHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        private lateinit var item: GachaItem

        private val itemNameView: TextView = itemView.findViewById(R.id.item_name)
        private val rarityView: TextView = itemView.findViewById(R.id.item_rarity)
        private val descView: TextView = itemView.findViewById(R.id.item_desc)
        private val idView: TextView = itemView.findViewById(R.id.item_id)

        fun bind(item: GachaItem) {
            this.item = item
            itemNameView.text = this.item.itemName
            rarityView.text = rarityToWord(this.item.rarity)
            descView.text = this.item.description
            idView.text = this.item.id.toString()
        }

        fun rarityToWord(rarity: Int): String {
            return when {
                rarity < 10 -> "Rare"
                rarity < 40 -> "Uncommon"
                else -> "Common"
            }
        }
    }

    private inner class GachaAdapter(var items: List<GachaItem>)
        : RecyclerView.Adapter<GachaHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GachaHolder {
            val view = layoutInflater.inflate(R.layout.list_item_gacha, parent, false)
            return GachaHolder(view)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: GachaHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
        }
    }
}