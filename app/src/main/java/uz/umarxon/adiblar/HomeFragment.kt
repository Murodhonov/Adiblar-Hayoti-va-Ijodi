package uz.umarxon.adiblar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import uz.umarxon.adiblar.Adapters.ViewPagerAdapter1
import uz.umarxon.adiblar.Data.MySharedPreference
import uz.umarxon.adiblar.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var root:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        root = inflater.inflate(R.layout.fragment_home, container, false)

        MySharedPreference.init(binding.root.context)

        binding.viewPager2.adapter =  ViewPagerAdapter1(childFragmentManager,lifecycle)

        binding.viewPager2.isUserInputEnabled = false

        binding.bottomBar.setOnItemSelectedListener {position:Int ->
            when(position){
                0->binding.viewPager2.setCurrentItem(0,true)
                1->binding.viewPager2.setCurrentItem(1,true)
                2->binding.viewPager2.setCurrentItem(2,true)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.bottomBar.itemActiveIndex = 0
        binding.viewPager2.currentItem = 0

    }

}