package com.example.recycler_permisos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.recycler_permisos.Models.Permiso;
import com.example.recycler_permisos.adapters.PermisoAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvpermisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Permiso> ListaPermisos = new ArrayList<>();
        ListaPermisos.add(new Permiso(Manifest.permission.CALL_PHONE, "Llamar"));
        ListaPermisos.add(new Permiso(Manifest.permission.CAMERA, "Camara"));

        List<Permiso> permisosNoConcedidos = new ArrayList<>();
        for (Permiso permiso : ListaPermisos) {
            if (ContextCompat.checkSelfPermission(this, permiso.getNombre()) != PackageManager.PERMISSION_GRANTED) {
                permisosNoConcedidos.add(permiso);
            }
        }

        rvpermisos = findViewById(R.id.rcpermisos);
        rvpermisos.setAdapter(new PermisoAdapter(this, permisosNoConcedidos));
        rvpermisos.setLayoutManager(new LinearLayoutManager(this));
        rvpermisos.setHasFixedSize(true);
    }
}
