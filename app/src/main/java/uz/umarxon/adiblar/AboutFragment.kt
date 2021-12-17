package uz.umarxon.adiblar

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.umarxon.adiblar.Data.MySharedPreference
import uz.umarxon.adiblar.databinding.FragmentAboutBinding
import uz.umarxon.adiblar.models.AdibClass

class AboutFragment : Fragment() {

    lateinit var binding:FragmentAboutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater)

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        binding.backBadge.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.actionBar.visibility = View.GONE

        binding.searchBadge.setOnClickListener {
            binding.searchBadge.visibility = View.GONE
            binding.backBadge.visibility = View.GONE
            binding.savedBackground.visibility = View.GONE
            binding.actionBar.visibility = View.VISIBLE
        }

        binding.close.setOnClickListener {
            binding.searchBadge.visibility = View.VISIBLE
            binding.backBadge.visibility = View.VISIBLE
            binding.savedBackground.visibility = View.VISIBLE
            binding.actionBar.visibility = View.GONE
        }

        binding.edtSearch.addTextChangedListener {
            if (binding.edtSearch.text.isNotEmpty()){
                binding.adibMalumot.setTextToHighlight(binding.edtSearch.text.toString());
                binding.adibMalumot.setTextHighlightColor(android.R.color.holo_green_light);
                binding.adibMalumot.setCaseInsensitive(true);
                binding.adibMalumot.highlight();
            }else{
                binding.adibMalumot.setTextToHighlight("");
                binding.adibMalumot.setTextHighlightColor(android.R.color.holo_green_light);
                binding.adibMalumot.setCaseInsensitive(true);
                binding.adibMalumot.highlight();
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val user = arguments?.getSerializable("user") as AdibClass

        Picasso.get().load(user.imageUrl).into(binding.image)

        binding.image.setOnClickListener {
            findNavController().navigate(R.id.imageVIewFragment, bundleOf("user" to user))
        }

        MySharedPreference.init(binding.root.context)

        val list = MySharedPreference.adibList
        var index = -1

        for (i in list.indices){
            if (list[i].imageUrl == user.imageUrl){
                index = i
                break
            }
        }

        if (index!=-1){
            binding.likeImage.setImageResource(R.drawable.fragme_liked)
            binding.likeImage.setColorFilter(Color.parseColor("#00B238"))
        }else{
            binding.likeImage.setImageResource(R.drawable.frame)
            binding.likeImage.setColorFilter(Color.parseColor("#000000"))
        }

        binding.savedBackground.setOnClickListener {
            if (index!=-1){
                binding.likeImage.setImageResource(R.drawable.fragme_liked)
                binding.likeImage.setColorFilter(Color.parseColor("#00B238"))
                val list2 = MySharedPreference.adibList
                list2.removeAt(index)
                MySharedPreference.adibList = list2
                onResume()
            }else{
                binding.likeImage.setImageResource(R.drawable.frame)
                binding.likeImage.setColorFilter(Color.parseColor("#000000"))
                val list2 = MySharedPreference.adibList
                list2.add(user)
                MySharedPreference.adibList = list2
                onResume()
            }
        }

        binding.adibName.text = user.name
        binding.adibBirth.text = "(${user.birth}-${user.die})"
        binding.adibMalumot.text = user.about
    }

}