package com.example.calcdemo;

import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class CalcActivity extends Activity {
	private EditText et ;
	//private Button but = null ;
	private static String firstNumber = "0";// 第一次输入的值  
    private static String secondNumber = "0";// 第二次输入的值  
    private static String num = "0";// 显示的结果  
    private static int flg = 0;// 结果累加一次 
    public Counts take = null;
	private int [] btNum = {R.id.txtspl,R.id.txt0,R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4,R.id.txt5,R.id.txt6,R.id.txt7,R.id.txt8,R.id.txt9} ;
	private int [] sum = {R.id.sub,R.id.jian,R.id.mul,R.id.div} ;
	private Button[] buttons = new Button[btNum.length] ;
	private Button[] sumbtn = new Button[sum.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        this.et = (EditText)super.findViewById(R.id.add) ;
        et.setText("0") ;
        et.setEnabled(false);
        GetNumber get = new GetNumber() ;
        for (int i = 0; i < btNum.length; i++) {
        	buttons[i]  = (Button) findViewById(btNum[i]) ;
        	buttons[i].setOnClickListener(get) ;
		}
        Compute cm = new Compute() ;
        for (int i = 0; i < sum.length; i++) {
			sumbtn[i]  = (Button) findViewById(sum[i]);
			sumbtn[i].setOnClickListener(cm);
		}
        
        Button eq = (Button) findViewById(R.id.txteq);
        
        
        eq.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                if (flg == 0) {  
                    secondNumber = et.getText().toString();  
                    if (take == Counts.DIV && secondNumber.equals("0")) {  
                        et.setText("0不能为被除数");  
                    } else {  
                        num = take.Values(firstNumber, secondNumber);  
                        firstNumber = num;  
                        secondNumber = "0";  
                        et.setText(num);  
                        flg = 1;  
                         
                    }  
                }  
            }  
        });  
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				et.setText("");
				num = "" ;
				firstNumber = secondNumber = num ;
				flg = 0 ; 
			}
        	
        });
    }
    
    class GetNumber implements OnClickListener{
		public void onClick(View v) {
			if(flg == 1)
				num = "0" ;
				flg = 0 ;
			if(num.equals("0")){
				//et.setText("") ;
				num = v.getId() == R.id.txtspl ? "0": "" ;
			}
			String txt = ((Button) v).getText().toString();  
            boolean s = Pattern.matches("-*(\\d+).?(\\d)*", num + txt);  
            num = s ? (num + txt) : num;   
            et.setText(num);  
		}
    }
    
    private class Compute implements OnClickListener{

		@Override
		public void onClick(View v) {
			firstNumber = et.getText().toString() ;
			switch (v.getId()){
			case R.id.sub :
				take = Counts.ADD ;
				break ;
			case R.id.jian :
				take = Counts.MINUS ;
				break ;
			case R.id.mul :
				take = Counts.MUL ;
				break ;
			case R.id.div :
				take = Counts.DIV ;
				break ;
			}
			num = "0" ;
			flg = 0 ;
		}
    	
    }
    
}
