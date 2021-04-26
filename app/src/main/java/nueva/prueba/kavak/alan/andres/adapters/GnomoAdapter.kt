package nueva.prueba.kavak.alan.andres.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nueva.prueba.kavak.alan.andres.R
import nueva.prueba.kavak.alan.andres.models.Brastlewark

abstract class GnomoAdapter(val context: Context, val gnomoList: ArrayList<Brastlewark>) :
    RecyclerView.Adapter<GnomoAdapter.GnomoAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GnomoAdapter.GnomoAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GnomoAdapterViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = gnomoList.size

    override fun onBindViewHolder(holder: GnomoAdapter.GnomoAdapterViewHolder, position: Int) {
        val gnomo: Brastlewark = gnomoList[position]
        holder.bind(gnomo)
    }

    inner class GnomoAdapterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.view_holder_gnomo, parent, false)) {

        private var cardView: CardView? = null
        private var imgGnomo: ImageView? = null
        private var txtIdGnomo: TextView? = null
        private var txtNameGnomo: TextView? = null
        private var txtAgeGnomo: TextView? = null
        private var txtWeightGnomo: TextView? = null
        private var txtHeightGnomo: TextView? = null

        init {
            cardView = itemView.findViewById(R.id.cardView)
            imgGnomo = itemView.findViewById(R.id.imgGnomo)
            txtIdGnomo = itemView.findViewById(R.id.txtIdGnomo)
            txtNameGnomo = itemView.findViewById(R.id.txtNameGnomo)
            txtAgeGnomo = itemView.findViewById(R.id.txtAgeGnomo)
            txtWeightGnomo = itemView.findViewById(R.id.txtWeightGnomo)
            txtHeightGnomo = itemView.findViewById(R.id.txtHeightGnomo)
        }

        fun bind(gnomoList: Brastlewark) {
            cardView!!.setOnClickListener { getDataGnomo(gnomoList) }

            Picasso.get().load(gnomoList.thumbnailGnomo).error(R.drawable.ic_gnomo).fit()
                .centerCrop().into(imgGnomo)
            txtIdGnomo!!.text = "ID: ${gnomoList.idGnomo}"
            txtNameGnomo!!.text = "Nombre: ${gnomoList.nameGnomo}"
            txtAgeGnomo!!.text = "Edad: ${gnomoList.ageGnomo}"
            txtWeightGnomo!!.text = "Peso: ${gnomoList.weightGnomo}"
            txtHeightGnomo!!.text = "Altura: ${gnomoList.heightGnomo}"
        }
    }

    abstract fun getDataGnomo(gnomo: Brastlewark)

    fun refreshData(gnomoList: ArrayList<Brastlewark>) {
        this.gnomoList.clear()
        this.gnomoList.addAll(gnomoList)

        notifyDataSetChanged()
    }
}