import java.util.LinkedList;

public class Intersections {
    private LinkedList<Intersection> intersections;

    public Intersections() {
        this.intersections = new LinkedList<Intersection>();
    }
    
    public Intersections(Intersection... intersections) {
        this.intersections = new LinkedList();
        for(int i=0; i<intersections.length; i++) {
            this.intersections.add(i, intersections[i]);
        }
    }

    public Intersection hit() {
        LinkedList<Intersection> positives = new LinkedList();
        for(int i=0; i<this.intersections.size(); i++) {
            if (this.intersections.get(i).gett() > 0) {positives.push(this.intersections.get(i));}
            }
        if(positives.size() == 0) {return new Intersection(-1, new Object());}
        Intersection comp = positives.get(0);
        //System.out.println(comp);
        for(Intersection x : positives) {
            if(x.gett() < comp.gett()) {comp = x;}
        }
        return comp;
    }

    public LinkedList<Intersection> getIntersections() {
        return this.intersections;
    }

    public void addOne(Intersection intersect) {
        this.intersections.add(this.intersections.size(), intersect);;
    }

    public void sortByT() {
        for(int i=0; i<this.intersections.size()-1; i++) {
            int index = i;
            for(int j=i+1; j<this.intersections.size(); j++) {
                if(this.intersections.get(j).gett() < this.intersections.get(index).gett()) {index = j;}
            }
            Intersection min = intersections.get(index);
            this.intersections.remove(index);
            this.intersections.add(index, intersections.get(i));
            this.intersections.remove(i);
            this.intersections.add(i, min);
        }
        return;
    }

    public String toString() {
        String s = ("--------------------------------------------");
        s+= ("\n-Intersections-\n");
        for(int i=0; i<intersections.size(); i++) {
            s+=("\n" + intersections.get(i).toString());
        }
        s+= ("\n--------------------------------------------");
        return s;
    }
}
