package highmark.qt.TestGenerator;

public class MethodWriter {
	String name;
	String content;
	public MethodWriter(String name, String orgClassName) {
		this.name = name;
		
	}
	
	public void addContent(String content) {
		this.content += content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return 
				"\t@Test"
				+ "\tpublic void " + name + "() {\n"
				+ content		
				+ "};"
				;
	}
	
	public static String writeToStringTest(String orgClassName) {
		String constanceName = orgClassName.substring(0, 1).toLowerCase() + orgClassName.substring(1, orgClassName.length()-1);
		return 
				"\t\t" + orgClassName + " " + constanceName + " = new " + orgClassName + "();\n"
				+ "\t\tassertEquals(" + constanceName + ".toString(), " + constanceName + ".toString());\n";
	}
}
