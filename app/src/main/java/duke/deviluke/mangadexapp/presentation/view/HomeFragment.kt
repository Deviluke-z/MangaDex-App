package duke.deviluke.mangadexapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import duke.deviluke.mangadexapp.MangaDexApplication.Companion.DEBUG_TAG
import duke.deviluke.mangadexapp.R
import duke.deviluke.mangadexapp.data.modelJson.toModel
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.databinding.FragmentHomeBinding
import duke.deviluke.mangadexapp.presentation.adapter.GenresAdapter
import duke.deviluke.mangadexapp.presentation.adapter.LatestMangaListAdapter
import duke.deviluke.mangadexapp.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var latestMangaListAdapter: LatestMangaListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).binding.bnvMain.visibility = View.VISIBLE
        viewModel = (activity as MainActivity).viewModel
        genresAdapter = (activity as MainActivity).genresAdapter
        latestMangaListAdapter = (activity as MainActivity).latestMangaListAdapter

        lifecycleScope.launch {
            setupAdapter()
            getRandomManga()
            getLatestMangaList()
        }

        binding.btnRandom.setOnClickListener(getRandomMangaListener)
        binding.btnUpdateLatest.setOnClickListener(updateLatestMangaListListener)
    }

    private suspend fun getRandomManga() = lifecycleScope.launch(Dispatchers.Default) {
        Log.d(DEBUG_TAG, "HomeFragment: getRandomManga()")
        viewModel.getRandomMangaData()
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.randomMangaData.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        Log.d(DEBUG_TAG, "HomeFragment: getRandomManga(): Success")
                        response.data?.let { data ->
                            val result = data.toModel().data
                            binding.tvTitle.text = result.attributes.title.en
                            genresAdapter.differ.submitList(result.attributes.tags)
                            binding.tvDescriptions.text = result.attributes.description.en
                        }
                    }

                    is Resource.Loading -> {
                        Log.d(DEBUG_TAG, "HomeFragment: getRandomManga(): Loading")
                    }

                    is Resource.Failure -> {
                        Log.d(DEBUG_TAG, "HomeFragment: getRandomManga(): Failure")
                        response.message.let {
                            Log.d(DEBUG_TAG, it.toString())
                            Snackbar.make(
                                requireContext(),
                                binding.root,
                                "An occur happened: ${it.toString()}",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private suspend fun getLatestMangaList() = lifecycleScope.launch(Dispatchers.Default) {
        Log.d(DEBUG_TAG, "HomeFragment: getLatestMangaList()")
        viewModel.getLatestMangaListData()
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.latestMangaList.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        Log.d(DEBUG_TAG, "HomeFragment: getLatestMangaList(): Success")
                        response.data?.let { data ->
                            val result = data.toModel().data
                            latestMangaListAdapter.differ.submitList(result)
                            Log.d(
                                DEBUG_TAG,
                                "HomeFragment: getLatestMangaList(): list size = ${latestMangaListAdapter.differ.currentList.size}"
                            )
                        }
                    }

                    is Resource.Loading -> {
                        Log.d(DEBUG_TAG, "HomeFragment: getLatestMangaList(): Loading")
                    }

                    is Resource.Failure -> {
                        Log.d(DEBUG_TAG, "HomeFragment: getLatestMangaList(): Failure")
                        response.message.let {
                            Log.d(DEBUG_TAG, it.toString())
                            Snackbar.make(
                                requireContext(),
                                binding.root,
                                "An occur happened: ${it.toString()}",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvcGenres.apply {
            adapter = genresAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rcvLatestUpdate.apply {
            adapter = latestMangaListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private val getRandomMangaListener = View.OnClickListener {
        lifecycleScope.launch {
            getRandomManga()
        }
    }

    private val updateLatestMangaListListener = View.OnClickListener {
        lifecycleScope.launch {
            getLatestMangaList()
        }
    }
}