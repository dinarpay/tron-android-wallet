package com.devband.tronwalletforandroid.ui.block;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;

import com.devband.tronwalletforandroid.R;
import com.devband.tronwalletforandroid.common.BaseFragment;
import com.devband.tronwalletforandroid.common.CommonActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018. 5. 24..
 */

public class BlockActivity extends CommonActivity {

    private static final int FRAGMENT_OVERVIEW = 0;
    private static final int FRAGMENT_BLOCK = 1;
    private static final int FRAGMENT_TRANSACTION = 2;
    private static final int FRAGMENT_ACCOUNT = 3;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;

    private List<BaseFragment> mFragments = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = (item) -> {
        switch (item.getItemId()) {
            case R.id.bottom_navigation_overview:
                changeFragment(FRAGMENT_OVERVIEW);
                return true;
            case R.id.bottom_navigation_block:
                changeFragment(FRAGMENT_BLOCK);
                return true;
            case R.id.bottom_navigation_transaction:
                changeFragment(FRAGMENT_TRANSACTION);
                return true;
            case R.id.bottom_navigation_account:
                changeFragment(FRAGMENT_ACCOUNT);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);

        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mFragments.add(OverviewFragment.newInstance());
        mFragments.add(BlockFragment.newInstance());
        mFragments.add(TransactionFragment.newInstance());
        mFragments.add(AccountFragment.newInstance());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, mFragments.get(FRAGMENT_OVERVIEW))
                .add(R.id.content, mFragments.get(FRAGMENT_BLOCK))
                .add(R.id.content, mFragments.get(FRAGMENT_TRANSACTION))
                .add(R.id.content, mFragments.get(FRAGMENT_ACCOUNT))
                .commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setSelectedItemId(FRAGMENT_OVERVIEW);
        changeFragment(FRAGMENT_OVERVIEW);
    }

    private void changeFragment(int num) {

        int titleResId = -1;

        switch (num) {
            case FRAGMENT_OVERVIEW:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(mFragments.get(FRAGMENT_OVERVIEW))
                        .hide(mFragments.get(FRAGMENT_BLOCK))
                        .hide(mFragments.get(FRAGMENT_TRANSACTION))
                        .hide(mFragments.get(FRAGMENT_ACCOUNT))
                        .commit();
                titleResId = R.string.bottom_navigation_menu_overview;
                break;
            case FRAGMENT_BLOCK:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mFragments.get(FRAGMENT_OVERVIEW))
                        .show(mFragments.get(FRAGMENT_BLOCK))
                        .hide(mFragments.get(FRAGMENT_TRANSACTION))
                        .hide(mFragments.get(FRAGMENT_ACCOUNT))
                        .commit();
                titleResId = R.string.bottom_navigation_menu_block;
                break;
            case FRAGMENT_TRANSACTION:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mFragments.get(FRAGMENT_OVERVIEW))
                        .hide(mFragments.get(FRAGMENT_BLOCK))
                        .show(mFragments.get(FRAGMENT_TRANSACTION))
                        .hide(mFragments.get(FRAGMENT_ACCOUNT))
                        .commit();
                titleResId = R.string.bottom_navigation_menu_transaction;
                break;
            case FRAGMENT_ACCOUNT:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mFragments.get(FRAGMENT_OVERVIEW))
                        .hide(mFragments.get(FRAGMENT_BLOCK))
                        .hide(mFragments.get(FRAGMENT_TRANSACTION))
                        .show(mFragments.get(FRAGMENT_ACCOUNT))
                        .commit();
                titleResId = R.string.bottom_navigation_menu_account;
                break;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleResId);
        }
    }
}
