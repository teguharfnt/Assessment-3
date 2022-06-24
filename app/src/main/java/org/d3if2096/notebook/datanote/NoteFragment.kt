package org.d3if2096.notebook.datanote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2096.notebook.databinding.FragmentNoteBinding
import org.d3if2096.notebook.db.DataDb

class NoteFragment: Fragment() {

    private val viewModel: NoteViewModel by lazy {
        val db = DataDb.getInstance(requireContext())
        val factory = NoteViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    private lateinit var binding: FragmentNoteBinding
    private lateinit var myAdapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = NoteAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
}