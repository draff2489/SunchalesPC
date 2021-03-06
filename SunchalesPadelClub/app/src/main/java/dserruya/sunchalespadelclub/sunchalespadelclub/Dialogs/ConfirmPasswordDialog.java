package dserruya.sunchalespadelclub.sunchalespadelclub.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import dserruya.sunchalespadelclub.sunchalespadelclub.R;

public class ConfirmPasswordDialog extends DialogFragment {

    private static final String TAG = "ConfirmPasswordDialog";

    public interface OnConfirmPasswordListener {
        public void onConfirmPassword(String pasword);
    }

    OnConfirmPasswordListener mOnConfirmPasswordListener;

    TextView mPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm_password, container, false);
        mPassword = (TextView) view.findViewById(R.id.confirm_password);
        Log.d(TAG, "onCreateView: started");

        TextView confirmDialog = (TextView) view.findViewById(R.id.dialogdConfirm);
        confirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: captures password and confirming the dialog");

                String password = mPassword.getText().toString();
                if (!password.equals("")) {
                    mOnConfirmPasswordListener.onConfirmPassword(password);
                    getDialog().dismiss();
                }else{
                    Toast.makeText(getActivity(), "Debes ingresar una contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView cancelDialog = (TextView) view.findViewById(R.id.dialogdCancel);
        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: closing the dialog");
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mOnConfirmPasswordListener = (OnConfirmPasswordListener) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
        }
    }
}
