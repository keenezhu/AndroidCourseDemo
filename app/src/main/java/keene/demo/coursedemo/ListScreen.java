package keene.demo.coursedemo;

import android.content.ContentResolver;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ListScreen extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
        dataSet = initDataSet();
        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new CustomAdapter());
        listView.setOnItemClickListener(this);
    }

    private SimpleAdapter getSimpleAdapter() {
        SimpleAdapter sa = null;
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> itemData = new HashMap<String, Object>();
            itemData.put("text", "item" + i);
            if (i % 2 == 0) {
                itemData.put("image", R.drawable.ic_share_black_24dp);
            } else {
                itemData.put("image", R.drawable.ic_mode_edit_black_24dp);
            }
            data.add(itemData);

        }

        sa = new SimpleAdapter(this, data, R.layout.list_item_layout,
                new String[]{"text", "image"}, new int[]{R.id.item_text, R.id.item_img});

        return sa;
    }


    @Override
    public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
           String text=(String) listView.getItemAtPosition(position);
           Toast.makeText(this,text+id,Toast.LENGTH_SHORT).show();
    }

    private List<HashMap<String, Object>> dataSet;

    private List<HashMap<String, Object>> initDataSet() {
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> itemData = new HashMap<String, Object>();
            itemData.put("text", "item" + i);
            if (i % 2 == 0) {
                itemData.put("image", R.drawable.ic_share_black_24dp);
            } else {
                itemData.put("image", R.drawable.ic_mode_edit_black_24dp);
            }
            data.add(itemData);

        }
        return data;
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataSet.size();
        }

        @Override
        public Object getItem(int position) {
            return dataSet.get(position).get("text");
        }

        @Override
        public long getItemId(int position) {
            return position * 2;
        }

        final class ViewCache {
            TextView tv;
            Button button;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewCache vc;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_layout, null);
                vc = new ViewCache();
                vc.tv = (TextView) convertView.findViewById(R.id.item_text);
                vc.button = (Button) convertView.findViewById(R.id.item_button);
                convertView.setTag(vc);

            } else {
                vc=(ViewCache) convertView.getTag();
            }
            vc.tv.setText((String)dataSet.get(position).get("text"));
            vc.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataSet.remove(position);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }


}
