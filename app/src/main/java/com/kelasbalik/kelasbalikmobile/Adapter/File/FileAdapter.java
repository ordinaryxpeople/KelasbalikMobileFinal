package com.kelasbalik.kelasbalikmobile.Adapter.File;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kelasbalik.kelasbalikmobile.Model.File.FileData;
import com.kelasbalik.kelasbalikmobile.PdfviewActivity;
import com.kelasbalik.kelasbalikmobile.R;
import com.kelasbalik.kelasbalikmobile.VideoviewActivity;

import java.io.InputStream;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.MyViewHolder>{
    List<FileData> fileDataList;
    private Context context;

    public FileAdapter(List<FileData> fileDataList){
        this.fileDataList = fileDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_file, parent, false);
        FileAdapter.MyViewHolder mViewHolder = new FileAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (fileDataList.get(position).getTipefile().equals("0")){
            new DownloadImageFromInternet(holder.vivURLFile).execute("https://admin.kelasbalik.id/assets/media/ebook/"+fileDataList.get(position).getUrlgambar());
            String pdfpath="https://admin.kelasbalik.id/assets/media/ebook/"+fileDataList.get(position).getUrlfile();

            holder.tvJudulFile.setText(fileDataList.get(position).getJudul());
            holder.tvDeskripsiFile.setText(fileDataList.get(position).getDeskripsi());
            holder.tvURLPengunggahFile.setText(fileDataList.get(position).getPengunggah());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PdfviewActivity.class);
                    intent.setType("text/plain");
                    intent.putExtra("url_pdf",pdfpath);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }else if (fileDataList.get(position).getTipefile().equals("1")){
            new DownloadImageFromInternet(holder.vivURLFile).execute("https://admin.kelasbalik.id/assets/media/emodul/"+fileDataList.get(position).getUrlgambar());
            String pdfpath="https://admin.kelasbalik.id/assets/media/emodul/"+fileDataList.get(position).getUrlfile();

            holder.tvJudulFile.setText(fileDataList.get(position).getJudul());
            holder.tvDeskripsiFile.setText(fileDataList.get(position).getDeskripsi());
            holder.tvURLPengunggahFile.setText(fileDataList.get(position).getPengunggah());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PdfviewActivity.class);
                    intent.setType("text/plain");
                    intent.putExtra("url_pdf",pdfpath);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }else if (fileDataList.get(position).getTipefile().equals("2")) {
            new DownloadImageFromInternet(holder.vivURLFile).execute("https://admin.kelasbalik.id/assets/media/video/"+fileDataList.get(position).getUrlgambar());
            String pdfpath="https://admin.kelasbalik.id/assets/media/video/"+fileDataList.get(position).getUrlfile();

            holder.tvJudulFile.setText(fileDataList.get(position).getJudul());
            holder.tvDeskripsiFile.setText(fileDataList.get(position).getDeskripsi());
            holder.tvURLPengunggahFile.setText(fileDataList.get(position).getPengunggah());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, VideoviewActivity.class);
                    intent.setType("text/plain");
                    intent.putExtra("url_pdf",pdfpath);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return fileDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView vivURLFile;
        public TextView tvJudulFile, tvDeskripsiFile, tvURLPengunggahFile;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vivURLFile = (ImageView) itemView.findViewById(R.id.ivURLFile);
            tvURLPengunggahFile = (TextView) itemView.findViewById(R.id.tvURLPengunggahFile);
            tvJudulFile = (TextView) itemView.findViewById(R.id.tvURLJudulFile);
            tvDeskripsiFile = (TextView) itemView.findViewById(R.id.tvURLDeskripsiFile);
            cardView = (CardView) itemView.findViewById(R.id.cvFileContainer);
            context = itemView.getContext();
        }
    }
}

class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    public DownloadImageFromInternet(ImageView imageView) {
        this.imageView=imageView;
    }
    protected Bitmap doInBackground(String... urls) {
        String imageURL=urls[0];
        Bitmap bimage=null;
        try {
            InputStream in=new java.net.URL(imageURL).openStream();
            bimage= BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
