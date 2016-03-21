package kate0214.lab_4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class  MainActivity extends AppCompatActivity {

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.i("opencv", "opencv initialization failed");
        } else {
            Log.i("opencv", "opencv initialization successful");
        }
    }

    EditText editText;
    ImageView imageView2;
    TextView textView;
    final String TAG = "MyLogs";
    Button getPixels;
    ImageView imageView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        imageView=(ImageView) findViewById(R.id.imageView);
      //  textView=(TextView) findViewById(R.id.textView);
        getPixels = (Button) findViewById(R.id.buttonStartBInterpolation);
        Context context = getApplicationContext();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.logo);
        imageView.setImageBitmap(bitmap);
        imageView2=(ImageView) findViewById(R.id.imageView2);
        editText=(EditText) findViewById(R.id.editText);

        // getPixels.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Bitmap convertColorIntoBlackAndWhiteImage(Bitmap orginalBitmap) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);

        Bitmap blackAndWhiteBitmap = orginalBitmap.copy(
                Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setColorFilter(colorMatrixFilter);

        Canvas canvas = new Canvas(blackAndWhiteBitmap);
        canvas.drawBitmap(blackAndWhiteBitmap, 0, 0, paint);

        return blackAndWhiteBitmap;
    }


    public void startBInterpolation(View v) {
        String s=editText.getText().toString();
        double countSize=Double.parseDouble(s);
        Context context = getApplicationContext();
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);
        int ii = bitmap2.getHeight();
        int jj = bitmap2.getWidth();
        Mat matrix = new Mat(bitmap2.getHeight(), bitmap2.getWidth(), CvType.CV_8UC1);
        Mat m=new Mat((int)(countSize*bitmap2.getHeight()), (int)(countSize*bitmap2.getWidth()), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap2, matrix);
        double current[];
        int k=0;
        int mas[]=new int[ii*jj];
        double temp[]=new double[4];
        int size= (int) ( ii*countSize*jj*countSize);
        int result[]=new int[size];
        for (int i=0;i<ii;i++)
        {
            for(int j=0;j<jj;j++)
            {
                temp=matrix.get(i,j);
                mas[k]=(int) temp[0];
                k++;
            }
        }
        result= BInterpolation(mas, jj, ii, (int) (jj * countSize), (int) (ii * countSize));
        int kk=0;
        for (int i=0;i<(int)(countSize*ii);i++)
        {
            for(int j=0;j<(int)(countSize*jj);j++)
            {
                temp[0]=result[kk];
                temp[1]=result[kk];
                temp[2]=result[kk];
                temp[3]=result[kk];
                m.put(i,j,temp);
                kk++;
            }
        }
        Bitmap bitmap3=Bitmap.createBitmap((int)(countSize*jj),(int) (countSize*ii), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(m,bitmap3);
        imageView2.setImageBitmap(bitmap3);
        //textView.setText("You made it");

    }



    public int[] BInterpolation(int[] pixels, int w, int h, int w2, int h2) {
        int A, B, C, D, x, y, poz, NewPixelColor ;
        float xNew, yNew;
        int count = 0 ;
        int[] finalMas = new int[w2*h2] ;
        int counter=0;
        float xRatio = ((float)(w-1))/w2 ;
        float yRatio = ((float)(h-1))/h2 ;

        for (int i=0;i<h2;i++) {
            for (int j=0;j<w2;j++) {
                x = (int)(xRatio * j) ;
                y = (int)(yRatio * i) ;
                poz = y*w+x ;
                A = pixels[poz] & 0xff ;
                B = pixels[poz+1] & 0xff ;
                C = pixels[poz+w] & 0xff ;
                D = pixels[poz+w+1] & 0xff ;

                xNew = (xRatio * j) - x ;
                yNew = (yRatio * i) - y ;
                // Y = A(1-w)(1-h) + B(w)(1-h) + C(h)(1-w) + Dwh
                NewPixelColor = (int)(A*(1-xNew)*(1-yNew) +  B*(xNew)*(1-yNew) + C*(yNew)*(1-xNew)   +  D*(xNew*yNew)) ;
                counter++;
                finalMas[count++] = NewPixelColor ;

            }
        }
        return finalMas ;
    }

  }
