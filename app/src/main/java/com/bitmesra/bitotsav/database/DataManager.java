package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.events.EventItem;
import com.bitmesra.bitotsav.database.models.events.FlagshipItem;
import com.bitmesra.bitotsav.database.models.events.EventDetailsDto;
import com.bitmesra.bitotsav.database.models.login.SignUpBody;
import com.bitmesra.bitotsav.database.models.login.SignUpResultBody;
import com.bitmesra.bitotsav.features.EventDtoType;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Observable;

public class DataManager {

    NetworkManager networkManager = null;
    RealmManager realmManager = null;

    private DataManager() {

    }

    public static DataManager getDataManager() {
        return DataManagerHelper.INSTANCE;
    }

    /**
     * Signup funtion
     */

    public Observable<Response<SignUpResultBody>> signUp(Context context, SignUpBody body) {
        createNetworkManager(context);
        return networkManager.signUp(body);
    }

    /**
     * Event functions
     */
    public List<EventItem> getEventList() {
        List<EventItem> list = new ArrayList<>();
        list.add(new EventItem("Day 1", R.drawable.home1));
        list.add(new EventItem("Day 2", R.drawable.home2));
        list.add(new EventItem("Day 3", R.drawable.home1));
        list.add(new EventItem("Day 4", R.drawable.home2));
        list.add(new EventItem("Informals", R.drawable.home1));
        return list;
    }

    public Observable<List<EventDto>> getTimelineList(Context context, int dayNumber) {
        createNetworkManager(context);
        return networkManager.getTimelineEvents(dayNumber);
    }

    public List<FlagshipItem> getFlagshipList() {
        List<FlagshipItem> list = new ArrayList<>();
        list.add(new FlagshipItem("Pukaar", "Dramsoc", R.drawable.home1));
        list.add(new FlagshipItem("Saptak", "Music club", R.drawable.home2));
        list.add(new FlagshipItem("Rhapsody", "Music club", R.drawable.home1));
        list.add(new FlagshipItem("Dance Saga", "Dance club", R.drawable.home2));
        list.add(new FlagshipItem("Stomp the yard", "Dance club", R.drawable.home1));
        list.add(new FlagshipItem("Talkies", "PSOC", R.drawable.home2));
        list.add(new FlagshipItem("Mr. and Miss Bitotsav", "Rotaract", R.drawable.home1));
        list.add(new FlagshipItem("Mun", "Unesquo", R.drawable.home2));
        list.add(new FlagshipItem("B-Plan", "EDC", R.drawable.home1));
        list.add(new FlagshipItem("Overnight coding", "ACM club", R.drawable.home2));
        list.add(new FlagshipItem("Takeshi's Castle", "Robolution", R.drawable.home1));
        return list;
    }

    public List<EventDto> getInformalList() {
        List<EventDto> list = new ArrayList<>();
        list.add(new EventDto("Rangmanch", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        list.add(new EventDto("I, Me, Myself", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        list.add(new EventDto("Tune up together", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        list.add(new EventDto("Foot loose", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        list.add(new EventDto("Panacha", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        return list;
    }

    /**
     * Flagship functions
     */
    public Observable<EventDetailsDto> getFlagshipEvent(Context context, String eventName) {
        createNetworkManager(context);
        return networkManager.getFlagshipEvent(eventName);
    }


    /**
     * Helpers classes and functions
     */

    private void createNetworkManager(Context context) {
        if (networkManager == null) {
            networkManager = new NetworkManager(context);
        }
    }

    private void createRealmManager() {
        if (realmManager == null) {
            realmManager = new RealmManager();
        }
    }

    public RealmManager getRealmManager() {
        createRealmManager();
        return realmManager;
    }

    private static class DataManagerHelper {
        private static final DataManager INSTANCE = new DataManager();
    }

}

