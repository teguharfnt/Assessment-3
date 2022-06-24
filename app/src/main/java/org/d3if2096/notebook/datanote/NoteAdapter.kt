package org.d3if2096.notebook.datanote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if2096.notebook.databinding.ListDataBinding
import org.d3if2096.notebook.db.DataEntity

class NoteAdapter :
    ListAdapter<DataEntity, NoteAdapter.ViewHolder>(DIFF_CALLBACK) {

        companion object{
            private val DIFF_CALLBACK =
                object : DiffUtil.ItemCallback<DataEntity>(){
                    override fun areItemsTheSame(
                        oldItem: DataEntity,
                        newItem: DataEntity
                    ): Boolean {
                        return oldItem.id == newItem.id
                    }

                    override fun areContentsTheSame(
                        oldItem: DataEntity,
                        newItem: DataEntity
                    ): Boolean {
                        return oldItem==newItem
                    }
                }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListDataBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListDataBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item : DataEntity) = with(binding){
            judulTextView.text = item.judul
            deskripsi2TextView.text = item.deskripsi
        }
    }
}