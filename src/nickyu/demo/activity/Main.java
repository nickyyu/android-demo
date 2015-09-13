package nickyu.demo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Main extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new SimpleAdapter(this, initData(), android.R.layout.simple_list_item_1, new String[]{"item"}, new int[]{android.R.id.text1}));
	}

	
	private List<Map<String, Object>> initData(){
		
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		String items[]={"课表","修改MpAndroidChart柱状图左右滚动"};
		for(String str:items){
			Map<String, Object> map=new HashMap<>();
			map.put("item", str);
			list.add(map);
		}
		return list;
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent=new Intent();
		switch (position) {
		case 0:
			intent.setClass(Main.this, ClassSchedule.class);
			break;
		case 1:
			intent.setClass(Main.this, BarChartActivity.class);
			break;

		default:
			break;
		}
		startActivity(intent);
	}
}
