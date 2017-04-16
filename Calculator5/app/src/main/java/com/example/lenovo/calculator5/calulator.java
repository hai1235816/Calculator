package com.example.lenovo.calculator5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.lang.Math.sqrt;

public class calulator extends AppCompatActivity implements View.OnClickListener {

    boolean end, point,error, cal_click, equ_click, err_sqrt, x_exe, add_sub_cli, num_click;
    BigDecimal resFor, resLat, saving, num, one, ten, zero, saving_equ, m_one;
    double trans;
    int opt, deci = 0, show_deci;
    String show_text;

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_add, btn_sub, btn_mul,btn_p,
            btn_div, btn_add_sub, btn_xx, btn_1x, btn_sqrt, btn_back, btn_C, btn_CE, btn_equal;
    TextView showresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //声明控件
        setContentView(R.layout.activity_calulator);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_p = (Button) findViewById(R.id.btn_p);
        btn_add_sub = (Button) findViewById(R.id.btn_add_sub);
        btn_1x = (Button) findViewById(R.id.btn_1x);
        btn_xx = (Button) findViewById(R.id.btn_xx);
        btn_C = (Button) findViewById(R.id.btn_C);
        btn_CE = (Button) findViewById(R.id.btn_CE);
        btn_sqrt = (Button) findViewById(R.id.btn_sqrt);
        btn_back = (Button) findViewById(R.id.btn_back);
        showresult = (TextView) findViewById(R.id.text_view);
        resFor = new BigDecimal(0);
        resLat = new BigDecimal(0);
        saving = new BigDecimal(0);
        one = new BigDecimal(1);
        ten = new BigDecimal(10);
        zero = new BigDecimal(0);
        num = new BigDecimal(1);
        m_one = new BigDecimal(-1);
//添加监听器
        btn_CE.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_p.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_add_sub.setOnClickListener(this);
        btn_xx.setOnClickListener(this);
        btn_1x.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_sqrt.setOnClickListener(this);
    }

    /*public void  debug(){
        System.out.println("resLat:"+resLat);
        System.out.println("resFor:"+resFor);
        System.out.println("saving:"+saving);
        System.out.println("trans:"+trans);
        System.out.println("cal_click:"+cal_click);
        System.out.println("num_click:"+num_click);
    }*/

    public void clear() {
        end = error = point = err_sqrt = equ_click = cal_click = x_exe = add_sub_cli = num_click = false;
        resFor = resLat = saving_equ = saving = zero;
        opt = deci = 0;
    }

    public void num_exe(){
        int i;
        if(equ_click||x_exe||(add_sub_cli&&end)) {
            resLat = zero;
            if(equ_click) end = false;
        }
        add_sub_cli = equ_click = cal_click = false;
        saving = resLat;
        if(point){
            deci++;
            for(num = BigDecimal.valueOf(1), i = 0; i<deci; i++)
                num = num.divide(ten,15, RoundingMode.HALF_EVEN);
        }
        x_exe = false;
        num_click = true;
    }

    public void cal_exe(){
        equ_click = point = false;
        if(!end) {
            resFor = resLat;
            resLat = zero;
        }
    }

    public void cal_end_exe(){
        resLat = zero;
        saving = resFor;
        end = cal_click = true;
        x_exe = add_sub_cli = num_click = false;
    }

    public void calcu() {
        switch (opt) {
            case 1:
                resFor = resFor.add(resLat);
                break;
            case 2:
                resFor = resFor.subtract(resLat);
                break;
            case 3:
                resFor = resFor.multiply(resLat);
                break;
            case 4:
                if (resLat.compareTo(zero)!=0) resFor = resFor.divide(resLat, 15, RoundingMode.HALF_EVEN);
                else error = true;
                break;
        }
    }

    public void onClick(View v) {
        int i;
        switch (v.getId()) {
            case R.id.btn_add:
                cal_exe();
                if(cal_click||!end) {
                    cal_end_exe();
                    opt = 1;
                    break;
                }
                calcu();
                opt = 1;
                if(end) resLat = zero;
                cal_end_exe();
                break;
            case R.id.btn_sub:
                cal_exe();
                if(cal_click||!end) {
                    cal_end_exe();
                    opt = 2;
                    break;
                }
                calcu();
                opt = 2;
                if(end) resLat = zero;
                cal_end_exe();
                break;
            case R.id.btn_mul:
                cal_exe();
                if(cal_click||!end) {
                    cal_end_exe();
                    opt = 3;
                    break;
                }
                calcu();
                opt = 3;
                if(end) resLat = zero;
                cal_end_exe();
                break;
            case R.id.btn_div:
                cal_exe();
                if(cal_click||!end) {
                    cal_end_exe();
                    opt = 4;
                    break;
                }
                calcu();
                opt = 4;
                if(end) resLat = zero;
                cal_end_exe();
                break;
            case R.id.btn0:
                equ_click = false;
                cal_click = false;
                if(!point)  resLat = resLat.multiply(ten);
                else deci++;
                x_exe = false;
                break;
            case R.id.btn1:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num);
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(1));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(1).multiply(m_one));
                }
                break;
            case R.id.btn2:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(2)));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(2));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(2)).multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(2).multiply(m_one));
                }
                break;
            case R.id.btn3:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(3)));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(3));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(3)).multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(3).multiply(m_one));
                }
                break;
            case R.id.btn4:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(4)));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(4));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(4)).multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(4).multiply(m_one));
                }
                break;
            case R.id.btn5:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(5)));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(5));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(5)).multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(5).multiply(m_one));
                }
                break;
            case R.id.btn6:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(6)));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(6));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(6)).multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(6).multiply(m_one));
                }
                break;
            case R.id.btn7:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(7)));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(7));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(7)).multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(7).multiply(m_one));
                }
                break;
            case R.id.btn8:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(8)));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(8));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(8)).multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(8).multiply(m_one));
                }
                break;
            case R.id.btn9:
                num_exe();
                if(resLat.compareTo(zero)>=0) {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(9)));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(9));
                }
                else {
                    if(point) resLat = resLat.add(num.multiply(BigDecimal.valueOf(9)).multiply(m_one));
                    else resLat = resLat.multiply(ten).add(BigDecimal.valueOf(9).multiply(m_one));
                }
                break;
            case R.id.btn_equal:
                if(!end) {
                    deci = 0;
                    equ_click = true;
                    point = false;
                    break;
                }
                if(cal_click&&resLat.compareTo(zero)==0) resLat = resFor;  //执行xx,1/x,sqrt....
                cal_click = true;

                x_exe = point = num_click = add_sub_cli = false;

                if(equ_click)
                {
                    resLat = saving_equ;
                    calcu();
                    resLat = zero;
                    break;
                }
                calcu();
                saving_equ = resLat;
                resLat = zero;
                equ_click = true;

                break;
            case R.id.btn_p:
                if (!point&&resLat.divideAndRemainder(one)[1].compareTo(zero)==0) point = true;
                else break;
                equ_click = false;
                saving = resLat.divideAndRemainder(one)[1];
                break;
            case R.id.btn_C:
                clear();
                break;
            case R.id.btn_CE:
                resLat = saving = zero;
                equ_click = err_sqrt = error = point = x_exe = add_sub_cli = num_click = false;
                showresult.setText("0");
                break;
            case R.id.btn_add_sub:
                if(num_click||x_exe) resLat = resLat.divide(BigDecimal.valueOf(-1));
                else resLat = resFor.divide(BigDecimal.valueOf(-1));
                add_sub_cli = true;
                break;

            case R.id.btn_xx:
                if(cal_click) resLat = saving.multiply(saving);
                else resLat = resLat.multiply(resLat);
                //add_sub_cli = false;
                saving = resLat;
                point = false;
                x_exe = true;
                break;
            case R.id.btn_1x:
                if(cal_click) {
                    if(resFor.compareTo(zero)==0) error = true;
                    if(saving.compareTo(zero)!=0) resLat = one.divide(saving,15,RoundingMode.HALF_EVEN);
                    else error = true;
                }
                else if(resLat.compareTo(zero)!=0)resLat = one.divide(resLat,15,RoundingMode.HALF_EVEN);
                else error = true;
                if(error) {
                    clear();
                    error = true;
                }
                add_sub_cli = point = false;
                saving = resLat;
                x_exe = true;

                break;
            case R.id.btn_sqrt:
                if(cal_click) {
                    if(saving.compareTo(zero)>=0) resLat = BigDecimal.valueOf(sqrt(saving.doubleValue()));
                    else err_sqrt = true;
                }
                else {
                    if(resLat.compareTo(BigDecimal.valueOf(0))>=0) resLat = BigDecimal.valueOf(sqrt(resLat.doubleValue()));
                    else err_sqrt = true;
                }
                saving = resLat;
                point = false;
                x_exe = true;
                //add_sub_cli = false;
                break;

            case R.id.btn_back:
                if(cal_click) break;
                if(point) {
                    if(deci==0) point = false;
                    else {
                        for(i = 0;i<deci;i++)
                            resLat = resLat.multiply(ten);
                        resLat = (resLat.subtract(resLat.divideAndRemainder(ten)[1])).divide(ten);
                        for(i = 0; i<deci-1; i++)
                            resLat = resLat.divide(ten);
                    }
                }
                else if(resLat.compareTo(ten)==-1&&resLat.compareTo(BigDecimal.valueOf(-10))==1) resLat = zero;
                else {
                    if(resLat.compareTo(zero)<=0) {
                        resLat = resLat.multiply(BigDecimal.valueOf(-1));
                        resLat = (resLat.subtract(resLat.divideAndRemainder(ten)[1])).divide(ten).multiply(BigDecimal.valueOf(-1));
                    }
                    else resLat = (resLat.subtract(resLat.divideAndRemainder(ten)[1])).divide(ten);
                }
                if(deci>0) deci--;

                break;
        }

        if(point||!cal_click||num_click||x_exe||add_sub_cli)
            trans = resLat.doubleValue();
        else
            trans = resFor.doubleValue();
        show_text = trans+"";

        if(trans%1==0&&(trans+"").indexOf('E')<0) show_text = (trans+"").substring(0,(trans+"").indexOf("."));
        if(point&&trans%1==0) show_text = show_text+".";

        if(point) {
            i = (show_text + "").indexOf(".");
            show_deci = (show_text + "").substring(i).length() - 1;
            for (; show_deci < deci; show_deci = (show_text + "").substring(i).length() - 1)
                show_text = show_text + "0";
        }
        else deci = 0;
        showresult.setText(show_text);
        if(error) showresult.setText("cannot divided by zero");
        if(err_sqrt) showresult.setText("invalid input");
    }
}