package com.appera.app.doghub;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DogAdapter adapter;
    private DogApiService dogApiService = DogApiService.create();
    private List<Dog> dogList;
    private List<Dog> filteredDogList; // For storing filtered dog breeds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dogList = new ArrayList<>();
        filteredDogList = new ArrayList<>(); // Initialize filtered list
        adapter = new DogAdapter(MainActivity.this, filteredDogList); // Set adapter to filtered list
        recyclerView.setAdapter(adapter);

        fetchDogData();
    }

    private void fetchDogData() {
        dogApiService.getAllBreeds().enqueue(new Callback<DogBreedsResponse>() {
            @Override
            public void onResponse(Call<DogBreedsResponse> call, Response<DogBreedsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, List<String>> breeds = response.body().getMessage();
                    dogList.clear();
                    for (Map.Entry<String, List<String>> entry : breeds.entrySet()) {
                        String breed = entry.getKey();
                        fetchRandomImageAndInfo(breed); // Fetch image and additional info
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch breeds", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DogBreedsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchRandomImageAndInfo(String breed) {
        dogApiService.getRandomImage(breed).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String imageUrl = response.body().getMessage();
                    // Example attributes; you might want to replace these with actual API data
                    String lifespan = "10-15 years";
                    String temperament = "Friendly";
                    String group = "Working";

                    Dog dog = new Dog(breed, imageUrl, lifespan, temperament, group);
                    dogList.add(dog);
                    filteredDogList.add(dog); // Add to both lists initially
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Dog info not available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to set up the search functionality
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Inflate your menu

        MenuItem searchItem = menu.findItem(R.id.action_search); // Find search item
        SearchView searchView = (SearchView) searchItem.getActionView(); // Get the search view

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // No action on submit
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterDogs(newText); // Call the filter method
                return true;
            }
        });
        return true;
    }

    // Method to filter dogs based on search input
    private void filterDogs(String query) {
        filteredDogList.clear(); // Clear the filtered list

        if (query.isEmpty()) {
            filteredDogList.addAll(dogList); // If the query is empty, show all dogs
        } else {
            for (Dog dog : dogList) {
                if (dog.getBreed().toLowerCase().contains(query.toLowerCase())) {
                    filteredDogList.add(dog); // Add matching dogs to filtered list
                }
            }
        }

        adapter.notifyDataSetChanged(); // Notify adapter about data changes
    }
}
