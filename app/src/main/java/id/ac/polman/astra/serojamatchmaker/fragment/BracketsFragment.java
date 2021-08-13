package id.ac.polman.astra.serojamatchmaker.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import id.ac.polman.astra.serojamatchmaker.R;
import id.ac.polman.astra.serojamatchmaker.adapter.BracketListAdapter;
import id.ac.polman.astra.serojamatchmaker.adapter.BracketsSectionAdapter;
import id.ac.polman.astra.serojamatchmaker.customview.WrapContentHeightViewPager;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseBracketGet;
import id.ac.polman.astra.serojamatchmaker.model.BracketArrayed;
import id.ac.polman.astra.serojamatchmaker.model.BracketCard;
import id.ac.polman.astra.serojamatchmaker.model.ColomnData;
import id.ac.polman.astra.serojamatchmaker.model.CompetitorData;
import id.ac.polman.astra.serojamatchmaker.model.MatchData;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import id.ac.polman.astra.serojamatchmaker.utils.BracketsUtility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private WrapContentHeightViewPager viewPager;
    private BracketsSectionAdapter sectionAdapter;
    private ArrayList<ColomnData> sectionList;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;
    private APIService mAPIService;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brackts, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        setData();
        //intialiseViewPagerAdapter();
    }

    private void setData() {
        sectionList = new ArrayList<>();


        mAPIService = APIUtils.getAPIService();
        Call<ResponseBracketGet> call = mAPIService.getEventBracket("EVT0000001");

        call.enqueue(new Callback<ResponseBracketGet>() {
            @Override
            public void onResponse(Call<ResponseBracketGet> call, Response<ResponseBracketGet> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==true) {
                        int current = response.body().getData().get(0).getStageNumber();
                        int bracketBound = response.body().getData().get(0).getStageNumber()*2;
                        int counterStage = 0;

                        List<BracketArrayed> bracketArrayed = new ArrayList<>();
                        bracketArrayed.add(new BracketArrayed());
                        for(int i = 0; i <response.body().getData().size(); i++){
                            if(current!=response.body().getData().get(i).getStageNumber()){
                                counterStage++;
                                current = response.body().getData().get(i).getStageNumber();
                                bracketArrayed.add(new BracketArrayed());
                            }
                            BracketCard bracketcard = new BracketCard();
                            bracketcard.setStage_type(response.body().getData().get(i).getStageType());
                            if(response.body().getData().get(i).getIsWo()==null){
                                bracketcard.setIs_wo(false);
                            }
                            else if(response.body().getData().get(i).getIsWo()==1){
                                bracketcard.setIs_wo(true);
                            }
                            else{
                                bracketcard.setIs_wo(false);
                            }

                            if(response.body().getData().get(i).getIsWoMoved()==null){
                                bracketcard.setIs_wo_moved(false);
                            }
                            else if(response.body().getData().get(i).getIsWoMoved()==1){
                                bracketcard.setIs_wo_moved(true);
                            }
                            else if(response.body().getData().get(i).getIsWoMoved()==0){
                                bracketcard.setIs_wo_moved(false);
                            }

                            if(response.body().getData().get(i).getIsEnd()==1){
                                bracketcard.setTeam_a_name(response.body().getData().get(i).getTeamA());
                                bracketcard.setTeam_b_name(response.body().getData().get(i).getTeamB());
                                if(response.body().getData().get(i).getStatus().equals("FINISHED")){
                                    bracketcard.setSkor_a_name(response.body().getData().get(i).getSkorA());
                                    bracketcard.setSkor_b_name(response.body().getData().get(i).getSkorB());
                                }
                            }
                            else if(response.body().getData().get(i).getIsWo()==0&&
                                    response.body().getData().get(i).getIsEnd()==0){
                                bracketcard.setTeam_a_name(response.body().getData().get(i).getTeamA());
                                bracketcard.setTeam_b_name(response.body().getData().get(i).getTeamB());
                                if(response.body().getData().get(i).getStatus().equals("FINISHED")){
                                    bracketcard.setSkor_a_name(response.body().getData().get(i).getSkorA());
                                    bracketcard.setSkor_b_name(response.body().getData().get(i).getSkorB());
                                }
                            }
                            else{
                                if(response.body().getData().get(i).getTeamA()==null){
                                    bracketcard.setTeam_b_name(response.body().getData().get(i).getTeamB());
                                }
                                else{
                                    bracketcard.setTeam_a_name(response.body().getData().get(i).getTeamA());
                                }
                            }
                            bracketArrayed.get(counterStage).addBracket(bracketcard, response.body().getData().get(i).getIndexNumber());
                            Log.e("Update Error : ","ASD");

                        }

                        sectionList = new ArrayList<>();
                        CompetitorData competitorNull = new CompetitorData("-", "-");
                        for(int i = 0; i<bracketArrayed.size();i++){
                            ArrayList<MatchData> ColumnNmatchesList = new ArrayList<>();
                            BracketCard[] stageArray = bracketArrayed.get(i).getBracket();
                            for(int j=1;j<=bracketBound;j++){
                                MatchData matchData;
                                if(stageArray[j]==null){
                                    matchData = new MatchData(competitorNull, competitorNull);
                                }
                                else{
                                    if(stageArray[j].getIs_wo()){
                                        if(stageArray[j].getTeam_a_name()==null){
                                            matchData = new
                                                    MatchData(
                                                    new CompetitorData("-", "-"),
                                                    new CompetitorData(stageArray[j].getTeam_b_name(), Integer.toString(stageArray[j].getSkor_b_name())));
                                        }
                                        else{
                                            matchData = new
                                                    MatchData(
                                                    new CompetitorData(stageArray[j].getTeam_a_name(), Integer.toString(stageArray[j].getSkor_a_name())),
                                                    new CompetitorData("-", "-"));
                                        }
                                    }
                                    else{
                                        matchData = new
                                                MatchData(
                                                new CompetitorData(stageArray[j].getTeam_a_name(), Integer.toString(stageArray[j].getSkor_a_name())),
                                                new CompetitorData(stageArray[j].getTeam_b_name(), Integer.toString(stageArray[j].getSkor_b_name())));
                                    }

                                }

                                ColumnNmatchesList.add(matchData);

                            }
                            ColomnData colomnData = new ColomnData(ColumnNmatchesList);
                            sectionList.add(colomnData);
                            bracketBound=bracketBound/2;
                        }
                        Log.e("Update Error : ","ASD");
                    }else{

                    }
                }
                Log.e("Update Error : ","ASD");
                intialiseViewPagerAdapter();
            }

            @Override
            public void onFailure(Call<ResponseBracketGet> call, Throwable t) {
                Log.e("Update Error : ", t.getMessage());
                Toast.makeText(getActivity(), "Data gagal tersimpan!", Toast.LENGTH_LONG).show();
            }
        });
        //-----------
        /*
        ArrayList<MatchData> Colomn1matchesList = new ArrayList<>();
        ArrayList<MatchData> colomn2MatchesList = new ArrayList<>();
        ArrayList<MatchData> colomn3MatchesList = new ArrayList<>();

        CompetitorData competitorOne = new CompetitorData("Manchester United Fc", "2");
        CompetitorData competitorTwo = new CompetitorData("Arsenal", "1");
        CompetitorData competitorThree = new CompetitorData("Chelsea", "2");
        CompetitorData competitorFour = new CompetitorData("Tottenham", "1");
        CompetitorData competitorFive = new CompetitorData("Manchester FC", "2");
        CompetitorData competitorSix = new CompetitorData("Liverpool", "4");
        CompetitorData competitorSeven = new CompetitorData("West ham ", "2");
        CompetitorData competitorEight = new CompetitorData("Bayern munich", "1");
        MatchData matchData1 = new MatchData(competitorOne,competitorTwo);
        MatchData matchData2 = new MatchData(competitorThree, competitorFour);
        MatchData matchData3 = new MatchData(competitorFive,competitorSix);
        MatchData matchData4 = new MatchData(competitorSeven, competitorEight);

        Colomn1matchesList.add(matchData1);
        Colomn1matchesList.add(matchData2);
        Colomn1matchesList.add(matchData3);
        Colomn1matchesList.add(matchData4);

        ColomnData colomnData1 = new ColomnData(Colomn1matchesList);
        sectionList.add(colomnData1);

        //---------------------

        CompetitorData competitorNine = new CompetitorData("Manchester United Fc", "2");
        CompetitorData competitorTen = new CompetitorData("Chelsea", "4");
        CompetitorData competitorEleven = new CompetitorData("Liverpool", "2");
        CompetitorData competitorTwelve = new CompetitorData("westham", "1");

        MatchData matchData5 = new MatchData(competitorNine,competitorTen);
        MatchData matchData6 = new MatchData(competitorEleven, competitorTwelve);

        colomn2MatchesList.add(matchData5);
        colomn2MatchesList.add(matchData6);

        ColomnData colomnData2 = new ColomnData(colomn2MatchesList);
        sectionList.add(colomnData2);

        //-------------------
        CompetitorData competitorThirteen = new CompetitorData("Chelsea", "2");
        CompetitorData competitorForteen = new CompetitorData("Liverpool", "1");
        MatchData matchData7 = new MatchData(competitorThirteen, competitorForteen);
        colomn3MatchesList.add(matchData7);
        ColomnData colomnData3 = new ColomnData(colomn3MatchesList);
        sectionList.add(colomnData3);
        */


    }

    private void intialiseViewPagerAdapter() {

        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(),this.sectionList);
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageMargin(-200);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(50);

        viewPager.addOnPageChangeListener(this);
    }

    private void initViews() {

        viewPager = (WrapContentHeightViewPager) getView().findViewById(R.id.container);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (mCurrentPagerState != ViewPager.SCROLL_STATE_SETTLING) {
            // We are moving to next screen on right side
            if (positionOffset > 0.5) {
                // Closer to next screen than to current
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view here
                    if (getBracketsFragment(position).getColomnList().get(0).getHeight()
                            != BracketsUtility.dpToPx(131))
                        getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                    if (getBracketsFragment(position + 1).getColomnList().get(0).getHeight()
                            != BracketsUtility.dpToPx(131))
                        getBracketsFragment(position + 1).shrinkView(BracketsUtility.dpToPx(131));
                }
            } else {
                // Closer to current screen than to next
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateViewhere

                    if (getBracketsFragment(position + 1).getCurrentBracketSize() ==
                            getBracketsFragment(position + 1).getPreviousBracketSize()) {
                        getBracketsFragment(position + 1).shrinkView(BracketsUtility.dpToPx(131));
                        getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                    } else {
                        int currentFragmentSize = getBracketsFragment(position + 1).getCurrentBracketSize();
                        int previousFragmentSize = getBracketsFragment(position + 1).getPreviousBracketSize();
                        if (currentFragmentSize != previousFragmentSize) {
                            getBracketsFragment(position + 1).expandHeight(BracketsUtility.dpToPx(262));
                            getBracketsFragment(position).shrinkView(BracketsUtility.dpToPx(131));
                        }
                    }
                }
            }
        } else {
            // We are moving to next screen left side
            if (positionOffset > 0.5) {
                // Closer to current screen than to next
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view for screen

                }
            } else {
                // Closer to next screen than to current
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateviewfor screem
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public BracketsColomnFragment getBracketsFragment(int position) {
        BracketsColomnFragment bracktsFrgmnt = null;
        if (getChildFragmentManager() != null) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof BracketsColomnFragment) {
                        bracktsFrgmnt = (BracketsColomnFragment) fragment;
                        if (bracktsFrgmnt.getSectionNumber() == position)
                            break;
                    }
                }
            }
        }
        return bracktsFrgmnt;
    }
}
