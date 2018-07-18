package dserruya.pc.sunchales.sunchalespc.addedituser;


import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dserruya.pc.sunchales.sunchalespc.R;
import dserruya.pc.sunchales.sunchalespc.data.User;
import dserruya.pc.sunchales.sunchalespc.data.UsersDbHelper;

/**
 * Vista para creación/edición de un usuario
 */
public class AddEditUserFragment extends Fragment {
    private static final String ARG_USER_ID = "arg_user_id";

    private String mUserId;

    private UsersDbHelper mUsersDbHelper;

    private FloatingActionButton mSaveButton;
    private TextInputEditText mNameField;
    private TextInputEditText mPhoneNumberField;
    private TextInputEditText mMailField;
    private TextInputEditText mScoreField;
    private TextInputLayout mNameLabel;
    private TextInputLayout mPhoneNumberLabel;
    private TextInputLayout mMailLabel;
    private TextInputLayout mScoreLabel;


    public AddEditUserFragment() {
        // Required empty public constructor
    }

    public static AddEditUserFragment newInstance(String userId) {
        AddEditUserFragment fragment = new AddEditUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserId = getArguments().getString(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_user, container, false);

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mNameField = (TextInputEditText) root.findViewById(R.id.et_name);
        mPhoneNumberField = (TextInputEditText) root.findViewById(R.id.et_phone_number);
        mMailField = (TextInputEditText) root.findViewById(R.id.et_mail);
        mScoreField = (TextInputEditText) root.findViewById(R.id.et_score);
        mNameLabel = (TextInputLayout) root.findViewById(R.id.til_name);
        mPhoneNumberLabel = (TextInputLayout) root.findViewById(R.id.til_phone_number);
        mMailLabel = (TextInputLayout) root.findViewById(R.id.til_mail);
        mScoreLabel = (TextInputLayout) root.findViewById(R.id.til_score);

        // Eventos
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditUser();
            }
        });

        mUsersDbHelper = new UsersDbHelper(getActivity());

        // Carga de datos
        if (mUserId != null) {
            loadUser();
        }

        return root;
    }

    private void loadUser() {
        new GetUserByIdTask().execute();
    }

    private void addEditUser() {
        boolean error = false;

        String name = mNameField.getText().toString();
        String phoneNumber = mPhoneNumberField.getText().toString();
        String mail = mMailField.getText().toString();
        String score = mScoreField.getText().toString();

        if (TextUtils.isEmpty(name)) {
            mNameLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(mail)) {
            mMailLabel.setError(getString(R.string.field_error));
            error = true;
        }


        if (TextUtils.isEmpty(score)) {
            mScoreLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (error) {
            return;
        }

        User user = new User(name, mail, phoneNumber, score, "");

        new AddEditUserTask().execute(user);

    }

    private void showUsersScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {
            getActivity().setResult(Activity.RESULT_OK);
        }

        getActivity().finish();
    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showLawyer(User user) {
        mNameField.setText(user.getName());
        mPhoneNumberField.setText(user.getPhoneNumber());
        mMailField.setText(user.getMail());
        mScoreField.setText(user.getScore());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al editar usuario", Toast.LENGTH_SHORT).show();
    }

    private class GetUserByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mUsersDbHelper.getUserById(mUserId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showLawyer(new User(cursor));
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        }

    }

    private class AddEditUserTask extends AsyncTask<User, Void, Boolean> {

        @Override
        protected Boolean doInBackground(User... users) {
            if (mUserId != null) {
                return mUsersDbHelper.updateUser(users[0], mUserId) > 0;

            } else {
                return mUsersDbHelper.saveUser(users[0]) > 0;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            showUsersScreen(result);
        }

    }

}
