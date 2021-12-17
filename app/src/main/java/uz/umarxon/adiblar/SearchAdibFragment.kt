package uz.umarxon.adiblar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import uz.umarxon.adiblar.Adapters.RvAdapter
import uz.umarxon.adiblar.Adapters.RvAdibAdapter
import uz.umarxon.adiblar.databinding.FragmentJahonAdabiyotiBinding
import uz.umarxon.adiblar.databinding.FragmentSearchAdibBinding
import uz.umarxon.adiblar.models.AdibClass

class SearchAdibFragment : Fragment() {

    lateinit var binding:FragmentSearchAdibBinding
    lateinit var list:ArrayList<AdibClass>
    lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchAdibBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.close.setOnClickListener {
            findNavController().popBackStack()
        }

        firebaseFirestore = FirebaseFirestore.getInstance()

        firebaseFirestore.collection("adiblar_royhati")
            .get()
            .addOnCompleteListener{
                if (it.isSuccessful){
                    list = ArrayList()
                    val result = it.result
                    result?.forEach {queryDocumentSnapshot ->
                        val user = queryDocumentSnapshot.toObject(AdibClass::class.java)
                        list.add(user)
                    }
                    if (binding.edtSearch.text.isEmpty()){
                        update(list)
                    }
                }
            }

        binding.edtSearch.addTextChangedListener {
            onResume()
            val list2 = ArrayList<AdibClass>()
            for (i in list.indices){
                if (list[i].name!!.toLowerCase().startsWith(binding.edtSearch.text.toString().toLowerCase())){
                    list2.add(list[i])
                }
            }
            update(list2)
        }
    }
    fun update(list:ArrayList<AdibClass>){
        binding.rv.adapter = RvAdibAdapter(binding.root.context,list,object : RvAdibAdapter.RvClick{
            override fun onCLick(adib: AdibClass) {
                findNavController().navigate(R.id.aboutFragment, bundleOf("user" to adib))
            }
        })
    }


}