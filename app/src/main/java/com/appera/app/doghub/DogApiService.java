package com.appera.app.doghub;

// DogApiService.java
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogApiService {

    String BASE_URL = "https://dog.ceo/api/";

    @GET("breeds/list/all") // Fetch the list of all breeds
    Call<DogBreedsResponse> getAllBreeds();

    @GET("breed/{breed}/images/random") // Fetch a random image for a breed
    Call<ImageResponse> getRandomImage(@Path("breed") String breed);

    // Create an instance of the service
    static DogApiService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(DogApiService.class);
    }
}

