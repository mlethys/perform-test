package mkabala.pl.perform_test.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.List;

import de.greenrobot.event.EventBus;
import mkabala.pl.perform_test.R;
import mkabala.pl.perform_test.event.ShowArticleEvent;
import mkabala.pl.perform_test.model.News;

/**
 * Created by mkabala on 12.09.2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private List<News> newsList;

    public NewsListAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final News news = newsList.get(position);
        createThumbnail(holder.thumbnail, news);
        holder.lead.setText(news.getTitle());
        holder.pubDate.setText(news.getPubDate());
        holder.description.setText(news.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ShowArticleEvent(news.getLink()));
            }
        });
    }

    private void createThumbnail(SimpleDraweeView view, News news) {
        Uri imageUri = Uri.parse(news.getEnclosure().getUrl());
        ImageRequest request = ImageRequest.fromUri(imageUri);
        DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request).setOldController(view.getController()).build();
        view.setController(controller);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView thumbnail;
        private TextView lead;
        private TextView pubDate;
        private TextView description;

        public NewsViewHolder(View itemView) {
            super(itemView);
            thumbnail = (SimpleDraweeView) itemView.findViewById(R.id.thumbnail);
            lead = (TextView) itemView.findViewById(R.id.title);
            pubDate = (TextView) itemView.findViewById(R.id.date);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
