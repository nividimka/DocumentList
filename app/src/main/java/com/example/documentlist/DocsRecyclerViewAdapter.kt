package com.example.documentlist

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class DocsRecyclerViewAdapter : RecyclerView.Adapter<DocsRecyclerViewAdapter.DocsViewHolder>() {
    private val items = ArrayList<VKDoc>()

    class DocsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
        val tagMessage: TextView = itemView.findViewById(R.id.tag_message)
        val tagLayout: View = itemView.findViewById(R.id.tag_layout)
        val information: TextView = itemView.findViewById(R.id.information)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_doc, parent, false)
        return DocsViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DocsViewHolder, position: Int) {
        val doc = items[position]
        holder.title.text = doc.title
        var resourceInt = R.drawable.ic_placeholder_document_book_72
        when(doc.type){
            1->resourceInt = R.drawable.ic_placeholder_document_text_72
            2->resourceInt = R.drawable.ic_placeholder_document_archive_72
            3->resourceInt = R.drawable.ic_placeholder_document_video_72
            4->resourceInt = R.drawable.ic_placeholder_document_image_72
            5->resourceInt = R.drawable.ic_placeholder_document_music_72
            6->resourceInt = R.drawable.ic_placeholder_document_video_72
            7->resourceInt = R.drawable.ic_placeholder_document_book_72
            8->resourceInt = R.drawable.ic_placeholder_document_other_72
        }
        val radius = holder.image.context.resources.getDimensionPixelSize(R.dimen.corner_radius)
        Glide.with(holder.image.context)
            .load(resourceInt)
            .transform(RoundedCorners(radius))
            .into(holder.image)
        if(doc.tagArray.size==0){
            holder.tagLayout.visibility = View.GONE
        }else {
            holder.tagLayout.visibility = View.VISIBLE
            holder.tagMessage.text = doc.getTagString()
        }
        holder.information.text = doc.getInformationString()
    }

    fun updateItems(items: List<VKDoc>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}