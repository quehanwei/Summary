package mesh.csrmesh.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mesh.csrmesh.R;
import mesh.csrmesh.entities.LightBulbModel;

public class LightBulbAdapter extends BaseAdapter {

    private Context context;
    private List<LightBulbModel> lightBulbModels;
    public LightBulbAdapter(Context context,List<LightBulbModel> lightBulbModels) {
        this.lightBulbModels=lightBulbModels;
        this.context=context;
    }

    @Override
    public int getCount() {
        return lightBulbModels.size();
    }

    @Override
    public Object getItem(int position) {
        return lightBulbModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LightBulbModel model=lightBulbModels.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lightbulb, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.selected = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(model.isSelected()){
            holder.selected.setBackground(context.getResources().getDrawable(R.drawable.light_on));
            holder.selected.setTextColor(Color.WHITE);
            holder.selected.setText("ON");
        }else{
            holder.selected.setBackground(context.getResources().getDrawable(R.drawable.light_off));
            holder.selected.setTextColor(Color.parseColor("#EFC94D"));
            holder.selected.setText("OFF");
        }
        holder.name.setText(model.getName());;
        return convertView;
    }

    class ViewHolder {
        public TextView name;
        public TextView selected;
    }
}
