package manager.dc.com.dcoursemanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import manager.dc.com.dcoursemanager.OBJ.Courses;
import manager.dc.com.dcoursemanager.R;


public class MainTableAdapter extends BaseAdapter implements View.OnClickListener {

    List<Courses> mList;
    private LayoutInflater mInflater;
    Context mContext;
    ViewHolder mHolder;
    MyClickListener myClickListener;

    public interface MyClickListener{
        public void clickListener(View v);
    }

    @Override
    public void onClick(View v) {
        myClickListener.clickListener(v);
    }

    public MainTableAdapter(Context context, List<Courses> list,MyClickListener listener) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        myClickListener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        mHolder = new ViewHolder();
        if (view == null) {
            view = View.inflate(mContext,R.layout.fragment_maintale_listview, null);

            mHolder.t0 = view.findViewById(R.id.MainTable_Protion);
            mHolder.t1 = view.findViewById(R.id.MainTable_Monday);
            mHolder.t2 = view.findViewById(R.id.MainTable_Tuesday);
            mHolder.t3 = view.findViewById(R.id.MainTable_Wednesday);
            mHolder.t4 = view.findViewById(R.id.MainTable_Thursday);
            mHolder.t5 = view.findViewById(R.id.MainTable_Friday);
            mHolder.t6 = view.findViewById(R.id.MainTable_Saturday);
            mHolder.t7 = view.findViewById(R.id.MainTable_Sunday);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.t0.setText(position);
        mHolder.t1.setOnClickListener(this);
        mHolder.t2.setOnClickListener(this);
        mHolder.t3.setOnClickListener(this);
        mHolder.t4.setOnClickListener(this);
        mHolder.t5.setOnClickListener(this);
        mHolder.t6.setOnClickListener(this);
        mHolder.t7.setOnClickListener(this);
        setData(position);
        return view;
    }

    private void setData(int postion) {
        if (mList.get(postion).getW1() != null) {
            mHolder.t1.setText(mList.get(postion).getW1().getCourseName());
            mHolder.t1.setTag(postion);
        }
        if (mList.get(postion).getW2() != null) {
            mHolder.t2.setText(mList.get(postion).getW2().getCourseName());
            mHolder.t1.setTag(postion);
        }
        if (mList.get(postion).getW3() != null) {
            mHolder.t3.setText(mList.get(postion).getW3().getCourseName());
            mHolder.t1.setTag(postion);
        }
        if (mList.get(postion).getW4() != null) {
            mHolder.t4.setText(mList.get(postion).getW4().getCourseName());
            mHolder.t1.setTag(postion);
        }
        if (mList.get(postion).getW5() != null) {
            mHolder.t5.setText(mList.get(postion).getW5().getCourseName());
            mHolder.t1.setTag(postion);
        }
        if (mList.get(postion).getW6() != null) {
            mHolder.t6.setText(mList.get(postion).getW6().getCourseName());
            mHolder.t1.setTag(postion);
        }
        if (mList.get(postion).getW7() != null) {
            mHolder.t7.setText(mList.get(postion).getW7().getCourseName());
            mHolder.t1.setTag(postion);
        }
    }



    class ViewHolder {
        TextView t0;
        TextView t1;
        TextView t2;
        TextView t3;
        TextView t4;
        TextView t5;
        TextView t6;
        TextView t7;
    }


}
