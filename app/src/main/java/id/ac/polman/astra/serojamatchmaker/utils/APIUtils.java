package id.ac.polman.astra.serojamatchmaker.utils;

import id.ac.polman.astra.serojamatchmaker.remote.APIService;

public class APIUtils {
    public APIUtils() {
    }

    public static final String BASE_URL = "http://192.168.1.6:8080/api/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
