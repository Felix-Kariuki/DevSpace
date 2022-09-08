package com.flexcode.devspace.quotes.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.flexcode.devspace.R
import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.databinding.FragmentQuotesBinding
import com.flexcode.devspace.quotes.domain.models.Quotes
import com.flexcode.devspace.quotes.presentation.adapters.QuotesAdapter
import com.flexcode.devspace.quotes.presentation.viewmodels.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class QuotesFragment : Fragment(R.layout.fragment_quotes) {

    private var _binding: FragmentQuotesBinding? = null
    private val binding get() = _binding!!
    private val quotesViewModel : QuotesViewModel by viewModels()
    private lateinit var quotesAdapter: QuotesAdapter
    lateinit var quotesList: ArrayList<Quotes>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater,container,false)

        quotesAdapter = QuotesAdapter(QuotesAdapter.OnClickListener{
            /**
             *onclick action
             */
        })


        quotesList = ArrayList()

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getQuotes()
        searchQuotes()

    }

    private fun searchQuotes() {
        binding.searchView.setEndIconOnClickListener{
            val searchItem = binding.etSearchQuote.text.toString().trim()
            val results = quotesList.filter { it.en.contains(searchItem) }
            quotesAdapter.submitList(results)
            binding.rvQuotes.adapter = quotesAdapter
        }
    }

    private fun getQuotes() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            quotesViewModel.getAllQuotes().collect { result ->
                when(result){
                    is Resource.Success -> {
                        quotesAdapter.submitList(result.data)
                        binding.apply {
                            rvQuotes.isVisible = true
                            animationView.isGone = true
                            rvQuotes.adapter = quotesAdapter
                        }
                    }
                    is Resource.Loading -> {
                        binding.apply {
                            rvQuotes.isGone = true
                            animationView.isVisible = true
                        }
                    }
                    is Resource.Error -> {
                        binding.apply {
                            rvQuotes.isVisible = true
                            animationView.isGone = true
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}