package com.skaminski.naspontana.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.skaminski.naspontana.R;
import com.skaminski.naspontana.api.ApiUtil;
import com.skaminski.naspontana.generated.ActivityToCheck;
import com.skaminski.naspontana.generated.Category;
import com.skaminski.naspontana.generated.Datum;
import com.skaminski.naspontana.other.Utl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    String data;
    static String dzien = "";
    static String godzina = "";
    static String id = "";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_aktynosc)
    EditText etAktynosc;
    @BindView(R.id.et_opis)
    EditText etOpis;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.et_data)
    TextView etData;
    @BindView(R.id.et_godzina)
    TextView etGodzina;
    @BindView(R.id.et_typ)
    TextView etTyp;

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.fab)
    public void addAction() {
        if (!godzina.equals("")
                && !id.equals("")
                && !dzien.equals("")) {

            ActivityToCheck activityToCheck = new ActivityToCheck();
            data = dzien + godzina;
            activityToCheck.setCategoryId(id);
            activityToCheck.setDescription(etOpis.getText().toString());
            activityToCheck.setMinutesDiff("30");
            activityToCheck.setStartDate(data);
            activityToCheck.setName(etAktynosc.getText().toString());
            List<String> friendsIds = new ArrayList<>();
            for (Datum datum : ApiUtil.friendsList.getData()) {
                friendsIds.add(datum.getId());
            }
            activityToCheck.setFriends(friendsIds);
            activityToCheck.setFacebookId(Utl.getLoginResult(this).getAccessToken().getUserId());

            ApiUtil api = new ApiUtil();
            api.getService().addActivity(activityToCheck).enqueue(new Callback<Void>() {
                //// TODO: 13.12.2016 zmienic typ zwracany 
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("e", "e");
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("e", "e");
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Uzupelnij pola", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);

    }

    @OnClick(R.id.et_typ)
    public void setType() {
        List<String> list = new ArrayList<>();
        for (Category category : ApiUtil.categoryList) {
            list.add(category.getName());
        }

        new MaterialDialog.Builder(this)
                .title("Wybierz kategorie")
                .items(list)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        id = "" + ApiUtil.categoryList.get(position).getId();
                        EventBus.getDefault().post("u");
                    }
                })
                .show();
    }

    @OnClick(R.id.et_data)
    public void data() {
        DialogFragment newFragment2 = new DatePickerFragment();
        newFragment2.show(getSupportFragmentManager(), "datePicker");
        EventBus.getDefault().post("u");
    }

    @OnClick(R.id.et_godzina)
    public void godzina() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            godzina = hourOfDay + ":" + minute + ":00";
            EventBus.getDefault().post("u");
            //// TODO: 13.12.2016 dodac do pola  etGOdzina
        }
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            dzien = year + "-" + month + "-" + day + "T";
            EventBus.getDefault().post("u");
            //// TODO: 13.12.2016 dodac do pola  etData
        }
    }

    @Subscribe
    public void update(String msg) {
        try {
            for (Category category : ApiUtil.categoryList) {
                if (category.getId() == Integer.parseInt(id))
                    etTyp.setText(category.getName());
            }
        } catch (Throwable e) {
        }

        etData.setText(dzien);
        etGodzina.setText(godzina);
    }
}
