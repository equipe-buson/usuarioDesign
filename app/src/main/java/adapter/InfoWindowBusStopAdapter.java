package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.wolfsoft.kcab.R;

public class InfoWindowBusStopAdapter implements GoogleMap.InfoWindowAdapter{
    private Context context;

    public InfoWindowBusStopAdapter(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


    @Override
    public View getInfoContents(Marker marker) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.infowindow_busstop, null);

        TextView tvClique = v.findViewById(R.id.cliqueparaselecionar);
        TextView tvLinha = v.findViewById(R.id.endereco);
        tvClique.setText("Clique para selecionar");
        tvLinha.setText(marker.getTitle());
        return v;
    }

}
