package com.quispe.ismael.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quispe.ismael.laboratoriocalificado03.databinding.ItemProfesorBinding

class ProfesorAdapter(
    var list: List<Profesor>
) : RecyclerView.Adapter<ProfesorAdapter.ViewHolder>() {

    private var onItemClickListener: ((Profesor) -> Unit)? = null
    private var onItemLongClickListener: ((Profesor) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemProfesorBinding.bind(view)

        fun bind(profesor: Profesor) {

            binding.tvNombre.text = "${profesor.name} ${profesor.last_name}"
            binding.tvCorreo.text = profesor.email
            binding.tvTelefono.text = profesor.phone
            Glide
                .with(itemView)
                .load(profesor.imageUrl)
                .into(binding.ivFoto)


            binding.root.setOnClickListener {
                onItemClickListener?.let { it(profesor) }
            }
            binding.root.setOnLongClickListener {
                onItemLongClickListener?.let {
                    it(profesor)
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProfesorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemProfesor = list[position]
        holder.bind(itemProfesor)
    }

    override fun getItemCount(): Int = list.size

    fun setOnItemClickListener(listener: (Profesor) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: (Profesor) -> Unit) {
        onItemLongClickListener = listener
    }
}
