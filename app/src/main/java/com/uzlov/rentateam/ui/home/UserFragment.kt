package com.uzlov.rentateam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uzlov.rentateam.databinding.FragmentUserLayoutBinding
import com.uzlov.rentateam.databinding.FragmentUsersBinding
import com.uzlov.rentateam.repo.models.Data
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UserFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentUserLayoutBinding? = null
    private val binding get() = _binding!!
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(user: Data): UserFragment {
            val fragment = UserFragment()
            fragment.arguments = bundleOf("USER_KEY" to user)
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && !arguments?.isEmpty!!){
            loadUser(arguments?.getParcelable<Data>("USER_KEY") as Data)
        }
    }

    private fun loadUser(data: Data) {
        with(binding){
            tvEmailName.text = data.email
            tvFirstName.text = data.first_name
            tvlastName.text = data.last_name

            Glide.with(requireContext())
                .load(data.avatar)
                .centerCrop()
                .into(userImage)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
        _binding = null
    }
}