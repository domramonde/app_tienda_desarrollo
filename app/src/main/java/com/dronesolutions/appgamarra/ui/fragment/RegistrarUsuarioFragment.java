package com.dronesolutions.appgamarra.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dronesolutions.appgamarra.R;
import com.dronesolutions.appgamarra.ui.activity.IngresarActivity;
import com.dronesolutions.appgamarra.ui.models.Usuario;
import com.dronesolutions.appgamarra.ui.services.RestClient;
import com.dronesolutions.appgamarra.ui.services.Services;
import com.dronesolutions.appgamarra.ui.utils.Generic;
import com.dronesolutions.appgamarra.ui.utils.ViewPagerAdapter;
import com.google.gson.Gson;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarUsuarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class
RegistrarUsuarioFragment extends Fragment {
    // TODO :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: VARIABLES :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText txtCorreo, txtNombre, txtApellidos, txtContrasena;
    private Button btnAceptar;

    private OnFragmentInteractionListener mListener;
    IngresarActivity ingresarActivity;

    // TODO :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: CONSTRUCTOR :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public RegistrarUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarUsuarioFragment newInstance(String param1, String param2) {
        RegistrarUsuarioFragment fragment = new RegistrarUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    // TODO :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: EVENTOS :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registrar_usuario, container, false);

        InicializarElementos(view);
        ConfigurarAcciones();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    /// TODO ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: METODOS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private void InicializarElementos(View v) {
        txtCorreo = (EditText) v.findViewById(R.id.registrar_txt_correo);
        txtNombre = (EditText) v.findViewById(R.id.registrar_txt_nombre);
        txtApellidos = (EditText) v.findViewById(R.id.registrar_txt_apellidos);
        txtContrasena = (EditText) v.findViewById(R.id.registrar_txt_contrasena);
        btnAceptar = (Button) v.findViewById(R.id.registrar_btn_aceptar);

        ingresarActivity=  (IngresarActivity)getActivity();
    }

    private void ConfigurarAcciones() {
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar(v);
            }
        });
    }

    private void Registrar(final View v) {
        String correo = txtCorreo.getText().toString().trim();
        String nombre = txtNombre.getText().toString().trim();
        String apellidos = txtApellidos.getText().toString().trim();
        String contrasena = txtContrasena.getText().toString().trim();

        Usuario usuarioRegistrar = new Usuario();
        usuarioRegistrar.setCorreo(correo);
        usuarioRegistrar.setNombre(nombre);
        usuarioRegistrar.setApellidos(apellidos);
        usuarioRegistrar.setContrasena(contrasena);

        RestClient restClient = new RestClient(Services.URL_SECURITY);
        Call<ResponseBody> call = restClient.iServices.setRegistrarUsuario(usuarioRegistrar);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.e("jsonObject", jsonObject.toString());
                        JSONObject data = jsonObject.getJSONObject("data");
                        String msg = data.getString("mensaje");
                        int estado = data.getInt("estado");

                        if(estado == 1){
                            LimpiarCajas();
                            ingresarActivity.viewPager.setCurrentItem(0);
                        }

                        Generic.showToast(getView(), msg);

                        // SaveUserInRealm(u);
                        //launchActivityMain(u);
                        //dialogLoading.dismiss();
                    } catch (Exception e) {
                        //dialogLoading.dismiss();
                        e.printStackTrace();
                        //Messages.showSB(v, e.getMessage(), "ok");
                    }
                } else {
                    // dialogLoading.dismiss();
                    //Messages.showSB(v, getString(R.string.msg_login_fail), "ok");
                    Generic.showToast(v, getString(R.string.msg_error_registrar_servidor));

                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //dialogLoading.dismiss();
                //Messages.showSB(v, getString(R.string.msg_servidor_inaccesible), "ok");
                Generic.showToast(getView(), getString(R.string.msg_error_registrar_servidor));
                Log.e("error", t.getMessage());
            }
        });

    }

    private void LimpiarCajas(){
        txtCorreo.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtContrasena.setText("");
    }

}
