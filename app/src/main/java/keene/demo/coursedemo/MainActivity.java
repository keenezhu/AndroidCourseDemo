package keene.demo.coursedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * for entrance of this project
 * @version 1.0
 * @author keene 
 */
public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_VERIFY = 1;
    public static final int REQUEST_SINGOUT = 2;
    private TextView dataShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataShow = (TextView) findViewById(R.id.title_text);
    }
/**
 * for handling button click events
 *
 * @author keene
 * @version 1.0
 * @param v
 * @return void
*/
    public void onClickButton(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.data_pass: {
                onClickDataPass();
                break;
            }
            case R.id.menu_test: {
                Intent nextPage = new Intent(this, MenuActivity.class);
                startActivity(nextPage);
                break;
            }
            case R.id.listview_adapter: {
                Intent nextPage = new Intent(this, ListScreen.class);
                startActivity(nextPage);
                break;
            }
            case R.id.recycler_view: {
                Intent nextPage = new Intent(this, RecyclerViewScreen.class);
                startActivity(nextPage);
                break;
            }
            case R.id.service_button: {
                Intent nextPage = new Intent(this, ServiceTestScreen.class);
                startActivity(nextPage);
                break;
            }
        }
    }

    private void onClickDataPass() {
        Intent nextPage = new Intent(this, DataPassActivity.class);
        startActivityForResult(nextPage, REQUEST_VERIFY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VERIFY && resultCode == DataPassActivity.RESULT_BACK) {
            DataModel dataModel = (DataModel) data.getSerializableExtra("input_data");
            dataShow.setText(dataModel.getData());
        }
    }
}
