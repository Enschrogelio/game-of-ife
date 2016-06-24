package com.ashley.com.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.ashley.com.gameoflife.Cell.Health;

public class WorldView3 extends SurfaceView implements OnTouchListener {
	private World3 mWorld;
	private WorldListener mWorldListener = null;

	public interface WorldListener {
		public void onInitWorld();
		public void onNextGeneration(int day, int liveCells);
	}

	public WorldView3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setupView();
	}

	public WorldView3(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupView();
	}

	public WorldView3(Context context) {
		super(context);
		setupView();
	}

	private void setupView(){
		setWillNotDraw(false);
		
		setOnTouchListener(this);
		setWorld(3,3);
	}

	/**
	 * @param i
	 * @param j
	 */
	public void setWorld(int rows, int cols) {
		World3 world3 = new World3(rows, cols);
		setWorld(world3);
	}
	
	/**
	 * @param world
	 */
	public void setWorld(World3 world3) {
		mWorld = world3;
		invalidate();
		
		if(mWorldListener!=null) {
			mWorldListener.onInitWorld();
		}
	}
	
	/**
	 * @return
	 */
	public World3 getWorld() {
		return mWorld;
	}
	
	/* (non-Javadoc)
	 * @see android.view.SurfaceView#draw(android.graphics.Canvas)
	 */
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		final int numRows = mWorld.getNumRows();
		final int numCols = mWorld.getNumCols();
		
		final int cellWidth = getCellWidth();
		final int cellHeight =  getCellHeight();
		
		for(int row=0; row < numRows; row++) {
			for(int col=0; col < numCols; col++) {
				int left = col * cellWidth;
				int top = row * cellHeight;
				int right = left + cellWidth;
				int bottom = top + cellHeight;
				
				Cell cell = mWorld.getCell(row, col);
				
				Paint cellPaint = cell.isAlive() ? getLivePaint(): getDiedPaint();
				canvas.drawRect(left, top, right, bottom, cellPaint);
				canvas.drawRect(left, top, right, bottom, getBorderPaint());
			}
		}
	}



	/* (non-Javadoc)
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();
			
			Cell cell = getCellFromXY(x,y);
			
			cell.setHealth(cell.isAlive()?Health.DEAD:Health.ALIVE);
			
			invalidate();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Draw the next generation
	 */
	public void nextGeneration() {
		mWorld.nextGeneration();
		invalidate();
		
		if(mWorldListener!=null) {
			mWorldListener.onNextGeneration(mWorld.getNumGenerations(),mWorld.getNumLiveCells());
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	private Cell getCellFromXY(float x, float y) {
		int row = (int) (y / getCellHeight());
		int col = (int) (x / getCellWidth());
		
		return mWorld.getCell(row, col);
	}

	/**
	 * 
	 * @return
	 */
	private int getCellWidth() {
		return (int) Math.rint(getWidth() / mWorld.getNumCols());
	}
	
	/**
	 * 
	 * @return
	 */
	private int getCellHeight() {
		return (int) Math.rint(getHeight() / mWorld.getNumRows());
	}

    //COLOR DE LAS CELULAS
	private Paint getLivePaint() {
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor((Color.parseColor("#696969")));
		return paint;
	}
	
	private Paint getDiedPaint() {
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(0x66cccccc);
		return paint;
	}

   //COLOR LINEAS
	private Paint getBorderPaint() {
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.BLACK);
		return paint;
	}

	/**
	 * @param worldActivity
	 */
	public void setWorldListener(WorldListener worldListener) {
		this.mWorldListener  = worldListener;
	}

	
}
