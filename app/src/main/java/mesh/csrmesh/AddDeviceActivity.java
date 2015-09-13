package mesh.csrmesh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddDeviceActivity extends BaseActivity {
    private ImageView back;
    private TextView done;
    private TextView selectall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvice);
        initFindViewById();
    }

    private void initFindViewById() {
        back= (ImageView) findViewById(R.id.back);
        done= (TextView) findViewById(R.id.done);
        selectall= (TextView) findViewById(R.id.selectall);

        back.setOnClickListener(this);
        done.setOnClickListener(this);
        selectall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.done:
                Intent intent=new Intent(this,LightBulbActivity.class);
                startActivity(intent);
                break;
        }
    }
}
