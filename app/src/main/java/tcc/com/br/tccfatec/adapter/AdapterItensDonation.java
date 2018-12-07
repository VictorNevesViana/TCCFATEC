package tcc.com.br.tccfatec.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import tcc.com.br.tccfatec.model.Item;
import tcc.com.br.tccfatec.R;
import tcc.com.br.tccfatec.util.ImageUtils;
import tcc.com.br.tccfatec.view.DetailsItemActivity;

public class AdapterItensDonation extends RecyclerView.Adapter<AdapterItensDonation.ViewHolder> {

    private ArrayList<Item> items;
    private Context context;

    public AdapterItensDonation(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterItensDonation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.donate_item,parent,false);
        return new ViewHolder(view);
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItensDonation.ViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.nameItem.setText(item.getName());
        holder.descriptionItem.setText(item.getDescription());
        holder.addresItem.setText(item.getAddress());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(DetailsItemActivity.Companion.newIntent(context,item));

            }
        });

        /*Glide.with(context)
                .load(ImageUtils.convert(item.getPhoto()))
                .error(R.drawable.ic_round_accessible_24px)
                .placeholder(R.drawable.ic_round_accessible_24px)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imgItem);*/

        if(ImageUtils.convert(item.getPhoto()) != null){
            holder.imgItem.setImageBitmap(ImageUtils.convert(item.getPhoto()));
        }else{
            holder.imgItem.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_round_accessible_24px));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgItem;
        TextView nameItem;
        TextView descriptionItem;
        TextView addresItem;
        View v;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            imgItem = itemView.findViewById(R.id.img_item);
            nameItem = itemView.findViewById(R.id.item_name);
            descriptionItem = itemView.findViewById(R.id.item_description);
            addresItem = itemView.findViewById(R.id.item_addres);
        }
    }
}
