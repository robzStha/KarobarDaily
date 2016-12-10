package com.app.karobardainik.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.karobardainik.R;
import com.app.karobardainik.fragments.CategoryListing;
import com.app.karobardainik.fragments.HomeFragment;
import com.app.karobardainik.models.Category;
import com.app.karobardainik.static_vars.StaticVars;
import com.app.karobardainik.utils.CommonMethods;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rvNavMenu;
    private DrawerLayout drawer;
    View selectedNavView;


    private Fragment mFragment;
    private RelativeLayout rlContentHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rlContentHome = (RelativeLayout) findViewById(R.id.content_home);


//        CommonMethods.printKeyHash(Home.this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        initNavMenu();
//        for(int i = 0; i< StaticVars.categoryList.size(); i++){
//            System.out.println("Name: "+ StaticVars.categoryList.get(i).getCategoryName()+" - id: "+ StaticVars.categoryList.get(i).getCategoryId());
//        }
        initMenuCats();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rvNavMenu = (RecyclerView) findViewById(R.id.rv_nav_menu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvNavMenu.setLayoutManager(linearLayoutManager);
        rvNavMenu.setAdapter(new NavMenuAdapter());


        mFragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(rlContentHome.getId(), mFragment).commit();
    }

    private void initMenuCats() {
        Category category = new Category();
        category.setCategoryId(0 + "");
        category.setCategoryName(getString(R.string.title_activity_home));
        StaticVars.categoryList.add(0, category);
        category = new Category();
        category.setCategoryId(-1 + "");
        category.setCategoryName(getString(R.string.videos));
        StaticVars.categoryList.add(StaticVars.categoryList.size(), category);
        category = new Category();
        category.setCategoryId(-2 + "");
        category.setCategoryName(getString(R.string.language));
        StaticVars.categoryList.add(StaticVars.categoryList.size(), category);
    }

//    private void initNavMenu() {
//        navMenu.add(new NavMenu(getString(R.string.title_activity_home), 0));
//        navMenu.add(new NavMenu("Category one", 1));
//        navMenu.add(new NavMenu("Category two",2));
//        navMenu.add(new NavMenu("Category three",3));
//        navMenu.add(new NavMenu("Category four",4));
//        navMenu.add(new NavMenu("Category five",5));
//        navMenu.add(new NavMenu("Category six",6));
//        navMenu.add(new NavMenu("Category seven",7));
//        navMenu.add(new NavMenu("Category eight",8));
//        navMenu.add(new NavMenu("Category nine",9));
//        navMenu.add(new NavMenu("Category ten",10));
//        navMenu.add(new NavMenu(getString(R.string.videos), -1));
//
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    class NavMenuHolder extends RecyclerView.ViewHolder {
        TextView tvMenuTitle;

        public NavMenuHolder(View itemView) {
            super(itemView);
            tvMenuTitle = (TextView) itemView.findViewById(R.id.tv_nav_menu_title);
        }
    }

    class NavMenuAdapter extends RecyclerView.Adapter<NavMenuHolder> {

        @Override
        public NavMenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav_menu, parent, false);
            return new NavMenuHolder(view);
        }

        @Override
        public long getItemId(int position) {
            return Long.parseLong(StaticVars.categoryList.get(position).getCategoryId());
        }

        @Override
        public void onBindViewHolder(NavMenuHolder holder, final int position) {
            final String catName = StaticVars.categoryList.get(position).getCategoryName();
            holder.tvMenuTitle.setText(CommonMethods.capitalize(catName, CommonMethods.CAPITALIZE_WORDS));
            holder.tvMenuTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    if (selectedNavView != null) {
                        selectedNavView.setSelected(false);
                    }
                    switch (Integer.parseInt(StaticVars.categoryList.get(position).getCategoryId())){
                        case 0:
                            mFragment = new HomeFragment();
                            ft.replace(rlContentHome.getId(), mFragment).commit();
                            getSupportActionBar().setTitle(catName);
                            break;
                        case -1:
                        case -2:
                            Toast.makeText(Home.this, "In progress", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Home.this, "The item is: " + StaticVars.categoryList.get(position).getCategoryName() + " id: " + getItemId(position), Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            getSupportActionBar().setTitle(catName);
                            mFragment = new CategoryListing().getInstance(getItemId(position));
                            ft.replace(rlContentHome.getId(), mFragment).commit();
                            Toast.makeText(Home.this, "The item is: " + StaticVars.categoryList.get(position).getCategoryName() + " id: " + getItemId(position), Toast.LENGTH_SHORT).show();
                            break;
                    }
                    view.setSelected(true);
                    drawer.closeDrawer(Gravity.LEFT);
                    selectedNavView = view;
                }
            });
        }

        @Override
        public int getItemCount() {
            return StaticVars.categoryList.size();
        }
    }

}
