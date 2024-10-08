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
import com.example.rxjavasample.ApiConstants
import com.example.rxjavasample.R
import com.example.rxjavasample.databinding.FragmentPostListBinding
import com.example.rxjavasample.data.entity.Post
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

    private var posts: List<Post> = listOf()

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
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.posts)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.favourites)))

        postListAdapter = PostListAdapter(listOf(), mContext)
        postListAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                if (binding.tabLayout.selectedTabPosition == 0) {
                    posts.single { it.id == position }.apply {
                        isFav = !isFav
                        postsViewModel.updateDBPostData(this)
                    }
                    postListAdapter.notifyDataSetChanged()
                }
            }
        })
        binding.postsRV.adapter = postListAdapter
        binding.postsRV.layoutManager = LinearLayoutManager(mContext)

        postsViewModel.statePostsList.observeForever { postListState ->
            when (postListState) {
                is StatePostList.Loading -> {
                    // handling loader
                }
                is StatePostList.Success -> {
                    posts = postListState.postList
                    postListAdapter.updateList(posts)
                    postListAdapter.notifyDataSetChanged()
                }
                is StatePostList.Failure -> {
                    Toast.makeText(mContext, ApiConstants.CHECK_INTERNET_CONNECTION, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val list = if (tab?.position == 0) {
                    posts
                } else {
                    posts.filter { it.isFav }
                }
                list.let {
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
        // ObserveForever doesn't get removed automatically so need to remove manually
        postsViewModel.statePostsList.removeObservers(this)
    }
}