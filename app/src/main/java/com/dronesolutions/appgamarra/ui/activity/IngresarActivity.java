package com.dronesolutions.appgamarra.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dronesolutions.appgamarra.R;
import com.dronesolutions.appgamarra.ui.fragment.RegistrarUsuarioFragment;
import com.dronesolutions.appgamarra.ui.fragment.ValidarUsuarioFragment;
import com.dronesolutions.appgamarra.ui.utils.ViewPagerAdapter;

import java.util.List;
import java.util.Vector;

public class IngresarActivity extends AppCompatActivity {
    //// TODO: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: VARIABLES ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public static ViewPager viewPager;
    private ViewPagerAdapter mPagerAdapter;


    //// TODO: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: EVENTOS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        SetUpElements();
        SetUpActions();

        //Seteamos el fragment de Validar Usuario
        //replaceFragment(new ValidarUsuarioFragment(), false,0,0,0,0);
    }


    //// TODO: :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: METODOS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private void SetUpElements() {
        viewPager = (ViewPager) findViewById(R.id.pager_login);

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, ValidarUsuarioFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, RegistrarUsuarioFragment.class.getName()));
        this.mPagerAdapter  = new ViewPagerAdapter(super.getSupportFragmentManager(), fragments);

        viewPager = (ViewPager)super.findViewById(R.id.pager_login);
        viewPager.setAdapter(this.mPagerAdapter);

    }

    private void SetUpActions() {

    }

    public void setFragmentActivo(int page) {
        viewPager.setCurrentItem(page);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack,
                                int animIdIn1,
                                int animIdOut1,
                                int animIdIn2,
                                int animIdOut2) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        String tag = fragment.getClass().getSimpleName();
        tag = "Tag_" + tag;
        transaction.setCustomAnimations(animIdIn1, animIdOut1, animIdIn2, animIdOut2);
        transaction.replace(R.id.frame_login_content, fragment, tag);
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

}

