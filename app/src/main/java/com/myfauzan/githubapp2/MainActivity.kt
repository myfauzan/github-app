package com.myfauzan.githubapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myfauzan.githubapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }

        })

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            SearchViewModel::class.java
        )

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            btnFind.setOnClickListener{
                findUser()
            }

            edSearch.setOnKeyListener{ v, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    findUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        viewModel.getSearchUsers().observe(this, {
            if (it != null){
                adapter.setList(it)
                showLoading(false)
            }
        })
    }


    private fun findUser() {
        binding.apply {
            val query = edSearch.text.toString()
            if (query.isNotEmpty()){
                showLoading(true)
                viewModel.setSearchUser(query)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}