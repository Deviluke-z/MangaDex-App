package duke.deviluke.mangadexapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import duke.deviluke.mangadexapp.data.model.ListMangaData
import duke.deviluke.mangadexapp.databinding.ItemCoverBinding

class LatestMangaListAdapter :
    RecyclerView.Adapter<LatestMangaListAdapter.LatestMangaListViewHolder>() {

    private val callback =
        object : DiffUtil.ItemCallback<ListMangaData.Data>() {
            override fun areItemsTheSame(
                oldItem: ListMangaData.Data,
                newItem: ListMangaData.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ListMangaData.Data,
                newItem: ListMangaData.Data
            ): Boolean {
                return oldItem == newItem
            }
        }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestMangaListViewHolder {
        val binding = ItemCoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestMangaListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: LatestMangaListViewHolder, position: Int) {
        val mangaItem = differ.currentList[position]
        holder.bind(mangaItem)
    }

    inner class LatestMangaListViewHolder(val view: ItemCoverBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(mangaList: ListMangaData.Data) {
            view.tvTitle.text = mangaList.attributes.title.en
        }
    }
}