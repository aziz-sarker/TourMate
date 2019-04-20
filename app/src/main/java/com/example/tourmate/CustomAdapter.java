package com.example.tourmate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourmate.ConvertWeather.ConvertWeather;
import com.example.tourmate.Weather.Weather;
import com.example.tourmate.Weather.WeatherMainPojoClass;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<com.example.tourmate.Weather.List> list;


    public CustomAdapter(Context context, List<com.example.tourmate.Weather.List> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       com.example.tourmate.Weather.List weatherMainPojoClass = list.get(i);

        /*int[] everyday = new int[]{0,0,0,0,0,0,0};

        for (int k=0; k<list.size();k++){

            date = list.get(k).getDt();
            wTempMin = list.get(k).getMain().getTempMin();
            wTempMax = list.get(k).getMain().getTempMax();

            if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Sat") && everyday[0] < 1){

                viewHolder.dateDT.setText(String.valueOf(ConvertWeather.convertUnixToDate(list.get(i).getDt())));
                viewHolder.minTemp.setText(String.valueOf(list.get(i).getMain().getTempMin())+"°C");
                viewHolder.maxTemp.setText(String.valueOf(list.get(i).getMain().getTempMax())+"°C");

                everyday[0] = 1;
            }if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Sun") && everyday[1] < 1){

                everyday[1] = 1;

            } if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Mon") && everyday[2] < 1){

                everyday[2] = 1;

            } if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Tue") && everyday[3] < 1){

                everyday[3] = 1;

            } if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Wed") && everyday[4] < 1){

                everyday[4] = 1;

            }if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Thu") && everyday[5] < 1){

                everyday[5] = 1;

            } if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Fri") && everyday[6] < 1){

                everyday[6] = 1;

            }
        }*/

        viewHolder.dateDT.setText(String.valueOf(ConvertWeather.convertUnixToDate(list.get(i).getDt())));
        viewHolder.minTemp.setText(String.valueOf(list.get(i).getMain().getTempMin())+"°C");
        viewHolder.maxTemp.setText(String.valueOf(list.get(i).getMain().getTempMax())+"°C");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView minTemp, maxTemp, dateDT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            minTemp = itemView.findViewById(R.id.minTemp);
            maxTemp = itemView.findViewById(R.id.maxTemp);
            dateDT = itemView.findViewById(R.id.dateDT);
        }
    }
}
