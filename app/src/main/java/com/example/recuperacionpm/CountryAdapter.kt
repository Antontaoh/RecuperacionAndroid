import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recuperacionpm.R
import com.example.recuperacionpm.model.Country

class CountryAdapter(private val countries: List<Country>, private val onClick: (Country) -> Unit) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName: TextView = view.findViewById(R.id.countryName)
        val flagImage: ImageView = view.findViewById(R.id.flagImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]

        // Obtener el nombre del país desde la estructura corregida
        holder.countryName.text = country.name.common ?: "Nombre desconocido"

        // Cargar la imagen de la bandera con Glide
        Glide.with(holder.itemView.context)
            .load(country.flags.png)
            .placeholder(R.drawable.default_flag) // Imagen por defecto en caso de error
            .into(holder.flagImage)

        holder.itemView.setOnClickListener { onClick(country) }
    }

    override fun getItemCount() = countries.size
}
