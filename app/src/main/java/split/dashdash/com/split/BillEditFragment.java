package split.dashdash.com.split;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

public class BillEditFragment extends android.app.Fragment{

    public static final BillEditFragment newInstance(){
        return new BillEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getActivity().getActionBar();
        if(actionBar != null){
            actionBar.setTitle("Edit");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStackImmediate();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
}
