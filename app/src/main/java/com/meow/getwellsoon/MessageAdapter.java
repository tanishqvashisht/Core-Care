package com.meow.getwellsoon;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    Context context;
    List<Messages> data;
    Model model;
    int ITEM_SEND = 1;
    int ITEM_RECEIVE = 2;
    private String currentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@gmail.com", "").replaceAll("\\.", "");


    public MessageAdapter(Context context, List<Messages> data, Model model) {
        this.context = context;
        this.data = data;
        this.model = model;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.sendermsg, parent, false);
            return new SenderViewHolder(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.recievermsg, parent, false);
            return new ReceiverViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getClass() == SenderViewHolder.class) {
            SenderViewHolder senderViewHolder = (SenderViewHolder) holder;
            ((SenderViewHolder) holder).sendermsgtext.setText(data.get(position).getMsg());
        } else {
            ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) holder;
            ((ReceiverViewHolder) holder).receivermsgtext.setText(data.get(position).getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getSenderusername().equals(currentUser)) return ITEM_SEND;
        return ITEM_RECEIVE;
    }
}

class ReceiverViewHolder extends RecyclerView.ViewHolder {

    CardView receivermsg;
    TextView receivermsgtext;
    ShapeableImageView pfpchat;

    public ReceiverViewHolder(@NonNull View itemView) {
        super(itemView);

        receivermsg = itemView.findViewById(R.id.recievermsg);
        receivermsgtext = itemView.findViewById(R.id.receivermsgtext);
    }
}

class SenderViewHolder extends RecyclerView.ViewHolder {

    CardView sendermsg;
    TextView sendermsgtext;

    public SenderViewHolder(@NonNull View itemView) {
        super(itemView);

        sendermsg = itemView.findViewById(R.id.sendermsg);
        sendermsgtext = itemView.findViewById(R.id.sendermsgtext);
    }
}