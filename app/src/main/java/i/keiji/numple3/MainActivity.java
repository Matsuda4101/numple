package i.keiji.numple3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // strings.xmlから配列読み込んでadapterに適用
        adapter = ArrayAdapter.createFromResource(this, R.array.select_array, android.R.layout.simple_list_item_single_choice);

        // データ生成
        makeButton();

        // 処理開始イベント
        Button startButton = findViewById(R.id.start_button);
        //スタートボタンに OnClickListener インターフェースを実装する
        startButton.setOnClickListener(new View.OnClickListener() {

            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {
                //TODO ナンプレ解答処理の追加
                Toast.makeText(MainActivity.this, "処理を開始します", Toast.LENGTH_LONG).show();
            }
        });


        //リセットイベント
        Button clearButton = findViewById(R.id.clear_button);
        //リセットボタンにOnClickListener インターフェースを実装する
        clearButton.setOnClickListener(new View.OnClickListener() {

            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {
                // コンテンツ部分のLayoutを取ってくる
                LinearLayout linearLayout = findViewById(R.id.numple_layout);
                // 内容を全部消す
                linearLayout.removeAllViews();
                makeButton();
                Toast.makeText(MainActivity.this, "リセットしました", Toast.LENGTH_LONG).show();
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            // AlertDialogで選択肢を表示
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("数値を選択してください");

            builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("tag", v.getTag().toString());
                    ((Button) v).setText(adapter.getItem(which));
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    };

    private void makeButton() {
        int buttonTagNum = 1;
        for (int i = 1; i <= 9; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 1; j <= 9; j++) {
                Button button = new Button(this);
                button.setTag(buttonTagNum);
                button.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                button.setOnClickListener(onClickListener);
                linearLayout.addView(button);
                buttonTagNum++;
            }
            LinearLayout layout = findViewById(R.id.numple_layout);
            layout.addView(linearLayout);
        }
    }
}