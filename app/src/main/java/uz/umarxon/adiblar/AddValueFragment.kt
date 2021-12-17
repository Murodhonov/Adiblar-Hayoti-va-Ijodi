package uz.umarxon.adiblar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_value.*
import uz.umarxon.adiblar.databinding.FragmentAddValueBinding
import uz.umarxon.adiblar.models.AdibClass

class AddValueFragment : Fragment() {

    lateinit var binding:FragmentAddValueBinding
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference
    lateinit var firebaseFirestore: FirebaseFirestore
    var imageUrl = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddValueBinding.inflate(layoutInflater)

        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()

        reference = firebaseStorage.getReference("images")

        binding.rasm.setOnClickListener {
            getImageContent.launch("image/*")
        }
        binding.image.setOnClickListener {
            getImageContent.launch("image/*")
        }

        binding.save.setOnClickListener{

            val name = binding.name.text.toString().trim()
            val birth = binding.birthday.text.toString().trim()
            val die = binding.diedate.text.toString().trim()
            val type = when(binding.type.selectedItemPosition){
                0->"one"
                1->"two"
                2->"three"
                else->""
            }
            val about = binding.about.text.toString().trim()

            if (name.isNotEmpty() && birth.isNotEmpty() && die.isNotEmpty() && type.trim().isNotEmpty() && about.isNotEmpty()) {

                val user = AdibClass(name, birth, die, type, about, imageUrl)

                firebaseFirestore.collection("adiblar_royhati")
                    .add(user)
                    .addOnSuccessListener { Toast.makeText(context, "${it.id} Muvaffaqiyatli yuklandi", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "${it.message} xatolik", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        return binding.root

    }

    private var getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri->
        binding.image.setImageURI(uri)

        val m = System.currentTimeMillis()

        val uploadTask = reference.child(m.toString()).putFile(uri)

        uploadTask.addOnSuccessListener {
            if (it.task.isSuccessful){
                val downloadUrl = it.metadata?.reference?.downloadUrl
                downloadUrl?.addOnSuccessListener {imageUri->
                    imageUrl = imageUri.toString()
                }
            }
        }.addOnFailureListener{
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
    }

}