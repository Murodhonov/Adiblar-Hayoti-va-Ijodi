package uz.umarxon.adiblar.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.umarxon.adiblar.Data.MySharedPreference
import uz.umarxon.adiblar.HomeFragment
import uz.umarxon.adiblar.MainActivity
import uz.umarxon.adiblar.R
import uz.umarxon.adiblar.SaqlanganlarFragment
import uz.umarxon.adiblar.databinding.ItemRvBinding
import uz.umarxon.adiblar.models.AdibClass

class RvAdapter(private val list: List<AdibClass>,var resetAdapter: ResetAdapter,var p:Int=0,var update:Update = object : Update{
    override fun updater(user: AdibClass) {

    } }) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: AdibClass, position: Int) {
            itemRv.name.text = user.name
            itemRv.date.text = "(${user.birth}-${user.die})"
            itemRv.root.setOnClickListener {
                resetAdapter.click(user,itemRv.card)
            }

            Picasso.get().load(user.imageUrl).into(itemRv.userImage)

            MySharedPreference.init(itemRv.root.context)

            val list = MySharedPreference.adibList
            var index = -1

            for (i in list.indices){
                if (list[i].imageUrl == user.imageUrl){
                    index = i
                        break
                }
            }

            if (index!=-1){
                itemRv.imageLike.setImageResource(R.drawable.fragme_liked)
                itemRv.like.setCardBackgroundColor(Color.parseColor("#00B238"))
            }else{
                itemRv.imageLike.setImageResource(R.drawable.frame)
                itemRv.like.setCardBackgroundColor(Color.parseColor("#F9F9F9"))
            }

            itemRv.like.setOnClickListener {
                if (index!=-1){
                    itemRv.imageLike.setImageResource(R.drawable.frame)
                    itemRv.like.setCardBackgroundColor(Color.parseColor("#F9F9F9"))
                    val list2 = MySharedPreference.adibList
                    list2.remove(user)
                    MySharedPreference.adibList = list2
                    update.updater(user)
                }else{
                    itemRv.imageLike.setImageResource(R.drawable.fragme_liked)
                    itemRv.like.setCardBackgroundColor(Color.parseColor("#00B238"))
                    val list2 = MySharedPreference.adibList
                    list2.add(user)
                    MySharedPreference.adibList = list2
                    update.updater(user)
                }
            }

        }
    }

    interface ResetAdapter{
        fun updateAdapter()
        fun click(user: AdibClass,card:CardView)
    }
    interface Update{
        fun updater(user:AdibClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}