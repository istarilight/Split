package split.dashdash.com.split;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.CursorAdapter;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import split.dashdash.com.Util.DatabaseUtil;
import split.dashdash.com.model.Bill;
import split.dashdash.com.model.BillContentProvider;
import split.dashdash.com.model.BillDao;

public class BillOverviewFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private BillCursorAdapter billCursorAdapter;
    private BillDao billDao;

    public static BillOverviewFragment newInstance() {
        return new BillOverviewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        billCursorAdapter = new BillCursorAdapter(getActivity(), null);
        setListAdapter(billCursorAdapter);
        getLoaderManager().initLoader(0, null, this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_bill, menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Bill");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_bill:
                getFragmentManager().beginTransaction()
                        .replace(R.id.navigation_content, BillEditFragment.newInstance())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyText("I am empty");
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(getListView().getCheckedItemCount()+"");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_cab_bill, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                mode.setTitle(getListView().getCheckedItemCount()+"");
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                getListView().setItemChecked(position, true);
                return true;
            }
        });
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        billDao = DatabaseUtil.getBillDao(activity);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), BillContentProvider.CONTENT_URI, null, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        billCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        billCursorAdapter.swapCursor(null);
    }

    private class BillCursorAdapter extends CursorAdapter {

        private LayoutInflater inflater;

        public BillCursorAdapter(Context context, Cursor c) {
            super(context, c);
            inflater = LayoutInflater.from(context);

        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return inflater.inflate(R.layout.listrow_bill, viewGroup, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Bill bill = billDao.readEntity(cursor, 0);
            TextView date = (TextView) view.findViewById(R.id.date);
            TextView partyMember = (TextView) view.findViewById(R.id.party_member);

            date.setText(bill.getTime());
            partyMember.setText(bill.getPartyMember());
        }
    }
}
