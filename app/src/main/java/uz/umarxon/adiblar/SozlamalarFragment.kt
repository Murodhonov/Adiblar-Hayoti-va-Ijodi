package uz.umarxon.adiblar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.umarxon.adiblar.databinding.FragmentSozlamalarBinding

class SozlamalarFragment : Fragment() {

    lateinit var binding:FragmentSozlamalarBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSozlamalarBinding.inflate(layoutInflater)

        binding.addAdib.setOnClickListener {
            findNavController().navigate(R.id.addValueFragment)
        }

        binding.share!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val body = "Adiblar Dasturi uchun @Murodhonov ga murojaat qilishingiz mumkin"
            val sub = "Adiblar dasturini ulashish"
            intent.putExtra(Intent.EXTRA_SUBJECT,sub)
            intent.putExtra(Intent.EXTRA_TEXT,body)
            startActivity(Intent.createChooser(intent,"Adiblar Dasturini ulashish"))
        }

        binding.about!!.setOnClickListener {
            Snackbar.make(binding.root,"@Murodhonov Umarxon tomonidan yaratildi !",Snackbar.LENGTH_LONG).show()
        }

        return binding.root
    }


}