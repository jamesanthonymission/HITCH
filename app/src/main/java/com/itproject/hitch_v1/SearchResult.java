package com.itproject.hitch_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {

    ArrayList<String> selection = new ArrayList<String>();

    String carwithaircon = "CAR 1 \n w/ A-C and w/ B \n AAA 111 \n James \n\n car 2 \n w/ A-C and w/o B \n BBB 222 \n Anthony \n\n CAR 5 \n w/ A-C and w/ B \n EEE 555 \n Ralph \n\n CAR 6 \n w/ A-C and w/o B \n FFF 666 \n Evan ";

    String carwithoutaircon = "CAR 3 \n w/o A-C and w/ B \n CCC 333 \n Regine \n\n CAR 4 \n w/o A-C and w/o B \n DDD 444 \n Lae \n\n CAR 7 \n w/o A-C and w/ B \n GGG 777 \n Alyssa \n\n CAR 8 \n w/o A-C and w/o B \n HHH 888 \n Junvir";

    String carwithbaggage = "CAR 1 \n w/ A-C and w/ B \n AAA 111 \n James \n\n CAR 3 \n w/o A-C and w/ B \n CCC 333 \n Regine \n\n CAR 5 \n w/ A-C and w/ B \n EEE 555 \n Ralph \n\n CAR 7 \n w/o a-c and w/ B \n GGG 777 \n Alyssa";

    String carwithoutbaggage = "CAR 2 \\n w/ A-C and w/o B \\n BBB 222 \\n Anthony \n\n CAR 4 \n w/o A-C and w/o B \n DDD 444 \n Lae \n\n CAR 6 \n w/ A-C and w/o B \n FFF 666 \n Evan \n\n CAR 8 \n w/o A-C and w/o B \n HHH 888 \n Junvir";




    CheckBox withAircon, withoutAircon, withBaggage, withoutBaggage;
    TextView filterTv,secondTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);



        filterTv = (TextView) findViewById(R.id.filterTv);
        // secondTv = (TextView) findViewById(R.id.secondTv);


        withAircon = (CheckBox)findViewById(R.id.withAircon);
        withoutAircon = (CheckBox)findViewById(R.id.withoutAircon);
        withBaggage = (CheckBox)findViewById(R.id.withBaggage);
        withoutBaggage = (CheckBox)findViewById(R.id.withoutBaggage);


    }
    public void onCheckBoxClicked(View view) {

//       boolean checked = ((CheckBox)view).isChecked();
//
//        String withaircon = "";
//
//        for(String Selections: selection){
//            withaircon = withaircon + Selections + "\n";
//        }
//        filterTv.setText(withaircon);
//        filterTv.setEnabled(true);


        switch (view.getId()){
            case R.id.withAircon:
                //selection.add("with aircon");
                filterTv.setText(carwithaircon);
                withoutAircon.setChecked(false);
                break;

            case R.id.withoutAircon:
                // selection.add("with out Aircon");
                filterTv.setText(carwithoutaircon);
                withAircon.setChecked(false);

                break;

            case R.id.withBaggage:
                //  selection.add("with baggage");
                filterTv.setText(carwithbaggage);
                withoutBaggage.setChecked(false);
                break;
            case R.id.withoutBaggage:
                // selection.add("with out baggage");
                filterTv.setText(carwithoutbaggage);
                withBaggage.setChecked(false);
                break;
        }
        if(withAircon.isChecked() && withBaggage.isChecked()){
            Toast.makeText(getBaseContext(), "aircon and baggage", Toast.LENGTH_SHORT).show();
            filterTv.setText("CAR 1 \n w/ A-C and w/ B \n AAA 111 \n James \n\n CAR 5 \n w/ A-C and w/ B \n EEE 555 \n Ralph");
        }

        else if (withAircon.isChecked() && withoutBaggage.isChecked()) {
            Toast.makeText(getBaseContext(), "aircon and without baggage", Toast.LENGTH_SHORT).show();
            filterTv.setText("CAR 2 \n w/ A-C and w/o B \n BBB 222 \n Anthony \n\n CAR 6 \n w/ A-C and w/o B \n FFF 666 \n Evan ");
        }

        else if(withoutAircon.isChecked() && withBaggage.isChecked()){
            Toast.makeText(getBaseContext(), "without aicon and with baggage", Toast.LENGTH_SHORT).show();
            filterTv.setText("CAR 3 \n w/o A-C and w/ B \n CCC 333 \n Regine \n\n CAR 5 \n w/ A-C and w/ B \n EEE 555 \n Ralph");
        }

        else if(withoutAircon.isChecked() && withoutBaggage.isChecked()){
            Toast.makeText(getBaseContext(), "without aircon and without baggage", Toast.LENGTH_SHORT).show();
            filterTv.setText("CAR 4 \n w/o A-C and w/o B \n DDD 444 \n Lae \n\n CAR 8 \n w/o A-C and w/o B \n HHH 888 \n Junvir");
        }

    }
}