package highmark.qt.TestGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

public class IOHelper {
	
	/**
	 * recurse to find all java files in dir and sub-dir
	 * @param searchPath
	 * @param pathList
	 * @return
	 */
	private static List<String> findJavaFilesHelper(Path searchPath, List<String> pathList, List <String> methodRequest) {
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(searchPath)) {
			for(Path path: stream) {
				if(path.toFile().isDirectory()) {
					findJavaFilesHelper(path, pathList, methodRequest);
				}
				else {
					String fileName = path.getFileName().toString();
					if(fileName.length() > 5 && fileName.substring(fileName.length() - 5).equals(".java")) {
						String filePath = path.toAbsolutePath().toString();
						if(methodExists(filePath, "toString")) {
							pathList.add(filePath);
						}
					}
				}
			}
			return pathList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * @param startPath
	 * @return list of java files in dir and sub-dir
	 */
	public static List<String> findJavaFiles(String startPath, List<String> methodRequest) {
		File dirCheck = new File(startPath);
		if(dirCheck.exists()) {
			Path searchPath = Paths.get(startPath);
			List<String> pathList = new ArrayList<String>();
			return findJavaFilesHelper(searchPath, pathList, methodRequest);
		}
		System.out.println("Path doesn't exist");
		return null;
	}
	
	/**
	 * make new dirs if they don't exist. create test class
	 * @param outPath
	 * @param packagePath
	 * @return
	 */
	public static void writeFile(String outPath, String packagePath, String orgClass, String testConcat, String content) {
		packagePath = packagePath.trim();
		packagePath = packagePath.substring(8, packagePath.length()-1).replace(".", "\\");
		String fullDirPath = outPath + "\\" + packagePath;
		String fullFilePath = fullDirPath + orgClass.trim() + testConcat + ".java";
		File fullFile = new File(fullFilePath);
		if(!fullFile.exists()) {
			File fullDir = new File(fullDirPath);
			if(!fullDir.exists()) fullDir.mkdirs();
			
		}
		
	}
	
	public static boolean testFilesNotExist(String outPath, String packagePath, String orgClass, String testConcat) {
		packagePath = packagePath.trim();
		packagePath = packagePath.substring(8, packagePath.length()-1).replace(".", "\\");
		String fullDirPath = outPath + "\\" + packagePath;
		String fullFilePath = fullDirPath + orgClass.trim() + testConcat + ".java";
		File fullFile = new File(fullFilePath);
		if(fullFile.exists())
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param filePath
	 * @param method
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean methodExists(String filePath, String method) throws FileNotFoundException {
		CompilationUnit cu = JavaParser.parse(new File(filePath));
        List<String> methodNames = new ArrayList<String>();
        MethodCollector methodCollector = new MethodCollector();
        methodCollector.visit(cu, methodNames);
        for(MethodDeclaration md: methodCollector.mdList) {
        	if(method.trim().equals(md.getNameAsString().trim()))
        		return true;
        }
        return false;
	}
	
	public static boolean MethodSetExists(String filePath, List<String> methods) throws FileNotFoundException {
		for(String method: methods) {
			if(methodExists(filePath, method))
				return true;
		}
		return false;
	}
}
