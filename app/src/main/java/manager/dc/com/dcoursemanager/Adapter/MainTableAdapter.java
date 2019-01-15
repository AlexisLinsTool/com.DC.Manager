package manager.dc.com.dcoursemanager.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import manager.dc.com.dcoursemanager.OBJ.Course;
import manager.dc.com.dcoursemanager.OBJ.Courses;
import manager.dc.com.dcoursemanager.R;


public class MainTableAdapter extends BaseAdapter implements View.OnClickListener {

    List<Courses> mList;
    Context mContext;
    ViewHolder mHolder;
    MyClickListener myClickListener;

    public interface MyClickListener{
        void clickListener(View v);
    }

    @Override
    public void onClick(View v) {
        myClickListener.clickListener(v);
    }

    public MainTableAdapter(Context context, List<Courses> list,MyClickListener listener) {
        mList = list;
        mContext = context;
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

            view = LayoutInflater.from(mContext).inflate(R.layout.list_maintale_listview,viewGroup,false);

            mHolder.t0 = view.findViewById(R.id.MainTable_Protion);
            mHolder.t1 = view.findViewById(R.id.MainTable_Monday);
            mHolder.t2 = view.findViewById(R.id.MainTable_Tuesday);
            mHolder.t3 = view.findViewById(R.id.MainTable_Wednesday);
            mHolder.t4 = view.findViewById(R.id.MainTable_Thursday);
            mHolder.t5 = view.findViewById(R.id.MainTable_Friday);
            mHolder.t6 = view.findViewById(R.id.MainTable_Saturday);
            mHolder.t7 = view.findViewById(R.id.MainTable_Sunday);
            view.setTag(R.id.tag_Holder,mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag(R.id.tag_Holder);
        }
        view.setTag(R.id.tag_postion,position);
        mHolder.t0.setText(String.valueOf(position+1));
        mHolder.t1.setOnClickListener(this);
        mHolder.t1.setTag(R.id.tag_postion,position);
        mHolder.t2.setOnClickListener(this);
        mHolder.t2.setTag(R.id.tag_postion,position);
        mHolder.t3.setOnClickListener(this);
        mHolder.t3.setTag(R.id.tag_postion,position);
        mHolder.t4.setOnClickListener(this);
        mHolder.t4.setTag(R.id.tag_postion,position);
        mHolder.t5.setOnClickListener(this);
        mHolder.t5.setTag(R.id.tag_postion,position);
        mHolder.t6.setOnClickListener(this);
        mHolder.t6.setTag(R.id.tag_postion,position);
        mHolder.t7.setOnClickListener(this);
        mHolder.t7.setTag(R.id.tag_postion,position);
        setData(position);
        return view;
    }

    private void setData(int postion) {
        if (mList.get(postion).getW1() != null) {
            setDataAs(mHolder.t1,postion,mList.get(postion).getW1());
        }
        if (mList.get(postion).getW2() != null) {
            setDataAs(mHolder.t2,postion,mList.get(postion).getW2());
        }
        if (mList.get(postion).getW3() != null) {
            setDataAs(mHolder.t3,postion,mList.get(postion).getW3());
        }
        if (mList.get(postion).getW4() != null) {
            setDataAs(mHolder.t4,postion,mList.get(postion).getW4());
        }
        if (mList.get(postion).getW5() != null) {
            setDataAs(mHolder.t5,postion,mList.get(postion).getW5());
        }
        if (mList.get(postion).getW6() != null) {
            setDataAs(mHolder.t6,postion,mList.get(postion).getW6());
        }
        if (mList.get(postion).getW7() != null) {
            setDataAs(mHolder.t7,postion,mList.get(postion).getW7());
        }
    }

    @SuppressLint("ResourceAsColor")
    private void setDataAs(TextView view, int postion, Course course){
        view.setText(course.getCourseName());
        view.setBackgroundResource(R.mipmap.table_card1);
        view.setTag(R.id.tag_postion,postion);
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
