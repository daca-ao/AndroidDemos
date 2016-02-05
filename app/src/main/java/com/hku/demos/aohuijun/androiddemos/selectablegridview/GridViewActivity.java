package com.hku.demos.aohuijun.androiddemos.selectablegridview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hku.demos.aohuijun.androiddemos.R;
import com.hku.demos.aohuijun.androiddemos.appscenter.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridViewActivity extends Activity
        implements AdapterView.OnItemClickListener, AbsListView.MultiChoiceModeListener {

    private static final int TYPE_GRID = 0;
    private static final int MENU_ALL = 0;
    private static final int MENU_CLEAR = 1;
    private static final int GRID_COUNT = 66;

    private GridView mGridView;
    private SelectableGridViewAdapter mAdapter;
    private ArrayList<Item> mGridItemList;
    private String[] mColorArray;
    private TextView mSelectedCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        mGridItemList = new ArrayList<>();
        mColorArray = getResources().getStringArray(R.array.color_array);
        Random random = new Random();
        for (int i = 0; i < GRID_COUNT; i++) {
            Item mGridItem = new Item();
            mGridItem.setType(TYPE_GRID);
            mGridItem.setData(i);
            mGridItem.setGroup(Color.parseColor(mColorArray[random.nextInt(mColorArray.length)]));
            mGridItem.setIsChosen(false);
            mGridItemList.add(mGridItem);
        }
        mAdapter = new SelectableGridViewAdapter(mGridItemList);
        mGridView = (GridView) findViewById(R.id.selectable_grid_view);
        mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
        mGridView.setMultiChoiceModeListener(this);
    }

    //  Override method of AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),
                getResources().getString(R.string.menu_select_count),
                Toast.LENGTH_SHORT).show();
    }

    //  Override methods of AbsListView.MultiChoiceModeListener
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        Item item = (Item) mAdapter.getItem(position);
        item.setIsChosen(checked);
        if (item.isChosen()) {
            mAdapter.increaseCount();
        } else {
            mAdapter.decreaseCount();
        }
        mAdapter.notifyDataSetChanged();
        mSelectedCountView.setText(formatString(mAdapter.getSelectedCount()));
        mode.invalidate();
    }

    private String formatString(int count) {
        return String.format(getResources().getString(R.string.menu_select_count), count);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        View v = LayoutInflater.from(this).inflate(R.layout.item_selectable_grid_view_count, null);
        mSelectedCountView = (TextView) v.findViewById(R.id.list_view_selected_count);
        mSelectedCountView.setText(formatString(mAdapter.getSelectedCount()));
        mode.setCustomView(v);
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        menu.getItem(MENU_ALL).setEnabled(mAdapter.getSelectedCount() != mGridItemList.size());
        mAdapter.setDisplayStatus(true);
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list_view_select:
                for (int i = 0; i < mGridView.getCount(); i++) {
                    if (!mGridView.isItemChecked(i))
                        mGridView.setItemChecked(i, true);
                }
                mSelectedCountView.setText(formatString(mAdapter.getSelectedCount()));
                mode.invalidate();
                break;
            case R.id.menu_list_view_un_select:
                for (int i = 0; i < mGridView.getCount(); i++) {
                    if (mGridView.isItemChecked(i))
                        mGridView.setItemChecked(i, false);
                }
                break;
            default:
                break;
        }
        mAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mAdapter.setAllSelected(false);
        mAdapter.setDisplayStatus(false);
        mAdapter.notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView gridTextView;
        public ImageView gridImageView;
        public RelativeLayout gridLayout;
    }

    private class SelectableGridViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<Item> mList;
        private boolean mDisplayStatus = false;
        private int mSelectedCount;

        public SelectableGridViewAdapter(List<Item> list) {
            this.mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mList = list;
        }

        /**
         * Set display status
         *
         * @param status
         */
        public void setDisplayStatus(boolean status) {
            this.mDisplayStatus = status;
        }

        /**
         * Set selected items displayable
         *
         * @param isDisplayed
         */
        public void setAllSelected(boolean isDisplayed) {
            for (int i = 0; i < mList.size(); i++) {
                Item item = (Item) mAdapter.getItem(i);
                item.setIsChosen(isDisplayed);
                if (!mGridView.isItemChecked(i) && isDisplayed) {
                    increaseCount();
                } else if (mGridView.isItemChecked(i) && !isDisplayed) {
                    decreaseCount();
                }
            }
        }

        private int increaseCount() {
            mSelectedCount += 1;
            if (mSelectedCount >= mList.size())
                mSelectedCount = mList.size();
            return mSelectedCount;
        }

        private int decreaseCount() {
            mSelectedCount -= 1;
            if (mSelectedCount < 0)
                mSelectedCount = 0;
            return mSelectedCount;
        }

        public int getSelectedCount() {
            return mSelectedCount;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_grid_view, null);
                holder.gridTextView = (TextView) convertView.findViewById(R.id.item_grid_text);
                holder.gridImageView = (ImageView) convertView.findViewById(R.id.item_grid_selector);
                holder.gridLayout = (RelativeLayout) convertView.findViewById(R.id.item_grid_layout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.gridLayout.setBackgroundColor(mList.get(position).getGroup());
            holder.gridTextView.setTextColor(mList.get(position).getGroup() == Color.BLACK ?
                    Color.WHITE : Color.BLACK);
            holder.gridTextView.setText(getResources().getString(R.string.grid_position)
                                        + " " + getItemId(position));
            if (mDisplayStatus) {
                holder.gridImageView.setVisibility(View.VISIBLE);
                holder.gridImageView.setImageResource(((Item) this.getItem(position)).isChosen() ?
                        R.drawable.ic_selector_selected : R.drawable.ic_selector_un_selected);
            } else {
                holder.gridImageView.setVisibility(View.INVISIBLE);
            }
            return convertView;
        }
    }
}
