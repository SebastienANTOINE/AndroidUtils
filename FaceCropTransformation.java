

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by sebastien.antoine on 10/04/2014.
 */
public class FaceCropTransformation implements Transformation {


        @Override
        public Bitmap transform(Bitmap source) {
            Bitmap output,cropBitmap;
            FaceCropper mFaceCropper = new FaceCropper(1);
            cropBitmap =  mFaceCropper.cropFace(source);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(cropBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            paint.setDither(true);

            int sideSizeMin = minSideSize(cropBitmap);

            output =  Bitmap.createBitmap(sideSizeMin,sideSizeMin, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(output);
            canvas.drawOval(new RectF(0,0,sideSizeMin,sideSizeMin),paint);


            return  output;
        }

        @Override
        public String key() {
            return "crop()";
        }


    private int minSideSize(Bitmap source){
       return source.getWidth()>source.getHeight()?source.getHeight():source.getWidth();
    }
}
