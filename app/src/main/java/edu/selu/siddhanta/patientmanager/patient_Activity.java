package edu.selu.siddhanta.patientmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class patient_Activity extends AppCompatActivity {
    int size, index;
    public static String user;
    ListView order_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetPatientList temp = new GetPatientList();
        final ArrayList<Patient> patientList = temp.getPateintList();
//        Patient cur_patient = patientList.get(0);d

        size = patientList.size();
        index = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        user = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView use_text = (TextView) findViewById(R.id.user_view);
        use_text.setText(user);

        order_list = (ListView)findViewById(R.id.list_view);


        Button next = (Button) findViewById(R.id.next_patient_button);
        Button prev = (Button) findViewById(R.id.previous_patient_button);

        getPatient(patientList.get(index));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == size-1){
                    getPatient(patientList.get(index));
                }
                else {
                    ++index;
                    getPatient(patientList.get(index));
                }

            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == 0){
                    index = 0;
                    getPatient(patientList.get(index));
                }
                else {
                    --index;
                    getPatient(patientList.get(index));
                }
            }
        });



    }

    void getPatient(Patient cur_patient){
        List<Order> orderList = cur_patient.getOrders();
        // print current patient's name
        TextView patient_text = (TextView) findViewById(R.id.patient_view);
        patient_text.setText(cur_patient.getName());
        // print current patient's id
        TextView id_text = (TextView) findViewById(R.id.id_view);
        id_text.setText(cur_patient.getId());
        // method call to print current patients orders
        Adapter orderAdapter = new Adapter(getApplicationContext(), R.layout.row, orderList );
        order_list.setAdapter(orderAdapter);
    }

//    void getOrder(final Patient cur_patient){
//        // fill button to refill medicine
//        Button fill = (Button) findViewById(R.id.fill_button);
////        for (int i=0; i <= orderList.size(); i++){
//        List<Order> orderList = cur_patient.getOrders();
//        final Order orders = orderList.get(0);
//
//
//// this part is where the containers get "wired" together
//        ScrollView sv = new ScrollView(this);
//        LinearLayout ll = new LinearLayout(this);
//
//
//        ll.setOrientation(LinearLayout.VERTICAL);
//        sv.addView(ll);
//
//
//        TextView rx_txt = (TextView) findViewById(R.id.rx_view);
//        rx_txt.setText(orders.getMedicine());
//
//        TextView dose_text = (TextView) findViewById(R.id.dosage_view);
//        dose_text.setText(orders.getDosage());
//
//        TextView rem_text = (TextView) findViewById(R.id.rem_view);
//        rem_text.setText(orders.getRefill_rem());


//
//        fill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                webCall(user,cur_patient.getId(), orders.getMedicine());
//            }
//        });
//    }

}



