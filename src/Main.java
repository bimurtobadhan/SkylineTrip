import java.awt.*;
import java.util.*;
import java.util.List;

import de.plusgis.index.rtree.HyperBoundingBox;
import de.plusgis.index.rtree.HyperPoint;
import de.plusgis.index.rtree.RTree;
import de.plusgis.index.rtree.RTreeException;


public class Main {

    String filename = null;
    RTree tree = null;
    public Main(){
        filename = "rtreefile14_3d";
        try {
            tree = new RTree(filename);
        } catch (RTreeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //skylineExecute(1,1);
        //generateTreeFile();
        //readAll();
        //skylineExecute(1,-1);
        //skyline();
        skylineExecute3d(1,1);

    }

    public void generateTreeFile(){
        generateTreeFileHelper(1,1);    //1st quadrant
        generateTreeFileHelper(-1,1);   //2nd quadrant
        generateTreeFileHelper(-1,-1);  //3rd quadrant
        generateTreeFileHelper(1,-1);   //4th quadrant
    }

    private void generateTreeFileHelper(int a,int b){
        Random random = new Random();
		for(int i=0;i<100;i++){
			double x = random.nextDouble()*100*a;
			double y = random.nextDouble()*100*b;
            double z = random.nextDouble()*10;
			HyperPoint min = new HyperPoint(new double[]{x,y,z});
			HyperPoint max = new HyperPoint(new double[]{x,y,z});
			HyperBoundingBox rect = new HyperBoundingBox(min, max);
			System.out.println(rect);
			try {
				tree.insert(new Integer(i), rect);
			} catch (RTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

    public void readAll(){
        try {
            HyperPoint a = new HyperPoint(new double[]{-100,-100,-1});
            HyperPoint b = new HyperPoint(new double[]{100,100,10});
            HyperBoundingBox box = new HyperBoundingBox(a, b);
            System.out.println(box);
            Object[] v = tree.retrieve(box);
//			for(int i=0;i<v.length;i++){
//				System.out.println(v);
//			}
		} catch (RTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void skyline(){
        skylineExecute(1,1);
        skylineExecute(-1,1);
        skylineExecute(-1,-1);
        skylineExecute(1,-1);
    }

    public void skylineExecute(int a, int b){
        double MAX_COORDINATE_VALUE_X = 100*a;
        double MAX_COORDINATE_VALUE_Y = 100*b;
        HyperPoint origin = new HyperPoint(new double[]{0, 0}) ;
        HyperPoint limit = new HyperPoint(new double[]{MAX_COORDINATE_VALUE_X, MAX_COORDINATE_VALUE_Y}) ;
        HyperBoundingBox searchRegion = new HyperBoundingBox(origin, limit, false);
        List<HyperBoundingBox> skylineEntries = new ArrayList<>();
        Stack<HyperPoint> todoList = new Stack<>();

        List<HyperBoundingBox> list = new ArrayList<>();

        try {
            HyperBoundingBox box1 = tree.boundedNNSearch(origin,searchRegion);
            list.add(box1);
        } catch (RTreeException e) {
            e.printStackTrace();
        }



        HyperBoundingBox firstNN = list.get(0);
        skylineEntries.add(firstNN);
        System.out.println(firstNN);
        todoList.push(new HyperPoint(new double[]{firstNN.getPMin().getCoord(0) - .0001*a, MAX_COORDINATE_VALUE_Y}));
        todoList.push(new HyperPoint(new double[]{MAX_COORDINATE_VALUE_X, firstNN.getPMin().getCoord(1) - .0001*b}));

        while (!todoList.empty()) {
            HyperPoint p = todoList.pop();
            HyperBoundingBox box = new HyperBoundingBox(origin, p, false);
            List<HyperBoundingBox> nnl = new ArrayList<>();
            try {
                HyperBoundingBox temp = tree.boundedNNSearch(origin, box);
                if(temp!=null)
                    nnl.add(temp);
            } catch (RTreeException e) {
                e.printStackTrace();
            }
            if (!nnl.isEmpty()) {
                HyperBoundingBox nn = nnl.remove(0);
                skylineEntries.add(nn);
                System.out.println(nn);
                todoList.push(new HyperPoint(new double[]{nn.getPMin().getCoord(0) - .001*a, p.getCoord(1)}));
                todoList.push(new HyperPoint(new double[]{p.getCoord(0), nn.getPMin().getCoord(1) -.001*b}));
            }
        }

        skylineEntries.sort(new Comparator<HyperBoundingBox>() {
            @Override
            public int compare(HyperBoundingBox o1, HyperBoundingBox o2) {
                if(o1.getPMin().getCoord(0) < o2.getPMin().getCoord(0))
                    return -1;
                else {
                    if(o1.getPMin().getCoord(0) > o2.getPMin().getCoord(0))
                        return 1;
                    else
                        return 0;
                }
            }
        });

        System.out.println("SkylineEntries: ");
        for(int i=0;i<skylineEntries.size();i++){
            System.out.println(skylineEntries.get(i));
        }
    }

    public void skylineExecute3d(int a, int b){
        double MAX_COORDINATE_VALUE_X = 100*a;
        double MAX_COORDINATE_VALUE_Y = 100*b;
        double MAX_COORDINATE_VALUE_Z = 10;
        HyperPoint origin = new HyperPoint(new double[]{0, 0, 0}) ;
        HyperPoint limit = new HyperPoint(new double[]{MAX_COORDINATE_VALUE_X, MAX_COORDINATE_VALUE_Y, MAX_COORDINATE_VALUE_Z}) ;
        HyperBoundingBox searchRegion = new HyperBoundingBox(origin, limit, false);
        List<HyperBoundingBox> skylineEntries = new ArrayList<>();
        Stack<HyperPoint> todoList = new Stack<>();

        List<HyperBoundingBox> list = new ArrayList<>();

        try {
            HyperBoundingBox box1 = tree.boundedNNSearch(origin,searchRegion);
            list.add(box1);
        } catch (RTreeException e) {
            e.printStackTrace();
        }



        HyperBoundingBox firstNN = list.get(0);
        skylineEntries.add(firstNN);
        System.out.println(firstNN);
        todoList.push(new HyperPoint(new double[]{firstNN.getPMin().getCoord(0) - .0001*a, MAX_COORDINATE_VALUE_Y, MAX_COORDINATE_VALUE_Z}));
        todoList.push(new HyperPoint(new double[]{MAX_COORDINATE_VALUE_X, firstNN.getPMin().getCoord(1) - .0001*b, MAX_COORDINATE_VALUE_Z}));
        todoList.push(new HyperPoint(new double[]{MAX_COORDINATE_VALUE_X, MAX_COORDINATE_VALUE_Y , firstNN.getPMin().getCoord(2) - .0001}));

        while (!todoList.empty()) {
            HyperPoint p = todoList.pop();
            HyperBoundingBox box = new HyperBoundingBox(origin, p, false);
            List<HyperBoundingBox> nnl = new ArrayList<>();
            try {
                HyperBoundingBox temp = tree.boundedNNSearch(origin, box);
                if(temp!=null)
                    nnl.add(temp);
            } catch (RTreeException e) {
                e.printStackTrace();
            }
            if (!nnl.isEmpty()) {
                HyperBoundingBox nn = nnl.remove(0);
                skylineEntries.add(nn);
                System.out.println(nn);
                todoList.push(new HyperPoint(new double[]{nn.getPMin().getCoord(0) - .001*a, p.getCoord(1), p.getCoord(2)}));
                todoList.push(new HyperPoint(new double[]{p.getCoord(0), nn.getPMin().getCoord(1) -.001*b, p.getCoord(2)}));
                todoList.push(new HyperPoint(new double[]{p.getCoord(0), p.getCoord(1) , nn.getPMin().getCoord(2) -.001}));
            }
        }

        skylineEntries.sort(new Comparator<HyperBoundingBox>() {
            @Override
            public int compare(HyperBoundingBox o1, HyperBoundingBox o2) {
                if(o1.getPMin().getCoord(0) < o2.getPMin().getCoord(0))
                    return -1;
                else {
                    if(o1.getPMin().getCoord(0) > o2.getPMin().getCoord(0))
                        return 1;
                    else
                        return 0;
                }
            }
        });

        System.out.println("SkylineEntries: ");
        for(int i=0;i<skylineEntries.size();i++){
            System.out.println(skylineEntries.get(i));
        }
    }




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Main m = new Main();
	}

}
