package mesh.csrmesh;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class SetUpActivity extends BaseActivity{
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initFindViewById();
    }

    private void initFindViewById() {
        back= (ImageView) findViewById(R.id.back);

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
            this.finish();
            break;
        }
    }
}
