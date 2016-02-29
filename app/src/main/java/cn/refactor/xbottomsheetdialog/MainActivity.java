package cn.refactor.xbottomsheetdialog;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cn.refactor.library.XBottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void showBottomSheetDialog(View v) {
        String[] datas = getDatas();
        XBottomSheetDialog dialog = new XBottomSheetDialog(this);
        dialog.setDatas(datas);
        dialog.setOnItemClickListener(new XBottomSheetDialog.OnItemClickListener() {
            @Override
            public void onClick(BottomSheetDialog dialog, int postion) {
                Toast.makeText(MainActivity.this, String.valueOf(postion), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public String[] getDatas() {
        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 0; i < 30; i ++) {
            dataList.add(String.valueOf(getString(R.string.text) + i));
        }
        return dataList.toArray(new String[dataList.size()]);
    }
}
