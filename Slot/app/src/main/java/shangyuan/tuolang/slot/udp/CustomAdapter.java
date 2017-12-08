package shangyuan.tuolang.slot.udp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import shangyuan.tuolang.slot.R;

/**
 * Created by gn5r on 17/11/03.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<UDPData> udpData;

    public CustomAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDataList(ArrayList<UDPData> udpData) {
        this.udpData = udpData;
    }

    @Override
    public int getCount() {
        return udpData.size();
    }

    @Override
    public Object getItem(int i) {
        return udpData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.dialog_list_data, viewGroup, false);

        ((TextView) view.findViewById(R.id.IPAddress)).setText("IPAddress:" + udpData.get(i).getIPAddress());
        ((TextView) view.findViewById(R.id.comPort)).setText("Port:" + udpData.get(i).getComPort());

        return view;
    }
}
