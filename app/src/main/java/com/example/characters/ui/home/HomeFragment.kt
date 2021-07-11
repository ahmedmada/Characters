package com.example.characters.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.mvrx.*
import com.example.characters.R
import com.example.characters.databinding.FragmentHomeBinding
import javax.inject.Inject
import kotlinx.coroutines.*
import com.example.characters.data.api.response.CharacterResponse
import com.example.characters.utils.Status
import dagger.android.support.AndroidSupportInjection

class HomeFragment : BaseMvRxFragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModel.Factory

    private val viewModel: HomeViewModel by fragmentViewModel()

    var isLoading = false

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        setupRecyclerView()

    }

    var list: List<CharacterResponse> = listOf(CharacterResponse(0, "", "", "", ""))


    private fun showCharacters(characters: List<CharacterResponse>) {

        if (list.size == 1) list = characters
        else for (character in characters) list +=character

        adapter.submitList(list)
    }




    private fun setupRecyclerView() {

        binding.charactersRecyclerView.setHasFixedSize(true)
        binding.charactersRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.charactersRecyclerView.adapter = adapter

    }

    override fun invalidate() {

        withState(viewModel) { state ->

            when (state.characters.status) {
                Status.SUCCESS -> {
                    binding.statusImage.visibility = View.GONE

                    state.characters.data?.let { showCharacters(it) }

                    isLoading = false
                    binding.progressBar.visibility = View.GONE

                    startTimer()

                }
                Status.ERROR -> {
                    isLoading = false
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    isLoading = true
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun startTimer(){
        viewLifecycleOwner.lifecycleScope.launch {
            while (true){
                adapter.notifyDataSetChanged()
                delay(1000)
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
