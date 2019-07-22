package com.example.doulai.demo;

/**
 * 作者：CMCC-ZHENGCHENG on 2016/11/13 21:42
 * 描述：
 * 包名：com.cmcc.inspace.adapters
 */
import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;




import java.util.ArrayList;
import java.util.List;

/**
 * 通用的Adapter，实现了getCount、getItem等方法,以及封装了getView的逻辑
 */
public abstract class CommonAdapter<T, H extends ViewParser> extends BaseAdapter {
    protected Context mContext = null;
    protected final List<T> mDataSet = new ArrayList<T>();

    public CommonAdapter(Context context) {
        this.mContext = context;
    }

    public int getCount() {
        return mDataSet.size();
    }

    public T getItem(int position) {
        return mDataSet.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    /**
     * @param t
     */
    public void addToFirst(T t) {
        mDataSet.add(0, t);
        notifyDataSetChanged();
    }

    /**
     * @param positon
     */
    public void deletePosition(int positon){
        mDataSet.remove(positon);
        notifyDataSetChanged();
    }

    /**
     * @param t
     */
    public void addToFirst(List<T> t) {
        mDataSet.addAll(t);
        notifyDataSetChanged();
    }

    /**
     * @param t
     */
    public void removeItem(T t) {
        if (t != null) {
            mDataSet.remove(t);
            notifyDataSetChanged();
        }
    }

    public void removeAll(){
        if(isListEmpty(mDataSet)){
            return;
        }
        for(T t : mDataSet){
            if(t != null) {
                mDataSet.remove(t);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * @return
     */
    public List<T> getDataSource() {
        return mDataSet;
    }

    /**
     * @param lists
     */
    public void updateListViewData(List<T> lists) {
        mDataSet.clear();
        if (!isListEmpty(lists)) {
            mDataSet.addAll(lists);

        }
        notifyDataSetChanged();
    }

    /**
     * @param t
     */
    public void addData(T t) {
        if (t != null) {
            mDataSet.add(t);
            notifyDataSetChanged();
        }
    }

    /**
     * @param lists
     */
    public void addData(List<T> lists) {
        if (!isListEmpty(lists)) {
            mDataSet.addAll(lists);
            notifyDataSetChanged();
        }
    }
    public static boolean isListEmpty(List<?> list){
        return list == null || list.isEmpty();
    }
    /**
     * @param newDatas
     */
    public void addDatasOnly(List<T> newDatas) {
        mDataSet.addAll(newDatas);
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = createViewHolder().inflate(mContext, parent, false);
        }

        H holder = (H) view.getTag();
        setItemData(position, holder, view);
        return view;
    }

    /**
     * &#x521b;&#x5efa;ViewHolder
     *
     * @return
     */
    protected abstract H createViewHolder();

    /**
     * 设置每项数据到View上
     *
     * @param position Item索引
     * @param viewHolder ViewHolder
     * @param rootView 根视图
     */
    protected abstract void setItemData(int position, H viewHolder, View rootView);

    /**
     * @param observer
     */
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if(observer != null ){
            super.unregisterDataSetObserver(observer);
        }

    }
}
