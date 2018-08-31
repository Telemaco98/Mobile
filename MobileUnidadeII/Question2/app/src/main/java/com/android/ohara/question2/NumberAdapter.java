package com.android.ohara.question2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberHolder>{
    private ArrayList<Integer> numbers;
    private Context            context;

    public NumberAdapter (Context context) {
        this.context = context;

        numbers = new ArrayList<>();
        for (int i = 0; i < 50; i++) numbers.add(i+1);
    }

    @NonNull
    @Override
    public NumberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.number_adapter, parent, false);
        NumberHolder numberHolder = new NumberHolder(view);
        return numberHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberHolder holder, int position) {
        int number = numbers.get(position);
        holder.txtNumber.setText(String.valueOf(number));
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public class NumberHolder extends RecyclerView.ViewHolder {
        private TextView txtNumber;
        private Button btnInfo;

        public NumberHolder (View view) {
            super (view);
            this.txtNumber = (TextView) view.findViewById(R.id.txtNumber);
            this.btnInfo   = (Button)   view.findViewById(R.id.btnInfo);
            btnInfo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Toast.makeText(context, "Você clicou no número " + (pos + 1), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}