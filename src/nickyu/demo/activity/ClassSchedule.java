package nickyu.demo.activity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;




import nickyu.demo.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ClassSchedule extends Activity {
	private TableLayout mTablayout;
	private TableLayout scrollView;
	private int colors[] = { Color.rgb(0xee, 0xff, 0xff),
			Color.rgb(0xf0, 0x96, 0x09), Color.rgb(0x8c, 0xbf, 0x26),
			Color.rgb(0x00, 0xab, 0xa9), Color.rgb(0x99, 0x6c, 0x33),
			Color.rgb(0x3b, 0x92, 0xbc), Color.rgb(0xd5, 0x4d, 0x34),
			Color.rgb(0xcc, 0xcc, 0xcc), Color.rgb(0x87, 0xCE, 0xEB),// 天蓝色
			Color.rgb(0xFF, 0xE4, 0xB5),// 鹿皮色
			Color.rgb(0xBC, 0x8F, 0x8F),// 褐玫瑰红
			Color.rgb(0xDD, 0xD7, 0x00),//
			Color.rgb(0xAF, 0x00, 0xFF),// 紫红色
			Color.rgb(0xF5, 0xF5, 0xDC),// 米色
			Color.rgb(0xDE, 0xB8, 0x87),// 实木色
			Color.rgb(0xDB, 0x70, 0x93),// 苍紫罗兰色
	};
	
	private String courses[]={"数学","","","英语","化学","地理","物理"};
	private String times[]={"8:30","10:45","2:30","7:00","4:30"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classschedule_item);
		mTablayout=(TableLayout) findViewById(R.id.clssch_content);
		scrollView=(TableLayout) findViewById(R.id.clashedule_course);
		initSchedu(initData());
	}
	
	
	
	/**初始化数据
	 * @return
	 */
	public List<Map<String, Object>> initData(){
		
		List<Map<String, Object>> mDatas=new ArrayList<>();
		Random random=new Random();
		for(int i=1;i<9;i++){
			Map<String, Object> map=new HashMap<>();
			map.put("cs_order", i);
			map.put("cs_time", times[random.nextInt(times.length)]);
		for(int j=0;j<7;j++){
			map.put("cid"+j+"_name",courses[random.nextInt(courses.length)] );
			
		}
		mDatas.add(map);
		}
		
		return mDatas;
	}
	
	
	
	/**初始化课表
	 * 
	 */
	@SuppressLint("NewApi")
	private void initSchedu(List<Map<String, Object>> schList){
		TableRow header=(TableRow) mTablayout.getChildAt(0);
		LinearLayout firstLayout=(LinearLayout) header.getChildAt(0);
		TextView firstColumn=(TextView) firstLayout.getChildAt(0);
		LinearLayout secondColumn=(LinearLayout) header.getChildAt(1);
		//初始化课表日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("M",
				Locale.getDefault());
		String month=dateFormat.format(new Date());
		firstColumn.setText(month+"月");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd",
				Locale.getDefault());
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期

		TextView mondayText=(TextView) secondColumn.getChildAt(0);
		mondayText.setText( dateFormat1.format(cal.getTime())+"日");// 将星期一课表的表头设置成:周一01-01
		cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		LinearLayout thirdLayout=(LinearLayout) header.getChildAt(2);
		TextView tusText=(TextView) thirdLayout.getChildAt(0);
		tusText.setText( dateFormat1.format(cal.getTime())+"日");
		LinearLayout fourLayout=(LinearLayout) header.getChildAt(3);
		TextView wedTextView=(TextView) fourLayout.getChildAt(0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		wedTextView.setText( dateFormat1.format(cal.getTime())+"日");
		LinearLayout fiveLayout=(LinearLayout) header.getChildAt(4);
		TextView thuTextView=(TextView) fiveLayout.getChildAt(0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		thuTextView.setText( dateFormat1.format(cal.getTime())+"日");
		LinearLayout sixLayout=(LinearLayout) header.getChildAt(5);
		TextView friTextView=(TextView) sixLayout.getChildAt(0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		friTextView.setText( dateFormat1.format(cal.getTime())+"日");
		LinearLayout sevenLayout=(LinearLayout) header.getChildAt(6);
		TextView satTextView=(TextView) sevenLayout.getChildAt(0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		satTextView.setText( dateFormat1.format(cal.getTime())+"日");

		
		// 因为老外那边把周日当成第一天，所以中国的本周日即为老外的新的一周的周日
		LinearLayout eightLayout=(LinearLayout) header.getChildAt(7);
		TextView sunTextView=(TextView) eightLayout.getChildAt(0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		sunTextView.setText( dateFormat1.format(cal.getTime())+"日");
		
		//初始化课表内容
		Random random=new Random();
		for(int i=0;i<schList.size();i++){
			TableRow row=new TableRow(ClassSchedule.this);
			row.setLayoutParams(header.getLayoutParams());
			LinearLayout fLayout=new LinearLayout(ClassSchedule.this);
			fLayout.setBackground(firstLayout.getBackground());
			fLayout.setLayoutParams(firstLayout.getLayoutParams());
			fLayout.setGravity(Gravity.CENTER);
			TextView fColumn=new TextView(ClassSchedule.this);
			fColumn.setLayoutParams(firstColumn.getLayoutParams());
			fColumn.setText("第"+schList.get(i).get("cs_order").toString()+"节");
			fLayout.addView(fColumn);
			row.addView(fLayout);
			//设置星期课程内容
			for(int j=1;j<=6;j++){
				
				LinearLayout layout=new LinearLayout(ClassSchedule.this);
				layout.setOrientation(LinearLayout.VERTICAL);
				layout.setLayoutParams(secondColumn.getLayoutParams());
				Object cr_name=schList.get(i).get("cid"+j+"_name");
				TextView cr_text=new TextView(ClassSchedule.this);
				cr_text.setLayoutParams(secondColumn.getChildAt(0).getLayoutParams());
				TextView timeView=new TextView(ClassSchedule.this);
				timeView.setLayoutParams(secondColumn.getChildAt(1).getLayoutParams());
				if(cr_name!=null){
				if (!"".equals(cr_name.toString())) {
					layout.setBackgroundColor(colors[random.nextInt(colors.length)]);
					cr_text.setText(cr_name.toString());
				timeView.setText(schList.get(i).get("cs_time").toString());
				}else{
					cr_text.setText(" ");
					timeView.setText(" ");
				}
				
				layout.addView(cr_text);
				layout.addView(timeView);
				row.addView(layout);
				}
			}
			LinearLayout layout=new LinearLayout(ClassSchedule.this);
			layout.setLayoutParams(secondColumn.getLayoutParams());
			layout.setOrientation(LinearLayout.VERTICAL);
			Object cr_name=schList.get(i).get("cid"+0+"_name");
			if(cr_name!=null){
			if (!"".equals(cr_name.toString())) {
				layout.setBackgroundColor(colors[random.nextInt(colors.length)]);
				TextView cr_text=new TextView(ClassSchedule.this);
				cr_text.setLayoutParams(secondColumn.getChildAt(0).getLayoutParams());
				cr_text.setText(cr_name.toString());
				layout.addView(cr_text);
			TextView timeView=new TextView(ClassSchedule.this);
			timeView.setLayoutParams(secondColumn.getChildAt(1).getLayoutParams());
			timeView.setText(schList.get(i).get("cs_time").toString());
			layout.addView(timeView);
			}
			row.addView(layout);
			}
			scrollView.addView(row);
		}
		
	}

}
