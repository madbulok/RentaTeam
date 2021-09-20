package com.uzlov.rentateam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uzlov.rentateam.R
import com.uzlov.rentateam.app.AppSate
import com.uzlov.rentateam.databinding.FragmentUsersBinding
import com.uzlov.rentateam.repo.models.Data
import com.uzlov.rentateam.ui.MyItemDecorator
import com.uzlov.rentateam.ui.adapter.UserAdapter
import com.uzlov.rentateam.viewmodels.UsersViewModel
import java.lang.Exception

class UsersFragment : Fragment() {

    private lateinit var homeViewModel: UsersViewModel
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val selectListener: UserAdapter.UserSelectListener =
        object : UserAdapter.UserSelectListener {
            override fun userSelect(data: Data, adapterPosition: Int) {
                openFragmentUserDetail(data)
            }
        }

    companion object {
        fun newInstance(): UsersFragment {
            val args = Bundle()
            val fragment = UsersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun openFragmentUserDetail(data: Data) {
        UserFragment.newInstance(data).show(childFragmentManager, "UserFragment")
    }

    private val userAdapter by lazy {
        UserAdapter(selectListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rwUsers.adapter = userAdapter
            rwUsers.layoutManager = LinearLayoutManager(requireContext())
            rwUsers.addItemDecoration(MyItemDecorator())
        }
        loadUsers()
    }

    private fun loadUsers() {
        showLoading()
        homeViewModel.observe().observe(viewLifecycleOwner, {
            renderResult(it)
        })
    }

    private fun renderResult(appSate: AppSate?) {
        when(appSate){
            is AppSate.Error -> {
                showError(appSate.t)
            }
            is AppSate.Loading -> {
                if (appSate.isLoading){
                    showLoading()
                }
            }
            is AppSate.Success -> {
                appSate.page.data?.let { renderData(it) }
            }
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(
            requireContext(),
            it?.message ?: getString(R.string.unknown_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun renderData(data: List<Data>) {
        hideLoading()
        userAdapter.setUsers(data)
    }

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
        binding.rwUsers.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
        binding.rwUsers.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}