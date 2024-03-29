package hz.laboratory.com.cmy.blood_list;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;

import hz.laboratory.com.R;
import hz.laboratory.com.cmy.bigdata.BigDataUtil;

/**
 * @author cmy
 */
public class BloodListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Context context;
    private Group group;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_list);
        group = findViewById(R.id.noData);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(v -> {
            BigDataUtil.sendActionLog("10.2");
        });
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         