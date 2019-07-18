package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    public String content="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView =findViewById(R.id.text_result);
        setContentView(R.layout.activity_main);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build();
        OpenWeatherMapApi openWeatherMapApi=retrofit.create(OpenWeatherMapApi.class);
        Map<String,String> parameters=new HashMap<>();
        parameters.put("Kolkata,In","4d99b523e2324aec5e0614b53d564e1d");
        Call<List<Post>> call=openWeatherMapApi.getPosts(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful())
                {
                    textView.setText(response.code());
                }
                List<Post> posts=response.body();
                for(Post post:posts)
                {

                    content+= "temp " + post.getTemp() + "\n";
                    content+="ground level "+post.getGrnd_level()+"\n";
                    content+="humidity "+post.getHumidity()+"\n";
                    content+="pressure"+post.getPressure()+"\n";
                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                   // textView.setText(t.getMessage());
            }
        });
    }
}
