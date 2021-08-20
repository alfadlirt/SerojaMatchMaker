package id.ac.polman.astra.serojamatchmaker.entity;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import retrofit2.Call;

public class EventRepository{

    APIService mAPIService;

    public MutableLiveData<List<Event>> event = new MutableLiveData<>();

   // Call<List<Event>> call = mAPIService.getEvent();

}
