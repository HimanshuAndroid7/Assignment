package com.neosoftassessment.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.neosoftassessment.R
import com.neosoftassessment.adapter.PagerListAdapter
import com.neosoftassessment.adapter.ViewPagerAdapter
import com.neosoftassessment.data.MainRepository
import com.neosoftassessment.databinding.ActivityMainBinding
import com.neosoftassessment.utils.MyViewModelFactory
import com.neosoftassessment.utils.doOnChange
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var mainAdapter = PagerListAdapter()
    var listTeam = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository()
        ))[MainViewModel::class.java]

        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }

        viewModel.getPlayersList("India", 20)
        viewModel.getBannersList(3)

        observeViews()
        initializeViews()

    }

    private fun initializeViews() {

        rv_items.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        viewPagerAdapter = ViewPagerAdapter()
        view_pager.adapter = viewPagerAdapter

        TabLayoutMediator(tab_layout, view_pager) { _, _ ->
        }.attach()

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                edt_search.setText("")
                when (position) {
                    0 -> viewModel.getPlayersList("India", 20)
                    1 -> viewModel.getPlayersList("England", 20)
                    else -> viewModel.getPlayersList("Australia", 20)
                }
                observeViews()
            }

            override fun onPageSelected(position: Int) {
            }
        })

        edt_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mainAdapter.updateList(s.toString(), listTeam)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    fun observeViews() {
        viewModel.playersList.doOnChange(this) {
            listTeam = it
            mainAdapter.updateList("", it)
        }
        viewModel.bannersList.doOnChange(this){
            viewPagerAdapter.updateList(it)
        }
    }
}