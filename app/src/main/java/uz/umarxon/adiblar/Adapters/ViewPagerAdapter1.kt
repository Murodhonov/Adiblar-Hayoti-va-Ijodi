package uz.umarxon.adiblar.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.umarxon.adiblar.*
import uz.umarxon.adiblar.Data.MySharedPreference
import uz.umarxon.adiblar.models.AdibClass

class ViewPagerAdapter1(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {

        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val adiblarListFragment = AdiblarFragment()
        return   when(position){

            0->{
                adiblarListFragment
            }
            1->{
                SaqlanganlarFragment()
            }
            2->{
                SozlamalarFragment()
            }

            else->{
                Fragment()
            }

        }
    }
}
