package mesh.csrmesh.view;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
	private List<View> mListView;
	public ViewPagerAdapter(List<View> mListView) {
		super();
		this.mListView = mListView;
	}

	// Destruction of the position position of the interface
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewGroup) arg0).removeView(mListView.get(arg1));
	}

	//Gets the current form interface number
	public int getCount() {
		return mListView.size();
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		((ViewGroup) arg0).addView(mListView.get(arg1), 0);
		return mListView.get(arg1);
	}
	//To determine whether the object is generated from the interface
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}
	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {}
	@Override
	public Parcelable saveState() {
		return null;
	}


}
