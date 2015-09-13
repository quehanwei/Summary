package mesh.csrmesh.view;


import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class MeshTextView extends View{
    // Context
    Context mContext;
    // paint objects
    private Paint mExternalCirclePaint = new Paint();
    private Paint mTextPaint = new Paint();
    private Paint mInternalCirclePaint = new Paint();
    // measure objects
    private int mCanvasWidth = 0;
    private int mCanvasHeight = 0;
    private int mCanvasMin = 0;
    private static final double INTERNAL_CIRCLE_PADDING_PERCENT = 0.04;

    static final int TEXT_COLOR = 0xff000000;
    float mDesiredTextSize =0;

    Rect mTextRect;

    public MeshTextView(Context context) {
        super(context);
    }

    public MeshTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
        // Set context
        mContext = context;
        // Set style for the external circle
        mExternalCirclePaint.setDither(true);
        // Set style for the texts
        mTextPaint.setStrokeWidth(0);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setShadowLayer(10.0f, 0.0f, 2.0f, 0xFFFFFFFF);
        mTextPaint.setColor(TEXT_COLOR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvasWidth = canvas.getWidth();
        mCanvasHeight = canvas.getHeight();
        mCanvasMin = Math.min(mCanvasWidth, mCanvasHeight);
        mTextRect = getTextRect();

        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));

        // draw external circle after applying gradient colour.
        Shader shaderExt = new LinearGradient(mCanvasWidth/2,0, mCanvasWidth/2, mCanvasHeight,new int[]{0xFFFA4AB8,0xFF7357C4,0xFF0464CD},new float[]{0f,0.5f,1f}, Shader.TileMode.CLAMP);
        mExternalCirclePaint.setShader(shaderExt);
        canvas.drawCircle(mCanvasWidth / 2, mCanvasHeight / 2, mCanvasMin /2, mExternalCirclePaint);

        // draw internal circle after applying gradient colour.
        Shader shader = new LinearGradient(mCanvasWidth/2,0, mCanvasWidth/2, mCanvasHeight,0xFFFFFFFF,0xFFFFFFFF, Shader.TileMode.CLAMP);
        mInternalCirclePaint.setShader(shader);
        canvas.drawCircle(mCanvasWidth / 2, mCanvasHeight / 2, (float) ((1.0 - INTERNAL_CIRCLE_PADDING_PERCENT) * mCanvasMin / 2), mInternalCirclePaint);
        // draw the texts
        drawTextandLabel("MESH", canvas);
    }

    private Rect getTextRect() {
        return new Rect(0,0,mCanvasMin,mCanvasMin);
    }

    private void drawTextandLabel(String text, Canvas canvas) {
        mDesiredTextSize = setTextSizeForWidth(mTextPaint, mTextRect.width()/2,text);
        int xPos = (mCanvasWidth / 2);
        int yPos = (int) ((mCanvasHeight / 2) - ((mTextPaint.descent() + mTextPaint.ascent()) / 2)) ;
        canvas.drawText(text, xPos, yPos, mTextPaint);
    }
    private static float setTextSizeForWidth(Paint paint, float desiredWidth,String text) {
        final float testTextSize = 40f;
        paint.setTextSize(testTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        float desiredTextSize = testTextSize * desiredWidth / bounds.width();
        paint.setTextSize(desiredTextSize);
        return desiredTextSize;
    }

   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (getLayoutParams().height == ActionBar.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(width, width);
        }
        else {
            setMeasuredDimension(width, height);
        }
    }*/
}
