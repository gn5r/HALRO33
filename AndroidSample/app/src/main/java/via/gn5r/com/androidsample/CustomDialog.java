package via.gn5r.com.androidsample;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by gn5r on 17/11/16.
 */

public class CustomDialog extends DialogFragment {
    private MainActivity mainActivity;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder customDialog = new AlertDialog.Builder(getActivity());

        mainActivity = (MainActivity) getArguments().getSerializable("MainActivity");

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.custom_dialog, null);

        customDialog.setTitle("UDP通信設定");
        customDialog.setView(content);

        final EditText editIPAddress = (EditText) content.findViewById(R.id.editIPAddress);
        final EditText editPort = (EditText) content.findViewById(R.id.editPort);
        final CheckBox checkBox = (CheckBox)content.findViewById(R.id.checkSave);

        customDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String IPAddress = editIPAddress.getText().toString();
                final String comPort = editPort.getText().toString();

                try {
                    if (!TextUtils.isEmpty(IPAddress) && !TextUtils.isEmpty(comPort)) {

                        mainActivity.setIPAddress(IPAddress);
                        mainActivity.setPort(comPort);
                        boolean isChecked = checkBox.isChecked();
                        if(isChecked == true){
                            mainActivity.saveInformation(IPAddress,comPort);
                            mainActivity.showText("保存しました");
                        }
                        UDPReceiveThread receiveThread = new UDPReceiveThread(mainActivity, Integer.parseInt(comPort));
                        receiveThread.start();
                        mainActivity.viewIPAddress();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
        return customDialog.create();
    }
}
