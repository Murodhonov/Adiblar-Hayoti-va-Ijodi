package uz.umarxon.adiblar.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.umarxon.adiblar.Data.MySharedPreference
import uz.umarxon.adiblar.R
import uz.umarxon.adiblar.databinding.ItemRvBinding
import uz.umarxon.adiblar.models.AdibClass
import java.io.Serializable

class RvAdibAdapter(val context: Context?, var list: ArrayList<AdibClass>, var rvClick: RvClick, var saqNoSaq:Int=0) :
    RecyclerView.Adapter<RvAdibAdapter.VH>(), Serializable {

    inner class VH(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(adib: AdibClass, position: Int) {
            itemRv.name.text = adib.name
            itemRv.date.text = "(${adib.birth}-${adib.die})"
            Picasso.get().load(adib.imageUrl).into(itemRv.userImage)

            MySharedPreference.init(context!!)
//            println(_root_ide_package_.uz.umarxon.adiblar.Data.MySharedPreference.adibList)
            var index = -1
            var mList = MySharedPreference.adibList
            for (n in mList.indices) {
                println("mList[n] = ${mList[n].imageUrl} == ${adib.imageUrl}")
                if (mList[n].imageUrl == adib.imageUrl) {
                    index = n
                    break
                }
            }
            println("index: $index")
            if (index!=-1){
                itemRv.imageLike.setImageResource(R.drawable.ic_saqlangan_2)
            }else{
                itemRv.imageLike.setImageResource(R.drawable.ic_saqlangan_1)
            }

            itemRv.root.setOnClickListener { rvClick.onCLick(adib) }
            itemRv.imageLike.setOnClickListener {

                if (index != -1) {
//                    itemRv.imageSelection.setImageResource(R.drawable.ic_saqlangan_1)
                    val l = MySharedPreference.adibList
                    println(""+l.removeAt(index)+"removed ")
                    MySharedPreference.adibList = l
                } else {
//                    itemRv.imageSelection.setImageResource(R.drawable.ic_saqlangan_2)
                    val l = MySharedPreference.adibList
                    l.add(adib)
                    MySharedPreference.adibList = l
                }
                println("saqNoSaq : $saqNoSaq")
                if (saqNoSaq == 1){
                    list.remove(adib)
                    notifyItemRemoved(position)
                    notifyItemRangeRemoved(position, mList.size)
                }else{
                    notifyItemChanged(position)
                }
            }
        }
    }

    interface RvClick {
        fun onCLick(adib: AdibClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}