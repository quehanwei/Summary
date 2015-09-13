package mesh.csrmesh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import mesh.csrmesh.view.MeshTextView;


public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";
    private ImageView setting;
    private MeshTextView homemesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFindViewById();
    }

    public void initFindViewById() {
        setting = (ImageView) findViewById(R.id.setting);
        homemesh = (MeshTextView) findViewById(R.id.homemesh);
        setting.setOnClickListener(this);
        homemesh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.setting:
                intent.setClass(MainActivity.this, SetUpActivity.class);
                break;
            case R.id.homemesh:
                intent.setClass(MainActivity.this, NetWorkKeyActivity.class);
                break;
        }
        startActivity(intent);
    }

}
