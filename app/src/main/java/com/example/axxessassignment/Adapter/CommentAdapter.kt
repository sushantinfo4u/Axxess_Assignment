package com.example.axxessassignment.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.axxessassignment.R
import com.example.axxessassignment.Room.CommentEntity

/**
 * @CommentAdapter is class is used for showing the comment to the
 * recycle view
 *
 *
 */
class CommentAdapter(val commentList:List<CommentEntity>,val context:Context): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(commet: CommentEntity) {

            Log.d("test","setting data in adapter ${commet.time}  ${commet.title}")

            val txtTItle = itemView.findViewById(R.id.txtTitle) as TextView
            val txtComment  = itemView.findViewById(R.id.txtComment) as TextView
            txtTItle.text = commet.time
            txtComment.text =commet.comment
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.comment_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(commentList[position])
    }
}