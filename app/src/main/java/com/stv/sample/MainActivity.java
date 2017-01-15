package com.stv.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.devilist.selectabletextview.SelectableTextView;
import com.devilist.selectabletextview.StringContentUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener,
        SelectableTextView.CustomActionMenuCallBack {

    private RadioGroup rg_text_gravity;
    private RadioGroup rg_text_content;

    private SelectableTextView selectableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {


        selectableTextView = (SelectableTextView) findViewById(R.id.ctv_content);
        selectableTextView.setText(Html.fromHtml(StringContentUtil.str_hanzi).toString());
        selectableTextView.clearFocus();
        selectableTextView.setCustomActionMenuCallBack(this);
        selectableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "SelectableTextView 的onClick事件", Toast.LENGTH_SHORT).show();
            }
        });

        rg_text_gravity = (RadioGroup) findViewById(R.id.rg_text_gravity);
        rg_text_content = (RadioGroup) findViewById(R.id.rg_text_content);
        ((RadioButton)findViewById(R.id.rb_justify)).setChecked(true);
        ((RadioButton)findViewById(R.id.rb_hanzi)).setChecked(true);
        rg_text_gravity.setOnCheckedChangeListener(this);
        rg_text_content.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_justify:
                selectableTextView.setTextJustify(true);
                selectableTextView.postInvalidate();
                break;
            case R.id.rb_left:
                selectableTextView.setTextJustify(false);
                selectableTextView.postInvalidate();
                break;
            case R.id.rb_hanzi:
                selectableTextView.setText(Html.fromHtml(StringContentUtil.str_hanzi).toString());
                selectableTextView.postInvalidate();
                break;
            case R.id.rb_en:
                selectableTextView.setText(Html.fromHtml(StringContentUtil.str_en).toString());
                selectableTextView.postInvalidate();
                break;
            case R.id.rb_muti:
                selectableTextView.setText(Html.fromHtml(StringContentUtil.str_muti).toString());
                selectableTextView.postInvalidate();
                break;
        }

    }

    @Override
    public boolean onCreateCustomActionMenu(SelectableTextView.ActionMenu menu) {
        menu.setActionMenuBgColor(0xbb000000);
        menu.setMenuItemTextColor(0xffffffff);
        List<String> titleList = new ArrayList<>();
        titleList.add("翻译");
        titleList.add("分享");
        menu.addCustomMenuItem(titleList);
        return false;
    }

    @Override
    public void onCustomActionItemClicked(String itemTitle, String selectedContent) {
        if (itemTitle.equals("翻译")) {
            Toast.makeText(MainActivity.this, "翻译", Toast.LENGTH_SHORT).show();
        }
        if (itemTitle.equals("分享")) {
            Toast.makeText(MainActivity.this, "分享", Toast.LENGTH_SHORT).show();
        }
    }

}
