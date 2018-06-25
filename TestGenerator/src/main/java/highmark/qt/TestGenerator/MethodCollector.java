package highmark.qt.TestGenerator;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodCollector extends VoidVisitorAdapter<List<String>> {
	List<MethodDeclaration> mdList = new ArrayList<MethodDeclaration>();
	List<String> collector;
	/* (non-Javadoc)
	 * @see com.github.javaparser.ast.visitor.VoidVisitorAdapter#visit(com.github.javaparser.ast.body.MethodDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(MethodDeclaration md, List<String> collector) {
		// TODO Auto-generated method stub
		super.visit(md, collector);
		mdList.add(md);
	}
	
	public List<MethodDeclaration> getMethodDeclarationList() {
		return mdList;
	}

}
