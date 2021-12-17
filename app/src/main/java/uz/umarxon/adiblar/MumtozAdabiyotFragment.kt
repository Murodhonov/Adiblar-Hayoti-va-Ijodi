package uz.umarxon.adiblar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import uz.umarxon.adiblar.Adapters.RvAdapter
import uz.umarxon.adiblar.Adapters.RvAdibAdapter
import uz.umarxon.adiblar.databinding.FragmentMumtozAdabiyotBinding
import uz.umarxon.adiblar.models.AdibClass

class MumtozAdabiyotFragment : Fragment() {

    lateinit var binding:FragmentMumtozAdabiyotBinding
    lateinit var list:ArrayList<AdibClass>
    lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMumtozAdabiyotBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onResume() {
        super.onResume()

        firebaseFirestore = FirebaseFirestore.getInstance()

        firebaseFirestore.collection("adiblar_royhati")
            .get()
            .addOnCompleteListener{
                if (it.isSuccessful){
                    list = ArrayList()
                    val result = it.result
                    result?.forEach {queryDocumentSnapshot ->
                        val user = queryDocumentSnapshot.toObject(AdibClass::class.java)
                        if (user.type == "one"){
                            list.add(user)
                        }
                    }
                    binding.rv.adapter = RvAdibAdapter(binding.root.context,list,object : RvAdibAdapter.RvClick{
                        override fun onCLick(adib: AdibClass) {
                            findNavController().navigate(R.id.aboutFragment, bundleOf("user" to adib))
                        }
                    })
                }
            }
    }
}