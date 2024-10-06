package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.myapplication.R;
import model.ModelLayanan;

public class AdapterLayanan extends RecyclerView.Adapter<AdapterLayanan.ViewHolder> {
    private List<ModelLayanan> layananList;
    private Context context;

    public AdapterLayanan(Context context, List<ModelLayanan> layananList) {
        this.context = context;
        this.layananList = layananList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Menginflate layout item_layanan
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModelLayanan layanan = layananList.get(position);
        // Menggunakan ID yang sesuai di item_layanan.xml
        holder.tvNamaLayanan.setText(layanan.getNama()); // Ganti ke getJenisLayanan()
        holder.tvHarga.setText(layanan.getHarga());
    }

    @Override
    public int getItemCount() {
        return layananList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaLayanan, tvHarga;

        public ViewHolder(View itemView) {
            super(itemView);
            // Menggunakan ID yang sesuai dari item_layanan.xml
            tvNamaLayanan = itemView.findViewById(R.id.tvItemLayNama); // Ganti ID dengan yang sesuai
            tvHarga = itemView.findViewById(R.id.tvItemLayHarga); // Ganti ID dengan yang sesuai
        }
    }
}
