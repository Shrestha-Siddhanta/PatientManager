package edu.selu.siddhanta.patientmanager;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static edu.selu.siddhanta.patientmanager.patient_Activity.user;

/**
 * Created by Siddhanta on 4/15/2017.
 */

public class Adapter extends ArrayAdapter {
    private List<Order> orders;
    private int resource;
    private LayoutInflater inflater;
    public Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        orders = objects;
        this.resource= resource;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(resource, null);
        }
        TextView rx = (TextView) convertView.findViewById(R.id.rx_view);
        TextView dose = (TextView) convertView.findViewById(R.id.dose_view);
        final TextView rem = (TextView) convertView.findViewById(R.id.rem_view);
//        fill button to refill medicine
        Button fill = (Button) convertView.findViewById(R.id.fill_button);

        rx.setText(orders.get(position).getMedicine());
        dose.setText(orders.get(position).getDosage());
        rem.setText(orders.get(position).getRefill_rem());


        fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                String useri = user;
                String idi = orders.get(position).getId();
                String med = orders.get(position).getMedicine();
                boolean webresponse = webCall(useri,idi,med );
                if(webresponse){

                    int remain = Integer.parseInt(orders.get(position).getRefill_rem());
                    if(remain > 0) {
                        remain = --remain;
                        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        String temp = Integer.toString(remain);
                        orders.get(position).setRefill_rem(temp);
                        rem.setText(temp);

                    }else{
                        Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                        String temp = Integer.toString(remain);
                        orders.get(position).setRefill_rem(temp);
                        rem.setText(temp);
                    }

                }
                else{
                    Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }


    private String readStream(InputStream in) { // parse web response into string (copied from web)
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    // web call method that takes user name, id and medicine to be filled
    public boolean webCall(String user, String id, String medicine ){
        boolean success = false;
        String response;
        URL url = null;
        try {
            url = new URL("http://www2.southeastern.edu/Academics/Faculty/jburris/rx_fill.php?login="+user+"&id="+id+"&rx="+medicine);
//            url = new URL("http://www2.southeastern.edu/Academics/Faculty/jburris/rx_fill.php?login=0518049&id=0000-44444&rx=Lisinopril"); // test url
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            InputStreamReader isw = new InputStreamReader(in);

            response = readStream(in) ;
            if (response.equals("<return>success</return>") ){

                success = true;

            }
            else{
                success = true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();

        }
        return success;
    }


}
