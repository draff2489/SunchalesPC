package dserruya.pc.sunchales.sunchalespc.userdetail;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import dserruya.pc.sunchales.sunchalespc.addedituser.AddEditUserActivity;
import dserruya.pc.sunchales.sunchalespc.data.User;
import dserruya.pc.sunchales.sunchalespc.users.UsersActivity;
import dserruya.pc.sunchales.sunchalespc.users.UsersFragment;

import dserruya.pc.sunchales.sunchalespc.R;
import dserruya.pc.sunchales.sunchalespc.data.UsersDbHelper;

/**
 * Vista para el detalle del abogado
 */
public class UserDetailFragment extends Fragment {
    private static final String ARG_USER_ID = "userId";

    private String mUserId;

    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mPhoneNumber;
    private TextView mMail;
    private TextView mScore;

    private UsersDbHelper mUsersDbHelper;


    public UserDetailFragment() {
        // Required empty public constructor
    }

    public static UserDetailFragment newInstance(String userId) {
        UserDetailFragment fragment = new UserDetailFragment();
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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        mAvatar = (ImageView) getActivity().findViewById(R.id.iv_avatar);
        mPhoneNumber = (TextView) root.findViewById(R.id.tv_phone_number);
        mMail = (TextView) root.findViewById(R.id.tv_mail);
        mScore = (TextView) root.findViewById(R.id.tv_score);

        mUsersDbHelper = new UsersDbHelper(getActivity());

        loadUser();

        return root;
    }

    private void loadUser() {
        new GetUserByIdTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                showEditScreen();
                break;
            case R.id.action_delete:
                new DeleteUserTask().execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UsersFragment.REQUEST_UPDATE_DELETE_USER) {
            if (resultCode == Activity.RESULT_OK) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        }
    }

    private void showUser(User user) {
        mCollapsingView.setTitle(user.getName());
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/" + user.getAvatarUri()))
                .centerCrop()
                .into(mAvatar);
        mPhoneNumber.setText(user.getPhoneNumber());
        mMail.setText(user.getMail());
        mScore.setText(user.getScore());
    }

    private void showEditScreen() {
        Intent intent = new Intent(getActivity(), AddEditUserActivity.class);
        intent.putExtra(UsersActivity.EXTRA_USER_ID, mUserId);
        startActivityForResult(intent, UsersFragment.REQUEST_UPDATE_DELETE_USER);
    }

    private void showUsersScreen(boolean requery) {
        if (!requery) {
            showDeleteError();
        }
        getActivity().setResult(requery ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    private void showDeleteError() {
        Toast.makeText(getActivity(),
                "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
    }

    private class GetUserByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mUsersDbHelper.getUserById(mUserId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showUser(new User(cursor));
            } else {
                showLoadError();
            }
        }

    }

    private class DeleteUserTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            return mUsersDbHelper.deleteUser(mUserId);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            showUsersScreen(integer > 0);
        }

    }

}
