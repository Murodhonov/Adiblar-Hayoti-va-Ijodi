package uz.umarxon.adiblar

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_tab.view.*
import uz.umarxon.adiblar.Adapters.ViewPagerAdapter2
import uz.umarxon.adiblar.databinding.FragmentAdiblarBinding

class AdiblarFragment : Fragment() {

    lateinit var binding:FragmentAdiblarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdiblarBinding.inflate(layoutInflater)

        binding.viewPager2.adapter = ViewPagerAdapter2(childFragmentManager)
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        binding.tabLayout.setupWithViewPager(binding.viewPager2)


        setTabs()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager2.currentItem = tab.position
                tab.customView!!.select.setTextColor(Color.WHITE)
                tab.customView!!.select.alpha = 1f
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.customView!!.select.setTextColor(Color.BLACK)
                tab.customView!!.select.alpha = 0.5f
            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        binding.search.setOnClickListener {
            findNavController().navigate(R.id.searchAdibFragment)
        }


        return binding.root
    }

    private fun setTabs() {
        val tabCount = binding.tabLayout.tabCount

        for (i in 0 until tabCount){
            val tabView = LayoutInflater.from(context).inflate(R.layout.item_tab, null, false)
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView = tabView

            if (i==0){
                tabView.select.text = "Mumtoz Adabiyot"
            }
            if (i==1){
                tabView.select.text = "O'zbek Adabiyot"
            }
            if (i==2){
                tabView.select.text = "Jahon Adabiyot"
            }
            tabView.select.alpha = 0.5f

            if (i==0){
                tabView.select.setTextColor(Color.WHITE)
            }else{
                tabView.select.setTextColor(Color.BLACK)
            }

        }
    }

}