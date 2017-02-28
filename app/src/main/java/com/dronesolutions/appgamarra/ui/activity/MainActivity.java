package com.dronesolutions.appgamarra.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dronesolutions.appgamarra.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /// // TODO: ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: VARIABLES ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    TabLayout tabs;
    ViewPager viewPager;
    EditText txtBusqueda;
    ImageButton btnBusqueda;

    public boolean busquedaActivo;

    /// // TODO: ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: EVENTS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpElements();
        setUpActions();


    }


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /// // TODO: ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: METODOS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private void setUpElements() {
        tabs = (TabLayout) findViewById(R.id.tabs_main);
        tabs.addTab(tabs.newTab().setText("Home"));
        tabs.addTab(tabs.newTab().setText("Para ti"));

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager_main);

        //Creating our pager adapter
        PageMain adapter = new PageMain(getSupportFragmentManager(), tabs.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtBusqueda = (EditText) findViewById(R.id.txt_busqueda_principal);
        btnBusqueda = (ImageButton) findViewById(R.id.btn_buscar_principal);
    }

    private void setUpActions() {
        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivarDesactivarBusqueda();
            }
        });

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    public void ActivarDesactivarBusqueda() {
        if (busquedaActivo) {
            busquedaActivo = false;
            Log.e("out", "out");
            ActivarBtnBusqueda(false);
            Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
            fadeOut.setDuration(200);
            txtBusqueda.startAnimation(fadeOut);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    txtBusqueda.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    txtBusqueda.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        } else {
            busquedaActivo = true;
            ActivarBtnBusqueda(true);
            Log.e("in", "in");
            Animation fadeInt = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            fadeInt.setDuration(200);
            txtBusqueda.startAnimation(fadeInt);
            fadeInt.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    txtBusqueda.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    txtBusqueda.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    public void ActivarBtnBusqueda(boolean activar) {
        if (activar) {
            Animation fadeOut = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.fade_out);
            fadeOut.setDuration(100);
            btnBusqueda.startAnimation(fadeOut);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    btnBusqueda.setVisibility(View.GONE);

                    Animation fadeInClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    fadeInClose.setDuration(100);
                    btnBusqueda.startAnimation(fadeInClose);
                    fadeInClose.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            btnBusqueda.setVisibility(View.VISIBLE);
                            btnBusqueda.setImageResource(R.drawable.ic_close_circle);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            btnBusqueda.setImageResource(R.drawable.ic_close_circle);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        } else {
            Animation fadeOut = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.fade_out);
            fadeOut.setDuration(100);
            btnBusqueda.startAnimation(fadeOut);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    btnBusqueda.setVisibility(View.GONE);

                    Animation fadeInClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    fadeInClose.setDuration(100);
                    btnBusqueda.startAnimation(fadeInClose);
                    fadeInClose.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            btnBusqueda.setVisibility(View.VISIBLE);
                            btnBusqueda.setImageResource(R.drawable.ic_buscar);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            btnBusqueda.setImageResource(R.drawable.ic_buscar);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


        }

    }

}
