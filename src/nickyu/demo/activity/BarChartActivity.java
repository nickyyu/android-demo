package nickyu.demo.activity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import nickyu.demo.R;

public class BarChartActivity extends FragmentActivity {

    private BarChart mChart;
    private String TAG="ScrollViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scrollview);

        mChart = (BarChart) findViewById(R.id.chart1);

        mChart.setDescription("");

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
//        xAxis.setPosition(XAxisPosition.BOTTOM_INSIDE);
//        xAxis.setLabelsToSkip(0);
        xAxis.setDrawGridLines(false);

        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisRight().setEnabled(false);
        mChart.getLegend().setEnabled(false);

        setData(10);
    }
    
    private void setData(int count) {
        
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * count) + 15;
            yVals.add(new BarEntry((int) val, i));
            xVals.add((int) val + "");
        }

        BarDataSet set = new BarDataSet(yVals, "Data Set");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setDrawValues(false);

     final   BarData data = new BarData(xVals, set);
mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
	
	@Override
	public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
		
Log.d(TAG, "e:"+e);
Log.d(TAG, "y:"+data.getXVals().get(e.getXIndex()));

	}
	
	@Override
	public void onNothingSelected() {
		
	}
});
        mChart.setData(data);
        mChart.invalidate();
        Matrix mMatrix=new Matrix();
        mMatrix.postScale(1.5f, 1f);
        mChart.getViewPortHandler().refresh(mMatrix, mChart, false);
        mChart.animateY(800);
    }
}
