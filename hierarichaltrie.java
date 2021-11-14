import java.util.*;
class hierarichaltrie{
	
	public static void main(String []args){
		f1trieNode head = new f1trieNode();
		
		String arr1[] = {"00*","00*","10*","0*","0*","0*","*"};
		String arr2[] = {"110*","11*","1*","01*","10*","1*","00*"};
		String arr3[] = {"R1","R2","R3","R4","R5","R6","R7"};
		
		
		HashMap<String,ArrayList<pair>> hm = new HashMap<String,ArrayList<pair>>();
		
		classify(arr1,arr2,arr3,hm);
		
		createtrie(hm,head);
		System.out.println(search("001","110",head));
	}
	public static void classify(String arr1[], String arr2[], String arr3[], HashMap<String,ArrayList<pair>> hm){
		for(int i=0;i<arr1.length;i++){
			if(hm.containsKey(arr1[i])){
				hm.get(arr1[i]).add(new pair(arr2[i],arr3[i]));
			}
			else{
				ArrayList<pair> al = new ArrayList<pair>();
				al.add(new pair(arr2[i],arr3[i]));
				hm.put(arr1[i],al);
			}
		}
		
		for(String str: hm.keySet()){
			System.out.println(str);
			for(int i=0;i<hm.get(str).size();i++){
				System.out.print(hm.get(str).get(i).F2+" "+hm.get(str).get(i).rule+"		");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public static void createtrie(HashMap<String,ArrayList<pair>> hm, f1trieNode head){
		for(String str : hm.keySet()){
			f1trieNode lastnode = insertF1(str,head);
			f2trieNode head2 = new f2trieNode();
			lastnode.ptr = head2;
			for(int i=0;i<hm.get(str).size();i++){
				insertF2(hm.get(str).get(i),head2);
			}
		}
	}
	
	public static f1trieNode insertF1(String s, f1trieNode head){
		
		f1trieNode curr = head;
		for(int i=0;i<s.length()-1;i++){
			if(s.charAt(i)=='0'){
				if(curr.zero == null)
					curr.zero=new f1trieNode();
				curr = curr.zero;
			}
			else{
				if(curr.one == null)
					curr.one = new f1trieNode();
				curr = curr.one;
			}
		}
		curr.isthere=true;
		return curr;
	}
	
	public static void insertF2(pair p, f2trieNode head){
		String s = p.F2;
		String rule = p.rule;
		f2trieNode curr = head;
		for(int i=0;i<s.length()-1;i++){
			if(s.charAt(i)=='0'){
				if(curr.zero == null)
					curr.zero=new f2trieNode();
				curr = curr.zero;
			}
			else{
				if(curr.one == null)
					curr.one = new f2trieNode();
				curr = curr.one;
			}
		}
		curr.Rule = rule;
		curr.isthere =true;
	}
	
	public static boolean search(String s1, String s2, f1trieNode head){
		f1trieNode curr = head;
		Stack<f1trieNode> st = new Stack<f1trieNode>();
		if(curr.isthere==true)
				st.add(curr);
		for(int i=0; i<s1.length() && curr!=null ;i++){
			if((s1.charAt(i)=='0' && curr.zero==null) || (s1.charAt(i)=='1' && curr.one==null)){
				curr = null;
				break;
			}
			else{
				if(s1.charAt(i)=='0')
					curr = curr.zero;
				else
					curr = curr.one;
				if(curr.isthere==true)
					st.add(curr);
			}
		}
		//return st.size()>0;
		int size = st.size();
		System.out.println(size);
		while(size>0){
			f2trieNode currancestor = st.pop().ptr;
			if(search2(s2,currancestor)==true){
				return true;
			}
			size--;
		}
		return false;
	}
	
	public static boolean search2(String s2, f2trieNode head){
		f2trieNode curr = head;
		Stack<f2trieNode> st = new Stack<f2trieNode>();
		if(curr.isthere==true)
				st.add(curr);
		for(int i=0; i<s2.length() && curr!=null ;i++){
			if((s2.charAt(i)=='0' && curr.zero==null) || (s2.charAt(i)=='1' && curr.one==null)){
				curr = null;
				break;
			}
			else{
				if(s2.charAt(i)=='0')
					curr = curr.zero;
				else
					curr = curr.one;
				if(curr.isthere==true)
					st.add(curr);
			}
		}
		
		//System.out.println("size of second level trie is :"+st.size());
		if(st.size()>0){
			System.out.println(st.peek().Rule);
		}
		return st.size()>0;
	}
}