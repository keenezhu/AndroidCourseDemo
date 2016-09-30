package keene.demo.coursedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;


public class RecyclerViewScreen extends AppCompatActivity {
    private RecyclerView rv;
    private List<String> texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initTexts();
        rv = (RecyclerView) findViewById(R.id.recycler_list);
        rv.setAdapter(new CustomAdapter(this));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0,0,0,50);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initTexts() {

        texts = new ArrayList<String>();

        for (int i = 0; i < 20; i++) {
            texts.add("text::" + i);
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<ViewCache> {
        private Context c;

        public CustomAdapter(Context con) {
            c = con;
        }

        @Override
        public ViewCache onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(c);
            View itemView = li.inflate(R.layout.recycler_item, parent, false);
            ViewCache vc = new ViewCache(itemView);
            return vc;
        }

        @Override
        public void onBindViewHolder(ViewCache holder, final int position) {
            TextView tv = holder.tv;
            tv.setText(texts.get(position));
            Button delete = holder.b;
            final ViewCache vc=holder;
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=vc.getLayoutPosition();
                    texts.remove(pos);
                    notifyItemRemoved(pos);
                }
            });
        }

        @Override
        public int getItemCount() {
            return texts.size();
        }
    }

    public class ViewCache extends RecyclerView.ViewHolder {
        public TextView tv;
        public Button b;

        public ViewCache(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.recycler_text);
            b = (Button) itemView.findViewById(R.id.delete);
        }
    }
}
