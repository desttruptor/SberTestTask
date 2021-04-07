package me.podlesnykh.sbertesttask.currency_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import me.podlesnykh.sbertesttask.R
import me.podlesnykh.sbertesttask.currency_list_fragment.adapters.CurrencyListAdapter
import me.podlesnykh.sbertesttask.databinding.FragmentCurrencyListBinding
import me.podlesnykh.sbertesttask.network.pojo.Valute

class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!

    // адаптер инициализируется пустым списком
    private val currencyList: List<Valute> = emptyList()
    private val adapter = CurrencyListAdapter(currencyList, ::openConverterScreen)

    private lateinit var viewModel: CurrencyListFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        val view = binding.root
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.label_currency_rate)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CurrencyListFragmentViewModelFactory(requireActivity().application)
        ).get(CurrencyListFragmentViewModel::class.java)

        setupRecyclerView()
        viewModel.loadCurrencyList()

        viewModel.errorDialog.observe(viewLifecycleOwner) {
            showError(it)
        }
        viewModel.loadingFlag.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        viewModel.currencyList.observe(viewLifecycleOwner) {
            adapter.submitList(it.currencyList)
        }
    }

    private fun showError(isShown: Boolean) {
        if (isShown) {
            val snackbar = Snackbar.make(
                binding.root,
                requireContext().getString(R.string.label_error),
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar.setAction(getString(R.string.label_retry)) {
                viewModel.loadCurrencyList()
            }
            snackbar.show()
        }
    }

    private fun showLoading(isShown: Boolean) {
        binding.currencyListProgress.visibility = if (isShown) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // переход на экран конвертера выбранной валюты
    private fun openConverterScreen(value: Double, nominal: Int, name: String) {
        findNavController().navigate(
            R.id.currencyConverterFragment,
            bundleOf(
                "value" to value,
                "nominal" to nominal,
                "name" to name
            )
        )
    }

    private fun setupRecyclerView() {
        binding.rvCurrencyList.adapter = adapter
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
        binding.rvCurrencyList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}