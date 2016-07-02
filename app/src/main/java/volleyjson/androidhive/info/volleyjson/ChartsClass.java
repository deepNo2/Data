package volleyjson.androidhive.info.volleyjson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartsClass extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart chart = (BarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Sunday");
        labels.add("Monday");
        labels.add("Tuesday");
        labels.add("Wednesday");
        labels.add("Thrusday");
        labels.add("Friday");


//        BarChart chart = new BarChart(this);

        chart.animateY(3000);
        //  setContentView(chart);


        BarData data = new BarData(labels, dataset);

        chart.setData(data);

        chart.setDescription("# of times Alice called Bob");

        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
    }
    public void buttonForNextClicked(View view){
        Intent intent = new Intent(ChartsClass.this, SecondVisual.class);
        startActivity(intent);

    }
}