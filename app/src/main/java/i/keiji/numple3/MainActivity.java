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
        adapter.add("1");
        adapter.add("2");
        adapter.add("3");
        adapter.add("4");
        adapter.add("5");
        adapter.add("6");
        adapter.add("7");
        adapter.add("8");
        adapter.add("9");
        adapter.add("");

        // データ生成
        for (int j = 1; j <= 9; j++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 1; i <= 9; i++) {
                Button button = new Button(this);
                button.setTag(String.valueOf(j+":"+i));
                button.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                button.setOnClickListener(onClickListener);
                spinnerButton = button;
                linearLayout.addView(button);
            }
            LinearLayout liner = findViewById(R.id.numple);
            liner.addView(linearLayout);
        }

        // クリックイベントを取得したいボタン
        Button button = (Button) findViewById(R.id.start);

        // ボタンに OnClickListener インターフェースを実装する
        button.setOnClickListener(new View.OnClickListener() {

            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "処理を開始します", Toast.LENGTH_LONG).show();
            }
        });
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // AlertDialogで選択肢を表示
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
            selectedIndex = which;
            Log.d("tag", spinnerButton.getTag().toString());
            spinnerButton.setText(adapter.getItem(which));
            alertDialog.dismiss();
        }
    };
}