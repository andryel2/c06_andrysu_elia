import fun.Predicate;
import rasterdata.RasterBufferedImage;
import rasterize.*;
import model.Point;
import rasterize.fill.SeedFill4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * trida pro kresleni na platno: vyuzita tridy RasterBufferedImage
 * 
 * @author PGRF FIM UHK
 * @version 2020
 */
// ukol nedostatky
public class CanvasRasterBufferedImage {   //jenom jednu aplikačni třidu, vyjimky , kresleni mimo rastr,
	// v interface rastru nejsou metody ktere jsem pouzil v samotne tride
	// 2 test přište(vyplnovani, i 2d do 3d teorie(bylo na prednaskach)), ukol2 9 týden, 3 test 12 týden

	private JPanel panel;
	private RasterBufferedImage raster;
	private int x1,y1;

	private Point p1,p2;
	private FilledLineRasterizer usecka;
	private DottedLineRasterizer useckaTeckovana;

	private PolygonRasterizer polygonRasterizer;

	private SeedFill4 seedFill4;
	private model.Polygon polygon; //pouze ukládat vrcholy, které jsou koncové pro jednotlivé úsečky


	public CanvasRasterBufferedImage(int width, int height) {
		JFrame frame = new JFrame();

		frame.setLayout(new BorderLayout());

		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		raster = new RasterBufferedImage(width, height);
		usecka = new FilledLineRasterizer(raster);
		useckaTeckovana = new DottedLineRasterizer(raster);
		polygonRasterizer = new PolygonRasterizer(usecka,useckaTeckovana);
		polygon = new model.Polygon();
		seedFill4 = new SeedFill4();


		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};
		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);


		panel.requestFocus();
		panel.requestFocusInWindow();

		initListeners(panel);

      /*.drawLine(raster, x1, y1,     //při tažení úsečka s mezerami
			panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_SHIFT:    //
						clear(0xaaaaaa);
						panel.addMouseMotionListener(new MouseAdapter() {
							@Override
							public void mouseMoved(MouseEvent e) {
								//zde se mně nepodařilo napsat podminky pro svislou, vodorovnou a úhlopříčnou úsečku
							}
						});
						panel.repaint();
						break;
					case KeyEvent.VK_C:
						clear(0xCD5C5C);
						polygon.clear();   //smaže se plátno i list bodů pro polygon
						panel.repaint();
						break;
				}
			}
		});

		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent me) {
				x1 = me.getX();
				y1 = me.getY();  //zadání prvního bodu pro úsečku
				panel.repaint();
			}


			@Override
			public void mouseReleased(MouseEvent e) {
							clear(0xaaaaaa);
				int x2 = e.getX();    // při uvolnění myši se úsečka překreslí plnou čárou
				int y2 = e.getY();
				usecka.drawLine(raster, x1, y1, x2, y2, 0xff0000);
				panel.repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {    //po kliknuti myší se vykresluje polygon
				//musí se zadat kliknutím 3 body, aby se začal vykreslovat
				clear(0xaaaaaa);
				Point p = new Point(e.getX(), e.getY());
				polygon.add(p);
				polygonRasterizer.rasterize(polygon);
				panel.repaint();
			}
		});

		panel.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {  //po tažení a následném uvolnění myši se vykresluje úsečka

				clear(0xaaaaaa);
			useckaTeckovana					e.getX(), e.getY(), 0xff0000);
				panel.repaint();
			}

		});*/

	}

	public void initListeners(JPanel panel) {

		panel.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (e.isShiftDown()) {
					//TODO
				} else if (SwingUtilities.isLeftMouseButton(e)) {
					x1 = e.getX();
					y1 = e.getY();
					panel.repaint();
				} else if (SwingUtilities.isMiddleMouseButton(e)) {
					polygon.clear();
					Point p1 = new Point(e.getX(),e.getY());
					polygon.add(p1);
					panel.repaint();
				} else if (SwingUtilities.isRightMouseButton(e)) {
					if(polygon.size() == 0){
						p1 = new Point(e.getX(),e.getY());
						polygon.add(p1);}
				}
			}

			/*public void mouseClicked(MouseEvent e) {
				if(e.isControlDown()){
					if (SwingUtilities.isLeftMouseButton(e)) {
						seedFill4.fill(e.getX(),e.getY(),0x66FF99,0xaaaaaa,raster);
						panel.repaint();
					} else if (SwingUtilities.isRightMouseButton(e)) {
						//TODO
					}
				}
			}*/

			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)){
					p2 = new Point(e.getX(),e.getY());
					polygon.add(p2);
					polygonRasterizer.rasterize(polygon);
					panel.repaint();//tady musím celé překreslit
				} else if(SwingUtilities.isMiddleMouseButton(e)){
					Point p3 = new Point(e.getX(),e.getY());
					polygon.add(p3);
					polygonRasterizer.obdelnik(polygon.get(0),p3,0xff0000);
					panel.repaint();
				} else if (SwingUtilities.isLeftMouseButton(e)) {
					clear(0xaaaaaa);
					int x2 = e.getX();
					int y2 = e.getY();
					usecka.drawLine(raster, x1, y1, x2, y2, 0xff0000);
					panel.repaint();
				}
			}
		});


		panel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (e.isShiftDown()) {
					//TODO
				} else if (SwingUtilities.isLeftMouseButton(e)) {
					clear(0xaaaaaa);
					usecka.drawLine(raster,x1,y1,e.getX(),e.getY(),0xff0000);
					panel.repaint();
				} else if (SwingUtilities.isRightMouseButton(e)) {
					clear(0xaaaaaa);

					if(polygon.size()<2){
						useckaTeckovana.drawLine(raster, p1.x, p1.y, e.getX(),e.getY(),0xff0000);
						panel.repaint();

					}
					if(polygon.size() >= 2){
						polygonRasterizer.rasterize(polygon);
						polygonRasterizer.teckovanaRasterize(polygon,e.getX(),e.getY(),raster,0xff0000);
						panel.repaint();

					}
					//panel.repaint();
				} else if (SwingUtilities.isMiddleMouseButton(e)) {
					clear(0xaaaaaa);
					Point p3 = new Point(e.getX(),e.getY());
					polygonRasterizer.obdelnik(polygon.get(0),p3,0xff0000);
					panel.repaint();
				}
			}
		});

		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// na klávesu C vymazat plátno
				if (e.getKeyCode() == KeyEvent.VK_C) {
					clear(0xaaaaaa);
					polygon.clear();
					panel.repaint();
				}
			}
		});

	}

	// man vše v kontruktoru, možná dat do samotné metody init, kterou potom zavolam v konstruktoru pro přehlednost


	public void clear(int color) {
		raster.setClearColor(color);
		raster.clear();
	}

	public void present(Graphics graphics) {
		raster.repaint(graphics);
	}

	public void start() {
		clear(0xaaaaaa);

		panel.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new CanvasRasterBufferedImage(800, 600).start());
	}

}