package mesh.csrmesh;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;


public class BaseActivity extends Activity implements View.OnClickListener{

    @Override
    public void onClick(View v) {

    }


    public void toastMakeText(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
