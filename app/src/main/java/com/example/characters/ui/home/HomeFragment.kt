package com.example.characters.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.App
import com.example.characters.R
import com.example.characters.databinding.FragmentHomeBinding
import com.example.characters.ui.home.HomeViewState.*
import com.example.characters.utils.ViewModelFactory
import com.gnova.data.api.response.CharacterResponse
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {


    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>
    private lateinit var viewModel: HomeViewModel

    var visibleItemCount = 0
    var totalItemCount = 0
    var pastVisibleItems = 0

    var page = 1
    var isLoading = false

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        val binding = FragmentHomeBinding.bind(view)
        _binding = binding


        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        setupRecyclerView()

        viewModel.onViewLoaded(page)

        observeviewState()

    }

    private fun observeviewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> {
                    Log.d("TAG", "LOADING")
                    binding.statusImage.visibility = View.VISIBLE
                    isLoading = true
//                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                }
                is Error -> {
                    Log.d("TAG", "ERROR HOME FRAGMENT")
                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                    isLoading = false
                    binding.progressBar.visibility = View.GONE
                }
                is Presenting -> {
                    binding.statusImage.visibility = View.GONE
                    showCharacters(it.results)
                    isLoading = false
                    binding.progressBar.visibility = View.GONE

                }

            }
        })
    }

    var list: List<CharacterResponse> = listOf(CharacterResponse(0, "","","",""))


    private fun showCharacters(characters: List<CharacterResponse>) {


        if (list.size == 1) list = characters
        else for (character in characters) list +=character

        adapter.submitList(list)
    }




    private fun setupRecyclerView() {

        binding.charactersRecyclerView.setHasFixedSize(true)
        binding.charactersRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.charactersRecyclerView.adapter = adapter



        binding.charactersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                super.onScrollStateChanged(recyclerView, newState)


                visibleItemCount = (binding.charactersRecyclerView.getLayoutManager() as GridLayoutManager)
                    .getChildCount()
                totalItemCount = (binding.charactersRecyclerView.getLayoutManager() as GridLayoutManager)
                    .getItemCount()
                pastVisibleItems = (binding.charactersRecyclerView.getLayoutManager() as GridLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()

                if (pastVisibleItems + visibleItemCount >= totalItemCount && !isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                    page+=10
                    viewModel.onViewLoaded(page)

                }
            }
        })

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
