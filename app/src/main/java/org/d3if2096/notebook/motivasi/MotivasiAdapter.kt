package org.d3if2096.notebook.motivasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if2096.notebook.R
import org.d3if2096.notebook.databinding.ListMotivasiBinding
import org.d3if2096.notebook.model.Motivasi
import org.d3if2096.notebook.network.MotivasiApi

class MotivasiAdapter : RecyclerView.Adapter<MotivasiAdapter.ViewHolder>() {

    private val data = mutableListOf<Motivasi>()

    fun updateData(newData: List<Motivasi>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListMotivasiBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(kontakPenting: Motivasi)= with(binding){
            deskripsi.text =kontakPenting.motivasi
            Glide.with(imageView.context)
                .load(MotivasiApi.getMotivasiUrl(kontakPenting.image))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  ListMotivasiBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}