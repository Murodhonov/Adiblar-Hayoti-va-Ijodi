package uz.umarxon.adiblar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.umarxon.adiblar.Adapters.RvAdapter
import uz.umarxon.adiblar.Adapters.RvAdibAdapter
import uz.umarxon.adiblar.Data.MySharedPreference
import uz.umarxon.adiblar.databinding.FragmentSaqlanganlarBinding
import uz.umarxon.adiblar.models.AdibClass

class SaqlanganlarFragment() : Fragment() {

    lateinit var binding:FragmentSaqlanganlarBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSaqlanganlarBinding.inflate(layoutInflater)

        MySharedPreference.init(binding.root.context)

        val list = MySharedPreference.adibList

        binding.search!!.setOnClickListener {
            findNavController().navigate(R.id.searchAdibFragment)
        }

        binding.rv.adapter = RvAdibAdapter(binding.root.context,list,object :RvAdibAdapter.RvClick{
            override fun onCLick(adib: AdibClass) {
                findNavController().navigate(R.id.aboutFragment, bundleOf("user" to adib))
            }
        },1)

        return binding.root
    }


    override fun onResume() {
        super.onResume()

        val list = MySharedPreference.adibList
        binding.rv.adapter = RvAdibAdapter(binding.root.context,list,object :RvAdibAdapter.RvClick{
            override fun onCLick(adib: AdibClass) {
                findNavController().navigate(R.id.aboutFragment, bundleOf("user" to adib))
            }
        },1)
    }

}