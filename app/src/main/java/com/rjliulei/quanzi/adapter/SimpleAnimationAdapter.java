package com.rjliulei.quanzi.adapter;

import android.animation.Animator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.rjliulei.quanzi.R;
import com.rjliulei.quanzi.activity.AnalyticsApplication;
import com.rjliulei.quanzi.bean.UserPhoneNum;

import java.util.List;

public class SimpleAnimationAdapter extends UltimateViewAdapter<RecyclerView.ViewHolder> {
	private List<UserPhoneNum> list;

	public SimpleAnimationAdapter(List<UserPhoneNum> list) {
		this.list = list;
	}

	private int mDuration = 300;
	private Interpolator mInterpolator = new LinearInterpolator();

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		UserPhoneNum item = list.get(position);
		String name = item.getRealName();
		((ViewHolder) holder).tvPhone.setText(item.getPhoneNum());
		((ViewHolder) holder).tvName.setText(name);
		((ViewHolder) holder).tvComment.setText(item.getComment());

		TextDrawable myDrawable = TextDrawable.builder().beginConfig().textColor(Color.WHITE).useFont(Typeface.DEFAULT)
				.toUpperCase().endConfig().buildRound(name.substring(0, 1), ColorGenerator.MATERIAL.getRandomColor());
		((ViewHolder) holder).ivCircle.setImageDrawable(myDrawable);

		for (Animator anim : getAdapterAnimations(holder.itemView, AdapterAnimationType.SlideInBottom)) {
			anim.setDuration(mDuration).start();
			anim.setInterpolator(mInterpolator);
		}

	}

	@Override
	public int getAdapterItemCount() {
		return list.size();
	}

	@Override
	public RecyclerView.ViewHolder getViewHolder(View view) {
		return new UltimateRecyclerviewViewHolder(view);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_qz_circle, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	public void insert(UserPhoneNum string, int position) {
		insert(list, string, position);
	}

	public void remove(int position) {
		remove(list, position);
	}

	public void clear() {
		clear(list);
	}

	@Override
	public void toggleSelection(int pos) {
		super.toggleSelection(pos);
	}

	@Override
	public void setSelected(int pos) {
		super.setSelected(pos);
	}

	@Override
	public void clearSelection(int pos) {
		super.clearSelection(pos);
	}

	public void swapPositions(int from, int to) {
		swapPositions(list, from, to);
	}

	@Override
	public long generateHeaderId(int position) {
		// URLogs.d("position--" + position + "   " + getItem(position));
		// if (getItem(position).length() > 0)
		// return getItem(position).charAt(0);
		// else
		return -1;
	}

	@Override
	public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
		// View view = LayoutInflater.from(viewGroup.getContext())
		// .inflate(R.layout.stick_header_item, viewGroup, false);
		// return new RecyclerView.ViewHolder(view) {
		// };
		return null;
	}

	@Override
	public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

		// TextView textView = (TextView)
		// viewHolder.itemView.findViewById(R.id.stick_text);
		// textView.setText(String.valueOf(getItem(position).charAt(0)));
		// viewHolder.itemView.setBackgroundColor(Color.parseColor("#AAffffff"));
		// ImageView imageView = (ImageView)
		// viewHolder.itemView.findViewById(R.id.stick_img);
		//
		// SecureRandom imgGen = new SecureRandom();
		// switch (imgGen.nextInt(3)) {
		// case 0:
		// imageView.setImageResource(R.drawable.test_back1);
		// break;
		// case 1:
		// imageView.setImageResource(R.drawable.test_back2);
		// break;
		// case 2:
		// imageView.setImageResource(R.drawable.test_back);
		// break;
		// }

	}

	class ViewHolder extends UltimateRecyclerviewViewHolder implements View.OnClickListener {

		TextView tvName;
		ImageView ivCircle;
		TextView tvPhone;
		TextView tvComment;
		ImageView ivPhone;
		ImageView ivSms;

		public ViewHolder(View itemView) {
			super(itemView);

			tvName = (TextView) itemView.findViewById(R.id.tv_name);
			tvPhone = (TextView) itemView.findViewById(R.id.tv_number);
			tvComment = (TextView) itemView.findViewById(R.id.tv_comment);
			ivCircle = (ImageView) itemView.findViewById(R.id.iv_circle);
			ivPhone = (ImageView) itemView.findViewById(R.id.iv_phone);
			ivSms = (ImageView) itemView.findViewById(R.id.iv_sms);

			// itemView.setOnClickListener(this);

			ivPhone.setOnClickListener(this);
			ivSms.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {

			UserPhoneNum item = list.get(this.getAdapterPosition());

			switch (v.getId()) {
			case R.id.iv_phone:

				Toast.makeText(AnalyticsApplication.getInstance(), item.getRealName(), Toast.LENGTH_SHORT).show();
				break;
			case R.id.iv_sms:
				break;
			}
		}
	}

	//
	// public String getItem(int position) {
	// if (customHeaderView != null)
	// position--;
	// if (position < list.size())
	// return list.get(position);
	// else return "";
	// }

}
