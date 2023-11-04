package com.example.recycler_permisos.adapters;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_permisos.Models.Permiso;
import com.example.recycler_permisos.R;

import java.util.List;

public class PermisoAdapter extends RecyclerView.Adapter<PermisoAdapter.ViewHolder> {
    private List<Permiso> LP;
    private Activity activity;

    public PermisoAdapter(Activity activity, List<Permiso> LP) {
        this.activity = activity;
        this.LP = LP;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater ly = LayoutInflater.from(parent.getContext());
        View v = ly.inflate(R.layout.permiso_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Permiso p = LP.get(position);
        holder.setData(p);
    }

    @Override
    public int getItemCount() {
        return LP.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPermiso;
        Switch swPermiso;
        Permiso permiso;

        public ViewHolder(@NonNull View itemview) {
            super(itemview);
            txtPermiso = itemview.findViewById(R.id.tvPermiso);
            swPermiso = itemview.findViewById(R.id.swpermiso);

            swPermiso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (ContextCompat.checkSelfPermission(activity, permiso.getNombre()) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(activity, new String[]{permiso.getNombre()}, 1938349);
                        }
                    }
                }
            });
        }

        public void setData(Permiso p) {
            permiso = p;
            txtPermiso.setText(p.getDescripcion());
        }
    }
}

