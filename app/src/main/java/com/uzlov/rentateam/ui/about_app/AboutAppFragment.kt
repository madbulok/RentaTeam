package com.uzlov.rentateam.ui.about_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uzlov.rentateam.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {

    companion object {
        fun newInstance(): AboutAppFragment {
            val args = Bundle()
            val fragment = AboutAppFragment()
            fragment.arguments = args
            return fragment
        }
    }


    private var _binding: FragmentAboutAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}