import java.util.*;
class setpruningtrie{
	public static void main(String []args){
		f1trieNode head = new f1trieNode();
		String arr1[] = {"00*","00*","10*","0*","0*","0*","*"};
		String arr2[] = {"110*","11*","1*","01*","10*","1*","00*"};
		String arr3[] = {"R1","R2","R3","R4","R5","R6","R7"};
		HashMap<String,HashMap<String,String>> hm = new HashMap<String,HashMap<String,String>>();
		
		classify(arr1,arr2,arr3,hm);
		
		createtrie(hm,head);
	}
	
	public static void classify(String arr1[], String arr2[], String arr3[], HashMap<String,HashMap<String,String>> hm){
		for(int i=0;i<arr1.length;i++){
			if(hm.containsKey(arr1[i])){
				hm.get(arr1[i]).put(arr2[i],arr3[i]);
			}
			else{
				HashMap<String,String> rulemap = new HashMap<String,String>();
				rulemap.put(arr2[i],arr3[i]);
				hm.put(arr1[i],rulemap);
			}
		}
		
		for(String str: hm.keySet()){
			System.out.println(str);
			for(String s: hm.get(str).keySet()){
				System.out.print(s+" "+hm.get(str).get(s)+"		");
			}
			System.out.println();
			System.out.println();
		}
	}
	
		
	public static void createtrie(HashMap<String,HashMap<String,String>> hm,f1trieNode head){
		for(String str : hm.keySet()){
			f1trieNode lastnode = insertF1(str,head);
			f2trieNode head2 = new f2trieNode();
			lastnode.ptr = head2;
			for(String s: hm.get(str).keySet()){
				insertF2(s,hm.get(str).get(s),head2);
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
	
	public static void insertF2(String s, String rule, f2trieNode head){
		
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
	public static void converttosetpruning(f1trieNode head){
		
	}
	
	
}