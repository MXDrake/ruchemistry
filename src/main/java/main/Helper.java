package main;

public class Helper {

	public static int getBeginPage(int totalPageCount, int current ){
		if (totalPageCount > 5) {
			 return Math.max(1, current - 5);

		} else {
			return Math.max(1, current - totalPageCount);
		}
	}

	public static int getEndPage(int totalPageCount, int begin){
		if (totalPageCount<begin) {
			return begin;
		}
		if (totalPageCount > 5) {

			return Math.min(begin + 5, totalPageCount);
		} else {
			return Math.min(begin + totalPageCount, totalPageCount);
		}
	}

}
