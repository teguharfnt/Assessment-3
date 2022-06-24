package org.d3if2096.notebook.motivasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2096.notebook.databinding.FragmentMotivasiBinding
import org.d3if2096.notebook.network.ApiStatus

class MotivasiFragment : Fragment() {
    private val viewModel: MotivasiViewModel by lazy {
        ViewModelProvider(this)[MotivasiViewModel::class.java]
    }

    private lateinit var binding: FragmentMotivasiBinding
    private lateinit var myAdapter: MotivasiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMotivasiBinding.inflate(layoutInflater, container, false)
        myAdapter = MotivasiAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context, RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

        viewModel.scheduleAdapter(requireActivity().application)
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}