package mesh.csrmesh.entities;

public class LightBulbModel {
    private boolean selected;
    private String name;

    public LightBulbModel(boolean select,String name){
        this.selected=select;
        this.name=name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
