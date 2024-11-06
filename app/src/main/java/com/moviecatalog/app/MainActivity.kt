package com.moviecatalog.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.moviecatalog.app.databinding.LayoutMainBinding
import com.moviecatalog.app.domain.model.Movies
import com.moviecatalog.app.ui.BottomSheetFragment
import com.moviecatalog.app.ui.base.BaseActivity
import com.moviecatalog.app.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<LayoutMainBinding>() {
    override val layoutId: Int
        get() = R.layout.layout_main
    private val viewModel: MovieViewModel by viewModels<MovieViewModel>()
    private var pageIndex: Int = 0
    private lateinit var movieBottomList: List<Movies>
      private  var statistics: List<Map.Entry<Char, Int>> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        initUI()
        eventListner()
        setUpViews()

    }

    private fun eventListner() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieData.collectLatest {
                    if (it.movieList.isNotEmpty()) {
                        movieBottomList = it.movieList.get(pageIndex).movieList
                        viewModel.getMovieStatsCount(movieBottomList)
                        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
                        }.attach()
                        viewModel.topCharCount.collectLatest { statistics = it }
                    }
                }

            }
        }
    }


    private fun initUI() {
        viewModel.getMovieList()
        binding.index = 0
        binding.viewModel = viewModel
    }
    private fun setUpViews() {

        binding.viewPager.registerOnPageChangeCallback(
            object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.search.setQuery("", false)
                    binding.index = position
                    pageIndex = position
                }
            },
        )

        binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(input: String?): Boolean {
                    viewModel.getFilteredData(pageIndex, input ?: "")
                    return true
                }
            },
        )

        binding.fabBtn.setOnClickListener {

            if (statistics?.isNotEmpty() == true) {
                val bottomSheet = BottomSheetFragment.newInstance(statistics, movieBottomList.size)
                bottomSheet.show(supportFragmentManager, bottomSheet.tag)
            }else{

                viewModel.getMovieStatsCount(movieBottomList)
            }
        }
    }


}