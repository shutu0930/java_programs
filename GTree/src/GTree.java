import java.util.ArrayList;
import java.util.LinkedList;

public class GTree {
	
	String data;
	ArrayList<GTree> children;
	
	public GTree(String data){
		this.data=data;
		children=new ArrayList<GTree>();
		
		
	}
	public GTree(String data, GTree trees[]){
		this.data=data;
		children=new ArrayList<GTree>();
		for(GTree t: trees) children.add(t);
		
		
	}
	@Override
	public String toString(){
		String res=data+"{";
		for(int i=0;i<children.size();i++){
			
			res+=children.get(i).toString();
			if(i<children.size()-1) res+=",";
		}
		res+="}";
		return res;
		
		
		
	}
	public int size(){
		int sum=1;
		
		for(GTree t:children){
			
			sum+=t.size();
		}
		
		return sum;
		
		
	}
	
	public int height(){
		int max=0;
		
		for(GTree t:children){
			
			max= Math.max(max, t.height()+1);
		}
		
		return max;
		
		
	}
	public void dft(){
		
		System.out.print(data+"  ");
		for(GTree t:children){
			
			t.dft();
		}
		
	}
	
	public void bft(){
		
		LinkedList<GTree> queue=new LinkedList<GTree>();
		queue.add(this);
		while(queue.isEmpty()==false){
			GTree t=queue.removeFirst();
			System.out.print(t.data+"  ");
			
			for(GTree c: t.children){
				
				queue.add(c);
			}
			
			
			
			
		}
		
//		for(GTree t: queue){
//			
//			System.out.print(t.data+"   ");
//		}
		//System.out.println(queue.toString());
		
	}
	
	public static void main(String[] args) {
		GTree c= new GTree("C");
		GTree tree=new GTree("A",new GTree[]{
				
				new GTree("B", new GTree[]{new GTree("C")}),
				new GTree("D"),
				new GTree("E", new GTree[]{new GTree("F"),new GTree("G")})
				
				
				
		});
		
		System.out.println(tree);
		System.out.println(tree.size());
		System.out.println(tree.height());
		
		tree.dft();
		System.out.println();
		tree.bft();

	}

}
