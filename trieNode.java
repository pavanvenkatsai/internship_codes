class f2trieNode{
	boolean isthere;
	String Rule;
	f2trieNode zero=null;
	f2trieNode one=null;
}
class f1trieNode{
	boolean isthere;
	f2trieNode ptr;
	f1trieNode zero=null;
	f1trieNode one=null;
}
class pair{
	String F2;
	String rule;
	pair(String F2, String rule){
		this.F2 = F2;
		this.rule = rule;
	}
}