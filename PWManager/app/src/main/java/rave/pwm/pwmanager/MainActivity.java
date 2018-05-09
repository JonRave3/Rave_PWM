package rave.pwm.pwmanager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity
        extends AppCompatActivity
        implements AccountDetailsFragment.OnFragmentInteractionListener,
                    AccountListFragment.OnListFragmentInteractionListener  {


    private AccountDetailsFragment mAccountDetails;
    private AccountListFragment mAccountList;
    private FragmentManager mFragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchToDetailsFragment();
                    return true;
                case R.id.navigation_dashboard:
                    switchToListFragment();
                    return true;
            }
            return false;
        }
    };
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(!checkLoggedIn()){
            Intent login = new Intent(this.getApplicationContext(), LoginActivity.class);
            startActivity(login);
            this.finish();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mFragmentManager = this.getFragmentManager();
        mAccountDetails = new AccountDetailsFragment();
        mAccountList = new AccountListFragment();


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    protected void onResume(){
        super.onResume();
        //show();

        if(checkLoggedIn()){
            try{
                mFragmentManager.beginTransaction().add(mAccountList, "AccountListFragment").commit();
            } catch (Exception e){
                Log.e("FragmentError", "Unable to load fragment: " + e.getMessage());
            }
            //1.a) prompt to login

        } else {
            Intent login = new Intent(this.getApplicationContext(), LoginActivity.class);
            startActivity(login);
            this.finish();
        }

    }
    @Override
    protected void onDestroy(){
        spe.putBoolean("isLoggedIn", false);
        spe.apply();
        super.onDestroy();
    }

    private boolean checkLoggedIn(){
        sp = getSharedPreferences(getString(R.string.app_guid_token), Context.MODE_PRIVATE);
        spe = sp.edit();
        return sp.getBoolean("isLoggedIn", false);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {
        //TODO: implement this
    }

    @Override
    public void onListFragmentInteraction(Accounts.AccountRecord item) {
        //TODO: implement this
    }

    private void switchToDetailsFragment(){
        try{
            Fragment detailsFrag = mFragmentManager.findFragmentById(R.id.account_details_fragment);
            if(detailsFrag == null){
                detailsFrag = AccountDetailsFragment.newInstance();
                mFragmentManager.beginTransaction().add(detailsFrag, "AccountDetailsFragment").commit();
            } else {
                mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, detailsFrag).commit();
            }

        } catch (Exception e) {
            Log.e("FragmentSwapError", "unable to swap current fragment with AccountDetailsFragment");
        }
    }
    private void switchToListFragment() {
        try{
            Fragment listFrag = mFragmentManager.findFragmentById(R.id.account_list_fragment);
            if(listFrag == null){
                listFrag = AccountListFragment.newInstance(1);
                mFragmentManager.beginTransaction().add(listFrag, "AccountListFragment").commit();
            } else {
                mFragmentManager.beginTransaction().replace(R.id.main_fragment_container, listFrag).commit();
            }
        } catch (Exception e) {
            Log.e("FragmentSwapError", "unable to swap current fragment with AccountListFragment");
        }

    }
}
