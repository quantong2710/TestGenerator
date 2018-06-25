package highmark.qt.TestGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.AnnotationExprMetaModel;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;

/**
 * Hello world!
 *
 */
public class TestGenerator 
{
	private static String samplePath = "E:\\Coding Repository\\EclipseRepos\\TestGenerator\\src\\main\\java\\sampleDTO";
    public static void main( String[] args ) throws FileNotFoundException
    {
    	List<String> methodRequest = new ArrayList<String>();
    	methodRequest.add("toString");
    	List<String> pathList = IOHelper.findJavaFiles(samplePath, methodRequest);
    	if(pathList!= null) {
        	for(String path: pathList) {
                CompilationUnit cu = JavaParser.parse(new File(path));
                List<String> methodNames = new ArrayList<String>();
                MethodCollector methodCollector = new MethodCollector();
                methodCollector.visit(cu, methodNames);
                String samplePath = "E:\\Coding Repository\\EclipseRepos\\TestGenerator\\test\\main\\java";
                String packagePath = cu.getPackageDeclaration().get().toString();
                String orgClassName = cu.getPrimaryTypeName().get().toString();
                String testConcat = "TestToString";
                methodCollector.getMethodDeclarationList().forEach(md -> System.out.println(md.getNameAsString()));
                IOHelper.writeFile(samplePath, packagePath, orgClassName, "", "");
        	}
    	}
    	else {
    		System.out.println("pathList = null; no java file found");
    	}
    	
//    	ClassOrInterfaceDeclaration myClass = new ClassOrInterfaceDeclaration();
//    	myClass.setName("Test");
//    	myClass.setPublic(true);
//    	myClass.addMethod("testToString", Modifier.PUBLIC);
//    	myClass.addAnnotation("Test");
//    	System.out.println(myClass);

    }

}
