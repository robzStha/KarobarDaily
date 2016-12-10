package com.app.karobardainik.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.karobardainik.R;
import com.app.karobardainik.models.Article;
import com.app.karobardainik.models.HomeCategory;
import com.app.karobardainik.models.MenuCategory;
import com.app.karobardainik.server_protocols.ApiCalls;
import com.app.karobardainik.server_protocols.RetrofitSingleton;
import com.app.karobardainik.utils.CommonMethods;
import com.app.karobardainik.utils.Opener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListing extends Fragment {


    Activity activity;
    private static String CAT_ID;
    private LayoutInflater inflater;
    private ApiCalls apiCalls;
    private ProgressDialog progressDialog;
    List<Article> articles = new ArrayList<>();
    private HomeCategory homeCategories;
    RecyclerView recyclerView;


    public CategoryListing() {
        // Required empty public constructor
    }

    public CategoryListing getInstance(long catId) {
        CategoryListing catListing = new CategoryListing();
        Bundle args = new Bundle();
        args.putLong(CAT_ID, catId);
        catListing.setArguments(args);
        return catListing;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = ((Activity) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        final View view = inflater.inflate(R.layout.fragment_category_listing, container, false);
        apiCalls = RetrofitSingleton.getApiCalls();


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading News...");
        progressDialog.setTitle(getString(R.string.app_name));
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);


        final Call<MenuCategory> categoriesCall = apiCalls.getCategoriesNewsById(getArguments().getLong(CAT_ID));
        categoriesCall.enqueue(new Callback<MenuCategory>() {
            @Override
            public void onResponse(Call<MenuCategory> call, Response<MenuCategory> response) {
                if (response.code() == 200) {
                    MenuCategory category = response.body();
                    category.getCategory();
                    homeCategories = category.getCategory();
                    articles = homeCategories.getArticles();
//                    System.out.println("Article title: " + articles.get(0).getTitle());
//                    System.out.println("Article title: " + articles.get(1).getTitle());
//                    System.out.println("Article title: " + articles.get(2).getTitle());
                    setupCategories(view);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MenuCategory> call, Throwable t) {
                Log.e("API error: ", t.toString());
                progressDialog.dismiss();
            }
        });

        return view;
    }

    private void openNews(String newsarticleId, String title) {
        Opener.WebViewActivity(getActivity(), newsarticleId, title);
    }

    private void setupCategories(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new ArticleViewAdapter());
//        if (articles.size() > 0) {
//            for (int j = 0; j < articles.size(); j++) {
//                View separator = inflater.inflate(R.layout.layout_news_separator, (ViewGroup) view, false);
//                View catNewsView = inflater.inflate(R.layout.layout_news_section, (ViewGroup) view, false);
//                catNewsView.setTag(articles.get(j));
//                TextView tvArticleTitle = (TextView) catNewsView.findViewById(R.id.tv_news_title);
//                tvArticleTitle.setText(articles.get(j).getTitle());
//                ImageView ivFeaturedImg = (ImageView) catNewsView.findViewById(R.id.iv_news_image);
//                CommonMethods.loadImageAccordingToWidth(getActivity().getApplicationContext(), ivFeaturedImg, articles.get(j).getFeatureimage());
//                TextView tvDate = (TextView) catNewsView.findViewById(R.id.tv_issue_date);
//                TextView tvDesc = (TextView) catNewsView.findViewById(R.id.tv_news_desc);
//                tvDate.setText(articles.get(j).getPublishdate());
//                tvDesc.setText(articles.get(j).getDescription());
//
//            }
//        }
    }

    private class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc, tvDate;
        ImageView ivFeaturedImg;
        LinearLayout llSection;


        public ArticleViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_news_desc);
            tvDate = (TextView) itemView.findViewById(R.id.tv_issue_date);
            ivFeaturedImg = (ImageView) itemView.findViewById(R.id.iv_news_image);
            llSection = (LinearLayout) itemView.findViewById(R.id.ll_section);
        }
    }

    private class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

        @Override
        public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.layout_news_section, parent, false);
            return new ArticleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticleViewHolder holder, int position) {
            final Article article = articles.get(position);
            holder.tvTitle.setText(article.getTitle());
            holder.tvDate.setText(article.getPublishdate());
            holder.tvDesc.setText(article.getDescription());
            holder.llSection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openNews(article.getNewsarticleId(), article.getTitle());
                }
            });

//            System.out.println("testing: "+ article.getTitle()+" - "+article.getFeatureimage());

            CommonMethods.loadImageAccordingToWidth(getContext().getApplicationContext(), holder.ivFeaturedImg, article.getFeatureimage());
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }
    }

}
