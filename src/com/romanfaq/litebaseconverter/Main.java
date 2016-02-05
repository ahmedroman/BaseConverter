package com.romanfaq.litebaseconverter;



import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {	
	

	private EditText number;
	private EditText currentBase;
	private EditText toBase;
	//private TextView tv ;
	private TextView texc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		number = (EditText) findViewById(R.id.num);
		currentBase = (EditText) findViewById(R.id.from);
		toBase = (EditText) findViewById(R.id.to);
		//texc = (TextView) findViewById(R.id.res);
		texc = (TextView) findViewById(R.id.tex);
		
	}
	
	public void click(View v){
		try{
			
			String x = number.getText().toString();
			String cb = currentBase.getText().toString();
			String ba = toBase.getText().toString();
			int y = Integer.parseInt(cb);
			int b = Integer.parseInt(ba);
		
			int c=0;
			for(int i=0;i<x.length();i++){
				if(x.charAt(i)=='.'){
					c=1;
					break;			
				}			
			}
			
			if(c==0){
				int s2 = Integer.parseInt(x , y);		
				String fi = Integer.toString(s2 , b);
				//texc.setText("");
				texc.setText(fi.toUpperCase());
			}else{
				
			String[] ar = x.split("\\.");
			
			@SuppressWarnings("unused")
			int sss = Integer.parseInt(ar[1] , y);
			
			int s2 = Integer.parseInt(ar[0] , y);		
			String fi = Integer.toString(s2 , b);		
			String ans = toDec(ar[1],y);		
			String fin = decTo(ans,b);		
			
			//tv.setText("");
			texc.setText(fi+"."+fin);
			}
			
		}catch(Exception e){
			//texc.setText("");		
			texc.setText("Please enter a valid number");
		}
	}
	public static String toDec(String s , int y){
		String ar[] = s.split("(?<=.)");
		double p = 1;
		double ans= 0;
		for(int i =0 ;i<ar.length ;i++){			
			if(ar[i].matches("[0-9]")){
				ans+=Double.parseDouble(ar[i])/(Math.pow(y , p));			
			}else if(!(ar[i].matches("[0-9]"))){
				String le = ar[i].toUpperCase();
				char ll = le.charAt(0);
				int it = cal(ll);
				ans+=it/(Math.pow(y , p));
			}			
			p++;		
		}
		String fan = Double.toString(ans);
		return fan ;
	}
	public static int cal(char s){
		char as = 'A';
		int i=10;
		for(;i<37;i++){
			if(s==as) break;
			as++;
			
		}
		return i;	
	}
	
	public static String decTo(String dd,int b){
		double ans = Double.parseDouble(dd);
		ArrayList<String> al = new ArrayList<String>();
		for(int i = 0 ; i < 12 ; i++){
			if((int)ans==ans) break;			
			else {
				ans = ans*b;
				String s = Double.toString(ans);
				String ar[]=s.split("\\.");
				al.add(ar[0]);
				StringBuffer sb = new StringBuffer();
				sb.append(".").append(ar[1]);
				String ss = sb.toString();
				ans = Double.parseDouble(ss);		
			}	
		}
		StringBuffer ssb = new StringBuffer();
		for(String a : al){	
			int hi = Integer.parseInt(a);
			if(hi>9){ 
				char nn = toCha(hi);
				ssb.append(nn);
				}else ssb.append(a);
			}		
		return ssb.toString();
	}
	public static char toCha(int a){
		char as ='A';		
		for(int i =10;i<37;i++){
			if(a==i) break;
			as++;
		
		}
		return as;		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		this.finish();
		return super.onOptionsItemSelected(item);
	}

}
