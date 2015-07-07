package com.example.sandeepwww.codefreakfinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class TabAnimationActivity extends AppCompatActivity {

   public List<ListItem> mItems;
    private DrawerLayout mDrawerLayout;

    public String[] upStartTime,upEndTime,upName,upPlatform,upDuration,upURL,upChallengeType;

    public String[] onEndTime,onName,onPlatform,onURL,onChallengeType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_animation);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();

        onEndTime = i.getStringArrayExtra("OET");
        onName = i.getStringArrayExtra("ONAME");
        onChallengeType = i.getStringArrayExtra("OCT");
        onPlatform = i.getStringArrayExtra("OPF");
        onURL = i.getStringArrayExtra("OURL");

        upStartTime = i.getStringArrayExtra("UST");
        upEndTime = i.getStringArrayExtra("UET");
        upName = i.getStringArrayExtra("UNAME");
        upChallengeType = i.getStringArrayExtra("UCT");
        upPlatform = i.getStringArrayExtra("UPF");
        upURL = i.getStringArrayExtra("UURL");
        upDuration = i.getStringArrayExtra("UDUR");

        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);

       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //if (navigationView != null) {
        //    setupDrawerContent(navigationView,viewPager);
        //}

        if (savedInstanceState == null) {
            setupViewPager2(viewPager);
        }

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void setupViewPager2(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        /*Bundle mainIntent1 = new Bundle();
        mainIntent1.putStringArray("OET", onEndTime);
        mainIntent1.putStringArray("ONAME", onName);
        mainIntent1.putStringArray("OURL", onURL);
        mainIntent1.putStringArray("OCT", onChallengeType);
        mainIntent1.putStringArray("OPF", onPlatform);


        Bundle mainIntent2 = new Bundle();
        mainIntent2.putStringArray("UET", upEndTime);
        mainIntent2.putStringArray("UST",upStartTime);
        mainIntent2.putStringArray("UNAME", upName);
        mainIntent2.putStringArray("UURL", upURL);
        mainIntent2.putStringArray("UCT", upChallengeType);
        mainIntent2.putStringArray("UPF", upPlatform);
        mainIntent2.putStringArray("UDUR", upDuration);*/



        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.ripple_material_dark),onName,onPlatform,onURL,onEndTime), "OnGoing");

        adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.ripple_material_dark),upName,upPlatform,upURL,upStartTime,upDuration), "UpComing");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static class DummyFragment extends Fragment {
        int color;
        CardAdapter adapter;
        List<ListItem> mItems=new ArrayList<ListItem>();
        public String[] upStartTime,upEndTime,upName,upPlatform,upDuration,upURL,upChallengeType;
        public String[] onEndTime,onName,onPlatform,onURL,onChallengeType;

        ListItem cardholder;

        public DummyFragment() {
        }

        @SuppressLint("ValidFragment")
        public DummyFragment(int color,String[] names, String[] sites, String[] urls, String[] endtimes) {
            this.color = color;
            this.onName=names;
            this.onPlatform=sites;
            this.onEndTime=endtimes;
            this.onURL=urls;


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            for (int i = 0; i < onName.length; i++) {
                cardholder = new ListItem();
                cardholder.setmName(onName[i].length() > 20? onName[i].substring(0, 20)+"..." : onName[i]);
                cardholder.setmSite(onPlatform[i]);
                cardholder.setmDuration("Ends At");
                cardholder.setmIcon(getDrawable(onPlatform[i]));
                cardholder.setmEndtime(onEndTime[i]);
                mItems.add(cardholder);
            }

            adapter = new CardAdapter(mItems);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(onURL[position]));
                    startActivity(browserIntent);
                    Toast.makeText(getActivity().getBaseContext(),"Opening link in Browser",Toast.LENGTH_LONG).show();

                }

                @Override
                public void onItemLongClick(View view, int position)
                {
                    // ...
                }
            }));
            return view;
        }
        public Integer getDrawable(String str)
        {
            switch(str)
            {
                case "HACKEARTH":return R.drawable.logo_he;
                case "HACKERRANK":return R.drawable.hackerrank;

                case "CODECHEF":return R.drawable.codechef;
                case "CODEFORCES":return R.drawable.codeforces;

                case "TOPCODER":return R.drawable.topcoder;
                default:return R.drawable.hackerrank;
            }

        }

    }




    public static class DummyFragment2 extends Fragment {
        int color;
        CardAdapter adapter;
        List<ListItem> mItems=new ArrayList<ListItem>();
        public String[] upStartTime,upEndTime,upName,upPlatform,upDuration,upURL,upChallengeType;
        public String[] onEndTime,onName,onPlatform,onURL,onChallengeType;

        ListItem cardholder;

        public DummyFragment2() {
        }

        @SuppressLint("ValidFragment")
        public DummyFragment2(int color,String[] names, String[] sites, String[] urls, String[] endtimes,String[] duration) {
            this.color = color;
            this.upName=names;
            this.upPlatform=sites;
            this.upEndTime=endtimes;
            this.upURL=urls;
            this.upDuration=duration;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            for (int i = 0; i < upName.length; i++) {
                cardholder = new ListItem();
                cardholder.setmName(upName[i].length() > 20 ? upName[i].substring(0, 20)+"..." : upName[i]);
                cardholder.setmSite(upPlatform[i]);
                cardholder.setmIcon(getDrawable(upPlatform[i]));
                cardholder.setmEndtime("Till: "+upEndTime[i]);
                cardholder.setmDuration("Duration: "+upDuration[i]);;
                mItems.add(cardholder);
            }

            adapter = new CardAdapter(mItems);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(upURL[position]));
                    startActivity(browserIntent);
                    Toast.makeText(getActivity().getBaseContext(),"Opening link in Browser",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onItemLongClick(View view, int position)
                {
                    // ...
                }
            }));
            return view;
        }

        public Integer getDrawable(String str)
        {
            switch(str)
            {
                case "HACKEREARTH":return R.drawable.hackerearth;
                case "HACKERRANK":return R.drawable.hackerrank;

                case "CODECHEF":return R.drawable.codechef;
                case "CODEFORCES":return R.drawable.codeforces;

                case "TOPCODER":return R.drawable.topcoder;
                default:return R.drawable.hackerrank;
            }

        }

    }

}
