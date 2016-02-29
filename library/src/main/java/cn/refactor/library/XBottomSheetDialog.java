package cn.refactor.library;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作者 : andy
 * 日期 : 16/2/29 22:50
 * 邮箱 : andyxialm@gmail.com
 * 描述 : BottomSheetDialog with recyclerview
 */
public class XBottomSheetDialog {
    private Context mContext;
    private BottomSheetDialog mBottomSheet;

    private OnItemClickListener mListener;
    private String[] mDatas;

    public XBottomSheetDialog(Context context) {
        mContext = context;
        initBottomSheet();
        initRecyclerView();
    }

    public BottomSheetDialog getBottomSheetDialog() {
        return mBottomSheet;
    }

    /**
     * DEFAULT 默认列表 设置数据
     * @param datas String[]
     */
    public void setDatas(String[] datas) {
        mDatas = datas;
    }

    public void show() {
        mBottomSheet.show();
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mListener = l;
    }

    private void initBottomSheet() {
        mBottomSheet = new BottomSheetDialog(mContext);
    }

    private void setContentView(View contentView) {
        mBottomSheet.setContentView(contentView);
    }

    private void initRecyclerView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.sheet_dialog_recycler_view, null);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.content_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new ContentAdapter());
        this.setContentView(contentView);
    }

    private class ContentAdapter extends RecyclerView.Adapter<ContentHolder> {

        @Override
        public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, null);
            return new ContentHolder(contentView);
        }

        @Override
        public void onBindViewHolder(ContentHolder holder, final int position) {
            holder.tv.setText(mDatas[position]);
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener == null) return;
                    mListener.onClick(getBottomSheetDialog(), position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas == null ? 0 : mDatas.length;
        }
    }

    private static class ContentHolder extends RecyclerView.ViewHolder {
        public View parent;
        public TextView tv;
        public ContentHolder(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.item_parent);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    public interface OnItemClickListener {
        void onClick(BottomSheetDialog dialog, int postion);
    }
}
