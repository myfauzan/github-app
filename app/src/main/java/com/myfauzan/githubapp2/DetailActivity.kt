package com.myfauzan.githubapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.myfauzan.githubapp2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getStringExtra(EXTRA_USERNAME)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        user.let {
            if (it != null) {
                viewModel.setDetailUsers(it)
                showLoading(true)
            }
        }
        showTabLayout()

        viewModel.getDetailUsers().observe(this){
            if (it != null){
                binding.tvDetailUsername.text = it.login
                binding.tvDetailName.text = it.name
                binding.tvDetailLocation.text = it.location
                binding.tvDetailCompany.text = it.company
                Glide.with(this)
                    .load(it.avatarUrl)
                    .circleCrop()
                    .into(binding.ivDetailPhoto)

                showLoading(false)
            }
        }
    }

    private fun showTabLayout() {
        val pagerAdapter = PagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = pagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs, viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }
        else{
            binding.progressBar.visibility = View.GONE
        }
    }
}