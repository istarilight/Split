package split.dashdash.com.split;

import android.app.Activity;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import split.dashdash.com.Util.DatabaseUtil;
import split.dashdash.com.model.Bill;
import split.dashdash.com.model.BillDao;
import split.dashdash.com.model.DaoMaster;
import split.dashdash.com.model.DaoSession;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpTestData();
        getFragmentManager().beginTransaction().replace(R.id.navigation_content, BillOverviewFragment.newInstance()).commitAllowingStateLoss();
    }

    private void setUpTestData() {
        final BillDao billDao = DatabaseUtil.getBillDao(this);

        billDao.deleteAll();
        Bill bill = new Bill();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy - hh:mm a");
        dateFormat.setTimeZone(TimeZone.getDefault());
        bill.setTime(dateFormat.format(new Date()));
        bill.setPartyMember("With Michael, Josh and Chris");
        Bill bill2 = new Bill();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy - hh:mm a");
        dateFormat.setTimeZone(TimeZone.getDefault());
        bill2.setTime(dateFormat.format(new Date()));
        bill2.setPartyMember("With mom and dad");
        Bill bill3 = new Bill();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy - hh:mm a");
        dateFormat.setTimeZone(TimeZone.getDefault());
        bill3.setTime(dateFormat.format(new Date()));
        bill3.setPartyMember("With Ashley and other guests");
        billDao.insert(bill);
        billDao.insert(bill2);
        billDao.insert(bill3);
    }

    private SimpleDateFormat dateFormat;

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        }
        else{
            super.onBackPressed();
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




    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
