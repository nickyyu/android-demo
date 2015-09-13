# android-demo
# 自定义View

### 一、自定义课表View  ClassSchedule
![image](https://github.com/nickyyu/MyProject/blob/master/Screenshot_2015-09-10-15-35-26.png)
### 二、修改MPAndroidChart开源类库的柱状图使其左右滑动，详见BarChartActivity
左右滑动的方法是让其先绘制，绘制完后对图表进行放大处理，

### 添加以下代码<br/> mChart.setData(data);
        mChart.invalidate();
        Matrix mMatrix=new Matrix();
        mMatrix.postScale(1.5f, 1f);
        mChart.getViewPortHandler().refresh(mMatrix, mChart, false);
        mChart.animateY(800);
![image](https://github.com/nickyyu/MyPicture/blob/master/20150913_095446.gif)
