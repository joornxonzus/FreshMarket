package com.breezelin.freshmarket.adpaters;

/**
 * Created by Breeze Lin
 * 15:01 2016/5/2
 * 1linyufeng1@gmail.com
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.breezelin.freshmarket.R;
import com.breezelin.freshmarket.entities.HomeContent;
import com.breezelin.freshmarket.entities.HomeRsp;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 主页内容列表的适配器
 */
public class HomeContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	/**
	 * 当前列表要显示的内容
	 */
	private HomeRsp contents;
	/**
	 * 操作数据用的context
	 */
	private Context context;
	/**
	 * 内容监听
	 */
	private HomeContentListener listener;

	/**
	 * 主页内容列表适配器
	 *
	 * @param context 上下文
	 */
	public HomeContentAdapter(Context context, HomeContentListener listener) {
		this.context = context;
		this.listener = listener;
	}

	/**
	 * 设置列表需要显示的内容
	 *
	 * @param contents 主页内容
	 */
	public void setContents(HomeRsp contents) {
		this.contents = contents;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RecyclerView.ViewHolder holder;
		switch (viewType) {
			case 0:
				holder = new BannerViewHolder(new View(context));
				break;
			case 1:
				holder = new ShortCutsViewHolder(new View(context));
				break;
			case 2:
				holder = new Group1ViewHolder(new View(context));
				break;
			case 3:
				holder = new Group2ViewHolder(new View(context));
				break;
			case 4:
				holder = new Group3ViewHolder(new View(context));
				break;
			case 5:
				holder = new Group4ViewHolder(new View(context));
				break;
			case 6:
				holder = new Group5ViewHolder(new View(context));
				break;
			default:
				holder = new Group6ViewHolder(LayoutInflater.from(context)
						.inflate(R.layout.item_home_content, parent, false));
				break;
		}
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		switch (position) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				List<HomeContent> items = contents.getGroup6().getItems();
				if (items != null && items.size() > 0) {
					((Group6ViewHolder) holder).setData(items.get(position - 7));
				}
				break;
		}
	}

	@Override
	public int getItemCount() {
		int ret = 0;
		if (contents != null) {
			if (contents.getBanners() != null) {
				ret++;
			}
			if (contents.getShortcutIcons() != null) {
				ret++;
			}
			if (contents.getGroup1() != null) {
				ret++;
			}
			if (contents.getGroup2() != null) {
				ret++;
			}
			if (contents.getGroup3() != null) {
				ret++;
			}
			if (contents.getGroup4() != null) {
				ret++;
			}
			if (contents.getGroup5() != null) {
				ret++;
			}
			if (contents.getGroup6() != null) {
				ret += contents.getGroup6().getItems().size();
			}
		}
		return ret;
	}

	@Override
	public int getItemViewType(int position) {
		if (position < 7) {
			return position;
		} else {
			return 7;
		}
	}

	/**
	 * 通用视图容器，所哟视图容器的父类
	 */
	private class CommonViewHolder extends RecyclerView.ViewHolder {
		public CommonViewHolder(View itemView) {
			super(itemView);
		}
	}

	/**
	 * 横幅广告视图容器
	 */
	private class BannerViewHolder extends CommonViewHolder {
		public BannerViewHolder(View itemView) {
			super(itemView);
		}
	}

	/**
	 * 四个小按钮视图容器
	 */
	private class ShortCutsViewHolder extends CommonViewHolder {
		public ShortCutsViewHolder(View itemView) {
			super(itemView);
		}
	}

	/**
	 * 一组内容视图容器
	 */
	private class Group1ViewHolder extends CommonViewHolder {
		public Group1ViewHolder(View itemView) {
			super(itemView);
		}
	}

	/**
	 * 二组内容视图容器
	 */
	private class Group2ViewHolder extends CommonViewHolder {
		public Group2ViewHolder(View itemView) {
			super(itemView);
		}
	}

	/**
	 * 三组内容视图容器
	 */
	private class Group3ViewHolder extends CommonViewHolder {
		public Group3ViewHolder(View itemView) {
			super(itemView);
		}
	}

	/**
	 * 四组内容视图容器
	 */
	private class Group4ViewHolder extends CommonViewHolder {
		public Group4ViewHolder(View itemView) {
			super(itemView);
		}
	}

	/**
	 * 五组内容视图容器
	 */
	private class Group5ViewHolder extends CommonViewHolder {
		public Group5ViewHolder(View itemView) {
			super(itemView);
		}
	}

	/**
	 * 六组内容视图容器
	 */
	private class Group6ViewHolder extends CommonViewHolder {

		/**
		 * 当前条目的内容
		 */
		private HomeContent content;
		/**
		 * 封面图
		 */
		private ImageView coverImg;
		/**
		 * 内容
		 */
		private TextView titleTxt;

		public Group6ViewHolder(View itemView) {
			super(itemView);
			coverImg = (ImageView) itemView.findViewById(R.id.img);
			titleTxt = (TextView) itemView.findViewById(R.id.txt);

			coverImg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onContentClick(content);
				}
			});
		}

		public void setData(HomeContent content) {
			// 内容
			this.content = content;
			// 标题
			titleTxt.setText(content.getBannerName());
			// 封面
			Picasso.with(context).load(content.getPictureUrl()).into(coverImg);
		}
	}

	/**
	 * 主页内容监听
	 */
	public interface HomeContentListener {
		void onContentClick(HomeContent content);
	}
}
