package sampleDTO;

public class sample2DTO {
	private int i1;
	private String s1;
	private sample1DTO d1;
	/**
	 * @return the i1
	 */
	public int getI1() {
		return i1;
	}
	/**
	 * @param i1 the i1 to set
	 */
	public void setI1(int i1) {
		this.i1 = i1;
	}
	/**
	 * @return the s1
	 */
	public String getS1() {
		return s1;
	}
	/**
	 * @param s1 the s1 to set
	 */
	public void setS1(String s1) {
		this.s1 = s1;
	}
	/**
	 * @return the d1
	 */
	public sample1DTO getD1() {
		return d1;
	}
	/**
	 * @param d1 the d1 to set
	 */
	public void setD1(sample1DTO d1) {
		this.d1 = d1;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "sample2DTO [i1=" + i1 + ", s1=" + s1 + ", d1=" + d1 + "]";
	}
}
