package mesh.csrmesh;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NetWorkKeyActivity extends BaseActivity {

    private ImageView back;
    private TextView nextstep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        initFindViewById();
    }

    private void initFindViewById() {
        back = (ImageView) findViewById(R.id.back);
        nextstep = (TextView) findViewById(R.id.nextstep);

        back.setOnClickListener(this);
        nextstep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.nextstep:
                Intent intent = new Intent(this, AddDeviceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
