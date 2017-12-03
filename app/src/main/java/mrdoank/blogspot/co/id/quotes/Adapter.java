package mrdoank.blogspot.co.id.quotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mrdoank.blogspot.co.id.quotes.model.Quote;

/**
 * Created by root on 02/12/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Quote> quotes;

    public Adapter(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.tv_Content.setText(quote.getContent());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_Content = itemView.findViewById(R.id.tv_content);
        }
    }

    public void addAll(List<Quote> contents) {
        this.quotes = contents;
        notifyDataSetChanged();
    }
}
