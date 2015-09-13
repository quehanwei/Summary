package mesh.csrmesh;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mesh.csrmesh.view.EditTextWithDel;
import mesh.csrmesh.view.GroupDialog;
import mesh.csrmesh.view.PercentRelativeLayout;

public class AddGroupActivity extends BaseActivity {

    private ImageView back;
    private EditTextWithDel groupname;
    private PercentRelativeLayout type;
    private TextView grouptype,cancel,done;

    private GroupDialog dialog;

    private boolean groupType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgroup);
        initFindViewById();
    }

    private void initFindViewById() {
        back = (ImageView) findViewById(R.id.back);
        groupname = (EditTextWithDel) findViewById(R.id.groupname);
        type = (PercentRelativeLayout) findViewById(R.id.type);
        grouptype= (TextView) findViewById(R.id.grouptype);
        cancel= (TextView) findViewById(R.id.cancel);
        done= (TextView) findViewById(R.id.done);

        back.setOnClickListener(this);
        type.setOnClickListener(this);
        cancel.setOnClickListener(this);
        done.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.cancel:
            case R.id.back:
                toastMakeText("关闭");
                finish();
                break;
            case R.id.type:
                if (dialog==null){
                    dialog=new GroupDialog(this, R.style.GroupDialog, new GroupDialog.GroupDialogListener() {
                        @Override
                        public void onClick(View v, int groups) {
                            if (v.getId()==R.id.cancel){
                                dialog.dismiss();
                            }else if (v.getId()==R.id.confirm){
                                groupType=groups==0?true:false;
                                grouptype.setText(groupType ? "Light Bulb Group" : "AC Plug Group");
                                dialog.dismiss();
                            }

                        }
                    });
                }
                dialog.show();
                break;
            case R.id.done:
                commitInfo();
                break;
        }
    }

    private void commitInfo() {
        String name=groupname.getText().toString().trim();
        if("".equals(name)) {
            toastMakeText("请输入分组名称");
            return;
        }
        toastMakeText("添加分组");
        finish();
    }
}
