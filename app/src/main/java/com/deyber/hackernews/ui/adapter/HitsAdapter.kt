package com.deyber.hackernews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deyber.hackernews.core.utils.toTimeAgoFromString
import com.deyber.hackernews.databinding.NewSnippetItemBinding
import com.deyber.hackernews.domain.model.ui.HitModel

class HitsAdapter (
    private val newSnippetClicked : (item : HitModel)->Unit
): RecyclerView.Adapter<HitsAdapter.ViewHolder>()  {

    private var hits: List<HitModel> = listOf()

    fun  setupHint(list : List<HitModel>){
        hits = list
        notifyDataSetChanged()
    }

    class ViewHolder (v: NewSnippetItemBinding): RecyclerView.ViewHolder(v.root){
        val title = v.snippetMessage
        val subtitle = v.snippetAuthor
        val container = v.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewSnippetItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = hits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = hits[position]
       with(holder){
           title.text = item.storyTitle
           subtitle.text = "${item.author} - ${item.createdAt?.toTimeAgoFromString()}"
           container.setOnClickListener {
               newSnippetClicked.invoke(item)
           }
       }
    }
}