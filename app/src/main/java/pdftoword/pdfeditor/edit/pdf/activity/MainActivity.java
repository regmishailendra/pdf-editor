package pdftoword.pdfeditor.edit.pdf.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pdftoword.pdfeditor.edit.pdf.BaseActivity;
import pdftoword.pdfeditor.edit.pdf.R;
import pdftoword.pdfeditor.edit.pdf.fragment.AboutDialogFragment;
import pdftoword.pdfeditor.edit.pdf.fragment.DownloadedFragment;
import pdftoword.pdfeditor.edit.pdf.fragment.MainFragment;
import pdftoword.pdfeditor.edit.pdf.utils.AppRate;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppRate.app_launched(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, mainFragment).commit();


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content, mainFragment).commit();

        } else if (id == R.id.nav_gallery) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        //    showInter();
            }
            DownloadedFragment mainFragment = new DownloadedFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content, mainFragment).commit();



        } else if (id == R.id.nav_slideshow) {
            AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("t", "a");
            aboutDialogFragment.setArguments(bundle);
            aboutDialogFragment.show(getSupportFragmentManager(), "");

        } else if (id == R.id.nav_manage) {
            AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("t", "c");
            aboutDialogFragment.setArguments(bundle);
            aboutDialogFragment.show(getSupportFragmentManager(), "");

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));

        } else if (id == R.id.pdf) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=pdf.anypdf.pdfsearcher")));

        } else if (id == R.id.p) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=embarazo.pregnant.pregnancy")));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (shouldExit) {
                finish();
            } else {
                showInter(false);
                shouldExit = true;
                shouldShowBackMessage = true;
            }

        }
    }

public void showInterAd(){
        super.showInter();
}

}
