package highmark.qt.TestGenerator;

public class ClassWriter {
	private final String name;
	private String content = "";
	private final String packageName;
	private String runWith;
	private String imports = 
			"import static org.junit.Assert.*;\n"
			+ "import org.junit.Test;\n";
	public ClassWriter(String name, String packageName) {
		this.name = name;
		this.packageName = packageName;
	}
	
	public ClassWriter(String name, String packageName, String runWith) {
		this.name = name;
		this.packageName = packageName;
		this.runWith = "@RunWith(" + runWith + ")\n" ;
	}
	
	public void addMethod(MethodWriter method) {
		content += method.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return
				packageName
				+ imports
				+ runWith
				+ "public class " + name + " {"
				+ content
				+ "\n}";
	}
	
}
