package com.app.karobardainik.fragments;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.app.karobardainik.R;
import com.app.karobardainik.activities.Home;
import com.app.karobardainik.models.Article;
import com.app.karobardainik.models.Bullion;
import com.app.karobardainik.models.Cartoon;
import com.app.karobardainik.models.Currency;
import com.app.karobardainik.models.GasNOil;
import com.app.karobardainik.models.HomeCategory;
import com.app.karobardainik.models.HomeResponse;
import com.app.karobardainik.models.NewsVideo;
import com.app.karobardainik.models.SpecialNews;
import com.app.karobardainik.server_protocols.ApiCalls;
import com.app.karobardainik.server_protocols.RetrofitSingleton;
import com.app.karobardainik.utils.CommonMethods;
import com.app.karobardainik.utils.Opener;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.Typeface.BOLD;
import static com.app.karobardainik.utils.CommonMethods.getUrlVideoRTSP;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private YouTubePlayer mYouTubePlayer;
    private static final String YOUTUBE_DEV_KEY = "AIzaSyDL8aDwb6xPoUfkqV2XAPu3O0RjUB-zdSY";


    Home home;
    private HomeResponse homeResponse;
    private List<SpecialNews> specialNewses = new ArrayList<>();
    private List<Cartoon> cartoons = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();
    private List<Bullion> bullions = new ArrayList<>();
    private List<HomeCategory> homeCategories = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private List<GasNOil> gasNOils = new ArrayList<>();
    private List<NewsVideo> newsVideos = new ArrayList<>();
    private ApiCalls apiCalls;

    ProgressDialog progressDialog;
    private LayoutInflater inflater;
    private VideoView videoView;
    private String videoUrl;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflater = inflater;
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        apiCalls = RetrofitSingleton.getApiCalls();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading News...");
        progressDialog.setTitle(getString(R.string.app_name));
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        final Call<HomeResponse> homeResponseCall = apiCalls.getHomeResponse();
        homeResponseCall.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if (response.isSuccessful()) {
                    homeResponse = response.body();
                    specialNewses = homeResponse.getSpecialNews();
                    cartoons = homeResponse.getCartoon();
                    bullions = homeResponse.getBullion();
                    homeCategories = homeResponse.getCategory();
                    currencies = homeResponse.getCurrencies();
                    gasNOils = homeResponse.getGasNOil();
                    newsVideos = homeResponse.getNewsVideos();
                    initView(view);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                Log.e("API error retrofit", t.getMessage().toString());
                progressDialog.dismiss();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void initView(View view) {

        setupSpecialNews(view);

        setupCartoon(view);

        setupCategories(view);

        setupCurrencies(view);

        setupBullion(view);

        setUpGasNOil(view);

        if(newsVideos.size()>0){
            LinearLayout llVideos = (LinearLayout) view.findViewById(R.id.ll_videos);
            View header = inflater.inflate(R.layout.layout_header_section, (ViewGroup) view, false);
            TextView tvHeaderTitle = (TextView) header.findViewById(R.id.tv_header_title);
            tvHeaderTitle.setText(getString(R.string.videos));
            llVideos.addView(header, 0);

            YouTubePlayerSupportFragment youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();
//            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//            transaction.add(R.id.you)
            youTubePlayerSupportFragment.initialize(YOUTUBE_DEV_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    if(!b){
                        mYouTubePlayer = youTubePlayer;
                        mYouTubePlayer.loadVideo("M4S0BEMihDQ");
                        mYouTubePlayer.play();
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });

//            for (int i = 0; i < newsVideos.size(); i++) {
//            NewsVideo newsVideo = newsVideos.get(0);
//            videoView = (VideoView) view.findViewById(R.id.vv_news_video);
//            new YourAsyncTask().execute();
//            }
        }
    }

    private class YourAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity(), "", "Loading Video wait...", true);
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                String url = "http://www.youtube.com/watch?v=1FJHYqE0RDg";
                videoUrl = getUrlVideoRTSP(url);
                Log.e("Video url =>>>>>", videoUrl);
            }
            catch (Exception e)
            {
                Log.e("Login Soap ", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            progressDialog.dismiss();
/*
            videoView.setVideoURI(Uri.parse("rtsp://v4.cache1.c.youtube.com/CiILENy73wIaGQk4RDShYkdS1BMYDSANFEgGUgZ2aWRlb3MM/0/0/0/video.3gp"));
            videoView.setMediaController(new MediaController(AlertDetail.this));
            videoView.requestFocus();
            videoView.start();*/
            videoView.setVideoURI(Uri.parse(videoUrl));
            MediaController mc = new MediaController(getActivity());
            videoView.setMediaController(mc);
            videoView.requestFocus();
            videoView.start();
            mc.show();
        }

    }

    private void setUpGasNOil(View view) {
        if (gasNOils.size() > 0) {
            LinearLayout llGasNOil = (LinearLayout) view.findViewById(R.id.ll_gas_n_oil);
            View header = inflater.inflate(R.layout.layout_header_section, (ViewGroup) view, false);
            TextView tvHeaderTitle = (TextView) header.findViewById(R.id.tv_header_title);
            tvHeaderTitle.setText(getString(R.string.gas_n_oil));
            llGasNOil.addView(header);
            for (int i = 0; i < gasNOils.size(); i++) {
                GasNOil gasNOil = gasNOils.get(i);
                View items = inflater.inflate(R.layout.layout_bullion_gasnoil_item, (ViewGroup) view, false);
                TextView tvName = (TextView) items.findViewById(R.id.tv_name);
                TextView tvUnit = (TextView) items.findViewById(R.id.tv_unit);

                if (i % 2 == 1) {
                    items.setBackgroundColor(getResources().getColor(R.color.colorMainBgDark));
                }

                tvName.setText(gasNOil.getName());
                tvUnit.setText(gasNOil.getUnit());
                llGasNOil.addView(items);
            }
        }
    }

    private void setupBullion(View view) {
        if (bullions.size() > 0) {
            LinearLayout llBullion = (LinearLayout) view.findViewById(R.id.ll_bullion);
            View header = inflater.inflate(R.layout.layout_header_section, (ViewGroup) view, false);
            TextView tvHeaderTitle = (TextView) header.findViewById(R.id.tv_header_title);
            tvHeaderTitle.setText(getString(R.string.bullion));
            llBullion.addView(header);
            for (int i = 0; i < bullions.size(); i++) {
                Bullion bullion = bullions.get(i);
                View items = inflater.inflate(R.layout.layout_bullion_gasnoil_item, (ViewGroup) view, false);
                TextView tvName = (TextView) items.findViewById(R.id.tv_name);
                TextView tvUnit = (TextView) items.findViewById(R.id.tv_unit);

                if (i % 2 == 1) {
                    items.setBackgroundColor(getResources().getColor(R.color.colorMainBgDark));
                }

                tvName.setText(bullion.getName());
                tvUnit.setText(bullion.getUnit());
                llBullion.addView(items);
            }
        }
    }

    private void setupCurrencies(View view) {
        if (currencies.size() > 0) {

            LinearLayout llCurrencies = (LinearLayout) view.findViewById(R.id.ll_currencies);
            View header = inflater.inflate(R.layout.layout_header_section, (ViewGroup) view, false);
            TextView tvHeaderTitle = (TextView) header.findViewById(R.id.tv_header_title);
            tvHeaderTitle.setText(getString(R.string.currencies));
            llCurrencies.addView(header);
            for (int i = 0; i < currencies.size(); i++) {
                Currency currency = currencies.get(i);
                View items = inflater.inflate(R.layout.layout_currencies_item, (ViewGroup) view, false);
                TextView tvName = (TextView) items.findViewById(R.id.tv_name);
                TextView tvUnit = (TextView) items.findViewById(R.id.tv_unit);
                TextView tvPurchase = (TextView) items.findViewById(R.id.tv_purchase);
                TextView tvSale = (TextView) items.findViewById(R.id.tv_sale);
                if (i == 0) {
                    View itemsHeader = inflater.inflate(R.layout.layout_currencies_item, (ViewGroup) view, false);
                    TextView tvHeaderName = (TextView) itemsHeader.findViewById(R.id.tv_name);
                    TextView tvHeaderUnit = (TextView) itemsHeader.findViewById(R.id.tv_unit);
                    TextView tvHeaderPurchase = (TextView) itemsHeader.findViewById(R.id.tv_purchase);
                    TextView tvHeaderSale = (TextView) itemsHeader.findViewById(R.id.tv_sale);
                    tvHeaderName.setText(getString(R.string.currencies));
                    tvHeaderUnit.setText(getString(R.string.unit));
                    tvHeaderPurchase.setText(getString(R.string.purchase));
                    tvHeaderSale.setText(getString(R.string.sale));
                    llCurrencies.addView(itemsHeader);
                    tvHeaderName.setTypeface(tvHeaderName.getTypeface(), BOLD);
                    tvHeaderUnit.setTypeface(tvHeaderUnit.getTypeface(), BOLD);
                    tvHeaderPurchase.setTypeface(tvHeaderPurchase.getTypeface(), BOLD);
                    tvHeaderSale.setTypeface(tvHeaderSale.getTypeface(), BOLD);
                    itemsHeader.setBackgroundColor(getResources().getColor(R.color.colorMainBg));
                }

                if (i % 2 == 0) {
                    items.setBackgroundColor(getResources().getColor(R.color.colorMainBgDark));
                }

                tvName.setText(currency.getCurrenciesName());
                tvUnit.setText(currency.getUnit());
                tvPurchase.setText(currency.getPurchase());
                tvSale.setText(currency.getSale());
                llCurrencies.addView(items);

            }
        }
    }

    private void setupCategories(View view) {
        if (homeCategories.size() > 0) {
            LinearLayout llCatNews = (LinearLayout) view.findViewById(R.id.ll_cat_news);
            for (int i = 0; i < homeCategories.size(); i++) {

                View header = inflater.inflate(R.layout.layout_header_section, (ViewGroup) view, false);
                HomeCategory homeCategory = homeCategories.get(i);
                TextView tvHeaderTitle = (TextView) header.findViewById(R.id.tv_header_title);
                tvHeaderTitle.setText(homeCategory.getCategoryName());
                llCatNews.setTag(homeCategory);
                llCatNews.addView(header);
                final List<Article> articles = homeCategory.getArticles();
                for (int j = 0; j < articles.size(); j++) {
                    View separator = inflater.inflate(R.layout.layout_news_separator, (ViewGroup) view, false);
                    View catNewsView = inflater.inflate(R.layout.layout_news_section, (ViewGroup) view, false);
                    catNewsView.setTag(articles.get(j));
                    TextView tvArticleTitle = (TextView) catNewsView.findViewById(R.id.tv_news_title);
                    tvArticleTitle.setText(articles.get(j).getTitle());
                    ImageView ivFeaturedImg = (ImageView) catNewsView.findViewById(R.id.iv_news_image);
                    CommonMethods.loadImageAccordingToWidth(getActivity().getApplicationContext(), ivFeaturedImg, articles.get(j).getFeatureimage());
                    TextView tvDate = (TextView) catNewsView.findViewById(R.id.tv_issue_date);
                    TextView tvDesc = (TextView) catNewsView.findViewById(R.id.tv_news_desc);
                    tvDate.setText(articles.get(j).getPublishdate());
                    tvDesc.setText(articles.get(j).getDescription());

                    llCatNews.addView(catNewsView);
                    if (articles.size() != (j + 1)) {
                        llCatNews.addView(separator);
                    }

                    catNewsView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Article article = (Article) view.getTag();
                            openNews(article.getNewsarticleId(), article.getTitle());
                        }
                    });
                }

                llCatNews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HomeCategory homeCategory = (HomeCategory) view.getTag();
                        openCategories(homeCategory.getCategoryId());
                    }
                });
            }
        }
    }

    private void openCategories(String categoryId) {
        Toast.makeText(getContext(), "Category Id: " + categoryId+" Under progress.", Toast.LENGTH_LONG).show();
    }

    private void openNews(String newsarticleId, String title) {
        Opener.WebViewActivity(getActivity(), newsarticleId, title);
    }

    private void setupCartoon(View view) {
        if (cartoons.size() > 0) {
            LinearLayout llCartoonSection = (LinearLayout) view.findViewById(R.id.ll_cartoon_section);
            llCartoonSection.setVisibility(View.VISIBLE);
            final ImageView ivCartoonImg = (ImageView) view.findViewById(R.id.iv_cartoon_img);
            TextView tvArtist = (TextView) view.findViewById(R.id.tv_artist);
            CommonMethods.loadImageAccordingToWidth(getActivity().getApplicationContext(), ivCartoonImg, cartoons.get(0).getImagename());

            tvArtist.setText(cartoons.get(0).getArtist());
        }
    }


    private void setupSpecialNews(View view) {
        View separator = inflater.inflate(R.layout.layout_news_separator, (ViewGroup) view, false);
        if (specialNewses.size() > 0) {
            View header = inflater.inflate(R.layout.layout_header_section, (ViewGroup) view, false);
            LinearLayout llSpecialNews = (LinearLayout) view.findViewById(R.id.ll_special_news);
            for (SpecialNews specialNews : specialNewses) {
                View newsSection = inflater.inflate(R.layout.layout_news_section, (ViewGroup) view, false);
                newsSection.setTag(specialNews);
                if (specialNewses.indexOf(specialNews) == 0) {
                    TextView title = (TextView) header.findViewById(R.id.tv_header_title);
                    title.setText(getString(R.string.header_special_news));
                    llSpecialNews.addView(header);
                }
                ImageView ivFeaturedImg = (ImageView) newsSection.findViewById(R.id.iv_news_image);
                CommonMethods.loadImageAccordingToWidth(getActivity().getApplicationContext(), ivFeaturedImg, specialNews.getFeatureimage());
                TextView tvNewsTitle = (TextView) newsSection.findViewById(R.id.tv_news_title);
                tvNewsTitle.setText(specialNews.getTitle());
                newsSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SpecialNews news = (SpecialNews) view.getTag();
//                        Toast.makeText(getContext(), news.getNewsarticleId(), Toast.LENGTH_LONG).show();
                        openNews(news.getNewsarticleId(), news.getTitle());
                    }
                });
                TextView tvDate = (TextView) newsSection.findViewById(R.id.tv_issue_date);
                TextView tvDesc = (TextView) newsSection.findViewById(R.id.tv_news_desc);
                tvDate.setText(specialNews.getPublishdate());
                tvDesc.setText(specialNews.getDescription());
                llSpecialNews.addView(newsSection);
                if (specialNewses.size() != specialNewses.indexOf(specialNews) + 1) {
                    llSpecialNews.addView(separator);
                }
            }
        }
    }
}
