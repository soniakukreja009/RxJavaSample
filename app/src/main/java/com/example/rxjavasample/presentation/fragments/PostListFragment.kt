package com.example.rxjavasample.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavasample.R
import com.example.rxjavasample.databinding.FragmentPostListBinding
import com.example.rxjavasample.presentation.adapter.OnItemClickListener
import com.example.rxjavasample.presentation.adapter.PostListAdapter
import com.example.rxjavasample.presentation.state.StatePostList
import com.example.rxjavasample.presentation.viewmodel.PostListViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostListFragment : Fragment() {

    private val postsViewModel by viewModels<PostListViewModel>()

    private lateinit var postListAdapter: PostListAdapter

    private lateinit var binding: FragmentPostListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostListBinding.bind(view)

        val mContext = requireContext()
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Posts"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Favourites"))

        postListAdapter = PostListAdapter(listOf(), mContext)
        postListAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                postsViewModel.onItemClicked(position)
                postListAdapter.notifyDataSetChanged()
            }
        })
        binding.postsRV.adapter = postListAdapter
        binding.postsRV.layoutManager = LinearLayoutManager(mContext)

        postsViewModel.statePostsList.observeForever { postListState ->
            when (postListState) {
                is StatePostList.Loading -> {
                    
                }
                is StatePostList.Success -> {
                    val posts = postListState.postList
                    postListAdapter.updateList(posts)
                    postListAdapter.notifyDataSetChanged()
                }
                is StatePostList.Failure -> {
                    val message = postListState.message
                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val list = if (tab?.position == 0) {
                    postsViewModel.postsList.value
                } else {
                    postsViewModel.postsList.value?.filter { it.isFav }
                }
                list?.let {
                    postListAdapter.updateList(list)
                    postListAdapter.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        postsViewModel.statePostsList.removeObservers(viewLifecycleOwner)
    }
}