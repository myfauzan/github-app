package com.myfauzan.githubapp2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myfauzan.githubapp2.databinding.FragmentFollowersBinding

class FollowersFragment : Fragment(R.layout.fragment_followers) {

    companion object{
        private const val ARG_USERNAME = "username"
    }

    private var _binding : FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowerViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFollowers.setHasFixedSize(true)
        showRecyclerView()
        setFollowers()
    }

    private fun setFollowers() {
        if (arguments != null) {
            val username = arguments?.getString(ARG_USERNAME)
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowerViewModel::class.java)
            showLoading(true)
            if (username != null) {
                viewModel.setDataFollowers(username)
            }
        }

        viewModel.getDataFollowers().observe(viewLifecycleOwner, { listFollowers ->
            if (listFollowers != null) {
                adapter.setList(listFollowers)
                showLoading(false)
            }
        })
    }

    private fun showRecyclerView() {
        binding.rvFollowers.layoutManager = LinearLayoutManager(activity)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        binding.rvFollowers.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    fun newInstance(username: String?) : FollowersFragment{
        val fragment = FollowersFragment()
        val bundle = Bundle()
        bundle.putString(ARG_USERNAME, username)
        fragment.arguments = bundle
        return fragment
    }
}