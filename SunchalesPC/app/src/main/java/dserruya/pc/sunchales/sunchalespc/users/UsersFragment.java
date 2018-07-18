package dserruya.pc.sunchales.sunchalespc.users;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import dserruya.pc.sunchales.sunchalespc.R;
import dserruya.pc.sunchales.sunchalespc.data.UsersContract;
import dserruya.pc.sunchales.sunchalespc.data.UsersDbHelper;
import dserruya.pc.sunchales.sunchalespc.addedituser.AddEditUserActivity;
import dserruya.pc.sunchales.sunchalespc.userdetail.UserDetailActivity;


/**
 * Vista para la lista de abogados del gabinete
 */
public class UsersFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_USER = 2;

    private UsersDbHelper mUsersDbHelper;

    private ListView mUsersList;
    private UsersCursorAdapter mUsersAdapter;
    private FloatingActionButton mAddButton;


    public UsersFragment() {
        // Required empty public constructor
    }

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_users, container, false);

        // Referencias UI
        mUsersList = (ListView) root.findViewById(R.id.users_list);
        mUsersAdapter = new UsersCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        // Setup
        mUsersList.setAdapter(mUsersAdapter);

        // Eventos
        mUsersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mUsersAdapter.getItem(i);
                String currentUserId = currentItem.getString(
                        currentItem.getColumnIndex(UsersContract.UserEntry.ID));

                showDetailScreen(currentUserId);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });


        getActivity().deleteDatabase(UsersDbHelper.DATABASE_NAME);

        // Instancia de helper
        mUsersDbHelper = new UsersDbHelper(getActivity());

        // Carga de datos
        loadUsers();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case AddEditUserActivity.REQUEST_ADD_USER:
                    showSuccessfullSavedMessage();
                    loadUsers();
                    break;
                case REQUEST_UPDATE_DELETE_USER:
                    loadUsers();
                    break;
            }
        }
    }

    private void loadUsers() {
        new UsersLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditUserActivity.class);
        startActivityForResult(intent, AddEditUserActivity.REQUEST_ADD_USER);
    }

    private void showDetailScreen(String userId) {
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra(UsersActivity.EXTRA_USER_ID, userId);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_USER);
    }

    private class UsersLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mUsersDbHelper.getAllUsers();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mUsersAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

}
