public class KMP
{

	/**
	 * Gnereate the KMP Table
	 * @param P
	 * @return
	 */
	public static int[] genKMPTable(String P)
	{
		int m = P.length();
		int[] F = new int[m];
		F[0] = 0;

		int i = 1;
		int j = 0;
		while (i < m) {
			if (P.charAt(i) == P.charAt(j)) {
				F[i] = j + 1;
				i++;
				j++;
			}
			else if (j > 0) {
				j = F[j - 1];
			}
			else {
				F[i] = 0;
				i++;
			}
		}

		return F;
	}

	/**
	 * Find the pattern in the string
	 * @param T
	 * @param P
	 * @return
	 */
	public static int find(String T, final String P)
	{
		int n = T.length();
		int m = P.length();

		int[] F = genKMPTable(P);
		int i = 0;
		int j = 0;

		while (i < n) {
			if (T.charAt(i) == P.charAt(j)) {
				if (j == m - 1) {
					return i - j;
				}
				else {
					i++;
					j++;
				}
			}
			else {
				if (j > 0) {
					j = F[j - 1];
				}
				else {
					i++;
				}
			}
		}

		return -1;
	}


}
