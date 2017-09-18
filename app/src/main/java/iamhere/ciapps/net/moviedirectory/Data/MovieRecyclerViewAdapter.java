package iamhere.ciapps.net.moviedirectory.Data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import iamhere.ciapps.net.moviedirectory.Activities.MovieDetailActivity;
import iamhere.ciapps.net.moviedirectory.Model.Movie;
import iamhere.ciapps.net.moviedirectory.R;

/**
 * Created by Ofer Dan-On on 9/18/2017.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>   {

    private Context context;
    private List<Movie> movieList;
    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context=context;
        movieList=movies;
    }

    @Override
    //creating the row
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent, false);

        return new ViewHolder(view,context);
    }

    @Override
    //binding the view
    public void onBindViewHolder(MovieRecyclerViewAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        String posterLink = movie.getPoster();
        holder.title.setText(movie.getTitle());
        holder.type.setText(movie.getType());
        holder.year.setText(movie.getYear());
        Picasso.with(context).load(posterLink).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public ImageView poster;
        public TextView year;
        public TextView type;



        public ViewHolder(View itemView, Context ctx){
            super(itemView);
            context=ctx;

            title = (TextView)itemView.findViewById(R.id.movieTitleID);
            poster = (ImageView)itemView.findViewById(R.id.movieImageID);
            year = (TextView)itemView.findViewById(R.id.movieReleaseID);
            type= (TextView)itemView.findViewById(R.id.movieCatID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Movie m = movieList.get(getAdapterPosition());
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("movie", m);
                    context.startActivity(intent);
//                    Toast.makeText(context, "row tapped", Toast.LENGTH_LONG).show();
                }
            });
        }

        public void onClick(View v){

        }
    }
}
