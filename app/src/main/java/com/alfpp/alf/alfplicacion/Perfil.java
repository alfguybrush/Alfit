package com.alfpp.alf.alfplicacion;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

;



public class Perfil extends Fragment {
    String id;
    BaseDatosAlfpp BD;

    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        id ="";
        View V = inflater.inflate(R.layout.fragment_perfil, container, false);
        Bundle bundle = this.getArguments();
        id = bundle.getString("id","");

        if(id!=""){
            BD = new BaseDatosAlfpp(getActivity().getApplicationContext());
            String user = BD.getUsuario(id);
            TextView usuario =(TextView) V.findViewById(R.id.tv_usuario_perfil);
            usuario.setText(user);
        }else{
            TextView usuario =(TextView) V.findViewById(R.id.tv_usuario_perfil);
            usuario.setText("error");
        }
        // Inflate the layout for this fragment
        return V;
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
        public void onFragmentInteraction(Uri uri);
    }

}
