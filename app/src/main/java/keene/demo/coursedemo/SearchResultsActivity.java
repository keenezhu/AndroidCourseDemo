package keene.demo.coursedemo;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultsActivity extends AppCompatActivity {
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        result=(TextView)findViewById(R.id.result);
        Intent searchData=getIntent();
        if (searchData!=null){
            String action=searchData.getAction();
            if(action.equals(Intent.ACTION_SEARCH)){
                result.setText(searchData.getStringExtra(SearchManager.QUERY));
            }
        }
    }
}
