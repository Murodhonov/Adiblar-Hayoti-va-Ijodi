package uz.umarxon.adiblar.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.umarxon.adiblar.*
import uz.umarxon.adiblar.Data.MySharedPreference

class ViewPagerAdapter2(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int  = 3
    override fun getItem(position: Int): Fragment {
        return   when(position){

            0->{
                MumtozAdabiyotFragment()
            }
            1->{
                UzbekAdabiyotFragment()
            }
            2->{
                JahonAdabiyotiFragment()
            }

            else->{
                Fragment()
            }

        }
    }
}