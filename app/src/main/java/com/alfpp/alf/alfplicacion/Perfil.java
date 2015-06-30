package com.alfpp.alf.alfplicacion;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

;


public class Perfil extends Fragment {
    int id;
    BaseDatosAlfpp BD;

    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        id =-1;
        View V = inflater.inflate(R.layout.fragment_perfil, container, false);
        Bundle bundle = this.getArguments();
        id = bundle.getInt("id",-1);

        if(id!= -1){
            BD = new BaseDatosAlfpp(getActivity().getApplicationContext());
            setTextViews(V);

        }else{
            TextView usuario =(TextView) V.findViewById(R.id.tv_usuario_perfil);
            usuario.setText("error");
        }
        // Inflate the layout for this fragment
        return V;
    }
    private void setTextViews(View v){
        TextView tvusuario =(TextView) v.findViewById(R.id.tv_usuario_perfil);
        TextView tvnombre = (TextView) v.findViewById(R.id.tv__nombre_perfil);
        TextView tvpeso = (TextView) v.findViewById(R.id.tv_peso_perfil);
        TextView tvedad = (TextView) v.findViewById(R.id.tv_edad_perfil);
        TextView tvsexo = (TextView) v.findViewById(R.id.tv_sexo_perfil);
        final TextView tvVo2 = (TextView) v.findViewById(R.id.tv_vo2_perfil);
        String name,surname,aux;
        double peso,altura,vo2;
        int edad,sexo;
        Usuario user;
        user= BD.getUsuario(id);

        name=user.getNombre();

        String Ssexo="";
        if (user.sexo ==0){
            Ssexo="Hombre";
        }else{
            if(user.sexo==1){
                Ssexo="Mujer";
            }
        }
        tvsexo.setText(Ssexo);

        tvusuario.setText(user.getUser());

        tvnombre.setText(name+" "+user.getApellido());
        aux = Double.toString(user.getPeso());
        tvpeso.setText("Peso: "+aux+"Kg");

        aux = Integer.toString(user.edad);
        String encodedText = Html.fromHtml("Edad: " + aux + "a&ntilde;os").toString();
        tvedad.setText(encodedText);
        aux = Double.toString(user.getVo2());
        tvVo2.setText("Vo2 Max ="+aux);
        tvVo2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method tv
                PopupMenu popup = new PopupMenu(getActivity(), tvVo2);
                popup.getMenuInflater().inflate(R.menu.my_popupmenu , popup.getMenu());
                //registra los eventos click para cada item del menu
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_one)
                        {
                            Toast.makeText(getActivity(),
                                    "Ejecutar : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (item.getItemId() == R.id.action_two)
                        {
                            Toast.makeText(getActivity(),
                                    "Ejecutar : " + item.getTitle() ,Toast.LENGTH_SHORT).show();
                        }
                        else if (item.getItemId() == R.id.action_three)
                        {
                            Toast.makeText(getActivity(),
                                    "Ejecutar : " + item.getTitle() ,Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                popup.show();

            }
        });


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
