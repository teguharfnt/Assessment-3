package org.d3if2096.notebook.data

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2096.notebook.R
import org.d3if2096.notebook.databinding.FragmentDataBinding
import org.d3if2096.notebook.db.DataDb

class DataFragment : Fragment() {
    private lateinit var binding: FragmentDataBinding

    private val viewModel: DataViewModel by lazy {
        val db = DataDb.getInstance(requireContext())
        val factory = DataViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[DataViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener { masukData() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.lihatdatat -> {
                findNavController().navigate(
                    R.id.action_dataFragment_to_noteFragment
                )
                return true
            }
            R.id.motivasi -> {
                findNavController().navigate(
                    R.id.action_dataFragment_to_motivasiFragment
                )
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun masukData() {

        val judul = binding.judulinput.text.toString()

        if (TextUtils.isEmpty(judul)) {
            Toast.makeText(context, "ISI JUDUL DULU", Toast.LENGTH_LONG).show()
            return
        }

        val deskripsi = binding.deskripsiinput.text.toString()

        if (TextUtils.isEmpty(deskripsi)) {
            Toast.makeText(context, "ISI DESKRIPSI DULU", Toast.LENGTH_LONG).show()
            return
        }
        viewModel.masukData(judul, deskripsi)

        binding.judulinput.text = null
        binding.deskripsiinput.text = null
    }

}




