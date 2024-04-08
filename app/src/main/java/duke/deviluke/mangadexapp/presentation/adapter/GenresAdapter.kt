package duke.deviluke.mangadexapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import duke.deviluke.mangadexapp.data.model.MangaData
import duke.deviluke.mangadexapp.databinding.ItemGenresBinding

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private val callback =
        object : DiffUtil.ItemCallback<MangaData.Data.Attributes.Tag>() {
            override fun areItemsTheSame(
                oldItem: MangaData.Data.Attributes.Tag,
                newItem: MangaData.Data.Attributes.Tag
            ): Boolean {
                return oldItem.attributes.name == newItem.attributes.name
            }

            override fun areContentsTheSame(
                oldItem: MangaData.Data.Attributes.Tag,
                newItem: MangaData.Data.Attributes.Tag
            ): Boolean {
                return oldItem == newItem
            }
        }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val binding = ItemGenresBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GenresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genresItem = differ.currentList[position]
        holder.bind(genresItem.attributes.name)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class GenresViewHolder(val view: ItemGenresBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(mangaData: MangaData.Data.Attributes.Tag.Attributes.Name) {
            view.tvGenres.text = mangaData.en
        }
    }
}