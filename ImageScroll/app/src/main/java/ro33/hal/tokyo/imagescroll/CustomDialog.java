package ro33.hal.tokyo.imagescroll;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by shangyuan.tuolang on 2017/11/18.
 */

public class CustomDialog extends DialogFragment {
    private MainActivity mainActivity;
    private AlertDialog.Builder listDialog;
    private ArrayList<UDPData> udpData;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        listDialog = new AlertDialog.Builder(getActivity());

        mainActivity = (MainActivity) getArguments().getSerializable("MainActivity");

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.dialog_list, null);

        final CustomAdapter adapter = new CustomAdapter(mainActivity);
        udpData = new ArrayList<>();
        udpData = mainActivity.getList();
        adapter.setDataList(udpData);
        adapter.notifyDataSetChanged();

        final ListView listView = (ListView) convertView.findViewById(R.id.custom_list);
        listView.setAdapter(adapter);
        /*  Listタップ時に格納されているIP Address と comPortを使用  */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String IPAddress = udpData.get(i).getIPAddress();
                String comPort = udpData.get(i).getComPort();

                if (!TextUtils.isEmpty(IPAddress)) {
                    if(TextUtils.isEmpty(comPort)){
                        comPort = "5555";
                    }
                    new UDPReceiveThread(mainActivity, Integer.parseInt(comPort)).start();
                    mainActivity.setUDPDatas(IPAddress, comPort);
                    mainActivity.viewIPAddress();
                    dismiss();
                }
            }
        });

        /*  Listロングタッチ時にタッチした要素を削除  */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mainActivity.showText(String.valueOf(i) + "個目を消しました");
                udpData.remove(i);
                adapter.setDataList(udpData);
                adapter.notifyDataSetChanged();
                mainActivity.deletePref(udpData, i);
                return false;
            }
        });

        Button button = (Button) convertView.findViewById(R.id.new_data);
        /*  Listの要素数が5個以下なら実行  */
        if (udpData.size() <= 5) {
            button.setOnClickListener(new onClick());
        } else {
            mainActivity.showText("これ以上データを追加出来ません");
        }

        listDialog.setTitle("UDP通信先");
        listDialog.setMessage("IPAddress:" + mainActivity.getMyIPAddress());
        listDialog.setView(convertView);

        return listDialog.create();
    }

    public class onClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            dismiss();

            AlertDialog.Builder createUDPData = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View convertView = inflater.inflate(R.layout.custom_dialog, null);

            createUDPData.setTitle("新規作成");
            createUDPData.setView(convertView);

            final EditText editIPAddress = (EditText) convertView.findViewById(R.id.editIPAddress);
            final EditText editComPort = (EditText) convertView.findViewById(R.id.editPort);
            final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkSave);

            createUDPData.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final String IPAddress = editIPAddress.getText().toString();
                    final String comPort = editComPort.getText().toString();

                    if (!TextUtils.isEmpty(IPAddress) && !TextUtils.isEmpty(comPort)) {
                        try {
                            if (checkBox.isChecked() == true) {
                                UDPData data = new UDPData();
                                data.setIPAddress(IPAddress);
                                data.setComPort(comPort);
                                udpData.add(data);
                                mainActivity.setPref(udpData, udpData.size());
                            }
                            mainActivity.showText(String.valueOf(udpData.size())
                                    + "個目を作成しました");
                            new UDPReceiveThread(mainActivity, Integer.parseInt(comPort)).start();
                            mainActivity.setUDPDatas(IPAddress, comPort);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            createUDPData.create().show();
        }
    }
}
