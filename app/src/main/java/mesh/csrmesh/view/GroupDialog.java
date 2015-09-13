package mesh.csrmesh.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mesh.csrmesh.R;

public class GroupDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private GroupDialogListener listener;

    private RelativeLayout lightgroup,acgroup;
    private TextView lightgroupselect,acgroupselect,cancel,confirm;

    private int groups=0;

    public interface GroupDialogListener {
        public void onClick(View view, int groups);
    }

    public GroupDialog(Context context, int theme, GroupDialogListener listener) {
        super(context, theme);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.groupdialog);
        lightgroup= (RelativeLayout) findViewById(R.id.lightgroup);
        acgroup= (RelativeLayout) findViewById(R.id.acgroup);
        lightgroupselect= (TextView) findViewById(R.id.lightgroupselect);
        acgroupselect= (TextView) findViewById(R.id.acgroupselect);
        cancel= (TextView) findViewById(R.id.cancel);
        confirm= (TextView) findViewById(R.id.confirm);

        lightgroup.setOnClickListener(this);
        acgroup.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.lightgroup){
            groups=0;
            lightgroupselect.setBackground(context.getResources().getDrawable(R.drawable.dialog_selected));
            acgroupselect.setBackground(context.getResources().getDrawable(R.drawable.dialog_unselect));
        }else if(v.getId()==R.id.acgroup){
            groups=1;
            acgroupselect.setBackground(context.getResources().getDrawable(R.drawable.dialog_selected));
            lightgroupselect.setBackground(context.getResources().getDrawable(R.drawable.dialog_unselect));
        }
        listener.onClick(v,groups);
    }

}
