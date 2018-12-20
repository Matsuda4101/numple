package i.keiji.numple3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int i,j,k=1;

    protected Button spinnerButton;
    protected AlertDialog alertDialog;
    protected ArrayAdapter<String> adapter;
    protected int selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice);
        adapter.add("");
        adapter.add("1");
        adapter.add("2");
        adapter.add("3");
        adapter.add("4");
        adapter.add("5");
        adapter.add("6");
        adapter.add("7");
        adapter.add("8");
        adapter.add("9");

        // データ生成
        makebutton();


        // 処理開始イベント
        Button startbutton = (Button) findViewById(R.id.start);
        //スタートボタンに OnClickListener インターフェースを実装する
        startbutton.setOnClickListener(new View.OnClickListener() {

            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "処理を開始します", Toast.LENGTH_LONG).show();
            }
        });


        //リセットイベント
         Button clearbutton = (Button) findViewById(R.id.clear);
        //リセットボタンにOnClickListener インターフェースを実装する
        clearbutton.setOnClickListener(new View.OnClickListener() {

            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {
                // コンテンツ部分のLayoutを取ってくる
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.numple);
                // 内容を全部消す
                linearLayout.removeAllViews();
                makebutton();
                Toast.makeText(MainActivity.this, "リセットしました", Toast.LENGTH_LONG).show();
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // AlertDialogで選択肢を表示
            spinnerButton = (Button) v;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("数値を選択してください");
            builder.setSingleChoiceItems(adapter, selectedIndex, onDialogClickListener);
            alertDialog = builder.create();
            alertDialog.show();
        }
    };
    private DialogInterface.OnClickListener onDialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialogで選択された内容を保持
            Log.d("tag", spinnerButton.getTag().toString());
            spinnerButton.setText(adapter.getItem(which));
            alertDialog.dismiss();
        }
    };
    private void makebutton(){
        for (i = 1; i <= 9; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (j = 1; j<= 9; j++) {
                Button button = new Button(this);
                button.setTag(k);
                button.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                button.setOnClickListener(onClickListener);
                spinnerButton = button;
                linearLayout.addView(button);
                k++;
            }
            LinearLayout liner = findViewById(R.id.numple);
            liner.addView(linearLayout);
        }
        k=0;
    }
}